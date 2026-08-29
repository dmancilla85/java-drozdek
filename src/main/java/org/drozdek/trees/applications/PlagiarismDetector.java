package org.drozdek.trees.applications;

import org.drozdek.trees.SuffixTree;
import org.drozdek.trees.nodes.SuffixTreeNode;

/// Plagiarism detector built on a Ukkonen suffix tree.
///
/// A reference document is compiled once into a suffix tree. Because a suffix
/// tree indexes every substring of the reference, a query phrase can be checked
/// for presence in O(m) time by walking down from the root. This is the same
/// machinery used by large-scale copy detection over a corpus.
///
/// **Real-world use case:** Academic and editorial plagiarism screening, and
/// duplicate-text detection in search engines and revision repositories.
///
/// Complexity Analysis:
/// Time Complexity: O(n) build, O(m) per phrase probe
/// Auxiliary Space: O(n) for the suffix tree
///
/// Bibliography:
///
/// - E. Ukkonen. *On-line construction of suffix trees*. Algorithmica, 1995. https://doi.org/10.1007/BF01206331
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 13.
///
/// @see SuffixTree
public class PlagiarismDetector extends SuffixTree {

    /// Builds a detector over the supplied reference document.
    ///
    /// @param reference the document being screened for copied phrases
    public PlagiarismDetector(String reference) {
        super();
        ukkonen(reference);
    }

    /// Checks whether the query phrase occurs verbatim in the reference.
    ///
    /// @param phrase the phrase to search for
    /// @return true if the phrase is a substring of the reference
    public boolean containsPhrase(String phrase) {
        if (phrase == null || phrase.isEmpty()) {
            return true;
        }
        return match(root, phrase);
    }

    /// Returns the fraction of the query phrase that appears verbatim, used as
    /// a simple similarity score. A single shared word yields a positive ratio.
    ///
    /// @param candidate the text being checked for overlap
    /// @return fraction of words found verbatim, in the range [0, 1]
    public double similarity(String candidate) {
        if (candidate == null || candidate.isBlank()) {
            return 0.0;
        }
        String[] words = candidate.trim().split("\\s+");
        int found = 0;
        for (String word : words) {
            if (containsPhrase(word)) {
                found++;
            }
        }
        return (double) found / words.length;
    }

    private boolean match(SuffixTreeNode node, String phrase) {
        if (phrase.isEmpty()) {
            return true;
        }
        int childIndex = phrase.charAt(0) - offset;
        int[] left = node.getLeft();
        int[] right = node.getRight();
        SuffixTreeNode[] descendants = node.getDescendants();
        if (childIndex < 0 || childIndex >= left.length || left[childIndex] == -1) {
            return false;
        }
        SuffixTreeNode child = descendants[childIndex];
        int labelStart = left[childIndex];
        int labelEnd = right[childIndex];
        int labelLen = labelEnd - labelStart + 1;
        int matched = 0;
        while (matched < phrase.length() && matched < labelLen
                && text.charAt(labelStart + matched) == phrase.charAt(matched)) {
            matched++;
        }
        if (matched == phrase.length()) {
            return true;
        }
        if (matched < labelLen) {
            return false;
        }
        if (child == null) {
            return false;
        }
        return match(child, phrase.substring(matched));
    }
}
