package org.drozdek.trees;

public class StaticBinaryTree {

    private static final int CAPACITY = 10;
    private final HeapNode[] tree;
    private int size;

    public StaticBinaryTree() {
        tree = new HeapNode[CAPACITY];
        size = 0;
    }

    public StaticBinaryTree(HeapNode root) {
        tree = new HeapNode[CAPACITY];
        tree[0] = root;
        size = 1;
    }

    public boolean setRightChild(int parent, HeapNode node) {
        try {
            if (((parent * 2) + 1) <= CAPACITY - 1 && !isFull()) {
                tree[(parent * 2) + 1] = node;
                size++;
                return true;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            return false;
        }

        return false;
    }

    public boolean setLeftChild(int parent, HeapNode node) {
        try {
            if ((parent * 2) <= CAPACITY - 1 && !isFull()) {
                tree[parent * 2] = node;
                size++;
                return true;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            return false;
        }

        return false;
    }

    public boolean setRightmostChild(int parent, HeapNode node) {
        try {
            if ((size - 1) % 2 != 0)
                tree[size - 1] = node;
            else
                tree[size - 2] = node;
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean setLeftmostChild(int parent, HeapNode node) {
        try {
            if ((size - 1) % 2 == 0)
                tree[size - 1] = node;
            else
                tree[size - 2] = node;
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public void setRoot(HeapNode node) {
        if (tree[0] == null && node != null) {
            tree[0] = node;
            size++;
        }
    }

    public boolean isFull() {
        return size == (CAPACITY - 1);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public HeapNode leftChild(int parent, HeapNode node) {
        try {
            return tree[(parent * 2) + 1];
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Object rightmostChild() {
        if ((size - 1) % 2 != 0)
            return tree[size - 1];
        else
            return tree[size - 2];
    }

    public Object leftmostChild() {
        if ((size - 1) % 2 == 0)
            return tree[size - 1];
        else
            return tree[size - 2];
    }

    public Object leftmostChild(int node) {
        if ((size - 1) % 2 == 0)
            return tree[size - 1];
        else
            return tree[size - 2];
    }
}
