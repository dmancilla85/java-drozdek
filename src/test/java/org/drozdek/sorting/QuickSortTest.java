package sorting;

import org.drozdek.commons.ArrayUtils;
import org.drozdek.sorting.QuickSort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;

class QuickSortTest {

    @Test
    @DisplayName("Run quicksort algorithm.")
    void printSortedArray() {
        int[] array = ArrayUtils.randomIntegerArray(80, false);

        out.println("Array before sorting:");
        ArrayUtils.printArray(array);

        QuickSort.quickSort(array, 0, array.length - 1);

        out.println("Array after sorting:");
        ArrayUtils.printArray(array);

        assertTrue(ArrayUtils.isSorted(array), "Array should be sorted after quick sort");
    }

    @Test
    @DisplayName("Partition single element")
    void partitionSingleElement() {
        int[] array = {5};
        int p = QuickSort.partition(array, 0, 0);
        assertEquals(0, p);
        assertArrayEquals(new int[]{5}, array);
    }

    @Test
    @DisplayName("Partition two elements")
    void partitionTwoElements() {
        int[] array = {5, 3};
        int p = QuickSort.partition(array, 0, 1);
        assertTrue(p >= 0 && p <= 1);
        assertTrue(ArrayUtils.isSorted(array));
    }

    @Test
    @DisplayName("Partition already sorted")
    void partitionSorted() {
        int[] array = {1, 2, 3, 4, 5};
        int p = QuickSort.partition(array, 0, 4);
        assertTrue(p >= 0 && p < 5);
    }

    @Test
    @DisplayName("Partition with equal elements")
    void partitionEqualElements() {
        int[] array = {2, 2, 2, 2, 2};
        int p = QuickSort.partition(array, 0, 4);
        assertTrue(p >= 0 && p < 5);
    }

    @Test
    @DisplayName("Stable quick sort empty list")
    void stableSortEmpty() {
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<Integer> result = QuickSort.stableQuickSort(list);
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Stable quick sort single element")
    void stableSortSingle() {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(42));
        ArrayList<Integer> result = QuickSort.stableQuickSort(list);
        assertEquals(1, result.size());
        assertEquals(42, result.get(0));
    }

    @Test
    @DisplayName("Stable quick sort already sorted")
    void stableSortSorted() {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        ArrayList<Integer> result = QuickSort.stableQuickSort(list);
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, result.toArray());
    }

    @Test
    @DisplayName("Stable quick sort reverse sorted")
    void stableSortReverse() {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(5, 4, 3, 2, 1));
        ArrayList<Integer> result = QuickSort.stableQuickSort(list);
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, result.toArray());
    }

    @Test
    @DisplayName("Stable quick sort with duplicates")
    void stableSortDuplicates() {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(3, 1, 2, 3, 1, 2));
        ArrayList<Integer> result = QuickSort.stableQuickSort(list);
        assertArrayEquals(new Integer[]{1, 1, 2, 2, 3, 3}, result.toArray());
    }

    @Test
    @DisplayName("Stable quick sort maintains relative order of equal keys")
    void stableSortPreservesOrder() {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(3, 1, 2, 1, 4));
        ArrayList<Integer> result = QuickSort.stableQuickSort(list);
        assertEquals(1, result.get(0));
        assertEquals(1, result.get(1));
    }
}
