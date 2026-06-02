package org.drozdek.trees;

import org.drozdek.trees.interfaces.TreeInterface;
import java.io.PrintStream;

/// In-threaded binary tree with threaded inorder traversal. Uses successor threads to perform
/// efficient in-order traversal without recursion or a stack.
///
/// Complexity Analysis:
/// Time Complexity: O(n) for traversal, O(log n) for search/insert on balanced
/// Auxiliary Space: O(1) for traversal, O(1) for insert
///
/// Source: [Geeks for Geeks](https://www.geeksforgeeks.org/threaded-binary-tree/)
public class IntThreadedTree implements TreeInterface {
    private IntThreadedTreeNode root;

    public IntThreadedTree() {
        root = null;
    }

    private int countNodes(IntThreadedTreeNode node) {

        if (node == null)
            return 0;

        int count = 1;
        if (node.left != null)
            count += countNodes(node.left);
        if (!node.successor && node.right != null)
            count += countNodes(node.right);
        return count;
    }

    public boolean isEmpty() {
        return root == null;
    }

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

            if (value < p.key)
                p = p.left;
            else
                // moves to the right only if is a descendant, does not follow the successor link
                if (!p.successor) {
                    p = p.right;
                } else
                    break;
        }

        // If the new node is a child of the left, its parent also becomes a successor
        if (value < prev.key) {
            prev.left = newNode;
            newNode.successor = true;
            newNode.right = prev;
        } else
        // If new node parent is not the rightmost node, it turns the parent successor to new node successor
        {
            if (prev.successor) {
                newNode.successor = true;
                prev.successor = false;
                newNode.right = prev.right;
            }
            // otherwise it has not successor
            prev.right = newNode;
        }
    }

    public void printInOrder(PrintStream out) {
        threadInOrder(out);
    }

    public int size() {
        return countNodes(root);
    }

    public void threadInOrder(PrintStream out) {
        IntThreadedTreeNode prev;
        IntThreadedTreeNode p = root;

        // process only non-empty trees
        if (p == null)
            return;

        // moves to the node on the left side
        while (p.left != null)
            p = p.left;

        while (p != null) {
            visit(p, out);
            prev = p;

            p = p.right;

            // moves to the right node only if is a descendant
            if (p != null && !prev.successor)
                // moves to the leftmost node, otherwise visits the successor
                while (p.left != null)
                    p = p.left;
        }

    }

    protected void visit(IntThreadedTreeNode p, PrintStream out) {
        if (p == null)
            return;

        out.println(p.key + " ");
    }
}
