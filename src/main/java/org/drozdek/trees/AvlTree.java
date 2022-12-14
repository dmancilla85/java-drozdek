package org.drozdek.trees;

/**
 * AVL tree is a self-balancing Binary Search Tree (BST) where the difference between heights of left and right
 * subtrees cannot be more than one for all nodes.
 * <p>
 * Complexity Analysis:
 * Time Complexity: O(n*log(n)), For Insertion
 * Auxiliary Space: O(1)
 * <p>
 * Source: <a href="https://www.geeksforgeeks.org/insertion-in-an-avl-tree/">Geeks for Geeks</a>
 */
public class AvlTree {

    AvlTreeNode root;

    // Get Balance factor of node N
    private int getBalance(AvlTreeNode node) {
        if (node == null)
            return 0;

        return height(node.left) - height(node.right);
    }

    private int countNodes(AvlTreeNode node) {

        //base case
        if (node == null)
            return 0;

        //recursive call to left child and right child and
        // add the result of these with 1 ( 1 for counting the root)
        return 1 + countNodes(node.left) + countNodes(node.right);
    }

    public int size() {
        return countNodes(root);
    }

    // A utility function to get the height of the tree
    public int height(AvlTreeNode n) {
        if (n == null)
            return 0;

        return n.height;
    }

    public void insert(int key) {
        root = insertNode(root, key);
    }

    private AvlTreeNode insertNode(AvlTreeNode node, int key) {

        /* 1.  Perform the normal BST insertion */
        if (node == null)
            return (new AvlTreeNode(key));

        if (key < node.key)
            node.left = insertNode(node.left, key);
        else if (key > node.key)
            node.right = insertNode(node.right, key);
        else // Duplicate keys not allowed
            return node;

        /* 2. Update height of this ancestor node */
        node.height = 1 + Math.max(height(node.left), height(node.right));

        /* 3. Get the balance factor of this ancestor
              node to check whether this node became
              unbalanced */
        int balance = getBalance(node);

        // If this node becomes unbalanced, then there
        // are 4 cases  left-left Case
        if (balance > 1 && key < node.left.key)
            return rightRotate(node);

        // right-right Case
        if (balance < -1 && key > node.right.key)
            return leftRotate(node);

        // left-right Case
        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left Case
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        /* return the (unchanged) node pointer */
        return node;
    }

    // A utility function to left rotate subtree rooted with x
    // See the diagram given above.
    private AvlTreeNode leftRotate(AvlTreeNode a) {
        AvlTreeNode b = a.right;
        AvlTreeNode t2 = b.left;

        // Perform rotation
        b.left = a;
        a.right = t2;

        //  Update heights
        a.height = Math.max(height(a.left), height(a.right)) + 1;
        b.height = Math.max(height(b.left), height(b.right)) + 1;

        // Return new root
        return b;
    }

    // A utility function to print preorder traversal
    // of the tree.
    // The function also prints height of every node
    private String preOrder(AvlTreeNode node) {

        StringBuilder tree = new StringBuilder();

        if (node != null) {
            tree.append(node.key);
            tree.append(" ");
            tree.append(preOrder(node.left));
            tree.append(preOrder(node.right));
        }

        return tree.toString();
    }

    // A utility function to print preorder traversal
    // of the tree.
    // The function also prints height of every node
    private String inOrder(AvlTreeNode node) {

        StringBuilder tree = new StringBuilder();
        
        if (node != null) {
            tree.append(inOrder(node.left));
            tree.append(node.key);
            tree.append(" ");
            tree.append(inOrder(node.right));
        }

        return tree.toString();
    }

    // A utility function to print preorder traversal
    // of the tree.
    // The function also prints height of every node
    private String postOrder(AvlTreeNode node) {

        StringBuilder tree = new StringBuilder();
        
        if (node != null) {
            tree.append(postOrder(node.left));
            tree.append(postOrder(node.right));
            tree.append(node.key);
            tree.append(" ");
        }
        
        return tree.toString();
    }

    @Override
    public String toString(){
        return root.toString();
    }

    public String preOrder(){
        return preOrder(root);
    }

    public String postOrder(){
        return postOrder(root);
    }

    public String inOrder(){
        return inOrder(root);
    }

    // A utility function to right rotate subtree rooted with y
    // See the diagram given above.
    private AvlTreeNode rightRotate(AvlTreeNode b) {
        AvlTreeNode a = b.left;
        AvlTreeNode t2 = a.right;

        // Perform rotation
        a.right = b;
        b.left = t2;

        // Update heights
        b.height = Math.max(height(b.left),
                height(b.right)) + 1;
        a.height = Math.max(height(a.left),
                height(a.right)) + 1;

        // Return new root
        return a;
    }
}
