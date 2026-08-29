package org.drozdek.strings.applications;

import java.util.List;
import org.drozdek.strings.AhoCorasick;

/// Multi-pattern virus scanner built on the Aho-Corasick automaton.
///
/// Malware signatures are compiled once into a trie with failure links. A
/// single pass over the scanned content reports every signature match in
/// linear time regardless of how many signatures are registered, which is far
/// faster than running a separate scan per signature.
///
/// **Real-world use case:** Antivirus and endpoint-detection engines that sweep
/// files and traffic for a large, known set of threat signatures in one pass.
///
/// Complexity Analysis:
/// Time Complexity: O(n + m + z) where n is text length, m is total pattern
///                  length, and z is the number of matches
/// Auxiliary Space: O(m) for the automaton
///
/// Bibliography:
///
/// - Alfred V. Aho and Margaret J. Corasick. *Efficient string matching*. CACM, 1975.
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 13.
///
/// @see AhoCorasick
public class VirusSignatureScanner {

    private final AhoCorasick matcher;

    /// Creates an empty scanner.
    public VirusSignatureScanner() {
        this.matcher = new AhoCorasick();
    }

    /// Registers a threat signature (must be called before scanning).
    ///
    /// @param signature signature string to detect
    public void addSignature(String signature) {
        matcher.addPattern(signature);
    }

    /// Finalizes the automaton after all signatures are registered.
    public void finalizeSignatures() {
        matcher.build();
    }

    /// Scans content for any registered signature.
    ///
    /// @param content the text to inspect
    /// @return list of [start, end) match ranges
    public List<int[]> scan(String content) {
        return matcher.search(content);
    }

    /// Returns whether the content contains any registered signature.
    ///
    /// @param content the text to inspect
    /// @return true if at least one signature is present
    public boolean isThreat(String content) {
        return !matcher.search(content).isEmpty();
    }
}
