package trees;

import org.drozdek.trees.HeapNode;
import org.drozdek.trees.StaticBinaryTree;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.*;

class StaticBinaryTreeTest {

    StaticBinaryTree tree;

    @BeforeEach
    void setUp() {
        tree = new StaticBinaryTree();
    }

    @Test
    @DisplayName("New tree should be empty")
    void isEmpty() {
        assertTrue(tree.isEmpty());
    }

    @Test
    @DisplayName("Set root and verify not empty")
    void setRoot() {
        tree.setRoot(new HeapNode(10));
        assertFalse(tree.isEmpty());
    }

    @Test
    @DisplayName("Set children and verify size")
    void setChildren() {
        tree.setRoot(new HeapNode(1));
        tree.setLeftChild(0, new HeapNode(2));
        tree.setRightChild(0, new HeapNode(3));
        assertEquals(3, tree.size());
    }

    @Test
    @DisplayName("Constructor with root node")
    void constructorWithRoot() {
        StaticBinaryTree t = new StaticBinaryTree(new HeapNode(42));
        assertFalse(t.isEmpty());
    }

    @Test
    @DisplayName("Leftmost and rightmost child")
    void leftmostRightmostChild() {
        tree.setRoot(new HeapNode(1));
        tree.setRightChild(0, new HeapNode(2));
        tree.setLeftChild(1, new HeapNode(3));
        assertNotNull(tree.leftmostChild());
        assertNotNull(tree.rightmostChild());
    }
}
