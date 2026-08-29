package org.drozdek.trees.applications;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlagiarismDetectorTest {

    private static final String REFERENCE = "the quick brown fox jumps over the lazy dog";

    @Test
    @DisplayName("Detects a verbatim phrase from the reference")
    void containsPhrase_present() {
        PlagiarismDetector detector = new PlagiarismDetector(REFERENCE);
        assertTrue(detector.containsPhrase("quick brown"));
        assertTrue(detector.containsPhrase("lazy dog"));
        assertTrue(detector.containsPhrase("fox"));
    }

    @Test
    @DisplayName("Rejects a phrase not present in the reference")
    void containsPhrase_absent() {
        PlagiarismDetector detector = new PlagiarismDetector(REFERENCE);
        assertFalse(detector.containsPhrase("fast fox"));
    }

    @Test
    @DisplayName("Similarity reflects the fraction of shared words")
    void similarity_partialMatch() {
        PlagiarismDetector detector = new PlagiarismDetector(REFERENCE);
        assertEquals(1.0, detector.similarity("the fox"), 0.001);
        assertEquals(0.5, detector.similarity("purple fox"), 0.001);
        assertEquals(0.0, detector.similarity("purple elephant"), 0.001);
    }
}
