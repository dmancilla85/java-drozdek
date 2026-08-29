package org.drozdek.compression.applications;

import java.util.Map;
import org.drozdek.compression.HuffmanCoding;
import org.drozdek.compression.HuffmanNode;
import org.drozdek.compression.RunLengthEncoding;

/// Combined compression pipeline that applies run-length encoding as a
/// pre-pass and then Huffman-codes the result for additional compaction.
///
/// This mirrors the book's Chapter 11 case study: long repetitive runs are
/// first collapsed by RLE (often the dominant win on redundant data), and the
/// remaining symbol distribution is then entropy-coded with Huffman codes.
///
/// **Real-world use case:** Archival payload compaction for log files,
/// telemetry streams, and structured text where repeated runs co-occur with
/// an uneven character distribution.
///
/// Complexity Analysis:
/// Time Complexity: O(n log n) dominated by Huffman tree construction
/// Auxiliary Space: O(n) for the intermediate and final representations
///
/// Bibliography:
///
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 11.
public class HuffmanRleArchiver {

    /// Result of the compressed pipeline.
    public static final class CompressionResult {
        private final String encodedBits;
        private final Map<Character, String> codes;
        private final HuffmanNode tree;
        private final String rleIntermediate;

        private CompressionResult(String encodedBits, Map<Character, String> codes,
                                  HuffmanNode tree, String rleIntermediate) {
            this.encodedBits = encodedBits;
            this.codes = codes;
            this.tree = tree;
            this.rleIntermediate = rleIntermediate;
        }

        /// Returns the final Huffman-encoded bit string.
        ///
        /// @return binary string representation
        public String getEncodedBits() {
            return encodedBits;
        }

        /// Returns the symbol-to-codeword map used for encoding.
        ///
        /// @return code map
        public Map<Character, String> getCodes() {
            return codes;
        }

        /// Returns the Huffman tree root used for decoding.
        ///
        /// @return tree root
        public HuffmanNode getTree() {
            return tree;
        }

        /// Returns the RLE intermediate string before Huffman encoding.
        ///
        /// @return RLE-encoded intermediate
        public String getRleIntermediate() {
            return rleIntermediate;
        }
    }

    /// Compresses the input through RLE then Huffman coding.
    ///
    /// @param text plain text to compress
    /// @return a {@link CompressionResult} holding the encoded bit string and
    ///         the structures needed to reverse the process
    public CompressionResult compress(String text) {
        String rle = RunLengthEncoding.compress(text);
        Map<Character, Integer> frequencies = HuffmanCoding.countFrequencies(rle);
        HuffmanNode tree = HuffmanCoding.buildTree(frequencies);
        Map<Character, String> codes = HuffmanCoding.generateCodes(tree);
        String encoded = HuffmanCoding.encode(rle, codes);
        return new CompressionResult(encoded, codes, tree, rle);
    }

    /// Reverses the pipeline: Huffman-decodes the bit string, then expands the
    /// resulting RLE string back to the original text.
    ///
    /// @param result the compressed result from {@link #compress(String)}
    /// @return the reconstructed original text
    public String decompress(CompressionResult result) {
        String rle = HuffmanCoding.decode(result.getEncodedBits(), result.getTree());
        return RunLengthEncoding.expand(rle);
    }
}
