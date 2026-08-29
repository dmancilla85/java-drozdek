package org.drozdek.compression.exercises;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class Lz77CompressionTest {

    @Test
    @DisplayName("Round trip preserves repetitive text")
    void roundTrip_repetitive() {
        String input = "abcabcabcabcabcabc";
        List<Lz77Compression.Token> tokens = Lz77Compression.compress(input);
        assertEquals(input, Lz77Compression.decompress(tokens));
    }

    @Test
    @DisplayName("Round trip preserves the default window")
    void roundTrip_customWindow() {
        String input = "the quick brown fox jumps over the lazy dog";
        List<Lz77Compression.Token> tokens = Lz77Compression.compress(input, 32);
        assertEquals(input, Lz77Compression.decompress(tokens));
    }

    @Test
    @DisplayName("Repeated substrings produce at least one match token")
    void compress_createsMatchTokens() {
        List<Lz77Compression.Token> tokens = Lz77Compression.compress("aaaaaa", 16);
        assertTrue(tokens.stream().anyMatch(t -> !t.isLiteral()));
    }

    @Test
    @DisplayName("Distinct characters produce only literals")
    void compress_distinctInputYieldsLiterals() {
        List<Lz77Compression.Token> tokens = Lz77Compression.compress("abcdef", 16);
        assertFalse(tokens.stream().anyMatch(t -> !t.isLiteral()));
    }

    @Test
    @DisplayName("Empty input yields no tokens and expands to empty")
    void roundTrip_emptyInput() {
        List<Lz77Compression.Token> tokens = Lz77Compression.compress("");
        assertEquals("", Lz77Compression.decompress(tokens));
    }
}
