package org.drozdek.recursion.exercises;

import java.util.ArrayList;
import java.util.List;

/// Generates every permutation of a string using recursion.
///
/// Each call fixes one character at the current position and recurses on the
/// remaining characters, producing all n! orderings of the input. This is a
/// canonical exercise in recursive enumeration and backtracking.
///
/// **Real-world use case:** Generating test inputs, anagrams, and candidate
/// orderings in combinatorial search where all orderings must be explored.
///
/// Complexity Analysis:
/// Time Complexity: O(n * n!) to generate and store all permutations
/// Auxiliary Space: O(n * n!) for the result set plus O(n) recursion depth
///
/// Bibliography:
///
/// - Permutation. *Wikipedia*. https://en.wikipedia.org/wiki/Permutation
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 5.
public final class StringPermutations {

    private StringPermutations() {
        // do nothing
    }

    /// Returns all n! permutations of the input string. Repeated characters
    /// yield repeated orderings.
    ///
    /// @param input the string to permute
    /// @return list of all permutations
    public static List<String> generate(String input) {
        List<String> result = new ArrayList<>();
        if (input == null) {
            return result;
        }
        permute(input.toCharArray(), 0, result);
        return result;
    }

    private static void permute(char[] chars, int index, List<String> result) {
        if (index == chars.length - 1) {
            result.add(new String(chars));
            return;
        }
        for (int i = index; i < chars.length; i++) {
            swap(chars, index, i);
            permute(chars, index + 1, result);
            swap(chars, index, i);
        }
    }

    private static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }
}
