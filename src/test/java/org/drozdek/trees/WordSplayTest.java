package org.drozdek.trees;

import org.drozdek.trees.Word;
import org.drozdek.trees.WordSplay;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class WordSplayTest {
    @Test
    @DisplayName("Run with empty input")
    void runWithEmptyInput() {
        WordSplay ws = new WordSplay();
        InputStream in = new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8));
        assertDoesNotThrow(() -> ws.run(in, "test"));
    }

    @Test
    @DisplayName("Run with simple text")
    void runWithSimpleText() {
        WordSplay ws = new WordSplay();
        InputStream in = new ByteArrayInputStream("hello world".getBytes(StandardCharsets.UTF_8));
        assertDoesNotThrow(() -> ws.run(in, "test"));
    }

    @Test
    @DisplayName("Run with repeated words increments frequency")
    void runWithRepeatedWords() {
        WordSplay ws = new WordSplay();
        InputStream in = new ByteArrayInputStream("hello hello world".getBytes(StandardCharsets.UTF_8));
        assertDoesNotThrow(() -> ws.run(in, "test"));
        Word hello = (Word) ws.search(new Word("HELLO"));
        assertNotNull(hello);
        assertEquals(2, hello.freq);
        assertEquals(2, ws.size());
    }

    @Test
    @DisplayName("Run with triple repeated word")
    void runWithTripleWord() {
        WordSplay ws = new WordSplay();
        InputStream in = new ByteArrayInputStream("hello hello hello".getBytes(StandardCharsets.UTF_8));
        assertDoesNotThrow(() -> ws.run(in, "test"));
        Word hello = (Word) ws.search(new Word("HELLO"));
        assertNotNull(hello);
        assertEquals(3, hello.freq);
        assertEquals(1, ws.size());
    }

    @Test
    @DisplayName("Search existing word with different Word object")
    void searchCompareTo() {
        WordSplay ws = new WordSplay();
        InputStream in = new ByteArrayInputStream("hello world".getBytes(StandardCharsets.UTF_8));
        assertDoesNotThrow(() -> ws.run(in, "test"));
        Word found = (Word) ws.search(new Word("HELLO"));
        assertNotNull(found);
        assertTrue(found.toString().startsWith("HELLO"));
    }

    @Test
    @DisplayName("Run with non-letter characters")
    void runWithNonLetters() {
        WordSplay ws = new WordSplay();
        InputStream in = new ByteArrayInputStream("hello, world! test123".getBytes(StandardCharsets.UTF_8));
        assertDoesNotThrow(() -> ws.run(in, "test"));
    }

    @Test
    @DisplayName("Run with single word")
    void runWithSingleWord() {
        WordSplay ws = new WordSplay();
        InputStream in = new ByteArrayInputStream("hello".getBytes(StandardCharsets.UTF_8));
        assertDoesNotThrow(() -> ws.run(in, "test"));
    }

    @Test
    @DisplayName("Run with words and numbers")
    void runWithNumbers() {
        WordSplay ws = new WordSplay();
        InputStream in = new ByteArrayInputStream("123 hello 456".getBytes(StandardCharsets.UTF_8));
        assertDoesNotThrow(() -> ws.run(in, "test"));
    }

    @Test
    @DisplayName("Run with only non-letters")
    void runWithOnlyNonLetters() {
        WordSplay ws = new WordSplay();
        InputStream in = new ByteArrayInputStream("123 456 789".getBytes(StandardCharsets.UTF_8));
        assertDoesNotThrow(() -> ws.run(in, "test"));
    }
}
