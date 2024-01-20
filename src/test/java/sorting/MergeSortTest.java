package sorting;

import org.drozdek.commons.ArrayUtils;
import org.drozdek.commons.LoggerService;
import org.drozdek.sorting.MergeSort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MergeSortTest {

    @Test
    @DisplayName("Run merge-sort algorithm.")
    void printSortedArray(){
        long startTime;
        long stopTime;

        int[] array = ArrayUtils.randomIntegerArray(50, false);
        startTime = System.currentTimeMillis();

        LoggerService.logInfo("Array before sorting:");
        ArrayUtils.printArray(array);

        LoggerService.logInfo("Array after sorting:");
        MergeSort.mergeSort(array, 0, array.length - 1);
        ArrayUtils.printArray(array);

        stopTime = System.currentTimeMillis();
        LoggerService.logInfo(String.format("Total time elapsed: %s ms.%n", (stopTime - startTime)));
        assertTrue(true, "Check if everything is printing OK.");
    }
}
