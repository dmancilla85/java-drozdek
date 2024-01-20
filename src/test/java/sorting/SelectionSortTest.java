package sorting;

import org.drozdek.commons.ArrayUtils;
import org.drozdek.sorting.InsertionSort;
import org.drozdek.sorting.SelectionSort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SelectionSortTest {
    @Test
    @DisplayName("Run selection sort algorithm.")
    void printSortedList(){
        long startTime;
        long stopTime;

        int[] array = ArrayUtils.randomIntegerArray(50, false);
        startTime = System.currentTimeMillis();

        out.println("Array before sorting:");
        ArrayUtils.printArray(array);

        out.println("Array after sorting:");
        SelectionSort.selectionSort(array);
        ArrayUtils.printArray(array);

        stopTime = System.currentTimeMillis();
        out.printf("Total time elapsed: %s ms.%n", (stopTime - startTime));
        assertTrue(true, "Check if everything is printing OK.");
    }
}
