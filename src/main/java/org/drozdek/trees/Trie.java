package org.drozdek.trees;

import org.drozdek.commons.LoggerService;
import org.drozdek.trees.interfaces.TreeInterface;
import org.drozdek.trees.nodes.TrieLeaf;
import org.drozdek.trees.nodes.TrieNode;
import org.drozdek.trees.nodes.TrieNonLeaf;

/// Trie (prefix tree) data structure. Supports insertion and lookup of strings with shared prefixes,
/// using a hybrid non-leaf/leaf node structure.
///
/// Complexity Analysis:
/// Time Complexity: O(m) where m is key length
/// Auxiliary Space: O(n * m) where n is number of keys and m is average key length
///
/// Source: [Geeks for Geeks](https://www.geeksforgeeks.org/trie-data-structure/)
public class Trie implements TreeInterface {
    public static final int NOT_FOUND = -1;
    private TrieNonLeaf root;

    public Trie() {
        root = null;
    }

    public Trie(String word) {
        // init on root
        root = new TrieNonLeaf(word.charAt(0));
        // to avoid posterior tries
        createLeaf(word.charAt(0), word.substring(1), root);
    }

    private void addCell(char ch, TrieNonLeaf p, int stop) {
        int i;
        int len = p.getLetters().length();
        char[] s = new char[len + 1];
        TrieNode[] tmp = p.getPtr();
        p.setPtr(new TrieNode[len + 1]);

        for (i = 0; i < len + 1; i++)
            p.getPtr()[i] = null;

        if (stop < len) {
            for (i = len; i >= stop + 1; i--) {
                p.getPtr()[i] = tmp[i - 1];
                s[i] = p.getLetters().charAt(i - 1);
            }
        }

        s[stop] = ch;

        for (i = stop - 1; i >= 0; i--) {
            p.getPtr()[i] = tmp[i];
            s[i] = p.getLetters().charAt(i);
        }

        p.setLetters(new String(s));
    }

    private void createLeaf(char ch, String suffix, TrieNonLeaf p) {
        int pos = position(p, ch);
        TrieLeaf lf = null;

        if (suffix != null && suffix.length() > 0) {
            lf = new TrieLeaf(suffix);
        }

        if (pos == NOT_FOUND) {
            for (pos = 0; pos < p.getLetters().length() && p.getLetters().charAt(pos) < ch; pos++)
                ;
            addCell(ch, p, pos);
        }

        p.getPtr()[pos] = lf;
    }

    public boolean found(String word) {
        TrieNode p = root;
        int pos;
        int i = 0;

        while (true) {
            if (p == null)
                return false;

            if (p.isLeaf()) {
                assert p instanceof TrieLeaf;
                TrieLeaf lf = (TrieLeaf) p;
                return word.substring(i).equals(lf.getSuffix());
            }

            if (i >= word.length()) {
                return ((TrieNonLeaf) p).isEndOfWord();
            }

            pos = position((TrieNonLeaf) p, word.charAt(i));

            if (pos != NOT_FOUND && i + 1 == word.length()) {
                if (((TrieNonLeaf) p).getPtr()[pos] == null)
                    return true;
                else
                    return !((TrieNonLeaf) p).getPtr()[pos].isLeaf() &&
                            ((TrieNonLeaf) ((TrieNonLeaf) p).getPtr()[pos]).isEndOfWord();
            } else if (pos != NOT_FOUND && ((TrieNonLeaf) p).getPtr()[pos] != null) {
                p = ((TrieNonLeaf) p).getPtr()[pos];
                i++;
            } else
                return false;
        }

    }

