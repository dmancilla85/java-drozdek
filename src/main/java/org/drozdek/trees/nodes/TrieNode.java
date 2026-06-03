package org.drozdek.trees.nodes;

/// Base class for Trie nodes. Holds a boolean flag indicating whether this is a leaf node.
///
/// Complexity Analysis:
/// Time Complexity: O(1)
/// Auxiliary Space: O(1)
///
/// Source: [Geeks for Geeks](https://www.geeksforgeeks.org/trie-data-structure/)
public class TrieNode {
    private boolean isLeaf;

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }
}
