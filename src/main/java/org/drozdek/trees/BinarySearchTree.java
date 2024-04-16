package org.drozdek.trees;

import org.drozdek.queues.Queue;
import org.drozdek.stacks.Stack;

import java.io.PrintStream;

/**
 * A binary Search Tree is a node-based binary tree data structure which has the following properties:
 * <p>
 * The left subtree of a node contains only nodes with keys lesser than the node’s key.
 * The right subtree of a node contains only nodes with keys greater than the node’s key.
 * The left and right subtree each must also be a binary search tree.
 * There must be no duplicate nodes.
 * <p>
 * Source: <a href="https://www.geeksforgeeks.org/binary-search-tree-set-1-search-and-insertion/">Geeks for Geeks</a>
 *
 * @author David
 * @version 1.0.0
 */
public class BinarySearchTree<T> {
    protected BinarySearchTreeNode<T> root;

    public BinarySearchTree() {
        root = null;
    }

    public BinarySearchTree(BinarySearchTreeNode<T> root) {
        this.root = root;
    }

    /**
     * Function to convert input BST to right linked list known as vine or backbone.
     *
     * @param grand Grandfather node
     * @return Count
     */
    private static int binarySearchTreeToVine(BinarySearchTreeNode<Integer> grand) {
        int count = 0;

        // Make tmp pointer to traverse and right flatten the given BST.
        BinarySearchTreeNode<Integer> tmp = grand.right;

        // Traverse until tmp becomes NULL
        while (tmp != null) {
            // If left exist for node pointed by tmp then right rotate it.
            if (tmp.left != null) {
                BinarySearchTreeNode<Integer> oldTmp = tmp;
                tmp = tmp.left;
                oldTmp.left = tmp.right;
                tmp.right = oldTmp;
                grand.right = tmp;
            }

            // If left doesn't exist add 1 to count and traverse further right to flatten remaining BST.
            else {
                count++;
                grand = tmp;
                tmp = tmp.right;
            }
        }

        return count;
    }

    /**
     * Function to compress given tree with its root as grand right
     *
     * @param grand Grandfather node
     * @param m
     */
    private static void compress(BinarySearchTreeNode<Integer> grand, int m) {
        // Make tmp pointer to traverse and compress the given BST.
        BinarySearchTreeNode<Integer> tmp = grand.right;

        // Traverse and left-rotate root m times to compress given vine form of BST.
        int i = 0;
        while (i < m) {
            BinarySearchTreeNode<Integer> oldTmp = tmp;
            tmp = tmp.right;
            grand.right = tmp;
            oldTmp.right = tmp.left;
            tmp.left = oldTmp;
            grand = tmp;
            tmp = tmp.right;
            i++;
        }
    }

    /**
     * Function to calculate the log base 2 of an integer
     *
     * @param n An integer x
     * @return Log base of x
     */
    private static int log2(int n) {
        // calculate log2 N indirectly using log() method
        return (int) (Math.log(n) / Math.log(2));
    }

    /**
     * The Day-Stout-Warren algorithm balances a binary search tree. This balancing produces a tree that not only has a
     * minimum height, but also forces all the nodes on the bottommost level to be filled from left to right.
     * The running time is O(n), where 'n' is the number of nodes in tree.
     *
     * @param tree Tree to balance
     * @return A balanced tree
     */
    public static BinarySearchTree<Integer> balanceWithDSW(BinarySearchTree<Integer> tree) {
        BinarySearchTreeNode<Integer> root = tree.root;

        // create dummy node with value 0
        BinarySearchTreeNode<Integer> grand = new BinarySearchTreeNode<>(0);

        // assign the right of dummy node as our input BST
        grand.right = root;

        // get the number of nodes in input BST and simultaneously convert it into right linked list.
        int count = binarySearchTreeToVine(grand);

        // gets the height of tree in which all levels are completely filled.
        int h = log2(count + 1);

        // get number of nodes until second last level
        int m = (int) Math.pow(2, h) - 1;

        // Left rotate for excess nodes at last level
        compress(grand, count - m);

        // Left rotation till m becomes 0 Step is done as mentioned in algo to make BST balanced.
        for (m = m / 2; m > 0; m /= 2) {
            compress(grand, m);
        }

        // return the balanced tree
        return new BinarySearchTree<>(grand.right);
    }

    /**
     * This balance algorithm requires a previously sorted additional array
     *
     * @param data A sorted data array
     */
    public void balanceWithDataArray(Comparable<T>[] data) {
        balanceWithDataArray(data, 0, data.length - 1);
    }

    private void balanceWithDataArray(Comparable<T>[] data, int first, int last) {
        if (first <= last) {
            int middle = (first + last) / 2;
            insert(data[middle]);
            balanceWithDataArray(data, first, middle - 1);
            balanceWithDataArray(data, middle + 1, last);
        }
    }

    public void breadthFirst(PrintStream out) {
        BinarySearchTreeNode<T> p = root;
        Queue<BinarySearchTreeNode<T>> queue = new Queue<>();

        if (p != null) {
            queue.enqueue(p);

            while (!queue.isEmpty()) {

                p = queue.dequeue();

                visit(p, out);

                if (p.left != null)
                    queue.enqueue(p.left);

                if (p.right != null) {
                    queue.enqueue(p.right);
                }
            }
        }
    }

