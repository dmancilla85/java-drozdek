package trees;

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
    @DisplayName("Print the expression tree")
    void test(){
        tree.ukkonen("banana");
        LoggerService.logInfo(tree.printTree());
    }

    @Test
    @DisplayName("Ukkonen builds suffix tree")
    void ukkonenBuild() {
        tree.ukkonen("banana");
        assertNotNull(tree.toString());
    }
}
