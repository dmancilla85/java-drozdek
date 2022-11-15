package org.drozdek.trees;

public class SplayTree<T> extends BinarySearchTree<T> {

    public SplayTree() {
        super();
    }

    private void continueRotation(BinarySearchTreeNode<T> gr, BinarySearchTreeNode<T> par, BinarySearchTreeNode<T> ch,
                                  BinarySearchTreeNode<T> desc) {
        // if pair has a grandpa
        if (gr != null) {
            if (gr.right == ((SplayTreeNode<T>) ch).parent)
                gr.right = ch;
            else
                gr.left = ch;
        } else
            root = ch;

        if (desc != null)
            ((SplayTreeNode<T>) desc).parent = par;

        ((SplayTreeNode<T>) par).parent = ch;
        ((SplayTreeNode<T>) ch).parent = gr;
    }

    private void rotateLeft(SplayTreeNode<T> p) {
        p.parent.right = p.left;
        p.left = p.parent;

        continueRotation(((SplayTreeNode<T>) p.parent).parent, p.left, p, p.left.right);
    }

    private void rotateRight(SplayTreeNode<T> p) {
        p.parent.left = p.right;
        p.right = p.parent;

        continueRotation(((SplayTreeNode<T>) p.parent).parent, p.right, p, p.right.left);
    }

    private void semiSplay(SplayTreeNode<T> p) {
        while (p != root) {
            if (((SplayTreeNode<T>) p.parent).parent == null)
                if (p.parent.left == p) {
                    rotateRight(p);
                } else rotateLeft(p);
            else if (p.parent.left == p)
                if (((SplayTreeNode<T>) p.parent).parent.left == p.parent) {
                    rotateRight((SplayTreeNode<T>) p.parent);
                    p = (SplayTreeNode<T>) p.parent;
                } else {
                    rotateRight(p);
                    rotateLeft(p);
                }
            else if (((SplayTreeNode<T>) (p.parent)).parent.right == p.parent) {
                rotateLeft((SplayTreeNode<T>) p.parent);
                p = (SplayTreeNode<T>) p.parent;
            } else {
                rotateLeft((SplayTreeNode<T>) p.parent);
                rotateRight((SplayTreeNode<T>) p.parent);
            }
            if (root == null)
                root = p;
        }
    }
}
