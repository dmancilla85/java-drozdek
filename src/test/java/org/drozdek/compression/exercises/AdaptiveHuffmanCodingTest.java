package org.drozdek.compression.exercises;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AdaptiveHuffmanCodingTest {

    @Test
    @DisplayName("Produce a codeword for every input symbol")
    void encode_emitsOneCodePerSymbol() {
        List<String> codes = AdaptiveHuffmanCoding.encode("abcabc");
        assertEquals(6, codes.size());
    }

    @Test
    @DisplayName("Skewed distributions compress more than uniform ones")
    void encodedBitLength_shrinksAsDistributionSkews() {
        int skewed = AdaptiveHuffmanCoding.encodedBitLength("aaaaaaaaab");
        int uniform = AdaptiveHuffmanCoding.encodedBitLength("abcdefghij");
        assertTrue(skewed < uniform);
    }

    @Test
    @DisplayName("Empty input produces no codewords")
    void encode_emptyInput() {
        assertTrue(AdaptiveHuffmanCoding.encode("").isEmpty());
    }

    @Test
    @DisplayName("All emitted codewords are non-empty for real input")
    void encode_noEmptyCodesForRealInput() {
        List<String> codes = AdaptiveHuffmanCoding.encode("hello");
        assertFalse(codes.contains(""));
    }

    @Test
    @DisplayName("Repetitive input produces a bounded total length")
    void encodedBitLength_repetitiveIsBounded() {
        int length = AdaptiveHuffmanCoding.encodedBitLength("aaaa");
        assertTrue(length >= 4 && length <= 8);
    }
}
