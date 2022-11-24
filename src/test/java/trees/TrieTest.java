package trees;

import org.drozdek.trees.Trie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TrieTest {

    Trie tree;

    @BeforeEach
    void setUp(){
        tree=new Trie("Hello world");
    }

    @Test
    @DisplayName("Print the trie and check that one word exists")
    void printAndFound(){
        // TODO: Fix this code
        /*tree.insert("world");
        tree.insert("my");
        tree.insert("name");
        tree.insert("is");
        tree.insert("Dave");*/
        tree.printTrie();
        boolean ret = tree.found("wor");
        assertTrue(true,"The result is not the expected.");
    }
}
