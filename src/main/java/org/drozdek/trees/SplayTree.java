package org.drozdek.trees;

import java.io.PrintStream;

public class SplayTree<T> {

    SplayTreeNode<T> root;

    public SplayTree() {
        root = null;
    }

    private void continueRotation(SplayTreeNode<T> gr, SplayTreeNode<T> par, SplayTreeNode<T> ch,
                                  SplayTreeNode<T> desc) {
        // if pair has a grandpa
        if (gr != null) {
            if (gr.right == ch.parent)
                gr.right = ch;
            else
                gr.left = ch;
        } else
            root = ch;

        if (desc != null)
            desc.parent = par;

        par.parent = ch;
        ch.parent = gr;
    }

    private int countNodes(SplayTreeNode<T> node) {

        //base case
        if (node == null)
            return 0;

        //recursive call to left child and right child and
        // add the result of these with 1 ( 1 for counting the root)
        return 1 + countNodes(node.left) + countNodes(node.right);
    }

    /**
     * Delete node by merging branches
     *
     * @param data Key to delete
     * @return Operation code
     */
    public int deleteByMerging(Comparable<T> data) {
        SplayTreeNode<T> tmp;
        SplayTreeNode<T> node;
        SplayTreeNode<T> p;
        SplayTreeNode<T> prev;

        SplayTreeNode<T>[] results = setUpDelete(data);
        p = results[0];
        prev = results[1];
        node = p;

        if (p == null || p.key != data)
            // 1: empty tree  / -1:  key not found
            return root != null ? -1 : 1;

        if (node.right == null)
            node = node.left;
        else if (node.left == null)
            node = node.right;
        else {
            tmp = node.left;

            while (tmp.right != null)
                tmp = tmp.right;

            tmp.right = node.right;
            node = node.left;
        }

        if (p == root)
            root = node;
        else if (prev.left == p)
            prev.left = node;
        else
            prev.right = node;
        return 0;
    }

    /**
     * Insert a new node in the tree.
     *
     * @param data Value to insert in the tree
     */
    public void insert(Comparable<T> data) {
        SplayTreeNode<T> p = root;
        SplayTreeNode<T> previous = null;

        while (p != null) {
            previous = p;

            if (p.key.compareTo((T) data) < 0) // should be <
                p = p.right;
            else
                p = p.left;
        }

        if (root == null)
            root = new SplayTreeNode<>(data);
        else if (previous.key.compareTo((T) data) < 0)
            previous.right = new SplayTreeNode<>(data);
        else
            previous.left = new SplayTreeNode<>(data);
    }

    private void rotateLeft(SplayTreeNode<T> p) {
        p.parent.right = p.left;
        p.left = p.parent;

        continueRotation(p.parent.parent, p.left, p, p.left.right);
    }

    private void rotateRight(SplayTreeNode<T> p) {
        p.parent.left = p.right;
        p.right = p.parent;

        continueRotation(p.parent.parent, p.right, p, p.right.left);
    }

    public Comparable<T> search(SplayTreeNode<T> p, Comparable<T> element) {
        while (p != null) {
            if (element == p.key)
                return p.key;
            else if (element.compareTo((T) p.key) < 0)
                p = p.left;
            else p = p.right;
        }
        return null;
    }

    public Comparable<T> search(Comparable<T> key) {
        return search(this.root, key);
    }

    /**
     *
     */
    public void semiSplay() {
        semiSplay(this.root);
    }

    private void semiSplay(SplayTreeNode<T> p) {
        while (p != root) {
            if (p.parent.parent == null) {
                if (p.parent.left == p)
                    rotateRight(p);
                else rotateLeft(p);
            } else if (p.parent.left == p) {
                if (p.parent.parent.left == p.parent) {
                    rotateRight(p.parent);
                    p = p.parent;
                } else {
                    rotateRight(p);
                    rotateLeft(p);
                }
            } else if (p.parent.parent.right == p.parent) {
                rotateLeft(p.parent);
                p = p.parent;
            } else {
                rotateLeft(p.parent);
                rotateRight(p.parent);
            }
            if (root == null)
                root = p;
        }
    }

    private SplayTreeNode<T>[] setUpDelete(Comparable<T> key) {
        SplayTreeNode<T>[] vars = new SplayTreeNode[2];
        vars[0] = root;
        vars[1] = null;

        while (vars[0] != null && vars[0].key != key) {
            vars[1] = vars[0];

            if (vars[0].key.compareTo((T) key) < 0)
                vars[0] = vars[0].right;
            else
                vars[0] = vars[0].left;
        }

        return vars;
    }

    /**
     * Print in-order
     * @param out Printing in order
     */
    public void inorder(PrintStream out) {
        inorder(root, out);
    }

    /**
     * Recursive implementation for the in-order tree path
     *
     * @param p Node to print
     * @param out Print stream
     */
    protected void inorder(SplayTreeNode<T> p, PrintStream out) {
        if (p == null)
            return;

        inorder(p.left, out);
        visit(p, out);
        inorder(p.right, out);
    }

    protected void visit(SplayTreeNode<T> p, PrintStream out) {
        out.println(p.key + " ");
    }

    /**
     * Number of nodes in the tree.
     *
     * @return The number of elements in node
     */
    public int size() {
        return countNodes(root);
    }

    @Override
    public String toString() {
        return root.toString();
    }
}
