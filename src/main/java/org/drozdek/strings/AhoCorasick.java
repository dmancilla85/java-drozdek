package org.drozdek.strings;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/// Aho–Corasick multiple-pattern matching over a trie with failure links.
///
/// All patterns are inserted into a trie, then a breadth-first pass adds
/// failure links so that a single linear scan of the text reports every
/// occurrence of every pattern. This is optimal for simultaneously searching
/// for a large dictionary of patterns.
///
/// **Real-world use case:** Anti-virus signature matching, spam filtering,
/// intrusion-detection signature sets, and bioinformatics motif search.
///
/// Complexity Analysis:
/// Time Complexity: O(n + m + z) for text length n, total pattern length m,
///                  and z matches reported
/// Auxiliary Space: O(m * alphabet) for the trie and failure links
///
/// Bibliography:
///
/// - A.V. Aho and M.J. Corasick. *Efficient string matching: an aid to
///   bibliographic search*.
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 13.
public class AhoCorasick {

    private static final class Node {
        private final Map<Character, Node> children = new HashMap<>();
        private Node failure;
        private String output;
    }

    private final Node root = new Node();

    /// Adds a pattern to the matcher. Must be called before matching begins.
    ///
    /// @param pattern word to search for
    public void addPattern(String pattern) {
        Node current = root;
        for (int i = 0; i < pattern.length(); i++) {
            current = current.children.computeIfAbsent(pattern.charAt(i), c -> new Node());
        }
        current.output = pattern;
    }

    /// Prepares the automaton by computing failure links (call once after all
    /// patterns are added).
    public void build() {
        Deque<Node> queue = new ArrayDeque<>();
        for (Node child : root.children.values()) {
            child.failure = root;
            queue.add(child);
        }
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            for (Map.Entry<Character, Node> entry : current.children.entrySet()) {
                char c = entry.getKey();
                Node child = entry.getValue();
                Node failure = current.failure;
                while (failure != null && !failure.children.containsKey(c)) {
                    failure = failure.failure;
                }
                child.failure = failure == null ? root : failure.children.get(c);
                queue.add(child);
            }
        }
    }

    /// Reports every occurrence of any added pattern as a list of
    /// {@code [start, end)} index pairs.
    ///
    /// @param text text to scan
    /// @return list of pairs, each {@code [startIndex, endIndex]}
    public List<int[]> search(String text) {
        List<int[]> results = new ArrayList<>();
        Node current = root;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            while (current != root && !current.children.containsKey(c)) {
                current = current.failure;
            }
            current = current.children.getOrDefault(c, root);
            Node follow = current;
            while (follow != root) {
                if (follow.output != null) {
                    results.add(new int[]{i - follow.output.length() + 1, i + 1});
                }
                follow = follow.failure;
            }
        }
        return results;
    }
}
