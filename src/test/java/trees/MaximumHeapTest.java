package trees;

import org.drozdek.trees.MaximumHeap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MaximumHeapTest {
    MaximumHeap heap;

    private void dumpData(){
        boolean ret;

        ret =heap.insertKey(5);
        ret =heap.insertKey(7);
        ret =heap.insertKey(153);
        ret =heap.insertKey(9);
        ret =heap.insertKey(16);

        if(!ret)
            System.out.println("The heap is full.");
    }

    @BeforeEach
    void setup(){
        heap=new MaximumHeap(5);
    }

    @Test
    @DisplayName("Create a maximum heap and extract the minimum key")
    void extractMaximum(){
        dumpData();

        int max = heap.extractMax();
        assertEquals(153,max,"The maximum extracted is not the expected.");
    }

    @Test
    @DisplayName("Create a maximum heap and view the minimum key")
    void getMaximum(){
        dumpData();

        int max = heap.getMax();
        assertEquals(153,max,"The maximum extracted is not the expected.");
    }

    @Test
    @DisplayName("Remove the maximum value in the heap")
    void deleteKey(){
        dumpData();
        heap.deleteKey(2);
        int max = heap.getMax();
        assertEquals(16,max,"The maximum extracted is not the expected.");
    }

    @Test
    @DisplayName("Change the maximum value in the heap")
    void changeValueOnAKey(){
        dumpData();
        heap.changeValueOnAKey (0, 18);
        int max = heap.getMax();
        assertEquals(18,max,"The maximum extracted is not the expected.");
    }

    @Test
    @DisplayName("Decrease the maximum value in the heap")
    void decreaseKey(){
        dumpData();
        heap.decreaseKey(0,100);
        int max = heap.getMax();
        assertEquals(100,max,"The maximum extracted is not the expected.");
    }

    @Test
    @DisplayName("Check that the heap is printed correctly.")
    void print(){
        dumpData();
        System.out.println (heap.toString());
        int max = heap.getMax();
        assertEquals(153,max,"The minimum extracted is not the expected.");
    }
}
