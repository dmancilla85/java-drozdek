package org.drozdek.trees;

import org.drozdek.commons.LoggerService;
import org.drozdek.trees.interfaces.TreeInterface;

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
        int len = p.letters.length();
        char[] s = new char[len + 1];
        TrieNode[] tmp = p.ptr;
        p.ptr = new TrieNode[len + 1];

        for (i = 0; i < len + 1; i++)
            p.ptr[i] = null;

        if (stop < len) {
            for (i = len; i >= stop + 1; i--) {
                p.ptr[i] = tmp[i - 1];
                s[i] = p.letters.charAt(i - 1);
            }
        }

        s[stop] = ch;

        for (i = stop - 1; i >= 0; i--) {
            p.ptr[i] = tmp[i];
            s[i] = p.letters.charAt(i);
        }

        p.letters = new String(s);
    }

    private void createLeaf(char ch, String suffix, TrieNonLeaf p) {
        int pos = position(p, ch);
        TrieLeaf lf = null;

        if (suffix != null && suffix.length() > 0) {
            lf = new TrieLeaf(suffix);
        }

        if (pos == NOT_FOUND) {
            for (pos = 0; pos < p.letters.length() && p.letters.charAt(pos) < ch; pos++)
                ;
            addCell(ch, p, pos);
        }

        p.ptr[pos] = lf;
    }

    public boolean found(String word) {
        TrieNode p = root;
        int pos;
        int i = 0;

        while (true) {
            if (p == null)
                return false;

            if (p.isLeaf) {
                assert p instanceof TrieLeaf;
                TrieLeaf lf = (TrieLeaf) p;
                return word.substring(i).equals(lf.suffix);
            }

            if (i >= word.length()) {
                return ((TrieNonLeaf) p).endOfWord;
            }

            pos = position((TrieNonLeaf) p, word.charAt(i));

            if (pos != NOT_FOUND && i + 1 == word.length()) {
                if (((TrieNonLeaf) p).ptr[pos] == null)
                    return true;
                else
                    return !((TrieNonLeaf) p).ptr[pos].isLeaf &&
                            ((TrieNonLeaf) ((TrieNonLeaf) p).ptr[pos]).endOfWord;
            } else if (pos != NOT_FOUND && ((TrieNonLeaf) p).ptr[pos] != null) {
                p = ((TrieNonLeaf) p).ptr[pos];
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
                current.endOfWord = true;
                return;
            }

            int pos = position(current, word.charAt(i));

            if (pos != NOT_FOUND && current.ptr[pos] == null) {
                if (i + 1 == word.length()) {
                    current.endOfWord = true;
                    return;
                }

                current.ptr[pos] = new TrieNonLeaf(word.charAt(i + 1));
                ((TrieNonLeaf) current.ptr[pos]).endOfWord = true;

                String s = (word.length() > i + 2) ? word.substring(i + 2) : null;
                createLeaf(word.charAt(i + 1), s, (TrieNonLeaf) current.ptr[pos]);
                return;
            } else if (pos != NOT_FOUND && current.ptr[pos].isLeaf) {
                TrieLeaf lf = (TrieLeaf) current.ptr[pos];

                if (lf.suffix.equals(word.substring(i + 1))) {
                    LoggerService.logError("Duplicate entry (#3): " + word);
                    return;
                }

                int offset = 0;

                do {
                    pos = position(current, word.charAt(i + offset));

                    if (word.length() == i + offset + 1) {
                        current.ptr[pos] = new TrieNonLeaf(lf.suffix.charAt(offset));
                        current = (TrieNonLeaf) current.ptr[pos];
                        current.endOfWord = true;
                        createLeaf(lf.suffix.charAt(offset), lf.suffix.substring(offset + 1), current);
                        return;
                    } else if (lf.suffix.length() == offset) {
                        current.ptr[pos] = new TrieNonLeaf(word.charAt(i + offset + 1));
                        current = (TrieNonLeaf) current.ptr[pos];
                        current.endOfWord = true;
                        createLeaf(word.charAt(i + offset + 1), word.substring(i + offset + 2), current);
                        return;
                    }

                    current.ptr[pos] = new TrieNonLeaf(word.charAt(i + offset + 1));
                    current = (TrieNonLeaf) current.ptr[pos];
                    offset++;
                } while (word.charAt(i + offset) == lf.suffix.charAt(offset - 1));

                offset--;
                String s = null;

                if (word.length() > i + offset + 2)
                    s = word.substring(i + offset + 2);

                createLeaf(word.charAt(i + offset + 1), s, current);

                if (lf.suffix.length() > offset + 1)
                    s = lf.suffix.substring(offset + 1);
                else
                    s = null;
                createLeaf(lf.suffix.charAt(offset), s, current);
                return;
            } else if (pos != NOT_FOUND) {
                current = (TrieNonLeaf) current.ptr[pos];
                i++;
            } else {
                addCell(word.charAt(i), current, 0);
                pos = position(current, word.charAt(i));
                if (i + 1 == word.length()) {
                    current.endOfWord = true;
                    return;
                }
                String suffix = word.substring(i + 1);
                current.ptr[pos] = new TrieLeaf(suffix);
                return;
            }
        }
    }

    protected String oldPrintTrie(int depth, TrieNode p, String prefix) {

        StringBuilder trie = new StringBuilder();

        if (p.isLeaf) {
            trie.append("\t".repeat(Math.max(0, depth)));
            trie.append(" >")
                    .append(prefix)
                    .append("|")
                    .append(((TrieLeaf) p).suffix);
        } else {
            for (int i = ((TrieNonLeaf) p).letters.length() - 1; i >= 0; i--) {
                if (((TrieNonLeaf) p).ptr[i] != null) {
                    prefix = prefix.substring(0, depth) + ((TrieNonLeaf) p).letters.charAt(i);
                } else {
                    trie.append("\t".repeat(Math.max(0, depth + 1)));
                    trie.append(" >>").append(prefix, 0, depth)
                            .append(((TrieNonLeaf) p).letters.charAt(i));
                }
            }

            if (((TrieNonLeaf) p).endOfWord) {
                trie.append("\t".repeat(Math.max(0, depth + 1)));
                trie.append(" >>").append(prefix, 0, depth);
            }
        }

        return trie.toString();
    }

    private int position(TrieNonLeaf p, char ch) {
        int i = 0;
        while (i < p.letters.length() && p.letters.charAt(i) != ch) {
            i++;
        }

        return (i < p.letters.length()) ? i : NOT_FOUND;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return sizeRecursive(root);
    }

    private int sizeRecursive(TrieNode node) {
        if (node == null) return 0;
        if (node.isLeaf) return 1;

        TrieNonLeaf n = (TrieNonLeaf) node;
        int count = n.endOfWord ? 1 : 0;
        for (int i = 0; i < n.letters.length(); i++) {
            if (n.ptr[i] != null) {
                count += sizeRecursive(n.ptr[i]);
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

        if (node.isLeaf) {
            buffer.append(prefix);
            buffer.append("leaf: ");
            buffer.append(((TrieLeaf) node).suffix);
            buffer.append(System.lineSeparator());
            return;
        }

        TrieNonLeaf n = (TrieNonLeaf) node;
        buffer.append(prefix);
        buffer.append(n.letters);
        if (n.endOfWord) buffer.append(" ($)");
        buffer.append(System.lineSeparator());

        int childCount = 0;
        for (TrieNode child : n.ptr) {
            if (child != null) childCount++;
        }

        int shown = 0;
        for (int i = n.letters.length() - 1; i >= 0; i--) {
            TrieNode child = n.ptr[i];
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
