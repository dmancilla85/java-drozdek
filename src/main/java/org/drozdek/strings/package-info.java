/// String-matching algorithms: exact pattern matching over text and
/// edit-distance computation between strings.
///
/// ## Real-world use case
/// These algorithms power text editors (find/replace), search engines,
/// bioinformatics sequence alignment, anti-virus signature scanning, and
/// spell-checking.
///
/// ## Contents
/// - `KnuthMorrisPratt` — linear-time matching with a failure function
/// - `BoyerMoore` — matching using a last-occurrence table
/// - `ShiftAndMatcher` — bit-parallel matching for short patterns
/// - `AhoCorasick` — multiple-pattern matching over a trie with failure links
/// - `WagnerFischerEditDistance` — dynamic-programming edit distance
///
/// Bibliography:
///
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 13.
///
/// @since 1.3
package org.drozdek.strings;
