package org.drozdek.strings;

import java.util.ArrayList;
import java.util.List;

/// Boyer–Moore string-matching algorithm with a last-occurrence heuristic.
///
/// Matching proceeds right-to-left within an aligned window of the text. A
/// precomputed last-occurrence table gives the rightmost position of each
/// character in the pattern, allowing the window to skip forward aggressively
/// whenever a mismatch occurs: the larger the skip, the faster the search on
/// natural-language text.
///
/// **Real-world use case:** High-speed text search in editors and `grep`-like
/// tools where early mismatches dominate over worst-case adversarial input.
///
/// Complexity Analysis:
/// Time Complexity: O(n * m) worst case, sub-linear in practice
/// Auxiliary Space: O(1) extra for the last-occurrence table (fixed alphabet)
///
/// Bibliography:
///
/// - R.S. Boyer and J.S. Moore. *A Fast String Searching Algorithm*.
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 13.
public final class BoyerMoore {

    private static final int ALPHABET_SIZE = 256;

    private BoyerMoore() {
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
        int[] last = buildLastOccurrence(pattern);
        return searchInWindow(text, pattern, last);
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
        int[] last = buildLastOccurrence(pattern);
        int start = 0;
        while (start + pattern.length() <= text.length()) {
            int relative = searchInWindow(text.substring(start), pattern, last);
            if (relative < 0) {
                break;
            }
            int absolute = start + relative;
            matches.add(absolute);
            start = absolute + pattern.length();
        }
        return matches;
    }

    private static int searchInWindow(String text, String pattern, int[] last) {
        int i = pattern.length() - 1;
        int j = pattern.length() - 1;
        while (i < text.length()) {
            if (text.charAt(i) == pattern.charAt(j)) {
                if (j == 0) {
                    return i;
                }
                i--;
                j--;
            } else {
                int shift = last[text.charAt(i)];
                i += pattern.length() - Math.min(j, shift + 1);
                j = pattern.length() - 1;
            }
        }
        return -1;
    }

    /// Builds the last-occurrence table mapping each character to its
    /// rightmost index in the pattern.
    ///
    /// @param pattern the pattern
    /// @return array of size {@value #ALPHABET_SIZE}
    public static int[] buildLastOccurrence(String pattern) {
        int[] last = new int[ALPHABET_SIZE];
        for (int i = 0; i < ALPHABET_SIZE; i++) {
            last[i] = -1;
        }
        for (int i = 0; i < pattern.length(); i++) {
            last[pattern.charAt(i)] = i;
        }
        return last;
    }
}
