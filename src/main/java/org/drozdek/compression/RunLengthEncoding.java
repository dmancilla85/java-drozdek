package org.drozdek.compression;

import java.util.ArrayList;
import java.util.List;

/// Run-length encoding (RLE): collapses consecutive repeated characters into
/// compact (count, symbol) pairs.
///
/// Compression iterates the input replacing runs of two or more identical
/// characters with a count followed by the character. Single characters are
/// emitted as-is so that compression never enlarges most inputs.
///
/// **Real-world use case:** Storing images with large uniform areas (simple
/// bitmap formats, fax transmission) and redundant configuration or log data.
///
/// Complexity Analysis:
/// Time Complexity: O(n) for both compression and expansion
/// Auxiliary Space: O(n) for the encoded/decoded output
///
/// Bibliography:
///
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 11.
public final class RunLengthEncoding {

    private RunLengthEncoding() {
        // do nothing
    }

    /// Compresses the input string using run-length encoding.
    ///
    /// Runs of three or more equal characters are written as
    /// {@code count:char}; otherwise each character is emitted as-is. A
    /// literal {@code ':'} is escaped as {@code "::"} so the format remains
    /// unambiguous for any input, including strings that contain digits.
    ///
    /// @param input text to compress
    /// @return run-length encoded string
    public static String compress(String input) {
        StringBuilder result = new StringBuilder();
        int i = 0;
        while (i < input.length()) {
            int j = i;
            while (j < input.length() && input.charAt(j) == input.charAt(i)) {
                j++;
            }
            int run = j - i;
            if (run >= 3) {
                result.append(run).append(':').append(input.charAt(i));
            } else {
                for (int k = i; k < j; k++) {
                    appendLiteral(result, input.charAt(i));
                }
            }
            i = j;
        }
        return result.toString();
    }

    private static void appendLiteral(StringBuilder builder, char c) {
        if (c == ':') {
            builder.append("::");
        } else {
            builder.append(c);
        }
    }

    /// Expands a run-length encoded string back to its original form.
    ///
    /// A run is recognized as decimal digits followed by {@code ':'} and a
    /// symbol; a doubled {@code "::"} denotes a literal colon.
    ///
    /// @param encoded run-length encoded text
    /// @return the expanded text
    public static String expand(String encoded) {
        StringBuilder result = new StringBuilder();
        int i = 0;
        while (i < encoded.length()) {
            char c = encoded.charAt(i);
            if (c == ':' && i + 1 < encoded.length() && encoded.charAt(i + 1) == ':') {
                result.append(':');
                i += 2;
            } else if (Character.isDigit(c)) {
                int count = readCount(encoded, i);
                i += digitCount(encoded, i);
                if (i < encoded.length() && encoded.charAt(i) == ':') {
                    char symbol = encoded.charAt(i + 1);
                    repeat(result, symbol, count);
                    i += 2;
                } else {
                    result.append(c);
                    i++;
                }
            } else {
                result.append(c);
                i++;
            }
        }
        return result.toString();
    }

    private static int readCount(String encoded, int start) {
        int count = 0;
        int i = start;
        while (i < encoded.length() && Character.isDigit(encoded.charAt(i))) {
            count = count * 10 + (encoded.charAt(i) - '0');
            i++;
        }
        return count;
    }

    private static int digitCount(String encoded, int start) {
        int count = 0;
        int i = start;
        while (i < encoded.length() && Character.isDigit(encoded.charAt(i))) {
            count++;
            i++;
        }
        return count;
    }

    private static void repeat(StringBuilder builder, char symbol, int count) {
        for (int k = 0; k < count; k++) {
            builder.append(symbol);
        }
    }

    /// Encodes a byte array into a list of (count, value) run-length tokens.
    ///
    /// @param data byte array to encode
    /// @return list where even indices are run lengths and odd indices values
    public static List<Integer> encodeBytes(byte[] data) {
        List<Integer> tokens = new ArrayList<>();
        int i = 0;
        while (i < data.length) {
            int j = i;
            while (j < data.length && data[j] == data[i]) {
                j++;
            }
            tokens.add(j - i);
            tokens.add((int) data[i]);
            i = j;
        }
        return tokens;
    }

    /// Decodes run-length tokens back into a byte array.
    ///
    /// @param tokens list of interleaved (count, value) pairs
    /// @return the reconstructed byte array
    public static byte[] decodeBytes(List<Integer> tokens) {
        List<Byte> result = new ArrayList<>();
        for (int i = 0; i + 1 < tokens.size(); i += 2) {
            int count = tokens.get(i);
            byte value = tokens.get(i + 1).byteValue();
            for (int k = 0; k < count; k++) {
                result.add(value);
            }
        }
        byte[] out = new byte[result.size()];
        for (int i = 0; i < out.length; i++) {
            out[i] = result.get(i);
        }
        return out;
    }
}
