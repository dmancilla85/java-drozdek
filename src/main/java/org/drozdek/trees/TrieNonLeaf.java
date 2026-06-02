package org.drozdek.trees;

/// Non-leaf node for Trie. Stores an ordered string of branching letters and pointers to child nodes.
///
/// Complexity Analysis:
/// Time Complexity: O(1)
/// Auxiliary Space: O(|alphabet|) per node
///
/// Source: [Geeks for Geeks](https://www.geeksforgeeks.org/trie-data-structure/)
public class TrieNonLeaf extends TrieNode {
    protected boolean endOfWord;
    protected String letters;
    protected TrieNode[] ptr;

    public TrieNonLeaf(char ch) {
        letters = "";
        letters += ch;
        isLeaf = false;
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

            if (child.isLeaf) {
                buffer.append(childPrefix);
                buffer.append("leaf: ");
                buffer.append(((TrieLeaf) child).suffix);
                buffer.append(System.lineSeparator());
            } else {
                ((TrieNonLeaf) child).print(buffer, childPrefix, childChildrenPrefix);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder(50);
        buffer.append(System.lineSeparator());
        print(buffer, "", "");
        return buffer.toString();
    }
}
