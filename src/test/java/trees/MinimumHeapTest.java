package trees;

import org.drozdek.trees.MinimumHeap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MinimumHeapTest {
    MinimumHeap heap;

    private void dumpData(){
        boolean ret;

        ret =heap.insertKey(5);
        ret =heap.insertKey(7);
        ret =heap.insertKey(1);
        ret =heap.insertKey(9);
        ret =heap.insertKey(16);

        if(!ret)
            System.out.println("The heap is full.");
    }

    @BeforeEach
    void setup(){
        heap=new MinimumHeap(5);
    }

    @Test
    @DisplayName("Create a minimum heap and extract the minimum key")
    void extractMinimum(){
        dumpData();

        int min = heap.extractMin();
        assertEquals(1,min,"The minimum extracted is not the expected.");
    }

    @Test
    @DisplayName("Create a minimum heap and view the minimum key")
    void getMinimum(){
        dumpData();

        int min = heap.getMin();
        assertEquals(1,min,"The minimum extracted is not the expected.");
    }

    @Test
    @DisplayName("Remove the minimum value in the heap")
    void deleteKey(){
        dumpData();
        heap.deleteKey(0);
        int min = heap.getMin();
        assertEquals(5,min,"The minimum extracted is not the expected.");
    }

    @Test
    @DisplayName("Change the minimum value in the heap")
    void changeValueOnAKey(){
        dumpData();
        heap.changeValueOnAKey (0, 18);
        int min = heap.getMin();
        assertEquals(5,min,"The minimum extracted is not the expected.");
    }

    @Test
    @DisplayName("Decrease the minimum value in the heap")
    void decreaseKey(){
        dumpData();
        heap.decreaseKey(1,-1);
        int min = heap.getMin();
        assertEquals(-1,min,"The minimum extracted is not the expected.");
    }

    @Test
    @DisplayName("Check that the heap is printed correctly.")
    void print(){
        dumpData();
        System.out.println (heap.toString());
        int min = heap.getMin();
        assertEquals(1,min,"The minimum extracted is not the expected.");
    }
}
