package org.drozdek.sorting;

import org.drozdek.commons.ArrayUtils;

import java.util.ArrayList;

public final class QuickSort {

    private QuickSort() {
        // do nothing
    }

    // Complexity: average: O(n log n), worst: O(n²)
    // Memory: O(log n)
    // Type: Unstable
    public static void quickSort(int[] array, int left, int right) {

        if (left < right) {
            int partitionPivotIndex = partition(array, left, right);

            // Recursively sort elements before and after the pivot
            quickSort(array, left, partitionPivotIndex - 1);
            quickSort(array, partitionPivotIndex + 1, right);
        }
    }

    // Partition the array using the first element as pivot
    public static int partition(int[] array, int left, int right) {
        int pivot = array[left];
        int i = left;
        int j = right;

        while (i < j) {
            // Move i forward while element is <= pivot
            while (array[i] <= pivot && i < j) {
                i++;
            }
            // Move j backward while element is > pivot
            while (array[j] > pivot) {
                j--;
            }
            // Swap elements at i and j if they haven't crossed
            if (i < j) {
                ArrayUtils.swap(array, i, j);
            }
        }
        // Place the pivot at its correct position
        array[left] = array[j];
        array[j] = pivot;

        return j;
    }

    public static ArrayList<Integer> stableQuickSort(ArrayList<Integer> array) {
        if (array.size() <= 1)
            return array;

        int middle = array.size() / 2;
        int pivot = array.get(middle);

        ArrayList<Integer> smaller = new ArrayList<>();
        ArrayList<Integer> greater = new ArrayList<>();
        classifyForStableSort(array, pivot, middle, smaller, greater);

        ArrayList<Integer> ans = new ArrayList<>();
        ans.addAll(stableQuickSort(smaller));
        ans.add(pivot);
        ans.addAll(stableQuickSort(greater));
        return ans;
    }

    private static void classifyForStableSort(ArrayList<Integer> array, int pivot, int middle,
                                              ArrayList<Integer> smaller, ArrayList<Integer> greater) {
        for (int i = 0; i < array.size(); i++) {
            int val = array.get(i);
            if (i == middle)
                continue;
            if (val < pivot)
                smaller.add(val);
            else if (val > pivot)
                greater.add(val);
            else if (i < middle)
                smaller.add(val);
            else
                greater.add(val);
        }
    }
}
