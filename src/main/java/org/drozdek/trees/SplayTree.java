package org.drozdek.trees;

import org.drozdek.trees.interfaces.TreeInterface;
import org.drozdek.trees.nodes.SplayTreeNode;

import java.io.PrintStream;

/// Self-adjusting splay tree. Recently accessed elements are moved to the root via splay operations,
/// providing amortized O(log n) performance.
///
/// Complexity Analysis:
/// Time Complexity: O(log n) amortized for search/insert/delete
/// Auxiliary Space: O(1)
///
/// Source: [Geeks for Geeks](https://www.geeksforgeeks.org/splay-tree/)
public class SplayTree<T> implements TreeInterface {

    SplayTreeNode<T> root;

    public SplayTree() {
        root = null;
    }

    private void continueRotation(SplayTreeNode<T> gr, SplayTreeNode<T> par, SplayTreeNode<T> ch,
                                  SplayTreeNode<T> desc) {
        // if pair has a grandpa
        if (gr != null) {
            if (gr.getRight() == ch.getParent())
                gr.setRight( ch);
            else
                gr.setLeft( ch);
        } else
            root = ch;

        if (desc != null)
            desc.setParent( par);

        par.setParent( ch);
        ch.setParent( gr);
    }

    private int countNodes(SplayTreeNode<T> node) {

        //base case
        if (node == null)
            return 0;

        //recursive call to left child and right child and
        // add the result of these with 1 ( 1 for counting the root)
        return 1 + countNodes(node.getLeft()) + countNodes(node.getRight());
    }

    /// Delete node by merging branches
    ///
    /// @param data Key to delete
    /// @return Operation code
    public int deleteByMerging(Comparable<T> data) {
        SplayTreeNode<T> tmp;
        SplayTreeNode<T> node;
        SplayTreeNode<T> p;
        SplayTreeNode<T> prev;

        SplayTreeNode<T>[] results = setUpDelete(data);
        p = results[0];
        prev = results[1];
        node = p;

        if (p == null || p.getKey() != data)
            // 1: empty tree  / -1:  key not found
            return root != null ? -1 : 1;

        if (node.getRight() == null)
            node = node.getLeft();
        else if (node.getLeft() == null)
            node = node.getRight();
        else {
            tmp = node.getLeft();

            while (tmp.getRight() != null)
                tmp = tmp.getRight();

            tmp.setRight( node.getRight());
            node = node.getLeft();
        }

        if (p == root)
            root = node;
        else if (prev.getLeft() == p)
            prev.setLeft( node);
        else
            prev.setRight( node);
        return 0;
    }

    /// Print in-order
    ///
    /// @param out Printing in order
    public void inorder(PrintStream out) {
        inorder(root, out);
    }

    /// Recursive implementation for the in-order tree path
    ///
    /// @param p   Node to print
    /// @param out Print stream
    protected void inorder(SplayTreeNode<T> p, PrintStream out) {
        if (p == null)
            return;

        inorder(p.getLeft(), out);
        visit(p, out);
        inorder(p.getRight(), out);
    }

    /// Insert a new node in the tree.
    ///
    /// @param data Value to insert in the tree
    public void insert(Comparable<T> data) {
        SplayTreeNode<T> p = root;
        SplayTreeNode<T> previous = null;

        while (p != null) {
            previous = p;

            if (p.getKey().compareTo((T) data) < 0) // should be <
                p = p.getRight();
            else
                p = p.getLeft();
        }

        if (root == null)
            root = new SplayTreeNode<>(data);
        else if (previous.getKey().compareTo((T) data) < 0)
            previous.setRight( new SplayTreeNode<>(data));
        else
            previous.setLeft( new SplayTreeNode<>(data));
    }

    private void rotateLeft(SplayTreeNode<T> p) {
        p.getParent().setRight( p.getLeft());
        p.setLeft( p.getParent());

        continueRotation(p.getParent().getParent(), p.getLeft(), p, p.getLeft().getRight());
    }

    private void rotateRight(SplayTreeNode<T> p) {
        p.getParent().setLeft( p.getRight());
        p.setRight( p.getParent());

        continueRotation(p.getParent().getParent(), p.getRight(), p, p.getRight().getLeft());
    }

    public Comparable<T> search(SplayTreeNode<T> p, Comparable<T> element) {
        while (p != null) {
            if (element == p.getKey())
                return p.getKey();
            else if (element.compareTo((T) p.getKey()) < 0)
                p = p.getLeft();
            else p = p.getRight();
        }
        return null;
    }

    public Comparable<T> search(Comparable<T> key) {
        return search(this.root, key);
    }

    /// Semi-splay the tree.
    public void semiSplay() {
        semiSplay(this.root);
    }

    private void semiSplay(SplayTreeNode<T> p) {
        while (p != root) {
            if (p.getParent().getParent() == null) {
                if (p.getParent().getLeft() == p)
                    rotateRight(p);
                else rotateLeft(p);
            } else if (p.getParent().getLeft() == p) {
                if (p.getParent().getParent().getLeft() == p.getParent()) {
                    rotateRight(p.getParent());
                    p = p.getParent();
                } else {
                    rotateRight(p);
                    rotateLeft(p);
                }
            } else if (p.getParent().getParent().getRight() == p.getParent()) {
                rotateLeft(p.getParent());
                p = p.getParent();
            } else {
                rotateLeft(p.getParent());
                rotateRight(p.getParent());
            }
            if (root == null)
                root = p;
        }
    }

    private SplayTreeNode<T>[] setUpDelete(Comparable<T> key) {
        SplayTreeNode<T>[] vars = new SplayTreeNode[2];
        vars[0] = root;
        vars[1] = null;

        while (vars[0] != null && vars[0].getKey() != key) {
            vars[1] = vars[0];

            if (vars[0].getKey().compareTo((T) key) < 0)
                vars[0] = vars[0].getRight();
            else
                vars[0] = vars[0].getLeft();
        }

        return vars;
    }

    public boolean isEmpty() {
        return root == null;
    }

    /// Number of nodes in the tree.
    ///
    /// @return The number of elements in node
    public int size() {
        return countNodes(root);
    }

    @Override
    public String toString() {
        return root.toString();
    }

    protected void visit(SplayTreeNode<T> p, PrintStream out) {
        out.println(p.getKey() + " ");
    }
}
