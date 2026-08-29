package org.drozdek.strings.applications;

import java.util.List;
import org.drozdek.strings.BoyerMoore;
import org.drozdek.strings.KnuthMorrisPratt;

/// Gene-sequence search using the KMP and Boyer-Moore pattern matchers.
///
/// A short nucleotide pattern is located within a genome sequence by the
/// Knuth-Morris-Pratt algorithm, which guarantees linear worst-case behaviour
/// even on repetitive genetic data, and cross-checked with the Boyer-Moore
/// algorithm, which is sub-linear on average.
///
/// **Real-world use case:** Bioinformatics tooling that searches for primers,
/// restriction sites, or markers within long DNA sequences.
///
/// Complexity Analysis:
/// Time Complexity: O(n + m) for KMP, sub-linear average for Boyer-Moore
/// Auxiliary Space: O(m) for precomputed tables
///
/// Bibliography:
///
/// - Donald E. Knuth, James H. Morris, and Vaughan R. Pratt. *Fast Pattern Matching in Strings*. SIAM, 1977.
/// - Robert S. Boyer and J. Strother Moore. *A Fast String Searching Algorithm*. CACM, 1977.
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 13.
///
/// @see KnuthMorrisPratt
/// @see BoyerMoore
public final class GeneSequenceSearch {

    private GeneSequenceSearch() {
        // do nothing
    }

    /// Finds the first occurrence of the gene pattern via KMP.
    ///
    /// @param genome the sequence to search
    /// @param gene   the pattern to find
    /// @return start index, or -1 if absent
    public static int kmpFirst(String genome, String gene) {
        return KnuthMorrisPratt.search(genome, gene);
    }

    /// Finds the first occurrence of the gene pattern via Boyer-Moore.
    ///
    /// @param genome the sequence to search
    /// @param gene   the pattern to find
    /// @return start index, or -1 if absent
    public static int boyerMooreFirst(String genome, String gene) {
        return BoyerMoore.search(genome, gene);
    }

    /// Finds every non-overlapping occurrence of the gene pattern via KMP.
    ///
    /// @param genome the sequence to search
    /// @param gene   the pattern to find
    /// @return start indices of all matches
    public static List<Integer> allOccurrences(String genome, String gene) {
        return KnuthMorrisPratt.findAll(genome, gene);
    }
}
