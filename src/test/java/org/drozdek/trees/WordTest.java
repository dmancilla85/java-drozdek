package org.drozdek.trees;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WordTest {

    @Test
    @DisplayName("Create word and check toString")
    void createWord() {
        Word w = new Word("hello");
        assertTrue(w.toString().contains("hello"));
        assertEquals(1, w.getFreq());
    }

    @Test
    @DisplayName("Compare words")
    void compareTo() {
        Word a = new Word("apple");
        Word b = new Word("banana");
        assertTrue(a.compareTo(b) < 0);
        assertTrue(b.compareTo(a) > 0);
        assertEquals(0, a.compareTo(new Word("apple")));
    }

    @Test
    @DisplayName("Increment frequency")
    void incrementFrequency() {
        Word w = new Word("test");
        w.incrementFreq();
        assertEquals(2, w.getFreq());
        assertTrue(w.toString().contains("(2)"));
    }

    @Test
    @DisplayName("Print method executes without error")
    void testPrint() {
        Word w = new Word("hello");
        assertDoesNotThrow(w::print);
    }

    @Test
    @DisplayName("Equals returns true for same word")
    void equalsSame() {
        Word a = new Word("hello");
        Word b = new Word("hello");
        assertEquals(a, b);
    }

    @Test
    @DisplayName("Equals returns false for different words")
    void equalsDifferent() {
        Word a = new Word("hello");
        Word b = new Word("world");
        assertNotEquals(a, b);
    }

    @Test
    @DisplayName("Equals returns false for null")
    void equalsNull() {
        Word a = new Word("hello");
        assertNotEquals(null, a);
    }

    @Test
    @DisplayName("Equals returns true for same reference")
    void equalsReflexive() {
        Word a = new Word("hello");
        assertEquals(a, a);
    }

    @Test
    @DisplayName("Hash code is consistent with equals")
    void hashCodeConsistent() {
        Word a = new Word("hello");
        Word b = new Word("hello");
        assertEquals(a.hashCode(), b.hashCode());
    }
}
