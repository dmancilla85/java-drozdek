package org.drozdek.graphs.algorithms;

import java.util.Arrays;

/// Gale–Shapley deferred-acceptance algorithm for the stable marriage
/// problem.
///
/// One side (the proposers) each propose to the highest-ranked acceptor who
/// has not yet rejected them; acceptors tentatively keep their best current
/// proposal and reject the rest. The process repeats until every proposer is
/// engaged. The resulting matching is stable: no couple would rather leave
/// their assigned partners for each other.
///
/// **Real-world use case:** Medical residency matching, school admission,
/// and kidney-exchange pairing, where stable preference-based assignment is
/// required.
///
/// Complexity Analysis:
/// Time Complexity: O(n^2) for n couples
/// Auxiliary Space: O(n^2) for the preference tables
///
/// Bibliography:
///
/// - Gale, D.; Shapley, L. S. *College admissions and the stability of marriage*. The American Mathematical Monthly, 1962.
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 8.
public final class GaleShapleyAlgorithm {

    private GaleShapleyAlgorithm() {
        // do nothing
    }

    /// Computes a stable matching given the preferences of both sides.
    ///
    /// @param proposerPrefs proposer -> ranking of acceptors (best first)
    /// @param acceptorPrefs acceptor -> ranking of proposers (best first)
    /// @return array where entry {@code i} is the acceptor matched to proposer `i`
    public static int[] match(int[][] proposerPrefs, int[][] acceptorPrefs) {
        int n = proposerPrefs.length;
        int[] proposerMatch = new int[n];
        int[] acceptorMatch = new int[n];
        Arrays.fill(proposerMatch, -1);
        Arrays.fill(acceptorMatch, -1);
        int[] next = new int[n];
        int[][] rank = new int[n][n];
        for (int a = 0; a < n; a++) {
            for (int p = 0; p < n; p++) {
                rank[a][acceptorPrefs[a][p]] = p;
            }
        }
        boolean anyFree = true;
        while (anyFree) {
            anyFree = false;
            for (int p = 0; p < n; p++) {
                if (proposerMatch[p] != -1 || next[p] >= n) {
                    continue;
                }
                int a = proposerPrefs[p][next[p]];
                next[p]++;
                if (acceptorMatch[a] == -1) {
                    proposerMatch[p] = a;
                    acceptorMatch[a] = p;
                } else {
                    int current = acceptorMatch[a];
                    if (rank[a][p] < rank[a][current]) {
                        proposerMatch[current] = -1;
                        proposerMatch[p] = a;
                        acceptorMatch[a] = p;
                        anyFree = true;
                    } else if (next[current] < n) {
                        anyFree = true;
                    }
                }
            }
        }
        return proposerMatch;
    }
}
