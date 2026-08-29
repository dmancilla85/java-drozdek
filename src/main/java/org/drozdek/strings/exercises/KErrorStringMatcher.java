package org.drozdek.strings.exercises;

import java.util.ArrayList;
import java.util.List;
import org.drozdek.strings.WagnerFischerEditDistance;

/// Exercise: string matching permitting up to {@code k} errors.
///
/// For each alignment window of the pattern over the text, the edit distance
/// between the window and the pattern is computed; windows whose distance is
/// at most {@code k} are reported as approximate matches.
///
/// **Real-world use case:** Fuzzy search in editors, DNA read alignment with
/// mismatches, and spell-check auto-correction.
///
/// Complexity Analysis:
/// Time Complexity: O(n * m^2) for the windowed approach
/// Auxiliary Space: O(m) per distance computation
///
/// Bibliography:
///
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 13.
public final class KErrorStringMatcher {

    private KErrorStringMatcher() {
        // do nothing
    }

    /// Returns the start indices of all windows matching the pattern with at
    /// most {@code k} errors.
    ///
    /// @param text    text to search within
    /// @param pattern approximate pattern to find
    /// @param k       maximum number of allowed errors
    /// @return list of match start indices
    public static List<Integer> search(String text, String pattern, int k) {
        List<Integer> matches = new ArrayList<>();
        if (pattern.length() > text.length()) {
            return matches;
        }
        for (int i = 0; i + pattern.length() <= text.length(); i++) {
            String window = text.substring(i, i + pattern.length());
            if (WagnerFischerEditDistance.distance(window, pattern) <= k) {
                matches.add(i);
            }
        }
        return matches;
    }
}
