package trees;

import org.drozdek.commons.LoggerService;
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
        // TODO: Fix the insert method in Trie.java
        LoggerService.logInfo(tree.print());
        tree.insert("world");
        tree.insert("my");
        tree.insert("name");
        tree.insert("is");
        tree.insert("Dave");
        LoggerService.logWarning(tree.print());
        boolean ret = tree.found("wor");
        assertTrue(!ret,"The result is not the expected.");
    }
}
