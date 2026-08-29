package org.drozdek.strings;

/// Wagner–Fischer dynamic-programming edit distance.
///
/// Computes the Levenshtein edit distance between two strings: the minimum
/// number of single-character insertions, deletions, and substitutions needed
/// to transform one string into the other. A bottom-up table keeps the
/// distance for every prefix pair.
///
/// **Real-world use case:** Spell-checking suggestions, DNA sequence
/// comparison, and fuzzy matching in search and version control.
///
/// Complexity Analysis:
/// Time Complexity: O(n * m) for strings of length n and m
/// Auxiliary Space: O(n * m) for the full distance table
///
/// Bibliography:
///
/// - R.A. Wagner and M.J. Fischer. *The String-to-String Correction Problem*.
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 13.
public final class WagnerFischerEditDistance {

    private WagnerFischerEditDistance() {
        // do nothing
    }

    /// Computes the edit distance between {@code a} and {@code b}.
    ///
    /// @param a first string
    /// @param b second string
    /// @return the minimum edit distance
    public static int distance(String a, String b) {
        int[][] table = new int[a.length() + 1][b.length() + 1];
        for (int i = 0; i <= a.length(); i++) {
            table[i][0] = i;
        }
        for (int j = 0; j <= b.length(); j++) {
            table[0][j] = j;
        }
        for (int i = 1; i <= a.length(); i++) {
            for (int j = 1; j <= b.length(); j++) {
                int substitution = table[i - 1][j - 1] + (a.charAt(i - 1) == b.charAt(j - 1) ? 0 : 1);
                int deletion = table[i - 1][j] + 1;
                int insertion = table[i][j - 1] + 1;
                table[i][j] = Math.min(substitution, Math.min(deletion, insertion));
            }
        }
        return table[a.length()][b.length()];
    }

    /// Returns whether the edit distance is at most the given threshold.
    ///
    /// @param a         first string
    /// @param b         second string
    /// @param threshold maximum acceptable distance
    /// @return {@code true} if within the threshold
    public static boolean withinDistance(String a, String b, int threshold) {
        return distance(a, b) <= threshold;
    }
}
