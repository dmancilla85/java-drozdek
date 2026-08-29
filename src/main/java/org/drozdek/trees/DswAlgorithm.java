package org.drozdek.trees;

import org.drozdek.trees.nodes.BinarySearchTreeNode;

/// Day–Stout–Warren (DSW) algorithm for balancing a binary search tree.
///
/// The algorithm rebalances a BST in-place in two passes. First it repeatedly
/// right-rotates nodes to compress the tree into a right-skewed "vine".
/// Second it performs a sequence of left rotations to re-expand the vine into
/// a perfectly balanced (as balanced as possible) tree. The whole process
/// runs in linear time and constant auxiliary space.
///
/// **Real-world use case:** Rebuilding a heavily skewed BST (such as one
/// produced by sorted insertion) into near-optimal shape to restore
/// logarithmic lookup.
///
/// Complexity Analysis:
/// Time Complexity: O(n)
/// Auxiliary Space: O(1)
///
/// Bibliography:
///
/// - Day, A. C. *Balancing a binary tree*. The Computer Journal, 1976.
/// - Stout, Q. F.; Warren, B. L. *Tree rebalancing in optimal time and space*. CACM, 1986.
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 6.
public final class DswAlgorithm {

    private DswAlgorithm() {
        // do nothing
    }

    /// Rearranges the tree rooted at the given node into a balanced tree and
    /// returns its new root.
    ///
    /// @param root root of the tree to balance
    /// @return new balanced root
    public static BinarySearchTreeNode<Integer> balance(BinarySearchTreeNode<Integer> root) {
        if (root == null) {
            return null;
        }
        BinarySearchTreeNode<Integer> pseudo = new BinarySearchTreeNode<>(null, null, root);
        int n = createBackbone(pseudo);
        createPerfectTree(pseudo, n);
        return pseudo.getRight();
    }

    private static int createBackbone(BinarySearchTreeNode<Integer> grand) {
        int count = 0;
        BinarySearchTreeNode<Integer> tmp = grand.getRight();
        while (tmp != null) {
            if (tmp.getLeft() != null) {
                BinarySearchTreeNode<Integer> oldTmp = tmp;
                tmp = tmp.getLeft();
                oldTmp.setLeft(tmp.getRight());
                tmp.setRight(oldTmp);
                grand.setRight(tmp);
            } else {
                count++;
                grand = tmp;
                tmp = tmp.getRight();
            }
        }
        return count;
    }

    private static void createPerfectTree(BinarySearchTreeNode<Integer> grand, int n) {
        int m = (1 << (Integer.SIZE - Integer.numberOfLeadingZeros(n + 1))) - 1;
        rotations(grand, n - m);
        while (m > 1) {
            m = m / 2;
            rotations(grand, m);
        }
    }

    private static void rotations(BinarySearchTreeNode<Integer> grand, int times) {
        BinarySearchTreeNode<Integer> tmp = grand.getRight();
        for (int i = 0; i < times && tmp != null && tmp.getRight() != null; i++) {
            BinarySearchTreeNode<Integer> oldTmp = tmp;
            tmp = tmp.getRight();
            grand.setRight(tmp);
            oldTmp.setRight(tmp.getLeft());
            tmp.setLeft(oldTmp);
            grand = tmp;
            tmp = tmp.getRight();
        }
    }
}
