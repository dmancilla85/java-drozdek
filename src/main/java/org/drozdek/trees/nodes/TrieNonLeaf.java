package org.drozdek.trees.nodes;

/// Non-leaf node for Trie. Stores an ordered string of branching letters and pointers to child nodes.
///
/// Complexity Analysis:
/// Time Complexity: O(1)
/// Auxiliary Space: O(|alphabet|) per node
///
/// Source: [Geeks for Geeks](https://www.geeksforgeeks.org/trie-data-structure/)
public class TrieNonLeaf extends TrieNode {
    private boolean endOfWord;
    private String letters;
    private TrieNode[] ptr;

    public TrieNonLeaf(char ch) {
        letters = "";
        letters += ch;
        setLeaf(false);
        endOfWord = false;
        ptr = new TrieNode[1];
    }

    public TrieNonLeaf() {
        this(Character.MIN_VALUE);
    }

    private void print(StringBuilder buffer, String prefix, String childrenPrefix) {
        buffer.append(prefix);
        buffer.append(letters);
        if (endOfWord) buffer.append(" ($)");
        buffer.append(System.lineSeparator());

        int childCount = 0;
        for (TrieNode child : ptr) {
            if (child != null) childCount++;
        }

        int shown = 0;
        for (int i = letters.length() - 1; i >= 0; i--) {
            TrieNode child = ptr[i];
            if (child == null) continue;
            shown++;
            boolean isLast = (shown == childCount);
            String childPrefix = isLast ? childrenPrefix + "└── " : childrenPrefix + "├── ";
            String childChildrenPrefix = isLast ? childrenPrefix + "    " : childrenPrefix + "│   ";

            if (child.isLeaf()) {
                buffer.append(childPrefix);
                buffer.append("leaf: ");
                buffer.append(((TrieLeaf) child).getSuffix());
                buffer.append(System.lineSeparator());
            } else {
                ((TrieNonLeaf) child).print(buffer, childPrefix, childChildrenPrefix);
            }
        }
    }

    public boolean isEndOfWord() {
        return endOfWord;
    }

    public void setEndOfWord(boolean endOfWord) {
        this.endOfWord = endOfWord;
    }

    public String getLetters() {
        return letters;
    }

    public void setLetters(String letters) {
        this.letters = letters;
    }

    public TrieNode[] getPtr() {
        return ptr;
    }

    public void setPtr(TrieNode[] ptr) {
        this.ptr = ptr;
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder(50);
        buffer.append(System.lineSeparator());
        print(buffer, "", "");
        return buffer.toString();
    }
}
