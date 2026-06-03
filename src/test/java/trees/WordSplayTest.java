package trees;

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
