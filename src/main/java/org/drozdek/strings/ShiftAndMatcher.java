package org.drozdek.strings;

import java.util.ArrayList;
import java.util.List;

/// Bit-parallel Shift-And string matching for short patterns.
///
/// Each character of the alphabet carries a bit mask indicating where it
/// appears in the pattern. A single machine word then tracks, across the
/// evolving match state, which prefix lengths are simultaneously suffixes of
/// the text read so far. A single bitwise shift-and operation per text
/// character drives the whole search, so long patterns are limited to the
/// width of a word (here 64 bits).
///
/// **Real-world use case:** Very fast matching where patterns fit in one or
/// two machine words, e.g. short identifiers and fixed tokens in scanners.
///
/// Complexity Analysis:
/// Time Complexity: O(n * m / w) where w is the word width; effectively O(n) for
///                  patterns up to 64 characters
/// Auxiliary Space: O(1) for the per-character masks
///
/// Bibliography:
///
/// - R. Baeza-Yates and G. Navarro. *Faster approximate string matching*.
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 13.
public final class ShiftAndMatcher {

    private static final int WORD_BITS = 64;

    private ShiftAndMatcher() {
        // do nothing
    }

    /// Returns the start index of the first occurrence of the pattern, or
    /// {@code -1} when it does not occur. Patterns longer than 64 characters
    /// throw an exception.
    ///
    /// @param text    text to search within
    /// @param pattern substring to find
    /// @return first match index, or -1
    public static int search(String text, String pattern) {
        if (pattern.isEmpty()) {
            return 0;
        }
        long[] masks = buildMasks(pattern);
        return searchMasks(text, pattern.length(), masks);
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
        long[] masks = buildMasks(pattern);
        long state = 0;
        int matchLength = pattern.length();
        for (int i = 0; i < text.length(); i++) {
            state = ((state << 1) | 1L) & masks[text.charAt(i)];
            if ((state & (1L << (matchLength - 1))) != 0) {
                matches.add(i - matchLength + 1);
            }
        }
        return matches;
    }

    /// Computes the per-character bit masks for the pattern.
    ///
    /// @param pattern the pattern (no longer than 64 characters)
    /// @return array indexed by character value
    public static long[] buildMasks(String pattern) {
        if (pattern.length() > WORD_BITS) {
            throw new IllegalArgumentException("Pattern longer than 64 characters");
        }
        long[] masks = new long[256];
        for (int i = 0; i < pattern.length(); i++) {
            masks[pattern.charAt(i)] |= 1L << i;
        }
        return masks;
    }

    private static int searchMasks(String text, int patternLength, long[] masks) {
        long state = 0;
        for (int i = 0; i < text.length(); i++) {
            state = ((state << 1) | 1L) & masks[text.charAt(i)];
            if ((state & (1L << (patternLength - 1))) != 0) {
                return i - patternLength + 1;
            }
        }
        return -1;
    }
}
