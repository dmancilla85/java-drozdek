package org.drozdek.sorting;

import java.util.ArrayList;
import java.util.List;

/// Radix Sort algorithm.
///
/// A non-comparative integer sort that processes numbers digit by digit from
/// the least significant to the most significant position, distributing
/// elements into digit buckets and recombining them after each pass. After
/// processing every digit the array is fully sorted.
///
/// **Real-world use case:** Sorting fixed-width numeric or string keys such
/// as machine addresses, telephone numbers, and card decks, where a
/// comparison-based lower bound can be beaten.
///
/// Complexity Analysis:
/// Time Complexity: O(d * n) where d is the number of digits and n the size;
///                  O(n * b) space for the buckets
/// Auxiliary Space: O(n + b) with b digit buckets
///
/// Bibliography:
///
/// - Radix sort. *Wikipedia*. https://en.wikipedia.org/wiki/Radix_sort
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 9.
public final class RadixSort {

    private RadixSort() {
        // do nothing
    }

    /// Sorts an array of non-negative integers using LSD radix sort.
    ///
    /// @param array Array of integers to sort
    public static void radixSort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        int max = max(array);
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingPass(array, exp);
        }
    }

    private static void countingPass(int[] array, int exp) {
        List<List<Integer>> buckets = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            buckets.add(new ArrayList<>());
        }
        for (int value : array) {
            int digit = (value / exp) % 10;
            buckets.get(digit).add(value);
        }
        int index = 0;
        for (List<Integer> bucket : buckets) {
            for (int value : bucket) {
                array[index++] = value;
            }
        }
    }

    private static int max(int[] array) {
        int max = array[0];
        for (int value : array) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }
}
