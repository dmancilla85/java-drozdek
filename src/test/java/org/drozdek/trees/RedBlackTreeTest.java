package org.drozdek.trees;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Red-Black Tree Tests")
class RedBlackTreeTest {
    private RedBlackTree<Integer> tree;

    @BeforeEach
    void setUp() {
        tree = new RedBlackTree<>();
    }

    @Test
    @DisplayName("New tree is empty")
    void newTreeIsEmpty() {
        assertTrue(tree.isEmpty());
        assertEquals(0, tree.size());
    }

    @Test
    @DisplayName("Insert single element")
    void insertSingle() {
        tree.insert(5);
        assertFalse(tree.isEmpty());
        assertEquals(1, tree.size());
        assertEquals(Integer.valueOf(5), tree.search(5));
    }

    @Test
    @DisplayName("Insert multiple elements and search")
    void insertMultipleAndSearch() {
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);
        tree.insert(3);
        tree.insert(7);

        assertEquals(Integer.valueOf(10), tree.search(10));
        assertEquals(Integer.valueOf(3), tree.search(3));
        assertEquals(Integer.valueOf(7), tree.search(7));
        assertNull(tree.search(99));
        assertEquals(5, tree.size());
    }

    @Test
    @DisplayName("In-order traversal produces sorted sequence")
    void inOrderIsSorted() {
        tree.insert(50);
        tree.insert(30);
        tree.insert(70);
        tree.insert(20);
        tree.insert(40);
        tree.insert(60);
        tree.insert(80);

        assertEquals("20 30 40 50 60 70 80", tree.inOrder());
    }

    @Test
    @DisplayName("Minimum returns smallest key")
    void minimum() {
        tree.insert(9);
        tree.insert(5);
        tree.insert(12);
        tree.insert(1);
        assertEquals(Integer.valueOf(1), tree.minimum());
    }

    @Test
    @DisplayName("Maximum returns largest key")
    void maximum() {
        tree.insert(9);
        tree.insert(5);
        tree.insert(12);
        tree.insert(1);
        assertEquals(Integer.valueOf(12), tree.maximum());
    }

    @Test
    @DisplayName("Minimum and maximum on empty tree")
    void minMaxEmpty() {
        assertNull(tree.minimum());
        assertNull(tree.maximum());
    }

    @Test
    @DisplayName("Tree stays balanced after ascending insertions")
    void balancedAfterAscending() {
        for (int i = 1; i <= 100; i++) {
            tree.insert(i);
        }
        assertEquals(100, tree.size());
        assertEquals(Integer.valueOf(1), tree.minimum());
        assertEquals(Integer.valueOf(100), tree.maximum());
        assertEquals(Integer.valueOf(50), tree.search(50));
    }

    @Test
    @DisplayName("Search on empty tree returns null")
    void searchEmpty() {
        assertNull(tree.search(42));
    }
}
