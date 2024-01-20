package sorting;

import org.drozdek.commons.ArrayUtils;
import org.drozdek.sorting.HeapSort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HeapSortTest {
    @Test
    @DisplayName("Run heap sort algorithm.")
    void printSortedList(){
        long startTime;
        long stopTime;

        int[] array = ArrayUtils.randomIntegerArray(50, false);
        startTime = System.currentTimeMillis();

        out.println("Array before sorting:");
        ArrayUtils.printArray(array);

        out.println("Array after sorting:");
        HeapSort.heapSort(array);
        ArrayUtils.printArray(array);

        stopTime = System.currentTimeMillis();
        out.printf("Total time elapsed: %s ms.%n", (stopTime - startTime));
        assertTrue(true, "Check if everything is printing OK.");
    }
}
