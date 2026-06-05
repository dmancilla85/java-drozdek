package org.drozdek.trees;

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
        String output = tree.toString();
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

    @Test
    @DisplayName("Empty trie is empty")
    void emptyTrie() {
        Trie t = new Trie();
        assertTrue(t.isEmpty());
        assertEquals(0, t.size());
    }

    @Test
    @DisplayName("Insert into empty trie")
    void insertIntoEmpty() {
        Trie t = new Trie();
        t.insert("test");
        assertFalse(t.isEmpty());
        assertTrue(t.found("test"));
    }

    @Test
    @DisplayName("Insert single character word")
    void insertSingleChar() {
        Trie t = new Trie();
        t.insert("a");
        assertTrue(t.found("a"));
    }

    @Test
    @DisplayName("Found returns false for longer prefix not in trie")
    void foundLongerPrefix() {
        assertFalse(tree.found("helloworld"));
    }

    @Test
    @DisplayName("Print returns non-empty for populated trie")
    void printNotEmpty() {
        String output = tree.toString();
        assertTrue(output.length() > 0);
    }

    @Test
    @DisplayName("Empty trie print shows empty marker")
    void emptyTriePrint() {
        Trie t = new Trie();
        String output = t.toString();
        assertNotNull(output);
    }

    @Test
    @DisplayName("Insert duplicate does not throw")
    void insertDuplicate() {
        assertDoesNotThrow(() -> tree.insert("hello"));
        assertTrue(tree.found("hello"));
    }

    @Test
    @DisplayName("Found on leaf node returns correct suffix")
    void foundOnLeaf() {
        assertTrue(tree.found("hello"));
    }

    @Test
    @DisplayName("Found on non-leaf endOfWord returns correct")
    void foundOnNonLeafEndOfWord() {
        tree.insert("he");
        assertTrue(tree.found("he"));
    }

    @Test
    @DisplayName("Found on non-leaf non-endOfWord returns false")
    void foundOnNonLeafNonEndOfWord() {
        Trie t = new Trie();
        t.insert("hello");
        assertFalse(t.found("hel"));
    }

    @Test
    @DisplayName("Insert word sharing prefix with different continuation")
    void insertPrefixContinuation() {
        Trie t = new Trie();
        t.insert("abc");
        t.insert("abd");
        assertTrue(t.found("abc"));
        assertTrue(t.found("abd"));
    }

    @Test
    @DisplayName("Insert word where existing leaf suffix equals new suffix")
    void insertDuplicateSuffix() {
        Trie t = new Trie();
        t.insert("abc");
        assertDoesNotThrow(() -> t.insert("abc"));
    }

    @Test
    @DisplayName("Insert word with partial prefix overlap")
    void insertPartialOverlap() {
        Trie t = new Trie();
        t.insert("abcde");
        t.insert("abxyz");
        assertTrue(t.found("abcde"));
        assertTrue(t.found("abxyz"));
    }

    @Test
    @DisplayName("Insert word where new word is shorter than existing")
    void insertShorterWord() {
        Trie t = new Trie();
        t.insert("abcdef");
        t.insert("abc");
        assertTrue(t.found("abc"));
        assertTrue(t.found("abcdef"));
    }

    @Test
    @DisplayName("Found returns false when prefix matches but word is absent")
    void foundPrefixNotWord() {
        Trie t = new Trie();
        t.insert("test");
        assertFalse(t.found("te"));
    }

    @Test
    @DisplayName("Print empty trie returns empty marker")
    void printTrieEmpty() {
        Trie t = new Trie();
        assertEquals("<EMPTY>", t.printTrie());
    }

    @Test
    @DisplayName("Found with null pointer check in leaf path")
    void foundNullCheckInTrie() {
        Trie t = new Trie();
        t.insert("ab");
        assertFalse(t.found("ac"));
    }

    @Test
    @DisplayName("Found non-leaf non-endOfWord after split returns false")
    void foundNonLeafNonEndOfWordSplit() {
        Trie t = new Trie("hello");
        t.insert("he");
        assertFalse(t.found("h"));
    }

    @Test
    @DisplayName("Insert with leaf suffix fully consumed before new word ends")
    void insertSuffixConsumed() {
        Trie t = new Trie("ab");
        t.insert("abcdef");
        assertTrue(t.found("ab"));
        assertTrue(t.found("abcdef"));
    }

    @Test
    @DisplayName("Insert where new word ends before leaf suffix")
    void insertShorterLeafSuffix() {
        Trie t = new Trie("abcdef");
        t.insert("abc");
        assertTrue(t.found("abc"));
        assertTrue(t.found("abcdef"));
    }

    @Test
    @DisplayName("Insert into null pointer position at non-leaf")
    void insertNullPointerPosition() {
        Trie t = new Trie("cat");
        t.insert("car");
        t.insert("catx");
        assertTrue(t.found("cat"));
        assertTrue(t.found("car"));
        assertTrue(t.found("catx"));
    }
}
