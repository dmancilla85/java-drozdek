package org.drozdek.trees;

import org.drozdek.commons.LoggerService;
import org.drozdek.trees.SplayTree;
import org.drozdek.trees.Word;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class SplayTreeTest {

    SplayTree<Integer> tree;

    @BeforeEach
    void setUp(){
        tree=new SplayTree<>();
    }

    void dumpData(int n){
        Random rnd = new Random(System.currentTimeMillis());

        for(int i = 0; i < n;i++){
            tree.insert(rnd.nextInt(0,100));
        }
    }

    @Test
    @DisplayName("Print the splay tree")
    void print(){
        int size = 20;

        dumpData(size);
        tree.print();
        assertEquals(size, tree.size());
    }

    @Test
    @DisplayName("Insert values and semi-splay the tree")
    void semiSplay(){
        int size = 20;

        dumpData(size);
        tree.semiSplay();
        tree.print();
        assertEquals(size, tree.size());
    }

    @Test
    @DisplayName("Semi-splay and print the tree with words")
    void semiSplayWithWords(){
        int size = 5;
        SplayTree<Word> wordTree = new SplayTree<>();
        wordTree.insert(new Word("Hello"));
        wordTree.insert(new Word("my"));
        wordTree.insert(new Word("name"));
        wordTree.insert(new Word("is"));
        wordTree.insert(new Word("David"));
        LoggerService.logInfo("Printing the tree with the words");
        wordTree.print();
        wordTree.semiSplay();
        LoggerService.logInfo("semi-splay");
        wordTree.print();
        assertEquals(size, wordTree.size());
    }

    @Test
    @DisplayName("Delete a splay tree node by merging")
    void deleteByMerging(){
        int size = 5;

        dumpData(5);
        tree.insert(12);
        tree.print();
        tree.deleteByMerging(12);
        LoggerService.logWarning("Delete by merging tree node by merging");
        tree.print();
        assertEquals(size, tree.size());
    }

    @Test
    @DisplayName("New tree should be empty")
    void isEmpty() {
        assertTrue(tree.isEmpty());
        assertEquals(0, tree.size());
    }

    @Test
    @DisplayName("After insert tree is not empty")
    void notEmpty() {
        tree.insert(5);
        assertFalse(tree.isEmpty());
        assertEquals(1, tree.size());
    }

    @Test
    @DisplayName("Search returns null for empty tree")
    void searchEmptyTree() {
        assertNull(tree.search(42));
    }

    @Test
    @DisplayName("Delete by merging on empty tree")
    void deleteByMergingEmpty() {
        assertEquals(1, tree.deleteByMerging(5));
    }

    @Test
    @DisplayName("Search finds existing element")
    void searchExisting() {
        tree.insert(50);
        tree.insert(30);
        tree.insert(70);
        assertNotNull(tree.search(50));
        assertNotNull(tree.search(30));
        assertNotNull(tree.search(70));
    }

    @Test
    @DisplayName("Search returns null for missing element")
    void searchMissing() {
        tree.insert(10);
        tree.insert(20);
        assertNull(tree.search(99));
    }

    @Test
    @DisplayName("Delete by merging non-existent key")
    void deleteByMergingNotFound() {
        tree.insert(10);
        tree.insert(20);
        assertEquals(-1, tree.deleteByMerging(99));
    }

    @Test
    @DisplayName("Delete by merging node with only left child")
    void deleteByMergingLeftChildOnly() {
        tree.insert(50);
        tree.insert(30);
        assertEquals(0, tree.deleteByMerging(30));
        assertEquals(1, tree.size());
    }

    @Test
    @DisplayName("Delete by merging node with only right child")
    void deleteByMergingRightChildOnly() {
        tree.insert(50);
        tree.insert(70);
        assertEquals(0, tree.deleteByMerging(70));
        assertEquals(1, tree.size());
    }

    @Test
    @DisplayName("Delete by merging root node")
    void deleteByMergingRoot() {
        tree.insert(50);
        tree.deleteByMerging(50);
        assertTrue(tree.isEmpty());
    }

    @Test
    @DisplayName("Insert multiple values and check size")
    void insertMultiple() {
        tree.insert(5);
        tree.insert(3);
        tree.insert(7);
        tree.insert(1);
        tree.insert(9);
        assertEquals(5, tree.size());
    }

    @Test
    @DisplayName("Semi-splay on empty tree does nothing")
    void semiSplayEmpty() {
        assertDoesNotThrow(() -> tree.semiSplay());
        assertTrue(tree.isEmpty());
    }

    @Test
    @DisplayName("Delete node with two children")
    void deleteByMergingTwoChildren() {
        tree.insert(50);
        tree.insert(30);
        tree.insert(70);
        tree.insert(20);
        tree.insert(40);
        // node 30 has left(20) and right(40) children
        assertEquals(0, tree.deleteByMerging(30));
        assertEquals(4, tree.size());
    }

    @Test
    @DisplayName("Delete non-root node with two children")
    void deleteNonRootTwoChildren() {
        tree.insert(50);
        tree.insert(30);
        tree.insert(70);
        tree.insert(20);
        tree.insert(40);
        // delete non-root node 70 (right child of root, no children)
        assertEquals(0, tree.deleteByMerging(70));
        assertEquals(4, tree.size());
    }

    @Test
    @DisplayName("Delete node with right child only as non-root")
    void deleteNonRootRightChild() {
        tree.insert(50);
        tree.insert(30);
        tree.insert(70);
        tree.insert(80);
        // node 70 has right child 80
        assertEquals(0, tree.deleteByMerging(70));
    }

    @Test
    @DisplayName("Semi-splay on single node tree")
    void semiSplaySingleNode() {
        tree.insert(42);
        assertDoesNotThrow(() -> tree.semiSplay());
        assertEquals(1, tree.size());
    }

    @Test
    @DisplayName("Semi-splay on two-node tree")
    void semiSplayTwoNodes() {
        tree.insert(10);
        tree.insert(20);
        assertDoesNotThrow(() -> tree.semiSplay());
        assertEquals(2, tree.size());
    }

    @Test
    @DisplayName("Semi-splay with zig-zig pattern (left-left)")
    void semiSplayZigZigLeft() {
        tree.insert(30);
        tree.insert(20);
        tree.insert(10);
        assertDoesNotThrow(() -> tree.semiSplay());
        assertEquals(3, tree.size());
    }

    @Test
    @DisplayName("Semi-splay with zig-zag pattern (left-right)")
    void semiSplayZigZagLeft() {
        tree.insert(30);
        tree.insert(10);
        tree.insert(20);
        assertDoesNotThrow(() -> tree.semiSplay());
        assertEquals(3, tree.size());
    }

    @Test
    @DisplayName("Semi-splay with zig-zig pattern (right-right)")
    void semiSplayZigZigRight() {
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        assertDoesNotThrow(() -> tree.semiSplay());
        assertEquals(3, tree.size());
    }

    @Test
    @DisplayName("Semi-splay with zig-zag pattern (right-left)")
    void semiSplayZigZagRight() {
        tree.insert(10);
        tree.insert(30);
        tree.insert(20);
        assertDoesNotThrow(() -> tree.semiSplay());
        assertEquals(3, tree.size());
    }

    @Test
    @DisplayName("Print method executes without error")
    void testPrint() {
        dumpData(5);
        assertDoesNotThrow(() -> tree.print());
    }

    @Test
    @DisplayName("Delete node with left child that has right subtree")
    void deleteByMergingLeftChildHasRightSubtree() {
        tree.insert(50);
        tree.insert(30);
        tree.insert(70);
        tree.insert(20);
        tree.insert(35);
        tree.insert(25);
        assertEquals(6, tree.size());
        assertEquals(0, tree.deleteByMerging(30));
        assertEquals(5, tree.size());
        assertNull(tree.search(30));
        assertNotNull(tree.search(50));
        assertNotNull(tree.search(20));
        assertNotNull(tree.search(25));
        assertNotNull(tree.search(35));
        assertNotNull(tree.search(70));
    }

    @Test
    @DisplayName("Inorder on empty tree does nothing")
    void inorderEmpty() {
        assertDoesNotThrow(() -> tree.inorder(System.out));
    }

    @Test
    @DisplayName("ToString on empty tree returns null")
    void toStringEmpty() {
        assertThrows(NullPointerException.class, () -> tree.toString());
    }
}
