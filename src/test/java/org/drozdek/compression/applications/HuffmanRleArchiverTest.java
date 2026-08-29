package org.drozdek.compression.applications;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HuffmanRleArchiverTest {

    @Test
    @DisplayName("Compressed pipeline round trips the original text")
    void roundTrip_restoresOriginal() {
        HuffmanRleArchiver archiver = new HuffmanRleArchiver();
        String input = "aaaaabbbbbcccccddeeeffff0000011111";
        HuffmanRleArchiver.CompressionResult result = archiver.compress(input);
        assertEquals(input, archiver.decompress(result));
    }

    @Test
    @DisplayName("Round trip with a short mixed string")
    void roundTrip_shortMixed() {
        HuffmanRleArchiver archiver = new HuffmanRleArchiver();
        String input = "hello world hello world";
        HuffmanRleArchiver.CompressionResult result = archiver.compress(input);
        assertEquals(input, archiver.decompress(result));
    }

    @Test
    @DisplayName("RLE intermediate should collapse repeated runs")
    void compress_rleIntermediateCollapsesRuns() {
        HuffmanRleArchiver archiver = new HuffmanRleArchiver();
        HuffmanRleArchiver.CompressionResult result = archiver.compress("aaaaabbbbcccdde");
        assertEquals("5:a4:b3:cdde", result.getRleIntermediate());
    }

    @Test
    @DisplayName("Empty input round trips")
    void roundTrip_emptyInput() {
        HuffmanRleArchiver archiver = new HuffmanRleArchiver();
        HuffmanRleArchiver.CompressionResult result = archiver.compress("");
        assertEquals("", archiver.decompress(result));
    }
}
