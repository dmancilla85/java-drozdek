package trees;

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
        System.out.println(tree.toString());
        assertEquals(size, tree.size());
    }

    @Test
    @DisplayName("Insert values and semi-splay the tree")
    void semiSplay(){
        int size = 20;

        dumpData(size);
        tree.semiSplay();
        System.out.println(tree.toString());
        assertEquals(size, tree.size());
    }

    @Test
    @DisplayName("Semi-splay and print the tree with words")
    void semiSplayWithWords(){
        int size = 10;

        tree.insert(new Word("Hello"));
        tree.insert(new Word("my"));
        tree.insert(new Word("name"));
        tree.insert(new Word("is"));
        tree.insert(new Word("Jack"));
        tree.insert(new Word("I"));
        tree.insert(new Word("hope"));
        tree.insert(new Word("you"));
        tree.insert(new Word("know"));
        tree.insert(new Word("Jack"));

        tree.semiSplay();
        System.out.println(tree.toString());
        assertEquals(size, tree.size());
    }
}
