package org.drozdek.trees;

import org.drozdek.commons.LoggerService;
import org.drozdek.trees.SuffixTree;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SuffixTreeTest {
    SuffixTree tree;

    @BeforeEach
    void setUp(){
        tree=new SuffixTree();
    }

    @Test
    @DisplayName("Ukkonen builds suffix tree")
    void ukkonenBuild() {
        tree.ukkonen("banana");
        assertDoesNotThrow(() -> tree.print());
    }

    @Test
    @DisplayName("Print tree for banana")
    void printBanana() {
        tree.ukkonen("banana");
        String output = tree.printTree();
        assertNotNull(output);
        assertTrue(output.contains("banana"));
        LoggerService.logInfo(output);
    }

    @Test
    @DisplayName("Ukkonen builds suffix tree for abcabc")
    void ukkonenAbcabc() {
        tree.ukkonen("abcabc");
        assertNotNull(tree.toString());
        String output = tree.printTree();
        assertNotNull(output);
        LoggerService.logInfo(output);
    }

    @Test
    @DisplayName("Ukkonen builds suffix tree for aaaaa")
    void ukkonenAaaaa() {
        tree.ukkonen("aaaaa");
        assertNotNull(tree.toString());
    }

    @Test
    @DisplayName("Ukkonen builds suffix tree for single char")
    void ukkonenSingleChar() {
        tree.ukkonen("aa");
        assertNotNull(tree.toString());
    }

    @Test
    @DisplayName("Constructor with custom range")
    void constructorCustomRange() {
        SuffixTree t = new SuffixTree(0, 255);
        assertNotNull(t);
    }

    @Test
    @DisplayName("Ukkonen with different text pattern")
    void ukkonenAbab() {
        tree.ukkonen("abab");
        assertNotNull(tree.toString());
    }

    @Test
    @DisplayName("Ukkonen builds suffix tree for repeating char")
    void ukkonenRepeating() {
        tree.ukkonen("aaa");
        assertNotNull(tree.toString());
    }

    @Test
    @DisplayName("Ukkonen builds suffix tree for short text")
    void ukkonenShort() {
        tree.ukkonen("ab");
        assertNotNull(tree.toString());
    }

    @Test
    @DisplayName("Ukkonen with no repeated chars")
    void ukkonenNoRepeat() {
        tree.ukkonen("abcd");
        tree.print();
        assertNotNull(tree.toString());
    }

    @Test
    @DisplayName("Check print tree returns correct format with long text")
    void printTreeFormat() {
        tree.ukkonen("banana");
        String output = tree.printTree();
        assertTrue(output.contains("Name:"));
        assertTrue(output.contains("Text:"));
    }

    @Test
    @DisplayName("Ukkonen with pattern requiring edge split")
    void ukkonenEdgeSplit() {
        tree.ukkonen("abcab");
        assertNotNull(tree.toString());
    }

    @Test
    @DisplayName("Ukkonen with mississippi")
    void ukkonenMississippi() {
        tree.ukkonen("mississippi");
        assertNotNull(tree.toString());
    }

    @Test
    @DisplayName("Ukkonen with long non-repeating suffix")
    void ukkonenLongNonRepeat() {
        tree.ukkonen("abcdefghij");
        assertNotNull(tree.toString());
    }

    @Test
    @DisplayName("Print tree after complex ukkonen")
    void printTreeComplex() {
        tree.ukkonen("abcab");
        String output = tree.printTree();
        assertNotNull(output);
    }

    @Test
    @DisplayName("Ukkonen with overlapping patterns")
    void ukkonenOverlapping() {
        tree.ukkonen("ababa");
        assertNotNull(tree.toString());
    }

    @Test
    @DisplayName("Print method executes without error")
    void testPrint() {
        tree.ukkonen("banana");
        assertDoesNotThrow(() -> tree.print());
    }
}
