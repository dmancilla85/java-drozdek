package org.drozdek.strings.exercises;

import java.util.ArrayList;
import java.util.List;

/// Exercise: Sunday quick-search — a simplified Boyer–Moore variant.
///
/// Rather than a full good-suffix shift, the Sunday algorithm aligns the
/// pattern with the text and, on any mismatch, uses the character that
/// immediately follows the current alignment to decide how far to slide the
/// pattern right, giving a fast and simple practical matcher.
///
/// **Real-world use case:** Fast text search in tooling that favours
/// simplicity and average-case speed over worst-case guarantees.
///
/// Complexity Analysis:
/// Time Complexity: O(n * m) worst case, sub-linear average case
/// Auxiliary Space: O(1) for the character-shift table
///
/// Bibliography:
///
/// - D.M. Sunday. *A Very Fast Substring Search Algorithm*.
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 13.
public final class SundayQuickSearch {

    private SundayQuickSearch() {
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
        int[] shift = buildShiftTable(pattern);
        int patternLength = pattern.length();
        int i = 0;
        while (i + patternLength <= text.length()) {
            int j = 0;
            while (j < patternLength && text.charAt(i + j) == pattern.charAt(j)) {
                j++;
            }
            if (j == patternLength) {
                return i;
            }
            if (i + patternLength >= text.length()) {
                break;
            }
            char next = text.charAt(i + patternLength);
            i += shift[next];
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
        int[] shift = buildShiftTable(pattern);
        int patternLength = pattern.length();
        int i = 0;
        while (i + patternLength <= text.length()) {
            int j = 0;
            while (j < patternLength && text.charAt(i + j) == pattern.charAt(j)) {
                j++;
            }
            if (j == patternLength) {
                matches.add(i);
                i += patternLength;
                continue;
            }
            if (i + patternLength >= text.length()) {
                break;
            }
            char next = text.charAt(i + patternLength);
            i += shift[next];
        }
        return matches;
    }

    /// Builds the Sunday quick-search shift table: the distance to slide when
    /// the character following the alignment is seen.
    ///
    /// @param pattern the pattern
    /// @return shift table indexed by character value
    public static int[] buildShiftTable(String pattern) {
        int[] shift = new int[256];
        int length = pattern.length();
        for (int i = 0; i < 256; i++) {
            shift[i] = length + 1;
        }
        for (int i = 0; i < length; i++) {
            shift[pattern.charAt(i)] = length - i;
        }
        return shift;
    }
}
