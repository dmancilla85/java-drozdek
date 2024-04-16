package org.drozdek.trees;

import org.drozdek.commons.LoggerService;

public class Trie {
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
                addCell(ch, p, pos);
        }

        p.ptr[pos] = lf;
    }

    public boolean found(String word) {
        TrieNode p = root;
        int pos;
        int i = 0;

        while (true) {
            if (p.isLeaf) {
                assert p instanceof TrieLeaf;
                TrieLeaf lf = (TrieLeaf) p;
                return (word.substring(i).equals(lf.suffix));
            } else if ((pos = position((TrieNonLeaf) p, word.charAt(i))) != NOT_FOUND
                    && i + 1 == word.length()) {
                if (((TrieNonLeaf) p).ptr[pos] == null)
                    return true;
                else
                    return (!(((TrieNonLeaf) p).ptr[pos]).isLeaf &&
                            ((TrieNonLeaf) ((TrieNonLeaf) p).ptr[pos]).endOfWord);
            } else if (pos != NOT_FOUND && ((TrieNonLeaf) p).ptr[pos] != null) {
                p = ((TrieNonLeaf) p).ptr[pos];
                i++;
            } else
                return false;
        }

    }

    public void insert(String word) {

        TrieLeaf lf;
        int offset;
        int pos;
        int i = 0;

        while (true) {
            if (i == word.length()) {
                if (root.endOfWord)
                    LoggerService.logError("Duplicate entry (#1): " + word);
                return;
            }

            pos = position(root, word.charAt(i));

            if (pos != NOT_FOUND && root.ptr[pos] == null) {
                if (i + 1 == word.length()) {
                    LoggerService.logError("Duplicate entry (#2): " + word);
                    return;
                }

                root.ptr[pos] = new TrieNonLeaf(word.charAt(i + 1));
                ((TrieNonLeaf) (root.ptr[pos])).endOfWord = true;

                String s = (word.length() > i + 2) ? word.substring(i + 2) : null;
                createLeaf(word.charAt(i + 1), s, (TrieNonLeaf) (root.ptr[pos]));
                return;
            } else if (pos != NOT_FOUND && root.ptr[pos].isLeaf) {
                lf = (TrieLeaf) root.ptr[pos];

                if (lf.suffix.equals(word.substring(i + 1))) {
                    LoggerService.logError("Duplicate entry (#3): " + word);
                    return;
                }

                offset = 0;

                do {
                    pos = position(root, word.charAt(i + offset));
                    // word=ABC, leaf=ABCDEF => leaf=DEF
                    if (word.length() == i + offset + 1) {
                        root.ptr[pos] = new TrieNonLeaf(lf.suffix.charAt(offset));
                        root = (TrieNonLeaf) root.ptr[pos];
                        root.endOfWord = true;
                        createLeaf(lf.suffix.charAt(offset), lf.suffix.substring(offset + 1), root);
                        return;
                    } else if (lf.suffix.length() == offset) {
                        root.ptr[pos] = new TrieNonLeaf(word.charAt(i + offset + 1));
                        root = (TrieNonLeaf) root.ptr[pos];
                        root.endOfWord = true;
                        createLeaf(word.charAt(i + offset + 1), word.substring(i + offset + 2), root);
                        return;
                    }

                    root.ptr[pos] = new TrieNonLeaf(word.charAt(i + offset + 1));
                    root = (TrieNonLeaf) root.ptr[pos];
                    offset++;
                } while (word.charAt(i + offset) == lf.suffix.charAt(offset - 1));

                offset--;
                String s = null;

                if (word.length() > i + offset + 2)
                    s = word.substring(i + offset + 2);

                createLeaf(word.charAt(i + offset + 1), s, root);

                if (lf.suffix.length() > offset + 1)
                    s = lf.suffix.substring(offset + 1);
                else
                    s = null;
                createLeaf(lf.suffix.charAt(offset), s, root);
                return;
            } else {
                if (pos != NOT_FOUND)
                    root = (TrieNonLeaf) root.ptr[pos];
                i++;
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

    public String print() {
        return root.toString();
    }

    public String printTrie() {
        if (root != null)
            return oldPrintTrie(0, root, "w");
        else
            return "<EMPTY>";
    }
}
