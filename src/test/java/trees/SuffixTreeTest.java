package trees;

import org.drozdek.trees.SuffixTree;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SuffixTreeTest {
    SuffixTree tree;

    @BeforeEach
    void setUp(){
        tree=new SuffixTree();
    }

    @Test
    @DisplayName("Print the expression tree")
    void test(){
        tree.ukkonen("Hello my dear friend!");
        tree.printTree();
    }
}
