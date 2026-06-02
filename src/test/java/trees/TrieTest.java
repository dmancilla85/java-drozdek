package trees;

import org.drozdek.commons.LoggerService;
import org.drozdek.trees.Trie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrieTest {

    Trie tree;

    @BeforeEach
    void setUp(){
        tree=new Trie("hello");
    }

    @Test
    @DisplayName("New trie should not be empty after init")
    void notEmpty() {
        assertFalse(tree.isEmpty());
    }

    @Test
    @DisplayName("Insert and find words")
    void insertAndFind() {
        tree.insert("help");
        tree.insert("world");
        assertTrue(tree.found("hello"));
        assertTrue(tree.found("help"));
        assertFalse(tree.found("wor"));
    }

    @Test
    @DisplayName("Found returns false for non-existent word")
    void foundNonExistent() {
        assertFalse(tree.found("xyz"));
    }

    @Test
    @DisplayName("Check size after inserts")
    void size() {
        assertTrue(tree.size() >= 1);
    }

    @Test
    @DisplayName("Print trie")
    void printTrie() {
        String output = tree.print();
        assertNotNull(output);
        LoggerService.logInfo(output);
    }

    @Test
    @DisplayName("Print old format trie")
    void printOldTrie() {
        String output = tree.printTrie();
        assertNotNull(output);
        LoggerService.logWarning(output);
    }

    @Test
    @DisplayName("Found returns false for word not in trie")
    void foundFalse() {
        boolean ret = tree.found("wor");
        assertFalse(ret, "The result is not the expected.");
    }
}
