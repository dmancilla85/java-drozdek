package org.drozdek.recursion;

import java.util.ArrayList;
import java.util.List;

import org.drozdek.commons.LoggerService;

/// Recursive search for the majority element — the value appearing more
/// than n/2 times — returning null when none exists.
///
/// **Real-world use case:** Vote counting in elections and fault-tolerant
/// consensus where one value dominates.
///
/// Complexity Analysis:
/// Time Complexity: O(n²) worst case due to candidate rescans
/// Auxiliary Space: O(n) recursion depth
///
/// @see <a href="https://en.wikipedia.org/wiki/Boyer%E2%80%93Moore_majority_vote_algorithm">Boyer-Moore majority vote (Wikipedia)</a>
public final class MajorityElement {
private MajorityElement() {
        // do nothing
    }

    // Given an integer array A, find the majority element.
    // An element x is the majority if it appears more than n/2 times.
    // At most one such element can exist.

    /// Recursively searches for the majority element by testing, one by one,
    /// the element at each index as a candidate.
    ///
    /// For every candidate the whole list is rescanned to count occurrences,
    /// so the worst case is quadratic. The input list is not modified. The
    /// recursion stops as soon as a majority element is found or the candidate
    /// index runs past the end of the list.
    ///
    /// @param a     list of integers to inspect
    /// @param index position of the current candidate being tested
    /// @return the majority element (a value appearing more than half the time),
    ///         or null if none exists or the list has fewer than three elements
    public static Integer run(List<Integer> a, int index) {

        int mitad = a.size() / 2;
        int count = 0;
        int number;

        // Base case: with 2 or fewer elements there is no majority
        if (a.size() <= 2)
            return null;

        // Pick the element at current index as a candidate
        if (index < a.size())
            number = a.get(index);
        else
            return null;

        for (int i = 0; i < a.size(); i++)
            if (a.get(i) == number)
                count++;

        if (mitad < count)
            return number;
        else
            return run(a, ++index);
    }

    /// Manual demo entry point: builds a sample list and logs the majority
    /// element found, or a message when no majority element exists.
    public static void test() {
        List<Integer> a = new ArrayList<>();
        Integer mayor = 0;

        a.add(1);
        a.add(6);
        a.add(3);
        a.add(3);
        a.add(3);
        a.add(6);
        a.add(6);
        a.add(6);
        a.add(1);
        a.add(6);
        a.add(6);

        mayor = run(a, 0);

        if (mayor != null)
            LoggerService.logInfo("El elemento mayoritario es " + mayor + ".");
        else
            LoggerService.logInfo("No hay elemento mayoritario.");
    }
}
