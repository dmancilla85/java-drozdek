package org.drozdek.recursion.applications;

import java.util.ArrayList;
import java.util.List;
import org.drozdek.recursion.AlphabeticallySorted;

/// Checks whether a password's character sequence is in non-decreasing order.
///
/// Lexicographic ordering validation is delegated to the recursive
/// AlphabeticallySorted routine, which compares characters case-insensitively.
///
/// **Real-world use case:** Simple input-validation gate for sorted word lists,
/// dictionary feeds, or ledgers that require monotonic ordering.
///
/// Complexity Analysis:
/// Time Complexity: O(n) recursive passes
/// Auxiliary Space: O(n) recursion depth
///
/// Bibliography:
///
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 5.
///
/// @see AlphabeticallySorted
public final class LexicographicOrderChecker {

    private LexicographicOrderChecker() {
        // do nothing
    }

    /// Returns whether the word's characters are in non-decreasing order.
    ///
    /// @param word the sequence to inspect
    /// @return true if every adjacent pair is in non-decreasing order
    public static boolean isSorted(String word) {
        if (word == null) {
            return true;
        }
        List<Character> chars = new ArrayList<>();
        for (char c : word.toCharArray()) {
            chars.add(c);
        }
        return AlphabeticallySorted.run(chars, 0);
    }
}
