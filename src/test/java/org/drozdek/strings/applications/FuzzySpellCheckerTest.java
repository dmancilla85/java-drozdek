package org.drozdek.strings.applications;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FuzzySpellCheckerTest {

    @Test
    @DisplayName("Suggests dictionary words within the distance threshold")
    void suggestions_withinThreshold() {
        FuzzySpellChecker checker = new FuzzySpellChecker(List.of("cat", "car", "dog", "cart"), 1);
        assertEquals(List.of("cat", "car", "cart"), checker.suggestions("car"));
    }

    @Test
    @DisplayName("Reports exact edit distance")
    void distance_computes() {
        FuzzySpellChecker checker = new FuzzySpellChecker(List.of("kitten"), 3);
        assertEquals(3, checker.distance("kitten", "sitting"));
    }

    @Test
    @DisplayName("Identifies exact dictionary words")
    void isCorrect_exact() {
        FuzzySpellChecker checker = new FuzzySpellChecker(List.of("apple"), 1);
        assertTrue(checker.isCorrect("apple"));
        assertFalse(checker.isCorrect("aple"));
    }
}
