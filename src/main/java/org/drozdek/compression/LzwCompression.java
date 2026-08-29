package org.drozdek.compression;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/// Lempel–Ziv–Welch (LZW) compression and decompression.
///
/// The compressor grows a dictionary of previously seen substrings on the
/// fly, replacing repeated substrings with their dictionary indices, while
/// the decompressor reconstructs the same dictionary from the index stream.
///
/// **Real-world use case:** Basis of GIF images, TIFF, and the Unix
/// {@code compress} tool; effective for text with many repeated patterns.
///
/// Complexity Analysis:
/// Time Complexity: O(n) for both compression and decompression of n symbols
/// Auxiliary Space: O(d) where d is the final dictionary size
///
/// Bibliography:
///
/// - T.A. Welch. *A Technique for High-Performance Data Compression*. IEEE
///   Computer, 1984. https://doi.org/10.1109/MC.1984.1659158
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 11.
public final class LzwCompression {

    /// Default dictionary capacity before the encoder stops adding entries.
    public static final int DEFAULT_MAX_ENTRIES = 65536;

    private LzwCompression() {
        // do nothing
    }

    /// Compresses a string to a list of dictionary codes.
    ///
    /// The initial dictionary maps every single character to its index.
    ///
    /// @param input text to compress
    /// @return list of integer codes
    public static List<Integer> compress(String input) {
        return compress(input, DEFAULT_MAX_ENTRIES);
    }

    /// Compresses a string with a configurable maximum dictionary size.
    ///
    /// @param input        text to compress
    /// @param maxEntries   dictionary capacity; once reached no new entries are added
    /// @return list of integer codes
    public static List<Integer> compress(String input, int maxEntries) {
        Map<String, Integer> dictionary = new HashMap<>();
        for (int i = 0; i < 256; i++) {
            dictionary.put(String.valueOf((char) i), i);
        }
        int nextCode = 256;

        List<Integer> output = new ArrayList<>();
        String current = "";
        for (int i = 0; i < input.length(); i++) {
            String next = current + input.charAt(i);
            if (dictionary.containsKey(next)) {
                current = next;
            } else {
                output.add(dictionary.get(current));
                if (nextCode < maxEntries) {
                    dictionary.put(next, nextCode++);
                }
                current = String.valueOf(input.charAt(i));
            }
        }
        if (!current.isEmpty()) {
            output.add(dictionary.get(current));
        }
        return output;
    }

    /// Decompresses a list of codes back to the original text.
    ///
    /// @param codes list of dictionary codes produced by {@link #compress(String)}
    /// @return the reconstructed string
    public static String decompress(List<Integer> codes) {
        Map<Integer, String> dictionary = new HashMap<>();
        for (int i = 0; i < 256; i++) {
            dictionary.put(i, String.valueOf((char) i));
        }
        int nextCode = 256;

        StringBuilder output = new StringBuilder();
        if (codes.isEmpty()) {
            return "";
        }

        String previous = dictionary.get(codes.get(0));
        output.append(previous);
        for (int i = 1; i < codes.size(); i++) {
            int code = codes.get(i);
            String entry = dictionary.containsKey(code) ? dictionary.get(code) : previous + previous.charAt(0);
            output.append(entry);
            dictionary.put(nextCode++, previous + entry.charAt(0));
            previous = entry;
        }
        return output.toString();
    }
}
