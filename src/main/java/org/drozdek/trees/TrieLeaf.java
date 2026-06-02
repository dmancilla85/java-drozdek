package org.drozdek.trees;

/// Leaf node for Trie. Stores the remaining suffix of a word after the branching path.
///
/// Complexity Analysis:
/// Time Complexity: O(1)
/// Auxiliary Space: O(1) per leaf
///
/// Source: [Geeks for Geeks](https://www.geeksforgeeks.org/trie-data-structure/)
public class TrieLeaf extends TrieNode {
    protected final String suffix;

    public TrieLeaf(String suffix) {
        this.suffix = suffix;
        isLeaf = true;
    }

    @Override
    public String toString() {
        return suffix;
    }
}
