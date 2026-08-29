package org.drozdek.compression;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/// Huffman coding: builds an optimal variable-length prefix code from a set
/// of symbol frequencies, then encodes and decodes text.
///
/// The algorithm repeatedly merges the two lowest-frequency nodes into a new
/// parent until a single tree remains. Each symbol receives a codeword whose
/// length is inversely proportional to its frequency, minimizing the expected
/// code length.
///
/// **Real-world use case:** Lossless compression in DEFLATE (ZIP/gzip), image
/// and audio formats, and file archival where symbol frequencies are known.
///
/// Complexity Analysis:
/// Time Complexity: O(n log n) to build the tree for n distinct symbols;
///                  O(L) to encode/decode a string of length L
/// Auxiliary Space: O(n) for the tree and code map
///
/// Bibliography:
///
/// - D.A. Huffman. *A Method for the Construction of Minimum-Redundancy Codes*.
///   Proceedings of the IRE, 1952. https://doi.org/10.1109/JRPROC.1952.273898
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 11.
public final class HuffmanCoding {

    private HuffmanCoding() {
        // do nothing
    }

    /// Computes the frequency of each character in the given text.
    ///
    /// @param text input text
    /// @return map of symbol to occurrence count
    public static Map<Character, Integer> countFrequencies(String text) {
        Map<Character, Integer> frequencies = new HashMap<>();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            frequencies.merge(c, 1, Integer::sum);
        }
        return frequencies;
    }

    /// Builds the Huffman tree from a frequency map.
    ///
    /// @param frequencies symbol-to-frequency map; may be empty
    /// @return the root of the Huffman tree, or {@code null} for empty input
    public static HuffmanNode buildTree(Map<Character, Integer> frequencies) {
        PriorityQueue<HuffmanNode> queue = new PriorityQueue<>();
        frequencies.forEach((symbol, count) -> queue.add(new HuffmanNode(symbol, count)));

        if (frequencies.size() == 1) {
            return new HuffmanNode(frequencies.keySet().iterator().next(), frequencies.values().iterator().next());
        }

        while (queue.size() > 1) {
            HuffmanNode first = queue.poll();
            HuffmanNode second = queue.poll();
            queue.add(new HuffmanNode(first, second));
        }

        return queue.poll();
    }

    /// Generates the bit-string code for every symbol in the tree.
    ///
    /// @param root root of the Huffman tree
    /// @return map of symbol to its binary codeword
    public static Map<Character, String> generateCodes(HuffmanNode root) {
        Map<Character, String> codes = new HashMap<>();
        if (root != null) {
            walk(root, "", codes);
        }
        return codes;
    }

    private static void walk(HuffmanNode node, String prefix, Map<Character, String> codes) {
        if (node.isLeaf()) {
            codes.put(node.getSymbol(), prefix.isEmpty() ? "0" : prefix);
            return;
        }
        walk(node.getLeft(), prefix + "0", codes);
        walk(node.getRight(), prefix + "1", codes);
    }

    /// Encodes the text into a single binary string using the given code map.
    ///
    /// Characters not present in the code map are skipped.
    ///
    /// @param text  text to encode
    /// @param codes symbol-to-codeword map
    /// @return concatenated binary string
    public static String encode(String text, Map<Character, String> codes) {
        StringBuilder encoded = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            String code = codes.get(c);
            if (code != null) {
                encoded.append(code);
            }
        }
        return encoded.toString();
    }

    /// Decodes a binary string back to the original text using the tree root.
    ///
    /// @param encoded binary string of 0s and 1s
    /// @param root    root of the Huffman tree
    /// @return the decoded text
    public static String decode(String encoded, HuffmanNode root) {
        StringBuilder decoded = new StringBuilder();
        HuffmanNode node = root;
        for (int i = 0; i < encoded.length(); i++) {
            node = encoded.charAt(i) == '0' ? node.getLeft() : node.getRight();
            if (node.isLeaf()) {
                decoded.append(node.getSymbol());
                node = root;
            }
        }
        return decoded.toString();
    }
}