    public void insert(String word) {
        if (root == null) {
            root = new TrieNonLeaf(word.charAt(0));
            createLeaf(word.charAt(0), word.substring(1), root);
            return;
        }

        TrieNonLeaf current = root;
        int i = 0;

        while (true) {
            if (i == word.length()) {
                current.setEndOfWord(true);
                return;
            }

            int pos = position(current, word.charAt(i));

            if (pos != NOT_FOUND && current.getPtr()[pos] == null) {
                if (i + 1 == word.length()) {
                    current.setEndOfWord(true);
                    return;
                }

                current.getPtr()[pos] = new TrieNonLeaf(word.charAt(i + 1));
                ((TrieNonLeaf) current.getPtr()[pos]).setEndOfWord(true);

                String s = (word.length() > i + 2) ? word.substring(i + 2) : null;
                createLeaf(word.charAt(i + 1), s, (TrieNonLeaf) current.getPtr()[pos]);
                return;
            } else if (pos != NOT_FOUND && current.getPtr()[pos].isLeaf()) {
                TrieLeaf lf = (TrieLeaf) current.getPtr()[pos];

                if (lf.getSuffix().equals(word.substring(i + 1))) {
                    LoggerService.logError("Duplicate entry (#3): " + word);
                    return;
                }

                int offset = 0;

                do {
                    pos = position(current, word.charAt(i + offset));

                    if (word.length() == i + offset + 1) {
                        current.getPtr()[pos] = new TrieNonLeaf(lf.getSuffix().charAt(offset));
                        current = (TrieNonLeaf) current.getPtr()[pos];
                        current.setEndOfWord(true);
                        createLeaf(lf.getSuffix().charAt(offset), lf.getSuffix().substring(offset + 1), current);
                        return;
                    } else if (lf.getSuffix().length() == offset) {
                        current.getPtr()[pos] = new TrieNonLeaf(word.charAt(i + offset + 1));
                        current = (TrieNonLeaf) current.getPtr()[pos];
                        current.setEndOfWord(true);
                        createLeaf(word.charAt(i + offset + 1), word.substring(i + offset + 2), current);
                        return;
                    }

                    current.getPtr()[pos] = new TrieNonLeaf(word.charAt(i + offset + 1));
                    current = (TrieNonLeaf) current.getPtr()[pos];
                    offset++;
                } while (word.charAt(i + offset) == lf.getSuffix().charAt(offset - 1));

                offset--;
                String s = null;

                if (word.length() > i + offset + 2)
                    s = word.substring(i + offset + 2);

                createLeaf(word.charAt(i + offset + 1), s, current);

                if (lf.getSuffix().length() > offset + 1)
                    s = lf.getSuffix().substring(offset + 1);
                else
                    s = null;
                createLeaf(lf.getSuffix().charAt(offset), s, current);
                return;
            } else if (pos != NOT_FOUND) {
                current = (TrieNonLeaf) current.getPtr()[pos];
                i++;
            } else {
                addCell(word.charAt(i), current, 0);
                pos = position(current, word.charAt(i));
                if (i + 1 == word.length()) {
                    current.setEndOfWord(true);
                    return;
                }
                String suffix = word.substring(i + 1);
                current.getPtr()[pos] = new TrieLeaf(suffix);
                return;
            }
        }
    }

    protected String oldPrintTrie(int depth, TrieNode p, String prefix) {

        StringBuilder trie = new StringBuilder();

        if (p.isLeaf()) {
            trie.append("\t".repeat(Math.max(0, depth)));
            trie.append(" >")
                    .append(prefix)
                    .append("|")
                    .append(((TrieLeaf) p).getSuffix());
        } else {
            for (int i = ((TrieNonLeaf) p).getLetters().length() - 1; i >= 0; i--) {
                if (((TrieNonLeaf) p).getPtr()[i] != null) {
                    prefix = prefix.substring(0, depth) + ((TrieNonLeaf) p).getLetters().charAt(i);
                } else {
                    trie.append("\t".repeat(Math.max(0, depth + 1)));
                    trie.append(" >>").append(prefix, 0, depth)
                            .append(((TrieNonLeaf) p).getLetters().charAt(i));
                }
            }

            if (((TrieNonLeaf) p).isEndOfWord()) {
                trie.append("\t".repeat(Math.max(0, depth + 1)));
                trie.append(" >>").append(prefix, 0, depth);
            }
        }

        return trie.toString();
    }

    private int position(TrieNonLeaf p, char ch) {
        int i = 0;
        while (i < p.getLetters().length() && p.getLetters().charAt(i) != ch) {
            i++;
        }

        return (i < p.getLetters().length()) ? i : NOT_FOUND;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return sizeRecursive(root);
    }

    private int sizeRecursive(TrieNode node) {
        if (node == null) return 0;
        if (node.isLeaf()) return 1;

        TrieNonLeaf n = (TrieNonLeaf) node;
        int count = n.isEndOfWord() ? 1 : 0;
        for (int i = 0; i < n.getLetters().length(); i++) {
            if (n.getPtr()[i] != null) {
                count += sizeRecursive(n.getPtr()[i]);
            }
        }
        return count;
    }

    public String print() {
        if (root == null) return System.lineSeparator() + "<EMPTY>" + System.lineSeparator();
        StringBuilder sb = new StringBuilder();
        sb.append(System.lineSeparator());
        printNode(sb, "", "", root);
        return sb.toString();
    }

    private void printNode(StringBuilder buffer, String prefix, String childrenPrefix, TrieNode node) {
        if (node == null) return;

        if (node.isLeaf()) {
            buffer.append(prefix);
            buffer.append("leaf: ");
            buffer.append(((TrieLeaf) node).getSuffix());
            buffer.append(System.lineSeparator());
            return;
        }

        TrieNonLeaf n = (TrieNonLeaf) node;
        buffer.append(prefix);
        buffer.append(n.getLetters());
        if (n.isEndOfWord()) buffer.append(" ($)");
        buffer.append(System.lineSeparator());

        int childCount = 0;
        for (TrieNode child : n.getPtr()) {
            if (child != null) childCount++;
        }

        int shown = 0;
        for (int i = n.getLetters().length() - 1; i >= 0; i--) {
            TrieNode child = n.getPtr()[i];
            if (child == null) continue;
            shown++;
            boolean isLast = (shown == childCount);
            if (isLast) {
                printNode(buffer, childrenPrefix + "└── ", childrenPrefix + "    ", child);
            } else {
                printNode(buffer, childrenPrefix + "├── ", childrenPrefix + "│   ", child);
            }
        }
    }

    public String printTrie() {
        if (root != null)
            return oldPrintTrie(0, root, "w");
        else
            return "<EMPTY>";
    }
}
