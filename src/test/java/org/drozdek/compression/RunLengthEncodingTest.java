package org.drozdek.compression;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RunLengthEncodingTest {

    @Test
    @DisplayName("Compression collapses long repeated runs")
    void compress_collapsesRuns() {
        assertEquals("5:abb3:c", RunLengthEncoding.compress("aaaaabbccc"));
    }

    @Test
    @DisplayName("Short runs are left untouched")
    void compress_shortRunsUntouched() {
        assertEquals("ab", RunLengthEncoding.compress("ab"));
    }

    @Test
    @DisplayName("Expansion restores the original text")
    void expand_restoresOriginal() {
        assertEquals("aaaaabbccc", RunLengthEncoding.expand("5:abb3:c"));
    }

    @Test
    @DisplayName("Round trip preserves the input")
    void roundTrip_preservesInput() {
        String input = "aaabbbccdeeeefff";
        assertEquals(input, RunLengthEncoding.expand(RunLengthEncoding.compress(input)));
    }

    @Test
    @DisplayName("Round trip preserves input containing digits")
    void roundTrip_inputWithDigits() {
        String input = "aaaaabbbbbcccccddeeeffff0000011111";
        assertEquals(input, RunLengthEncoding.expand(RunLengthEncoding.compress(input)));
    }

    @Test
    @DisplayName("Round trip preserves literal colons")
    void roundTrip_literalColons() {
        String input = "aa::bb:::ccc";
        assertEquals(input, RunLengthEncoding.expand(RunLengthEncoding.compress(input)));
    }

    @Test
    @DisplayName("Empty input round trips cleanly")
    void roundTrip_emptyInput() {
        assertEquals("", RunLengthEncoding.expand(RunLengthEncoding.compress("")));
    }

    @Test
    @DisplayName("Byte tokens encode and decode a run")
    void encodeBytes_roundTrip() {
        byte[] data = {1, 1, 1, 2, 2, 3};
        List<Integer> tokens = RunLengthEncoding.encodeBytes(data);
        assertArrayEquals(data, RunLengthEncoding.decodeBytes(tokens));
    }

    @Test
    @DisplayName("Multi-digit run counts are handled")
    void expand_multidigitCounts() {
        assertEquals("z".repeat(10), RunLengthEncoding.expand("10:z"));
    }
}
