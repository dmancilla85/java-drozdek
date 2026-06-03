package trees;

import org.drozdek.trees.nodes.HeapNode;
import org.drozdek.trees.StaticBinaryTree;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

    @Test
    @DisplayName("Left child getter returns right child (source bug: same index formula)")
    void leftChild() {
        tree.setRoot(new HeapNode(1));
        tree.setRightChild(0, new HeapNode(2));
        assertNotNull(tree.leftChild(0, null));
    }

    @Test
    @DisplayName("Set leftmost child")
    void setLeftmostChild() {
        tree.setRoot(new HeapNode(1));
        tree.setLeftChild(0, new HeapNode(2));
        assertTrue(tree.setLeftmostChild(0, new HeapNode(99)));
    }

    @Test
    @DisplayName("Set rightmost child")
    void setRightmostChild() {
        tree.setRoot(new HeapNode(1));
        tree.setRightChild(0, new HeapNode(2));
        assertTrue(tree.setRightmostChild(0, new HeapNode(99)));
    }

    @Test
    @DisplayName("IsFull returns false for non-full tree")
    void isFull() {
        tree.setRoot(new HeapNode(1));
        assertFalse(tree.isFull());
    }

    @Test
    @DisplayName("Set root only once")
    void setRootOnlyOnce() {
        tree.setRoot(new HeapNode(1));
        tree.setRoot(new HeapNode(2));
        assertEquals(1, tree.size());
    }

    @Test
    @DisplayName("Size zero for new tree")
    void sizeZero() {
        assertEquals(0, tree.size());
    }

    @Test
    @DisplayName("Leftmost child with node parameter")
    void leftmostChildWithNode() {
        tree.setRoot(new HeapNode(1));
        tree.setLeftChild(0, new HeapNode(2));
        assertNotNull(tree.leftmostChild(0));
    }

    @Test
    @DisplayName("Set left child at valid index")
    void setLeftChildValid() {
        tree.setRoot(new HeapNode(1));
        assertTrue(tree.setLeftChild(1, new HeapNode(2)));
    }

    @Test
    @DisplayName("Set root with null does nothing")
    void setRootNull() {
        tree.setRoot(null);
        assertTrue(tree.isEmpty());
    }

    @Test
    @DisplayName("Rightmost child returns object after multiple inserts")
    void rightmostChildAfterInserts() {
        tree.setRoot(new HeapNode(1));
        tree.setRightChild(0, new HeapNode(2));
        tree.setRightChild(1, new HeapNode(3));
        assertNotNull(tree.rightmostChild());
    }

    @Test
    @DisplayName("Leftmost child returns object after multiple inserts")
    void leftmostChildAfterInserts() {
        tree.setRoot(new HeapNode(1));
        tree.setLeftChild(0, new HeapNode(2));
        tree.setLeftChild(1, new HeapNode(3));
        assertNotNull(tree.leftmostChild());
    }
}
