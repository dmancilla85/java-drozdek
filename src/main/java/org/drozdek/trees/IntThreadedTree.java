package org.drozdek.trees;

import org.drozdek.trees.interfaces.TreeInterface;
import org.drozdek.trees.nodes.IntThreadedTreeNode;

import java.io.PrintStream;

/// In-threaded binary tree with threaded inorder traversal. Uses successor threads to perform
/// efficient in-order traversal without recursion or a stack.
///
/// **Real-world use case:** Constrained embedded environments where a call stack
/// is unavailable or too expensive, such as in-kernel data structure traversals.
///
/// Complexity Analysis:
/// Time Complexity: O(n) for traversal, O(log n) for search/insert on balanced
/// Auxiliary Space: O(1) for traversal, O(1) for insert
///
/// @see <a href="https://doi.org/10.1145/367177.367202">Perlis &amp; Thornton, 1960, Symbol manipulation by threaded lists (ACM)</a>
public class IntThreadedTree implements TreeInterface {
    private IntThreadedTreeNode root;

    public IntThreadedTree() {
        root = null;
    }

    private int countNodes(IntThreadedTreeNode node) {

        if (node == null)
            return 0;

        int count = 1;
        if (node.getLeft() != null)
            count += countNodes(node.getLeft());
        if (!node.isSuccessor() && node.getRight() != null)
            count += countNodes(node.getRight());
        return count;
    }

    public boolean isEmpty() {
        return root == null;
    }

    /// Inserts a value keeping the successor threads consistent.
    ///
    /// When the new node takes over a thread from its parent, the thread is transferred so
    /// in-order traversal stays correct. Runs in O(h), where h is the tree height.
    ///
    /// @param value key to insert
    public void insert(int value) {
        IntThreadedTreeNode newNode = new IntThreadedTreeNode(value);
        IntThreadedTreeNode p = root;
        IntThreadedTreeNode prev = null;

        if (root == null) {
            root = newNode;
            return;
        }

        while (p != null) {
            // finds a place to insert the new node
            prev = p;

            if (value < p.getKey())
                p = p.getLeft();
            else
                // moves to the right only if is a descendant, does not follow the successor link
                if (!p.isSuccessor()) {
                    p = p.getRight();
                } else
                    break;
        }

        // If the new node is a child of the left, its parent also becomes a successor
        if (value < prev.getKey()) {
            prev.setLeft( newNode);
            newNode.setSuccessor( true);
            newNode.setRight( prev);
        } else
        // If new node parent is not the rightmost node, it turns the parent successor to new node successor
        {
            if (prev.isSuccessor()) {
                newNode.setSuccessor( true);
                prev.setSuccessor( false);
                newNode.setRight( prev.getRight());
            }
            // otherwise it has not successor
            prev.setRight( newNode);
        }
    }

    /// Prints the keys in in-order using the threaded traversal.
    ///
    /// @param out destination stream for visited keys
    public void printInOrder(PrintStream out) {
        threadInOrder(out);
    }

    public int size() {
        return countNodes(root);
    }

    /// Performs an in-order traversal following threads instead of recursion.
    ///
    /// Starts at the leftmost node and repeatedly descends to the leftmost descendant of real
    /// right children or follows successor threads. Runs in O(n) time and O(1) space.
    ///
    /// @param out destination stream for visited keys
    public void threadInOrder(PrintStream out) {
        IntThreadedTreeNode prev;
        IntThreadedTreeNode p = root;

        // process only non-empty trees
        if (p == null)
            return;

        // moves to the node on the left side
        while (p.getLeft() != null)
            p = p.getLeft();

        while (p != null) {
            visit(p, out);
            prev = p;

            p = p.getRight();

            // moves to the right node only if is a descendant
            if (p != null && !prev.isSuccessor())
                // moves to the leftmost node, otherwise visits the successor
                while (p.getLeft() != null)
                    p = p.getLeft();
        }

    }

    /// Prints the key of the visited node followed by a space.
    ///
    /// @param p   node being visited, ignored when null
    /// @param out destination stream
    protected void visit(IntThreadedTreeNode p, PrintStream out) {
        if (p == null)
            return;

        out.println(p.getKey() + " ");
    }

    @Override
    public String toString() {
        if (root == null) return System.lineSeparator() + "<EMPTY>" + System.lineSeparator();
        StringBuilder buffer = new StringBuilder(50);
        buffer.append(System.lineSeparator());
        printNode(buffer, "", "", root);
        return buffer.toString();
    }

    private void printNode(StringBuilder buffer, String prefix, String childrenPrefix, IntThreadedTreeNode node) {
        if (node == null) return;
        buffer.append(prefix);
        buffer.append(node.getKey());
        buffer.append(System.lineSeparator());

        IntThreadedTreeNode left = node.getLeft();
        IntThreadedTreeNode right = node.isSuccessor() ? null : node.getRight();
        boolean hasLeft = left != null;
        boolean hasRight = right != null;

        if (hasRight) {
            printNode(buffer, childrenPrefix + "├── ", childrenPrefix + "│   ", left);
            printNode(buffer, childrenPrefix + "└── ", childrenPrefix + "    ", right);
        } else if (hasLeft) {
            printNode(buffer, childrenPrefix + "└── ", childrenPrefix + "    ", left);
        }
    }
}
