package trees;

import org.drozdek.trees.AvlTree;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AvlTreeTest {

    AvlTree tree;

    private void dumpData() {
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.insert(40);
        tree.insert(50);
        tree.insert(25);
    }

    @Test
    @DisplayName("Create a balanced tree and print in order")
    void inOrder() {
        /* Constructing tree given in the above figure */
        dumpData();

        System.out.println("In-order traversal" +
                " of constructed tree is : ");

        tree.inOrder();
        assertEquals(6, tree.size(), "The size of the tree doesn't match with the expected");
    }

    @Test
    @DisplayName("Create a balanced tree and print in post-order")
    void postOrder() {
        /* Constructing tree given in the above figure */
        dumpData();

        System.out.println("Post-order traversal" +
                " of constructed tree is : ");

        tree.postOrder();
        assertEquals(6, tree.size(), "The size of the tree doesn't match with the expected");
    }

    @Test
    @DisplayName("Create a balanced tree and print in pre-order")
    void preOrder() {
        /* Constructing tree given in the above figure */
        dumpData();

        /* The constructed AVL Tree would be
             30
            /  \
          20   40
         /  \     \
        10  25    50
        */
        System.out.println("Preorder traversal" +
                " of constructed tree is : ");

        tree.preOrder();
        assertEquals(6, tree.size(), "The size of the tree doesn't match with the expected");
    }

    @BeforeEach
    void setUp() {
        tree = new AvlTree();
    }

    @Test
    @DisplayName("Create a balanced tree and print his structure")
    void treeToString() {
        dumpData();
        System.out.println(tree);
        assertEquals(6, tree.size(), "The size of the tree doesn't match with the expected");
    }
}
