package org.drozdek.trees.exercises;

import org.drozdek.trees.nodes.BinarySearchTreeNode;

/// Computes the diameter of a binary tree.
///
/// The diameter (width) of a tree is the number of nodes on the longest path
/// between any two leaves (or between the root-related extreme pair). It is
/// computed by combining, at every node, the path that passes through that
/// node (left height + right height + 1) with the diameters of its subtrees.
///
/// **Real-world use case:** A classic exercise measuring the "spread" of a
/// tree, used in network latency and taxonomy breadth analysis.
///
/// Complexity Analysis:
/// Time Complexity: O(n)
/// Auxiliary Space: O(n) recursion depth in the worst case
///
/// Bibliography:
///
/// - Diameter of a binary tree. *Wikipedia*. https://en.wikipedia.org/wiki/Binary_tree
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 6.
public final class TreeDiameter {

    private TreeDiameter() {
        // do nothing
    }

    /// Returns the diameter of the tree rooted at the given node.
    ///
    /// @param root root of the tree
    /// @return number of nodes on the longest path
    public static int diameter(BinarySearchTreeNode<?> root) {
        return compute(root).diameter;
    }

    private static Result compute(BinarySearchTreeNode<?> node) {
        if (node == null) {
            return new Result(0, 0);
        }
        Result left = compute(node.getLeft());
        Result right = compute(node.getRight());
        int throughHere = left.height + right.height + 1;
        int diameter = Math.max(throughHere, Math.max(left.diameter, right.diameter));
        int height = 1 + Math.max(left.height, right.height);
        return new Result(diameter, height);
    }

    private static final class Result {
        private final int diameter;
        private final int height;

        private Result(int diameter, int height) {
            this.diameter = diameter;
            this.height = height;
        }
    }
}
