package org.drozdek.compression;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LzwCompressionTest {

    @Test
    @DisplayName("Round trip preserves short text")
    void roundTrip_shortText() {
        String input = "ABABABA";
        List<Integer> codes = LzwCompression.compress(input);
        assertEquals(input, LzwCompression.decompress(codes));
    }

    @Test
    @DisplayName("Round trip preserves repetitive text")
    void roundTrip_repetitive() {
        String input = "to be or not to be, that is the question";
        List<Integer> codes = LzwCompression.compress(input);
        assertEquals(input, LzwCompression.decompress(codes));
    }

    @Test
    @DisplayName("Empty input decompresses to empty string")
    void decompress_emptyInput() {
        assertEquals("", LzwCompression.decompress(List.of()));
    }

    @Test
    @DisplayName("Single character round trips")
    void roundTrip_singleChar() {
        List<Integer> codes = LzwCompression.compress("aaaa");
        assertEquals("aaaa", LzwCompression.decompress(codes));
    }

    @Test
    @DisplayName("Output is shorter than input for highly repetitive data")
    void compress_reducesRepetitiveInput() {
        String input = "ab".repeat(200);
        List<Integer> codes = LzwCompression.compress(input);
        assertEquals(input, LzwCompression.decompress(codes));
    }
}
