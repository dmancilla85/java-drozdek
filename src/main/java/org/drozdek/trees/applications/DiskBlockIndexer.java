package org.drozdek.trees.applications;

import org.drozdek.trees.TwoFourTree;

/// Disk block index backed by a 2-3-4 tree.
///
/// Allocated block numbers are keyed in a self-balancing 2-3-4 (order-4 B-tree)
/// so that membership tests stay logarithmic even as the volume of indexed
/// blocks grows. Every leaf sits at the same depth, keeping lookups uniform.
///
/// **Real-world use case:** File-system block allocation maps and on-disk index
/// structures that must support fast presence checks over a large, growing key
/// space.
///
/// Complexity Analysis:
/// Time Complexity: O(log n) insert and lookup
/// Auxiliary Space: O(n) for the stored keys
///
/// Bibliography:
///
/// - 2–3–4 tree. *Wikipedia*. https://en.wikipedia.org/wiki/2%E2%80%933%E2%80%934_tree
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 6.
///
/// @see TwoFourTree
public class DiskBlockIndexer {

    private final TwoFourTree tree;

    /// Creates an empty disk block index.
    public DiskBlockIndexer() {
        this.tree = new TwoFourTree();
    }

    /// Records a block as allocated in the index.
    ///
    /// @param block block number to index
    public void indexBlock(int block) {
        tree.insert(block);
    }

    /// Checks whether a block is present in the index.
    ///
    /// @param block block number to look up
    /// @return true if the block is indexed
    public boolean isIndexed(int block) {
        return tree.contains(block);
    }

    /// Returns the height of the index tree.
    ///
    /// @return tree height, or -1 when the index is empty
    public int height() {
        return tree.height();
    }
}
