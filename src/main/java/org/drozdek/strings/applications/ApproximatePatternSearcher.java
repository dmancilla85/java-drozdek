package org.drozdek.strings.applications;

import java.util.List;
import org.drozdek.strings.ShiftAndMatcher;

/// Bit-parallel pattern search over short text using the Shift-And algorithm.
///
/// Patterns that fit in a single 64-bit machine word are matched with one
/// shift-and per text character, which is extremely fast for small tokens such
/// as identifiers, tags, or markers.
///
/// **Real-world use case:** High-throughput token and tag scanning where
/// patterns are short enough to fit one machine word.
///
/// Complexity Analysis:
/// Time Complexity: O(n) for patterns of up to 64 characters
/// Auxiliary Space: O(1) for the per-character bit masks
///
/// Bibliography:
///
/// - R. Baeza-Yates and G. Navarro. *Faster approximate string matching*.
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 13.
///
/// @see ShiftAndMatcher
public final class ApproximatePatternSearcher {

    private ApproximatePatternSearcher() {
        // do nothing
    }

    /// Returns the first occurrence of the pattern, or -1 if absent.
    ///
    /// @param text    text to search within
    /// @param pattern short pattern (64 characters or fewer)
    /// @return first match index, or -1
    public static int firstOccurrence(String text, String pattern) {
        return ShiftAndMatcher.search(text, pattern);
    }

    /// Returns every non-overlapping occurrence of the pattern.
    ///
    /// @param text    text to search within
    /// @param pattern short pattern (64 characters or fewer)
    /// @return list of match start indices
    public static List<Integer> allOccurrences(String text, String pattern) {
        return ShiftAndMatcher.findAll(text, pattern);
    }
}
