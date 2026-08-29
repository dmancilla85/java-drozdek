package org.drozdek.strings.applications;

import java.util.List;
import org.drozdek.strings.WagnerFischerEditDistance;

/// Fuzzy spell-checker using the Wagner-Fischer edit distance.
///
/// For each query word the checker scans the dictionary and returns every entry
/// whose Levenshtein distance to the query falls within a configurable
/// threshold, plus the exact distance for a given pair via the underlying
/// edit-distance algorithm.
///
/// **Real-world use case:** Typo-tolerant search, spell-checking in editors,
/// and suggestion engines that rank candidate corrections by edit distance.
///
/// Complexity Analysis:
/// Time Complexity: O(d * n) per word comparison
/// Auxiliary Space: O(d * n) for the DP table
///
/// Bibliography:
///
/// - Robert A. Wagner and Michael J. Fischer. *The String-to-String Correction Problem*. JACM, 1974.
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 13.
///
/// @see WagnerFischerEditDistance
public class FuzzySpellChecker {

    private final List<String> dictionary;
    private final int maxDistance;

    /// Creates a checker over a fixed dictionary and edit-distance threshold.
    ///
    /// @param dictionary the known correct words
    /// @param maxDistance maximum edit distance considered a plausible match
    /// @throws IllegalArgumentException if the threshold is negative
    public FuzzySpellChecker(List<String> dictionary, int maxDistance) {
        if (maxDistance < 0) {
            throw new IllegalArgumentException("maxDistance cannot be negative");
        }
        this.dictionary = List.copyOf(dictionary);
        this.maxDistance = maxDistance;
    }

    /// Returns dictionary words within the configured edit distance of the query.
    ///
    /// @param word the possibly misspelled query
    /// @return candidate corrections
    public List<String> suggestions(String word) {
        return dictionary.stream()
                .filter(candidate -> WagnerFischerEditDistance.withinDistance(word, candidate, maxDistance))
                .toList();
    }

    /// Returns the exact edit distance between two words.
    ///
    /// @param a first word
    /// @param b second word
    /// @return Levenshtein distance
    public int distance(String a, String b) {
        return WagnerFischerEditDistance.distance(a, b);
    }

    /// Returns whether the query is an exact dictionary word.
    ///
    /// @param word the word to check
    /// @return true if present verbatim
    public boolean isCorrect(String word) {
        return dictionary.contains(word);
    }
}
