package org.drozdek.trees;

import org.drozdek.trees.HeapArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeapArrayTest {

    HeapArray heap;

    @BeforeEach
    void setUp() {
        heap = new HeapArray();
    }

    @Test
    @DisplayName("Insert values and display")
    void insertAndDisplay() {
        heap.insert(5);
        heap.insert(3);
        heap.insert(8);
        assertNotNull(heap.display());
        assertTrue(heap.display().contains("8"));
    }

    @Test
    @DisplayName("Verify left and right child positions")
    void childPositions() {
        assertEquals(0, heap.leftChild(0));
        assertEquals(1, heap.rightChild(0));
        assertEquals(2, heap.leftChild(1));
        assertEquals(3, heap.rightChild(1));
    }

    @Test
    @DisplayName("Run heap sort without error")
    void heapSort() {
        for (int i = 10; i >= 1; i--)
            heap.insert(i);
        heap.heapSort();
        assertNotNull(heap.display());
    }

    @Test
    @DisplayName("Print method executes without error")
    void testPrint() {
        heap.insert(5);
        heap.insert(3);
        heap.insert(8);
        assertDoesNotThrow(() -> heap.print());
    }
}
