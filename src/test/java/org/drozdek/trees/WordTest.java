package trees;

import org.drozdek.trees.Word;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WordTest {

    @Test
    @DisplayName("Create word and check toString")
    void createWord() {
        Word w = new Word("hello");
        assertTrue(w.toString().contains("hello"));
        assertEquals(1, w.freq);
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
        w.freq++;
        assertEquals(2, w.freq);
        assertTrue(w.toString().contains("(2)"));
    }
}
