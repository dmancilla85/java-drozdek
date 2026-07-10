package org.drozdek.sorting.exercises;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TernaryMergeSortTest {

    private boolean isSorted(List<Integer> list) {
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i - 1) > list.get(i))
                return false;
        }
        return true;
    }

    @Test
    @DisplayName("Sorting an array with one element")
    void mergeSort_singleElement() {
        List<Integer> list = new ArrayList<>();
        list.add(42);
        TernaryMergeSort.mergeSortAlter(list, 0, 0);
        assertEquals(1, list.size());
        assertEquals(42, list.getFirst());
    }

    @Test
    @DisplayName("Sorting an array with two elements")
    void mergeSort_twoElements() {
        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(1);
        TernaryMergeSort.mergeSortAlter(list, 0, 1);
        assertEquals(1, list.get(0));
        assertEquals(5, list.get(1));
    }

    @Test
    @DisplayName("Sorting an already sorted array")
    void mergeSort_alreadySorted() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        TernaryMergeSort.mergeSortAlter(list, 0, 3);
        assertTrue(isSorted(list));
    }

    @Test
    @DisplayName("Sorting a reverse-sorted array")
    void mergeSort_reverseSorted() {
        List<Integer> list = new ArrayList<>();
        list.add(9);
        list.add(7);
        list.add(5);
        list.add(3);
        list.add(1);
        TernaryMergeSort.mergeSortAlter(list, 0, 4);
        assertTrue(isSorted(list));
    }

    @Test
    @DisplayName("Sorting an array with duplicates")
    void mergeSort_duplicates() {
        List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(1);
        list.add(2);
        list.add(1);
        list.add(3);
        TernaryMergeSort.mergeSortAlter(list, 0, 4);
        assertTrue(isSorted(list));
    }

    @Test
    @DisplayName("Sorting the example from the test method")
    void mergeSort_example() {
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(8);
        list.add(1);
        list.add(75);
        list.add(3);
        list.add(27);
        list.add(22);
        list.add(25);
        TernaryMergeSort.mergeSortAlter(list, 0, 7);
        assertTrue(isSorted(list));
        assertEquals(1, list.get(0));
        assertEquals(75, list.get(7));
    }

    @Test
    @DisplayName("Sorting a larger random array")
    void mergeSort_largerArray() {
        List<Integer> list = new ArrayList<>();
        for (int i = 100; i >= 1; i--)
            list.add(i);
        TernaryMergeSort.mergeSortAlter(list, 0, 99);
        assertTrue(isSorted(list));
    }

    @Test
    @DisplayName("Merge with segment exhaustion paths")
    void mergeSort_exhaustionPaths() {
        List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(1);
        list.add(2);
        TernaryMergeSort.mergeSortAlter(list, 0, 2);
        assertTrue(isSorted(list));
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
    }
}
