package org.drozdek.trees;

import org.drozdek.trees.nodes.AvlTreeNode;

import org.drozdek.trees.interfaces.TreeInterface;

/// AVL tree is a self-balancing Binary Search Tree (BST) where the difference between heights of left and right
/// subtrees cannot be more than one for all nodes.
///
/// **Real-world use case:** In-memory database indexing where frequent lookups must
/// remain fast despite insertions and deletions.
///
/// Complexity Analysis:
/// Time Complexity: O(log n) for search, insert, and delete
/// Auxiliary Space: O(log n) for recursion stack
///
/// Bibliography:
///
/// - Thomas H. Cormen et al. *Introduction to Algorithms*, 4th ed. MIT Press.
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 6.
public class AvlTree implements TreeInterface {

    AvlTreeNode root;

    private int countNodes(AvlTreeNode node) {

        //base case
        if (node == null)
            return 0;

        //recursive call to left child and right child and
        // add the result of these with 1 ( 1 for counting the root)
        return 1 + countNodes(node.getLeft()) + countNodes(node.getRight());
    }

    // Get Balance factor of node N
    private int getBalance(AvlTreeNode node) {
        if (node == null)
            return 0;

        return height(node.getLeft()) - height(node.getRight());
    }

    /// Returns the stored height of the given node.
    ///
    /// @param n node whose height is queried
    /// @return node height, or 0 when the node is null
    public int height(AvlTreeNode n) {
        if (n == null)
            return 0;

        return n.getHeight();
    }

    // A utility function to print preorder traversal
    // of the tree.
    // The function also prints height of every node
    private String inOrder(AvlTreeNode node) {

        StringBuilder tree = new StringBuilder();

        if (node != null) {
            tree.append(inOrder(node.getLeft()));
            tree.append(node.getKey());
            tree.append(" ");
            tree.append(inOrder(node.getRight()));
        }

        return tree.toString();
    }

    /// Returns the keys in in-order (ascending) separated by spaces.
    ///
    /// @return space-separated key listing, or an empty string for an empty tree
    public String inOrder() {
        return inOrder(root);
    }

    /// Inserts a key, rebalancing the ancestors path on the way out.
    ///
    /// Duplicate keys are ignored. Runs in O(log n).
    ///
    /// @param key value to insert
    public void insert(int key) {
        root = insertNode(root, key);
    }

    private AvlTreeNode insertNode(AvlTreeNode node, int key) {

        /* 1.  Perform the normal BST insertion */
        if (node == null)
            return new AvlTreeNode(key);

        if (key < node.getKey())
            node.setLeft( insertNode(node.getLeft(), key));
        else if (key > node.getKey())
            node.setRight( insertNode(node.getRight(), key));
        else // Duplicate keys not allowed
            return node;

        /* 2. Update height of this ancestor node */
        node.setHeight( 1 + Math.max(height(node.getLeft()), height(node.getRight())));

        /* 3. Get the balance factor of this ancestor
              node to check whether this node became
              unbalanced */
        int balance = getBalance(node);

        // If this node becomes unbalanced, then there
        // are 4 cases  left-left Case
        if (balance > 1 && key < node.getLeft().getKey())
            return rightRotate(node);

        // right-right Case
        if (balance < -1 && key > node.getRight().getKey())
            return leftRotate(node);

        // left-right Case
        if (balance > 1 && key > node.getLeft().getKey()) {
            node.setLeft( leftRotate(node.getLeft()));
            return rightRotate(node);
        }

        // Right Left Case
        if (balance < -1 && key < node.getRight().getKey()) {
            node.setRight( rightRotate(node.getRight()));
            return leftRotate(node);
        }

        /* return the (unchanged) node pointer */
        return node;
    }

    // A utility function to left rotate subtree rooted with x
    // See the diagram given above.
    private AvlTreeNode leftRotate(AvlTreeNode a) {
        AvlTreeNode b = a.getRight();
        AvlTreeNode t2 = b.getLeft();

        // Perform rotation
        b.setLeft( a);
        a.setRight( t2);

        //  Update heights
        a.setHeight( Math.max(height(a.getLeft()), height(a.getRight())) + 1);
        b.setHeight( Math.max(height(b.getLeft()), height(b.getRight())) + 1);

        // Return new root
        return b;
    }

    // A utility function to print preorder traversal
    // of the tree.
    // The function also prints height of every node
    private String postOrder(AvlTreeNode node) {

        StringBuilder tree = new StringBuilder();

        if (node != null) {
            tree.append(postOrder(node.getLeft()));
            tree.append(postOrder(node.getRight()));
            tree.append(node.getKey());
            tree.append(" ");
        }

        return tree.toString();
    }

    /// Returns the keys in post-order (left, right, node) separated by spaces.
    ///
    /// @return space-separated key listing, or an empty string for an empty tree
    public String postOrder() {
        return postOrder(root);
    }

    // A utility function to print preorder traversal
    // of the tree.
    // The function also prints height of every node
    private String preOrder(AvlTreeNode node) {

        StringBuilder tree = new StringBuilder();

        if (node != null) {
            tree.append(node.getKey());
            tree.append(" ");
            tree.append(preOrder(node.getLeft()));
            tree.append(preOrder(node.getRight()));
        }

        return tree.toString();
    }

    /// Returns the keys in pre-order (node, left, right) separated by spaces.
    ///
    /// @return space-separated key listing, or an empty string for an empty tree
    public String preOrder() {
        return preOrder(root);
    }

    // A utility function to right rotate subtree rooted with y
    // See the diagram given above.
    private AvlTreeNode rightRotate(AvlTreeNode b) {
        AvlTreeNode a = b.getLeft();
        AvlTreeNode t2 = a.getRight();

        // Perform rotation
        a.setRight( b);
        b.setLeft( t2);

        // Update heights
        b.setHeight( Math.max(height(b.getLeft()),
                height(b.getRight())) + 1);
        a.setHeight( Math.max(height(a.getLeft()),
                height(a.getRight())) + 1);

        // Return new root
        return a;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return countNodes(root);
    }

    @Override
    public String toString() {
        return root.toString();
    }
}
