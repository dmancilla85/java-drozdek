package org.drozdek.trees;

public class Trie {
    public static final int NOT_FOUND = -1;
    private final TrieNonLeaf root;

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
        TrieNonLeaf p = root;
        TrieLeaf lf;
        int offset;
        int pos;
        int i = 0;

        while (true) {
            if (i == word.length()) {
                if (p.endOfWord)
                    System.out.println("Duplicate entry1: " + word);
                return;
            }

            pos = position(p, word.charAt(i));

            if (pos != NOT_FOUND && p.ptr[pos] == null) {
                if (i + 1 == word.length()) {
                    System.out.println("Duplicated entry: " + word);
                    return;
                }

                p.ptr[pos] = new TrieNonLeaf(word.charAt(i + 1));
                ((TrieNonLeaf) (p.ptr[pos])).endOfWord = true;

                String s = (word.length() > i + 2) ? word.substring(i + 2) : null;
                createLeaf(word.charAt(i + 1), s, (TrieNonLeaf) (p.ptr[pos]));
                return;
            } else if (pos != NOT_FOUND && p.ptr[pos].isLeaf) {
                lf = (TrieLeaf) p.ptr[pos];

                if (lf.suffix.equals(word.substring(i + 1))) {
                    System.out.println("duplicated entry: " + word);
                    return;
                }

                offset = 0;

                do {
                    pos = position(p, word.charAt(i + offset));
                    // word=ABC, leaf=ABCDEF => leaf=DEF
                    if (word.length() == i + offset + 1) {
                        p.ptr[pos] = new TrieNonLeaf(lf.suffix.charAt(offset));
                        p = (TrieNonLeaf) p.ptr[pos];
                        p.endOfWord = true;
                        createLeaf(lf.suffix.charAt(offset), lf.suffix.substring(offset + 1), p);
                        return;
                    } else if (lf.suffix.length() == offset) {
                        p.ptr[pos] = new TrieNonLeaf(word.charAt(i + offset + 1));
                        p = (TrieNonLeaf) p.ptr[pos];
                        p.endOfWord = true;
                        createLeaf(word.charAt(i + offset + 1), word.substring(i + offset + 2), p);
                        return;
                    }

                    p.ptr[pos] = new TrieNonLeaf(word.charAt(i + offset + 1));
                    p = (TrieNonLeaf) p.ptr[pos];
                    offset++;
                } while (word.charAt(i + offset) == lf.suffix.charAt(offset - 1));

                offset--;
                String s=null;

                if(word.length()>i+offset+2)
                    s=word.substring(i+offset+2);

                createLeaf(word.charAt(i+offset+1),s,p);

                if(lf.suffix.length() >offset+1)
                    s=lf.suffix.substring(offset+1);
                else
                    s=null;
                createLeaf(lf.suffix.charAt(offset),s,p);
                return;
            } else {
                if(pos!=NOT_FOUND)
                    p=(TrieNonLeaf) p.ptr[pos];
                i++;
            }
        }
    }

    private int position(TrieNonLeaf p, char ch) {
        int i = 0;
        while (i < p.letters.length() && p.letters.charAt(i) != ch) {
            i++;
        }

        return (i < p.letters.length()) ? i : NOT_FOUND;
    }

    protected void printTrie(int depth, TrieNode p, String prefix) {
        if (p.isLeaf) {
            for (int j = 1; j <= depth; j++)
                System.out.print("\t");
            System.out.println(" >" + prefix + "|" + ((TrieLeaf) p).suffix);
        } else {
            for (int i = ((TrieNonLeaf) p).letters.length() - 1; i >= 0; i--) {
                if (((TrieNonLeaf) p).ptr[i] != null) {
                    prefix = prefix.substring(0, depth) + ((TrieNonLeaf) p).letters.charAt(i);
                } else {
                    for (int j = 1; j <= depth + 1; j++)
                        System.out.print("\t");
                    System.out.println(" >>" + prefix.substring(0, depth) + ((TrieNonLeaf) p).letters.charAt(i));
                }
            }

            if (((TrieNonLeaf) p).endOfWord) {
                for (int j = 1; j <= depth + 1; j++)
                    System.out.print("\t");
                System.out.print(" >>" + prefix.substring(0, depth));
            }
        }
    }

    public void printTrie() {
        if (root != null)
            printTrie(0, root, "");
    }
}
