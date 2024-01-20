package sorting;

import org.drozdek.commons.ArrayUtils;
import org.drozdek.sorting.QuickSort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.assertTrue;

class QuickSortTest {

    @Test
    @DisplayName("Run quicksort algorithm.")
    void printSortedArray(){

        long startTime;
        long stopTime;

        int[] array = ArrayUtils.randomIntegerArray(80, false);

        startTime = System.currentTimeMillis();

        out.println("Array before sorting:");
        ArrayUtils.printArray(array);

        QuickSort.quickSort(array, 0, array.length - 1);

        out.println("Array after sorting:");
        ArrayUtils.printArray(array);

        stopTime = System.currentTimeMillis();
        out.printf("Total time elapsed: %s ms.%n", (stopTime - startTime));
        assertTrue(true, "Check if everything is printing OK.");
    }
}
