package org.drozdek.sorting;

import org.drozdek.commons.ArrayUtils;

import java.util.ArrayList;

public class QuickSort {

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

    // A sorting algorithm is said to be stable if it maintains the relative order of records
    // in the case of equality of keys.
    public static ArrayList<Integer> stableQuickSort(ArrayList<Integer> array) {

        // Base case
        if (array.size() <= 1) {
            return array;
        } else {

            // Let us choose middle element a pivot
            int middle = array.size() / 2;
            int pivot = array.get(middle);

            // key element is used to break the array
            // into 2 halves according to their values
            ArrayList<Integer> smaller = new ArrayList<>();
            ArrayList<Integer> greater = new ArrayList<>();

            // Put greater elements in greater list,
            // smaller elements in smaller list. Also,
            // compare positions to decide where to put.
            for (int i = 0; i < array.size(); i++) {
                int val = array.get(i);
                if (i != middle) {
                    if (val < pivot) {
                        smaller.add(val);
                    } else if (val > pivot) {
                        greater.add(val);
                    } else {

                        // If value is same, then considering
                        // position to decide the list.
                        if (i < middle) {
                            smaller.add(val);
                        } else {
                            greater.add(val);
                        }
                    }
                }
            }

            ArrayList<Integer> ans = new ArrayList<>();
            ArrayList<Integer> sa1 = stableQuickSort(smaller);
            ArrayList<Integer> sa2 = stableQuickSort(greater);

            // add all elements of smaller list into ans list
            for (Integer val1 : sa1)
                ans.add(val1);

            // add pivat element into ans list
            ans.add(pivot);

            // add all elements of greater list into ans list
            for (Integer val2 : sa2)
                ans.add(val2);

            // return ans list
            return ans;
        }
    }
}