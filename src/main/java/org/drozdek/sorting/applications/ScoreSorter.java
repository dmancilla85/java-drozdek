package org.drozdek.sorting.applications;

import java.util.ArrayList;
import java.util.List;

/// Ranks named scores by descending value using an in-place insertion sort.
///
/// This small application demonstrates how a sorting algorithm from the
/// `sorting` package is applied to a practical ranking problem: a list of
/// `(name, score)` records is reordered so that the highest score comes first,
/// with ties broken by the original order (a stable sort).
///
/// **Real-world use case:** Leaderboards, exam-score ranking, and any task
/// that must order labelled numeric measurements.
///
/// Complexity Analysis:
/// Time Complexity: O(n^2) worst case for the insertion sort
/// Auxiliary Space: O(1) in-place
///
/// Bibliography:
///
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 9.
public final class ScoreSorter {

    private ScoreSorter() {
        // do nothing
    }

    /// A single ranked entry holding a name and a numeric score.
    public record Score(String name, int value) {
    }

    /// Sort the given scores from highest to lowest, returning a new list.
    ///
    /// @param scores source entries (not modified)
    /// @return a new list ordered by descending score, stable for ties
    public static List<Score> rankDescending(List<Score> scores) {
        List<Score> result = new ArrayList<>(scores);
        for (int i = 1; i < result.size(); i++) {
            Score key = result.get(i);
            int j = i - 1;
            while (j >= 0 && result.get(j).value() < key.value()) {
                result.set(j + 1, result.get(j));
                j--;
            }
            result.set(j + 1, key);
        }
        return result;
    }
}
