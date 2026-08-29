package org.drozdek.compression.applications;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LzwFileCompressorTest {

    @Test
    @DisplayName("Round-trips a repetitive string losslessly")
    void compressDecompress_roundTrip() {
        String original = "abababababababab";
        assertEquals(original, LzwFileCompressor.decompress(LzwFileCompressor.compress(original)));
    }

    @Test
    @DisplayName("Repetitive text compresses below one code per character")
    void compressionRatio_belowOne() {
        assertTrue(LzwFileCompressor.compressionRatio("aaaaaaaaaaaaaaaaaaaaaaaa") < 1.0);
    }
}
