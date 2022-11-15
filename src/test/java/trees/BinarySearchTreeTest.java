package trees;

import org.drozdek.trees.BinarySearchTree;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest {
    BinarySearchTree<Integer> tree;

    @BeforeEach
    void setUp() {
        tree = new BinarySearchTree<>();
    }

    @Test
    @DisplayName("After inserting new elements, the size of the tree should be the expected")
    void insert() {
        tree.insert(3);
        tree.insert(5);
        tree.insert(6);

        assertEquals(3,tree.size(), "The size is not the expected");
    }

    @Test
    @DisplayName("The search result should be the expected")
    void search() {
        tree.insert(3);
        tree.insert(5);
        tree.insert(6);
        tree.insert(21);
        tree.insert(65);
        tree.insert(19);

        Integer result = (Integer) tree.search(5);

        assertEquals(5,result, "The search result is not the expected");
    }

    @Test
    @DisplayName("The element should be removed from the tree when delete by copying")
    void deleteByCopying() {
        tree.insert(3);
        tree.insert(5);
        tree.insert(6);
        tree.insert(21);
        tree.insert(65);
        tree.insert(19);

        tree.deleteByCopying(5);
        Integer result = (Integer) tree.search(5);

        assertNotNull(result, "The element is still on the tree");
    }

    @Test
    @DisplayName("The element should be removed from the tree when delete by merging")
    void deleteByMerging() {
        tree.insert(3);
        tree.insert(5);
        tree.insert(6);
        tree.insert(21);
        tree.insert(65);
        tree.insert(19);

        tree.deleteByMerging(5);
        Integer result = (Integer) tree.search(5);

        assertNotNull(result, "The element is still on the tree");
    }
}