    private int countNodes(BinarySearchTreeNode<T> node) {

        //base case
        if (node == null)
            return 0;

        //recursive call to left child and right child and
        // add the result of these with 1 ( 1 for counting the root)
        return 1 + countNodes(node.left) + countNodes(node.right);
    }

    /**
     * Delete node by copying branches
     *
     * @param data Key to delete
     * @return Operation code
     */
    public int deleteByCopying(T data) {
        BinarySearchTreeNode<T> node;
        BinarySearchTreeNode<T> p;
        BinarySearchTreeNode<T> prev;

        BinarySearchTreeNode<T>[] results = setUpDelete(data);
        p = results[0];
        prev = results[1];
        node = p;

        if (p == null)
            return 1;

        if (p.key != data)
            // 1: empty tree  / -1:  key not found
            return -1;

        if (node.right == null)
            node = node.left;
        else if (node.left == null)
            node = node.right;
        else {
            BinarySearchTreeNode<T>[] aux = moveToRightmostNode(node);

            BinarySearchTreeNode<T> tmp = aux[0];
            BinarySearchTreeNode<T> previous = aux[1];

            node.key = tmp.key;

            if (previous == node)
                previous.left = tmp.left;
            else
                previous.right = tmp.left;
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
     * Delete node by merging branches
     *
     * @param data Key to delete
     * @return Operation code
     */
    public int deleteByMerging(T data) {
        BinarySearchTreeNode<T> tmp;
        BinarySearchTreeNode<T> node;
        BinarySearchTreeNode<T> p;
        BinarySearchTreeNode<T> prev;

        BinarySearchTreeNode<T>[] results = setUpDelete(data);
        p = results[0];
        prev = results[1];
        node = p;

        if (p == null)
            return 1;

        if (p.key != data)
            // 1: empty tree  / -1:  key not found
            return -1;

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
     * Print in-order
     *
     * @param out Printing in order
     */
    public void inorder(PrintStream out) {
        inorder(root, out);
    }

    /**
     * Recursive implementation for the in-order tree path
     *
     * @param p   Node to print
     * @param out Print stream
     */
    protected void inorder(BinarySearchTreeNode<T> p, PrintStream out) {
        if (p == null)
            return;

        inorder(p.left, out);
        visit(p, out);
        inorder(p.right, out);
    }

    /**
     * Insert a new node in the tree.
     *
     * @param data Value to insert in the tree
     */
    public void insert(Comparable<T> data) {
        BinarySearchTreeNode<T> p = root;
        BinarySearchTreeNode<T> previous = null;

        while (p != null) {
            previous = p;

            if (p.key.compareTo((T) data) < 0) // should be <
                p = p.right;
            else
                p = p.left;
        }

        if (root == null)
            root = new BinarySearchTreeNode<>(data);
        else if (previous.key.compareTo((T) data) < 0)
            previous.right = new BinarySearchTreeNode<>(data);
        else
            previous.left = new BinarySearchTreeNode<>(data);
    }

    /**
     * Non-recursive implementation for the in-order tree path
     *
     * @param out
     */
    public void iterativeInorder(PrintStream out) {
        BinarySearchTreeNode<T> p = root;
        Stack<BinarySearchTreeNode<T>> stack = new Stack<>();

        while (p != null) {
            while (p != null) {
                // stacks the right child (if any) and the same node when it moves to the left
                if (p.right != null)
                    stack.push(p.right);

                stack.push(p);
                p = p.left;
            }

            // extracts a node without left child
            p = stack.pop();

            // visits the node and all the nodes without right child
            while (!stack.isEmpty() && p.right == null) {
                visit(p, out);
                p = stack.pop();
            }

            // visits the first node with a right child (if any)
            visit(p, out);
            p = !stack.isEmpty() ? stack.pop() : null;
        }
    }

    /**
     * Non-recursive implementation for the post-order tree path
     *
     * @param out
     */
    public void iterativePostorder(PrintStream out) {
        BinarySearchTreeNode<T> p = root;
        BinarySearchTreeNode<T> q = root;
        Stack<BinarySearchTreeNode<T>> stack = new Stack<>();

        while (p != null) {
            while (p.left != null) {
                stack.push(p);
                p = p.left;
            }

            while (p.right == null || p.left == q) {
                visit(p, out);
                q = p;

                if (stack.isEmpty())
                    return;

                p = stack.pop();
            }

            stack.push(p);
            p = p.right;

        }
    }

    /**
     * Non-recursive implementation for the pre-order tree path
     *
     * @param out
     */
    public void iterativePreorder(PrintStream out) {
        BinarySearchTreeNode<T> p = root;
        Stack<BinarySearchTreeNode<T>> stack = new Stack<>();

        if (p == null)
            return;

        stack.push(p);

        while (!stack.isEmpty()) {
            p = stack.pop();
            visit(p, out);

            if (p.right != null)
                stack.push(p.right);

            // the leftmost child is inserted after the rightmost child so that the leftmost child is at the top
            // of the stack
            if (p.left != null)
                stack.push(p.left);
        }
    }

    /**
     * Morris (In-Order) traversal is a tree traversal algorithm that does not employ the use of recursion or a stack.
     * In this traversal, links are created as successors and nodes are printed using these links. Finally, the changes
     * are rolled back to restore the original tree.
     */
    public void morrisInOrder(PrintStream out) {
        BinarySearchTreeNode<T> p = root;
        BinarySearchTreeNode<T> tmp;

        while (p != null) {
            if (p.left == null) {
                visit(p, out);
                p = p.right;
            } else {
                tmp = p.left;

                // moves to the rightmost node in left subtree, otherwise moves to the p temporal parent
                while (tmp.right != null && tmp.right != p)
                    tmp = tmp.right;

                // if the rightmost node was reached, it turns in a temporal parent of the current root
                if (tmp.right == null) {
                    tmp.right = p;
                    p = p.left;
                } else {
                    // Otherwise it reached a temporal parent, visits p node and then cuts the current parent's
                    // right pointer, whereby it ceases to be a father
                    visit(p, out);
                    tmp.right = null;
                    p = p.right;
                }
            }
        }
    }

    /**
     * Morris (Post-Order) traversal is a tree traversal algorithm that does not employ the use of recursion or a stack.
     * In this traversal, links are created as successors and nodes are printed using these links. Finally, the changes
     * are rolled back to restore the original tree.
     */
    public void morrisPostOrder(PrintStream out) {
        BinarySearchTreeNode<T> p = root;
        BinarySearchTreeNode<T> tmp;

        while (p != null) {
            if (p.right == null) {
                visit(p, out);
                p = p.left;
            } else {
                tmp = p.right;

                // moves to the rightmost node in left subtree, otherwise moves to the p temporal parent
                while (tmp.left != null && tmp.left != p)
                    tmp = tmp.left;

                // if the rightmost node was reached, it turns in a temporal parent of the current root
                if (tmp.left == null) {
                    // Node is visited after the tree transformation
                    tmp.left = p;
                    p = p.right;
                    visit(p, out);
                } else {
                    tmp.left = null;
                    p = p.left;
                }
            }
        }
    }

    /**
     * Morris (Pre-Order) traversal is a tree traversal algorithm that does not employ the use of recursion or a stack.
     * In this traversal, links are created as successors and nodes are printed using these links. Finally, the changes
     * are rolled back to restore the original tree.
     */
    public void morrisPreOrder(PrintStream out) {
        BinarySearchTreeNode<T> p = root;
        BinarySearchTreeNode<T> tmp;

        while (p != null) {
            if (p.left == null) {
                visit(p, out);
                p = p.right;
            } else {
                tmp = p.left;

                // moves to the rightmost node in left subtree, otherwise moves to the p temporal parent
                while (tmp.right != null && tmp.right != p)
                    tmp = tmp.right;

                // if the rightmost node was reached, it turns in a temporal parent of the current root
                if (tmp.right == null) {
                    // Node is visited before the tree transformation
                    visit(p, out);
                    tmp.right = p;
                    p = p.left;
                } else {
                    tmp.right = null;
                    p = p.right;
                }
            }
        }
    }

    private BinarySearchTreeNode<T>[] moveToRightmostNode(BinarySearchTreeNode<T> node) {
        BinarySearchTreeNode<T>[] results = new BinarySearchTreeNode[]{node.left, node};

        while (results[0].right != null) {
            results[1] = results[0];
            results[0] = results[0].right;
        }

        return results;
    }

    public void postorder(PrintStream out) {
        postorder(root, out);
    }

    /**
     * Recursive implementation for the post-order tree path
     *
     * @param p
     * @param out
     */
    protected void postorder(BinarySearchTreeNode<T> p, PrintStream out) {
        if (p == null)
            return;

        preorder(p.left, out);
        preorder(p.right, out);
        visit(p, out);
    }

    public void preorder(PrintStream out) {

        preorder(root, out);
    }

    /**
     * Recursive implementation for the pre-order tree path
     *
     * @param p
     * @param out
     */
    protected void preorder(BinarySearchTreeNode<T> p, PrintStream out) {
        if (p == null)
            return;

        visit(p, out);
        preorder(p.left, out);
        preorder(p.right, out);
    }

    public Comparable<T> search(BinarySearchTreeNode<T> p, Comparable<T> element) {
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

    private BinarySearchTreeNode<T>[] setUpDelete(T key) {
        BinarySearchTreeNode<T>[] vars = new BinarySearchTreeNode[2];
        vars[0] = root;
        vars[1] = null;

        while (vars[0] != null && vars[0].key != key) {
            vars[1] = vars[0];

            if (vars[0].key.compareTo(key) < 0)
                vars[0] = vars[0].right;
            else
                vars[0] = vars[0].left;
        }

        return vars;
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

    protected void visit(BinarySearchTreeNode<T> p, PrintStream out) {
        out.println(p.key + " ");
    }
}
