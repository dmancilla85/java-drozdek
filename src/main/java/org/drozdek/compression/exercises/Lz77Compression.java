package org.drozdek.compression.exercises;

import java.util.ArrayList;
import java.util.List;

/// LZ77 sliding-window compression and decompression.
///
/// Encodes the input by looking for the longest match between the current
/// position and a preceding sliding window, emitting either a literal or a
/// (distance, length, next) triple. Power-of-two window sizes simplify the
/// bit packing of the distance field.
///
/// **Real-world use case:** Foundation of DEFLATE (ZIP/gzip), PNG, and
/// streaming compressors that operate on a fixed-size look-behind window.
///
/// Complexity Analysis:
/// Time Complexity: O(n * window) worst case for compression, O(n) to expand
/// Auxiliary Space: O(window) for the sliding buffer
///
/// Bibliography:
///
/// - J. Ziv and A. Lempel. *A Universal Algorithm for Sequential Data
///   Compression*. IEEE Transactions on Information Theory, 1977.
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 11.
public final class Lz77Compression {

    /// A single LZ77 token: either a literal or a distance/length triple.
    public static final class Token {
        private final boolean literal;
        private final char value;
        private final int distance;
        private final int length;

        private Token(char value) {
            this.literal = true;
            this.value = value;
            this.distance = 0;
            this.length = 0;
        }

        private Token(int distance, int length) {
            this.literal = false;
            this.value = 0;
            this.distance = distance;
            this.length = length;
        }

        /// Returns whether this token is a raw literal character.
        ///
        /// @return {@code true} for literals, {@code false} for matches
        public boolean isLiteral() {
            return literal;
        }

        /// Returns the literal character (when {@link #isLiteral()}).
        ///
        /// @return the character, or 0 for match tokens
        public char getValue() {
            return value;
        }

        /// Returns the match distance back into the window.
        ///
        /// @return distance, or 0 for literals
        public int getDistance() {
            return distance;
        }

        /// Returns the match length.
        ///
        /// @return length, or 0 for literals
        public int getLength() {
            return length;
        }
    }

    private Lz77Compression() {
        // do nothing
    }

    /// Compresses the input using a default window size of 16.
    ///
    /// @param input text to compress
    /// @return list of LZ77 tokens
    public static List<Token> compress(String input) {
        return compress(input, 16);
    }

    /// Compresses the input using the given look-behind window size.
    ///
    /// @param input  text to compress
    /// @param window size of the sliding look-behind buffer
    /// @return list of LZ77 tokens
    public static List<Token> compress(String input, int window) {
        List<Token> tokens = new ArrayList<>();
        int position = 0;
        while (position < input.length()) {
            int bestLength = 0;
            int bestDistance = 0;
            int start = Math.max(0, position - window);
            for (int back = position - 1; back >= start; back--) {
                int length = 0;
                while (position + length < input.length()
                        && input.charAt(back + length) == input.charAt(position + length)) {
                    length++;
                }
                if (length > bestLength) {
                    bestLength = length;
                    bestDistance = position - back;
                }
            }
            if (bestLength > 0) {
                tokens.add(new Token(bestDistance, bestLength));
                position += bestLength;
            } else {
                tokens.add(new Token(input.charAt(position)));
                position++;
            }
        }
        return tokens;
    }

    /// Expands a token list back to the original text.
    ///
    /// @param tokens compressed tokens from {@link #compress(String, int)}
    /// @return the reconstructed string
    public static String decompress(List<Token> tokens) {
        StringBuilder output = new StringBuilder();
        for (Token token : tokens) {
            if (token.isLiteral()) {
                output.append(token.getValue());
            } else {
                int from = output.length() - token.getDistance();
                for (int i = 0; i < token.getLength(); i++) {
                    output.append(output.charAt(from + i));
                }
            }
        }
        return output.toString();
    }
}
