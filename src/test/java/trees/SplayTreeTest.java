package trees;

import org.drozdek.commons.LoggerService;
import org.drozdek.trees.SplayTree;
import org.drozdek.trees.Word;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        LoggerService.logInfo(tree.toString());
        assertEquals(size, tree.size());
    }

    @Test
    @DisplayName("Insert values and semi-splay the tree")
    void semiSplay(){
        int size = 20;

        dumpData(size);
        tree.semiSplay();
        LoggerService.logInfo(tree.toString());
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
        LoggerService.logInfo(wordTree.toString());
        wordTree.semiSplay();
        LoggerService.logInfo(wordTree.toString());
        assertEquals(size, wordTree.size());
    }

    @Test
    @DisplayName("Delete a splay tree node by merging")
    void deleteByMerging(){
        int size = 5;

        dumpData(5);
        tree.insert(12);
        LoggerService.logInfo(tree.toString());
        tree.deleteByMerging(12);
        LoggerService.logWarning(tree.toString());
        assertEquals(size, tree.size());
    }
}
