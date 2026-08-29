package org.drozdek.strings.applications;

/// Application that finds the longest substring shared exactly by two strings.
///
/// It enumerates every substring of the shorter string by decreasing length
/// and returns the first one that also appears in the other string, which is
/// by construction the longest common substring.
///
/// **Real-world use case:** Plagiarism screeners, diff tools, and genomic
/// shared-region discovery.
///
/// Complexity Analysis:
/// Time Complexity: O(n^2) substring checks over the shorter string
/// Auxiliary Space: O(1) besides the substring scan
///
/// Bibliography:
///
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 13.
public class LongestCommonSubstring {

    /// Returns the longest substring common to {@code a} and {@code b}, or an
    /// empty string when they share nothing.
    ///
    /// @param a first string
    /// @param b second string
    /// @return the longest common substring
    public String find(String a, String b) {
        String shorter = a.length() <= b.length() ? a : b;
        String longer = a.length() <= b.length() ? b : a;
        for (int length = shorter.length(); length > 0; length--) {
            for (int start = 0; start + length <= shorter.length(); start++) {
                String candidate = shorter.substring(start, start + length);
                if (longer.contains(candidate)) {
                    return candidate;
                }
            }
        }
        return "";
    }
}
