package org.drozdek.hashing.applications;

import java.util.ArrayList;
import java.util.List;
import org.drozdek.hashing.CichelliHash;

/// Compiler reserved-word table using Cichelli perfect hashing.
///
/// The set of language keywords is fixed at construction, so a perfect hash
/// function maps each keyword to a distinct slot in a compact range with zero
/// collisions. Lookups never re-probe, giving constant-time keyword recognition.
///
/// **Real-world use case:** Compiler and lexer keyword tables where the token
/// set is known in advance and every lookup must be a single O(1) probe.
///
/// Complexity Analysis:
/// Time Complexity: O(1) expected per lookup once built
/// Auxiliary Space: O(n) for the stored key set
///
/// Bibliography:
///
/// - Cichelli, R. A note on the minimal perfect hashing of keywords. *Communications of the ACM*.
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 10.
///
/// @see CichelliHash
public final class CompilerKeywordTable {

    private final List<String> keywords;

    /// Creates a perfect keyword table over a fixed set of keywords.
    ///
    /// @param keywords the fixed keyword set, none of which may be blank
    /// @throws IllegalArgumentException if the set is empty or contains a blank
    public CompilerKeywordTable(List<String> keywords) {
        if (keywords == null || keywords.isEmpty()
                || keywords.stream().anyMatch(k -> k == null || k.isEmpty())) {
            throw new IllegalArgumentException("keywords must be non-empty strings");
        }
        this.keywords = new ArrayList<>(keywords);
    }

    /// Returns the perfect-hash slot for a keyword, or -1 if absent.
    ///
    /// @param keyword the keyword to resolve
    /// @return a distinct slot in [0, 2 * size()) for members, otherwise -1
    public int slotOf(String keyword) {
        return CichelliHash.perfectHash(keywords, keyword);
    }

    /// Returns the number of keywords in the table.
    ///
    /// @return keyword count
    public int size() {
        return keywords.size();
    }
}
