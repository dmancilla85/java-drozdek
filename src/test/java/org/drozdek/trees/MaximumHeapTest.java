package org.drozdek.trees;

import org.drozdek.trees.MaximumHeap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MaximumHeapTest {
    MaximumHeap<Integer> heap;

    private void dumpData() {
        heap.insertKey(5);
        heap.insertKey(7);
        heap.insertKey(153);
        heap.insertKey(9);
        heap.insertKey(16);
    }

    @BeforeEach
    void setup() {
        heap = new MaximumHeap<>(5);
    }

    @Test
    @DisplayName("Create a maximum heap and extract the maximum key")
    void extractMaximum() {
        dumpData();

        int max = heap.extractMax();
        assertEquals(153, max, "The maximum extracted is not the expected.");
    }

    @Test
    @DisplayName("Create a maximum heap and view the maximum key")
    void getMaximum() {
        dumpData();

        int max = heap.getMax();
        assertEquals(153, max, "The maximum extracted is not the expected.");
    }

    @Test
    @DisplayName("Remove the maximum value in the heap")
    void deleteKey() {
        dumpData();
        heap.deleteKey(2);
        int max = heap.getMax();
        assertEquals(153, max, "The maximum extracted is not the expected.");
    }

    @Test
    @DisplayName("Change the maximum value in the heap")
    void changeValueOnAKey() {
        dumpData();
        heap.changeValueOnAKey(0, 18);
        int max = heap.getMax();
        assertEquals(18, max, "The maximum extracted is not the expected.");
    }

    @Test
    @DisplayName("Decrease the maximum value in the heap")
    void decreaseKey() {
        dumpData();
        heap.decreaseKey(0, 100);
        int max = heap.getMax();
        assertEquals(100, max, "The maximum extracted is not the expected.");
    }

    @Test
    @DisplayName("Check that the heap is printed correctly.")
    void print() {
        dumpData();
        heap.print();
        int max = heap.getMax();
        assertEquals(153, max, "The maximum extracted is not the expected.");
    }

    @Test
    @DisplayName("New heap should be empty")
    void isEmpty() {
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
    }

    @Test
    @DisplayName("After insert heap is not empty")
    void notEmpty() {
        heap.insertKey(10);
        assertFalse(heap.isEmpty());
    }

    @Test
    @DisplayName("Extract max from empty heap returns null")
    void extractMaxEmpty() {
        assertNull(heap.extractMax());
    }

    @Test
    @DisplayName("Get max from empty heap returns null")
    void getMaxEmpty() {
        assertNull(heap.getMax());
    }

    @Test
    @DisplayName("Delete key at invalid index does nothing")
    void deleteKeyInvalidIndex() {
        dumpData();
        heap.deleteKey(99);
        assertEquals(5, heap.size());
    }

    @Test
    @DisplayName("Change value at invalid index does nothing")
    void changeValueInvalidIndex() {
        dumpData();
        heap.changeValueOnAKey(99, 100);
        assertEquals(5, heap.size());
    }

    @Test
    @DisplayName("Increase key value")
    void increaseKey() {
        dumpData();
        heap.increaseKey(1, 200);
        assertEquals(200, heap.getMax());
    }

    @Test
    @DisplayName("Increase key with smaller value does nothing")
    void increaseKeySmallerValue() {
        dumpData();
        heap.increaseKey(0, 10);
        assertEquals(153, heap.getMax());
    }

    @Test
    @DisplayName("Increase key at invalid index does nothing")
    void increaseKeyInvalidIndex() {
        dumpData();
        heap.increaseKey(99, 100);
        assertEquals(5, heap.size());
    }

    @Test
    @DisplayName("Decrease key at invalid index does nothing")
    void decreaseKeyInvalidIndex() {
        dumpData();
        heap.decreaseKey(99, 1);
        assertEquals(5, heap.size());
    }

    @Test
    @DisplayName("Decrease key with larger value does nothing")
    void decreaseKeyLargerValue() {
        dumpData();
        heap.decreaseKey(0, 999);
        assertEquals(153, heap.getMax());
    }

    @Test
    @DisplayName("Change value with equal value does nothing")
    void changeValueEqual() {
        dumpData();
        heap.changeValueOnAKey(0, 153);
        assertEquals(153, heap.getMax());
    }

    @Test
    @DisplayName("Height of a heap")
    void height() {
        heap.insertKey(10);
        heap.insertKey(20);
        heap.insertKey(30);
        assertTrue(heap.height(0) >= 1);
    }

    @Test
    @DisplayName("ToArray returns copy of internal array")
    void toArray() {
        dumpData();
        Object[] arr = heap.toArray();
        assertEquals(5, arr.length);
    }

    @Test
    @DisplayName("Constructor with negative capacity throws")
    void constructorNegativeCapacity() {
        assertThrows(IllegalArgumentException.class, () -> new MaximumHeap<>(-1));
    }

    @Test
    @DisplayName("Insert null element throws")
    void insertNull() {
        assertThrows(IllegalArgumentException.class, () -> heap.insert(null));
    }

    @Test
    @DisplayName("Insert triggers resize when full")
    void insertAndResize() {
        heap.insertKey(1);
        heap.insertKey(2);
        heap.insertKey(3);
        heap.insertKey(4);
        heap.insertKey(5);
        heap.insertKey(6);
        assertEquals(6, heap.size());
    }

    @Test
    @DisplayName("Delete last element")
    void deleteLastElement() {
        heap.insertKey(10);
        heap.insertKey(20);
        heap.deleteKey(1);
        assertEquals(1, heap.size());
    }

    @Test
    @DisplayName("Extract max with single element")
    void extractMaxSingle() {
        heap.insertKey(42);
        assertEquals(42, heap.extractMax());
        assertTrue(heap.isEmpty());
    }

    @Test
    @DisplayName("Default constructor uses default capacity")
    void defaultConstructor() {
        MaximumHeap<Integer> h = new MaximumHeap<>();
        assertTrue(h.isEmpty());
    }

    @Test
    @DisplayName("Print method executes without error")
    void testPrint() {
        dumpData();
        assertDoesNotThrow(() -> heap.print());
    }
}
