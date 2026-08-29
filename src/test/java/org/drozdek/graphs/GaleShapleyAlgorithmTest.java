package org.drozdek.graphs;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.drozdek.graphs.algorithms.GaleShapleyAlgorithm;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GaleShapleyAlgorithmTest {

    @Test
    @DisplayName("Produces a stable matching for the classic example")
    void match_stable() {
        int[][] proposerPrefs = {
            {1, 2, 0},
            {0, 1, 2},
            {2, 0, 1}
        };
        int[][] acceptorPrefs = {
            {0, 2, 1},
            {2, 1, 0},
            {1, 0, 2}
        };
        int[] matching = GaleShapleyAlgorithm.match(proposerPrefs, acceptorPrefs);
        assertValid(matching, proposerPrefs, acceptorPrefs);
        assertStable(matching, proposerPrefs, acceptorPrefs);
    }

    @Test
    @DisplayName("2x2 instance is stable")
    void match_twoCouples() {
        int[][] proposerPrefs = {{0, 1}, {0, 1}};
        int[][] acceptorPrefs = {{0, 1}, {0, 1}};
        int[] matching = GaleShapleyAlgorithm.match(proposerPrefs, acceptorPrefs);
        assertValid(matching, proposerPrefs, acceptorPrefs);
        assertStable(matching, proposerPrefs, acceptorPrefs);
    }

    @Test
    @DisplayName("Every proposer and acceptor are matched")
    void match_complete() {
        int[][] proposerPrefs = {
            {1, 2, 0},
            {0, 1, 2},
            {2, 0, 1}
        };
        int[][] acceptorPrefs = {
            {0, 2, 1},
            {2, 1, 0},
            {1, 0, 2}
        };
        int[] matching = GaleShapleyAlgorithm.match(proposerPrefs, acceptorPrefs);
        boolean[] seenA = new boolean[3];
        for (int a : matching) {
            assertTrue(a >= 0 && a < 3);
            seenA[a] = true;
        }
        for (boolean s : seenA) {
            assertTrue(s);
        }
    }

    private static void assertValid(int[] matching, int[][] p, int[][] a) {
        for (int i = 0; i < matching.length; i++) {
            assertTrue(matching[i] >= 0 && matching[i] < a.length, "proposer " + i + " matched");
        }
    }

    private static void assertStable(int[] matching, int[][] proposerPrefs, int[][] acceptorPrefs) {
        int n = matching.length;
        int[] rankA = new int[n];
        for (int acceptor = 0; acceptor < n; acceptor++) {
            int proposer = findProposer(matching, acceptor);
            rankA[acceptor] = indexOf(acceptorPrefs[acceptor], proposer);
        }
        for (int p = 0; p < n; p++) {
            int currentA = matching[p];
            int rankCurrent = indexOf(proposerPrefs[p], currentA);
            for (int i = 0; i < rankCurrent; i++) {
                int preferred = proposerPrefs[p][i];
                int preferredProposer = findProposer(matching, preferred);
                if (indexOf(acceptorPrefs[preferred], p) < indexOf(acceptorPrefs[preferred], preferredProposer)) {
                    throw new AssertionError("blocking pair (" + p + "," + preferred + ")");
                }
            }
        }
    }

    private static int findProposer(int[] matching, int acceptor) {
        for (int p = 0; p < matching.length; p++) {
            if (matching[p] == acceptor) {
                return p;
            }
        }
        return -1;
    }

    private static int indexOf(int[] array, int value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }
}
