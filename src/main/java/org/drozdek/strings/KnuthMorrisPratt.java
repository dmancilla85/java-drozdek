package org.drozdek.strings;

import java.util.ArrayList;
import java.util.List;

/// Knuth–Morris–Pratt (KMP) linear-time pattern matching.
///
/// Precomputes a failure function that records, for each prefix position, the
/// length of its longest proper prefix that is also a suffix. During matching
/// the pattern index never moves backwards; instead it is reset via the
/// failure function, giving worst-case linear time even in the presence of
/// repetitive text.
///
/// **Real-world use case:** Text editors, search tools, and DNA/protein
/// sequence matching where worst-case performance must be guaranteed.
///
/// Complexity Analysis:
/// Time Complexity: O(n + m) for text of length n and pattern of length m
/// Auxiliary Space: O(m) for the failure function table
///
/// Bibliography:
///
/// - D.E. Knuth, J.H. Morris, V.R. Pratt. *Fast pattern matching in strings*.
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 13.
public final class KnuthMorrisPratt {

    private KnuthMorrisPratt() {
        // do nothing
    }

    /// Returns the start index of the first occurrence of the pattern, or
    /// {@code -1} when it does not occur.
    ///
    /// @param text    text to search within
    /// @param pattern substring to find
    /// @return first match index, or -1
    public static int search(String text, String pattern) {
        if (pattern.isEmpty()) {
            return 0;
        }
        int[] failure = buildFailureFunction(pattern);
        int j = 0;
        for (int i = 0; i < text.length(); i++) {
            while (j > 0 && text.charAt(i) != pattern.charAt(j)) {
                j = failure[j - 1];
            }
            if (text.charAt(i) == pattern.charAt(j)) {
                j++;
            }
            if (j == pattern.length()) {
                return i - j + 1;
            }
        }
        return -1;
    }

    /// Returns the start index of every non-overlapping occurrence of the
    /// pattern in the text.
    ///
    /// @param text    text to search within
    /// @param pattern substring to find
    /// @return list of match start indices
    public static List<Integer> findAll(String text, String pattern) {
        List<Integer> matches = new ArrayList<>();
        if (pattern.isEmpty()) {
            return matches;
        }
        int[] failure = buildFailureFunction(pattern);
        int j = 0;
        for (int i = 0; i < text.length(); i++) {
            while (j > 0 && text.charAt(i) != pattern.charAt(j)) {
                j = failure[j - 1];
            }
            if (text.charAt(i) == pattern.charAt(j)) {
                j++;
            }
            if (j == pattern.length()) {
                int start = i - j + 1;
                matches.add(start);
                i = start + pattern.length() - 1;
                j = 0;
            }
        }
        return matches;
    }

    /// Builds the KMP failure function for the pattern.
    ///
    /// @param pattern the pattern
    /// @return array where element i is the length of the longest proper
    ///         prefix-suffix of {@code pattern[0..i]}
    public static int[] buildFailureFunction(String pattern) {
        int[] failure = new int[pattern.length()];
        int j = 0;
        for (int i = 1; i < pattern.length(); i++) {
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = failure[j - 1];
            }
            if (pattern.charAt(i) == pattern.charAt(j)) {
                j++;
            }
            failure[i] = j;
        }
        return failure;
    }
}
