package trees;

import org.drozdek.trees.IntThreadedTree;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IntThreadedTreeTest {
    IntThreadedTree tree;

    @BeforeEach
    void setUp() {
        tree = new IntThreadedTree();
    }

    @Test
    @DisplayName("After inserting eight elements, the size of the tree should be the expected")
    void insert() {
        tree.insert(3);
        tree.insert(5);
        tree.insert(6);
        tree.insert(54);
        tree.insert(526);
        tree.insert(65);
        tree.insert(11);
        tree.insert(84);

        assertEquals(8,tree.size(), "The size is not the expected");
    }

    @Test
    @DisplayName("The tree should be printed in order")
    void threadInOrder() {
        tree.insert(3);
        tree.insert(5);
        tree.insert(6);
        tree.insert(21);
        tree.insert(65);
        tree.insert(19);

        tree.threadInOrder(System.out);


        assertEquals(6, tree.size(), "The tree size is not the expected");
    }

    @Test
    @DisplayName("The tree should be printed in order")
    void printInOrder() {
        tree.insert(3);
        tree.insert(5);
        tree.insert(6);
        tree.insert(21);
        tree.insert(65);
        tree.insert(19);

        tree.printInOrder(System.out);


        assertEquals(6, tree.size(), "The tree size is not the expected");
    }
}
