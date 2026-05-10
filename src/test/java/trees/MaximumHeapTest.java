package trees;

import org.drozdek.trees.MaximumHeap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        System.out.println(heap.toString());
        int max = heap.getMax();
        assertEquals(153, max, "The maximum extracted is not the expected.");
    }
}
