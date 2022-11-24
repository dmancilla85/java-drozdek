package trees;

import org.drozdek.commons.LoggerService;
import org.drozdek.trees.BinarySearchTree;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest {
    BinarySearchTree<Integer> tree;

    @Test
    @DisplayName("Balance a tree with an ordered array should give the same number of elements")
    void balanceWithDataArray() {
        Integer[] ordered = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8};
        tree.balanceWithDataArray(ordered);
        System.out.println(tree.toString());
        assertEquals(8, tree.size(), "The expected size doesn't match");
    }

    @Test
    @DisplayName("To check that balance with DSW is working correctly")
    void balanceWithDsw() {
        tree.insert(3);
        tree.insert(5);
        tree.insert(71);
        tree.insert(6);
        tree.insert(3);
        tree.insert(81);
        tree.insert(35);
        tree.insert(35);
        tree.insert(771);
        tree.insert(68);
        tree.insert(31);
        tree.insert(181);

        BinarySearchTree<Integer> balancedTree = BinarySearchTree.balanceWithDSW(tree);
        LoggerService.logInfo(balancedTree.toString());

        assertEquals(12, balancedTree.size(), "The expected size doesn't match");
    }

    @Test
    @DisplayName("To verify printing breadth first")
    void breadthFirst() {
        tree.insert(3);
        tree.insert(5);
        tree.insert(71);
        tree.insert(6);
        tree.insert(3);
        tree.insert(81);
        tree.breadthFirst(System.out);
        assertEquals(6, tree.size(), "The expected size doesn't match");
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

        int code = tree.deleteByCopying(5);
        Integer result = (Integer) tree.search(5);

        assertEquals(0, code, "The operation code is not the expected (0)");
        assertNull(result, "The element is still on the tree");
    }

    @Test
    @DisplayName("The element should be removed from the tree when delete by merging")
    void deleteByMerging() {
        tree.insert(3);
        tree.insert(5);
        tree.insert(2);
        tree.insert(6);
        tree.insert(1);
        tree.insert(8);

        int code =tree.deleteByMerging(5);
        Integer result = (Integer) tree.search(5);
        System.out.println(tree.toString());
        assertEquals(0, code, "The operation code is not the expected (0)");
        assertNull(result, "The element is still on the tree");
    }

    @Test
    @DisplayName("To verify printing in order")
    void inOrder() {
        tree.insert(3);
        tree.insert(5);
        tree.insert(71);
        tree.insert(6);
        tree.insert(3);
        tree.insert(81);
        tree.inorder(System.out);
        assertEquals(6, tree.size(), "The expected size doesn't match");
    }

    @Test
    @DisplayName("After inserting new elements, the size of the tree should be the expected")
    void insert() {
        tree.insert(3);
        tree.insert(5);
        tree.insert(6);

        assertEquals(3, tree.size(), "The size is not the expected");
    }

    @Test
    @DisplayName("To verify printing iterative in order")
    void iterativeInOrder() {
        tree.insert(3);
        tree.insert(5);
        tree.insert(71);
        tree.insert(6);
        tree.insert(3);
        tree.insert(81);
        tree.iterativeInorder(System.out);
        assertEquals(6, tree.size(), "The expected size doesn't match");
    }

    @Test
    @DisplayName("To verify printing iterative post-order")
    void iterativePostOrder() {
        tree.insert(3);
        tree.insert(5);
        tree.insert(71);
        tree.insert(6);
        tree.insert(3);
        tree.insert(81);
        tree.iterativePostorder(System.out);
        assertEquals(6, tree.size(), "The expected size doesn't match");
    }

    @Test
    @DisplayName("To verify printing iterative pre-order")
    void iterativePreOrder() {
        tree.insert(3);
        tree.insert(5);
        tree.insert(71);
        tree.insert(6);
        tree.insert(3);
        tree.insert(81);
        tree.iterativePreorder(System.out);
        assertEquals(6, tree.size(), "The expected size doesn't match");
    }

    @Test
    @DisplayName("To verify printing with Morris in order")
    void morrisInOrder() {
        tree.insert(3);
        tree.insert(5);
        tree.insert(71);
        tree.insert(6);
        tree.insert(3);
        tree.insert(81);
        tree.morrisInOrder(System.out);
        assertEquals(6, tree.size(), "The expected size doesn't match");
    }

    @Test
    @DisplayName("To verify printing with Morris post-order")
    void morrisPostOrder() {
        tree.insert(3);
        tree.insert(5);
        tree.insert(71);
        tree.insert(6);
        tree.insert(3);
        tree.insert(81);
        tree.morrisPostOrder(System.out);
        assertEquals(6, tree.size(), "The expected size doesn't match");
    }

    @Test
    @DisplayName("To verify printing with Morris pre-order")
    void morrisPreOrder() {
        tree.insert(3);
        tree.insert(5);
        tree.insert(71);
        tree.insert(6);
        tree.insert(3);
        tree.insert(81);
        tree.morrisPreOrder(System.out);
        assertEquals(6, tree.size(), "The expected size doesn't match");
    }

    @Test
    @DisplayName("To verify printing post-order")
    void postOrder() {
        tree.insert(3);
        tree.insert(5);
        tree.insert(71);
        tree.insert(6);
        tree.insert(3);
        tree.insert(81);
        tree.postorder(System.out);
        assertEquals(6, tree.size(), "The expected size doesn't match");
    }

    @Test
    @DisplayName("To verify printing pre-order")
    void preOrder() {
        tree.insert(3);
        tree.insert(5);
        tree.insert(71);
        tree.insert(6);
        tree.insert(3);
        tree.insert(81);
        tree.preorder(System.out);
        assertEquals(6, tree.size(), "The expected size doesn't match");
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

        assertEquals(5, result, "The search result is not the expected");
    }

    @BeforeEach
    void setUp() {
        tree = new BinarySearchTree<>();
    }
}
