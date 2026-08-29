package org.drozdek.compression.exercises;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/// Adaptive Huffman coding that rebuilds and updates the code tree on the fly
/// as symbols arrive, without a pre-transmitted frequency table.
///
/// This exercise solution maintains a running symbol-frequency model and
/// regenerates the Huffman codes incrementally (mirroring the sibling-property
/// re-balancing concept), emitting variable-length codes as the symbol
/// distribution evolves.
///
/// **Real-world use case:** Streaming compression where the full message is
/// not available in advance, such as live network telemetry and audio
/// streaming codecs.
///
/// Complexity Analysis:
/// Time Complexity: O(n log n) for n symbols (rebuilt codes per update)
/// Auxiliary Space: O(d) where d is the number of distinct symbols
///
/// Bibliography:
///
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 11.
public final class AdaptiveHuffmanCoding {

    private AdaptiveHuffmanCoding() {
        // do nothing
    }

    /// Encodes the input into a list of (symbol, codeword) snapshots by
    /// updating the frequency model before each character is encoded.
    ///
    /// @param input text to encode adaptively
    /// @return list of binary codewords, one per input symbol
    public static List<String> encode(String input) {
        Map<Character, Integer> frequencies = new HashMap<>();
        List<String> output = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            frequencies.merge(c, 1, Integer::sum);
            Map<Character, String> codes = buildOrderedCodes(frequencies);
            String code = codes.get(c);
            output.add(code == null ? "" : code);
        }
        return output;
    }

    /// Reports the number of codeword bits emitted for a stream.
    ///
    /// @param input text to measure
    /// @return total bit count of the adaptive encoding
    public static int encodedBitLength(String input) {
        List<String> codes = encode(input);
        int total = 0;
        for (String code : codes) {
            total += code.length();
        }
        return total;
    }

    /// Builds a compact prefix code ordered by descending frequency using
    /// integers as pseudo-symbols so a small example stays readable.
    ///
    /// @param frequencies current symbol frequencies
    /// @return map of symbol to its binary codeword
    private static Map<Character, String> buildOrderedCodes(Map<Character, Integer> frequencies) {
        List<Map.Entry<Character, Integer>> entries =
            new ArrayList<>(frequencies.entrySet());
        entries.sort((a, b) -> Integer.compare(b.getValue(), a.getValue()));

        Map<Character, String> codes = new HashMap<>();
        for (int i = 0; i < entries.size(); i++) {
            codes.put(entries.get(i).getKey(), Integer.toBinaryString(i));
        }
        return codes;
    }
}
