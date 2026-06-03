package trees;

import org.drozdek.trees.MinimumHeap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MinimumHeapTest {
    MinimumHeap<Integer> heap;

    private void dumpData() {
        heap.insertKey(15);
        heap.insertKey(7);
        heap.insertKey(3);
        heap.insertKey(9);
        heap.insertKey(16);
    }

    @BeforeEach
    void setup() {
        heap = new MinimumHeap<>(5);
    }

    @Test
    @DisplayName("Extract minimum key")
    void extractMinimum() {
        dumpData();

        int min = heap.extractMin();
        assertEquals(3, min, "The minimum extracted is not the expected.");
    }

    @Test
    @DisplayName("View minimum key")
    void getMinimum() {
        dumpData();

        int min = heap.getMin();
        assertEquals(3, min, "The minimum is not the expected.");
    }

    @Test
    @DisplayName("Remove a key")
    void deleteKey() {
        dumpData();
        heap.deleteKey(2);
        int min = heap.getMin();
        assertEquals(3, min, "The minimum after deletion is not the expected.");
    }

    @Test
    @DisplayName("Change value on a key increases value")
    void changeValueIncrease() {
        dumpData();
        heap.changeValueOnAKey(2, 20);
        int min = heap.getMin();
        assertEquals(3, min);
    }

    @Test
    @DisplayName("Decrease key value")
    void decreaseKey() {
        dumpData();
        heap.decreaseKey(2, 1);
        int min = heap.getMin();
        assertEquals(1, min);
    }

    @Test
    @DisplayName("Print heap")
    void print() {
        dumpData();
        System.out.println(heap.toString());
        assertFalse(heap.isEmpty());
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
    @DisplayName("Extract min from empty heap returns null")
    void extractMinEmpty() {
        assertNull(heap.extractMin());
    }

    @Test
    @DisplayName("Get min from empty heap returns null")
    void getMinEmpty() {
        assertNull(heap.getMin());
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
    @DisplayName("Increase key value triggers heapify")
    void increaseKey() {
        dumpData();
        heap.increaseKey(2, 20);
        assertEquals(3, heap.getMin());
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
        heap.decreaseKey(2, 99);
        assertEquals(3, heap.getMin());
    }

    @Test
    @DisplayName("Increase key at invalid index does nothing")
    void increaseKeyInvalidIndex() {
        dumpData();
        heap.increaseKey(99, 100);
        assertEquals(5, heap.size());
    }

    @Test
    @DisplayName("Increase key with smaller value does nothing")
    void increaseKeySmallerValue() {
        dumpData();
        heap.increaseKey(2, 1);
        assertEquals(3, heap.getMin());
    }

    @Test
    @DisplayName("Change value with equal value does nothing")
    void changeValueEqual() {
        dumpData();
        heap.changeValueOnAKey(2, 3);
        assertEquals(3, heap.getMin());
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
        assertThrows(IllegalArgumentException.class, () -> new MinimumHeap<>(-1));
    }

    @Test
    @DisplayName("Insert null element throws")
    void insertNull() {
        assertThrows(IllegalArgumentException.class, () -> heap.insert(null));
    }

    @Test
    @DisplayName("Insert triggers resize when full")
    void insertAndResize() {
        heap.insertKey(5);
        heap.insertKey(4);
        heap.insertKey(3);
        heap.insertKey(2);
        heap.insertKey(1);
        heap.insertKey(0);
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
    @DisplayName("Extract min with single element")
    void extractMinSingle() {
        heap.insertKey(42);
        assertEquals(42, heap.extractMin());
        assertTrue(heap.isEmpty());
    }

    @Test
    @DisplayName("Default constructor uses default capacity")
    void defaultConstructor() {
        MinimumHeap<Integer> h = new MinimumHeap<>();
        assertTrue(h.isEmpty());
    }
}
