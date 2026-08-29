package org.drozdek.compression.applications;

import java.util.List;
import org.drozdek.compression.LzwCompression;

/// Dictionary-based file compressor using LZW.
///
/// Text content is compressed to a stream of dictionary codes by the LZW
/// encoder and reconstructed via the matching decoder, with a compression
/// ratio reported for sizing estimates.
///
/// **Real-world use case:** GIF/TIFF and Unix `compress`-style lossless text
/// compression utilities with high repetition.
///
/// Complexity Analysis:
/// Time Complexity: O(n) for both encode and decode of n symbols
/// Auxiliary Space: O(d) for the LZW dictionary
///
/// Bibliography:
///
/// - T.A. Welch. *A Technique for High-Performance Data Compression*. IEEE Computer, 1984.
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 11.
///
/// @see LzwCompression
public final class LzwFileCompressor {

    private LzwFileCompressor() {
        // do nothing
    }

    /// Compresses text into a list of LZW dictionary codes.
    ///
    /// @param content text to compress
    /// @return list of integer codes
    public static List<Integer> compress(String content) {
        return LzwCompression.compress(content);
    }

    /// Reconstructs text from LZW dictionary codes.
    ///
    /// @param codes dictionary codes produced by {@link #compress(String)}
    /// @return the original text
    public static String decompress(List<Integer> codes) {
        return LzwCompression.decompress(codes);
    }

    /// Returns the compressed-to-original ratio as the number of codes per char.
    ///
    /// @param content text to measure
    /// @return codes.size() / content.length(); 0 for empty input
    public static double compressionRatio(String content) {
        if (content == null || content.isEmpty()) {
            return 0.0;
        }
        return (double) LzwCompression.compress(content).size() / content.length();
    }
}
