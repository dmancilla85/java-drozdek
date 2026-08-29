package org.drozdek.graphs.applications;

import org.drozdek.graphs.algorithms.GaleShapleyAlgorithm;

/// Hospital-resident matchmaker based on the Gale-Shapley stable-matching algorithm.
///
/// Residents (proposers) and hospitals (acceptors) each declare a strict ranking
/// of the opposite side. The Gale-Shapley deferred-acceptance procedure produces
/// a stable, proposer-optimal assignment in which no unmatched pair would rather
/// have each other over their assigned partner.
///
/// **Real-world use case:** Medical residency matching (NRMP), school placement,
/// and two-sided marketplace clearing.
///
/// Complexity Analysis:
/// Time Complexity: O(n^2) for n residents and n hospitals
/// Auxiliary Space: O(n) for the matching and rank tables
///
/// Bibliography:
///
/// - D. Gale and L. S. Shapley. *College Admissions and the Stability of Marriage*. American Mathematical Monthly, 1962.
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 9.
///
/// @see GaleShapleyAlgorithm
public final class HospitalResidentMatcher {

    private HospitalResidentMatcher() {
        // do nothing
    }

    /// Matches residents to hospitals to yield a stable assignment.
    ///
    /// @param residentPrefs  residents' ranking of hospitals, best first
    /// @param hospitalPrefs  hospitals' ranking of residents, best first
    /// @return array where the element at index i is the hospital matched to
    ///         resident i
    public static int[] matchResidents(int[][] residentPrefs, int[][] hospitalPrefs) {
        return GaleShapleyAlgorithm.match(residentPrefs, hospitalPrefs);
    }
}
