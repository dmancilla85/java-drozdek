package org.drozdek.trees;

import org.drozdek.trees.SimpleTree;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleTreeTest {

    SimpleTree<String> tree;

    @BeforeEach
    void setUp() {
        tree = new SimpleTree<>();
    }

    @Test
    @DisplayName("New tree should be empty")
    void isEmpty() {
        assertTrue(tree.isEmpty());
        assertEquals(0, tree.size());
    }

    @Test
    @DisplayName("Insert root and verify")
    void insertRoot() {
        tree.insertElement(null, "root");
        assertFalse(tree.isEmpty());
        assertEquals(1, tree.size());
        assertNotNull(tree.getRoot());
        assertEquals("root", tree.getRoot().getLabel());
    }

    @Test
    @DisplayName("Insert children and verify structure")
    void insertChildren() {
        tree.insertElement(null, "root");
        SimpleTree.TreeNode<String> root = tree.getRoot();
        tree.insertElement(root, "child1");
        tree.insertElement(root, "child2");
        assertEquals(3, tree.size());
        assertNotNull(tree.findNode("child1"));
        assertNotNull(tree.findNode("child2"));
    }

    @Test
    @DisplayName("Find non-existent node returns null")
    void findNonExistent() {
        assertNull(tree.findNode("nothing"));
    }

    @Test
    @DisplayName("Print method executes without error")
    void testPrint() {
        tree.insertElement(null, "root");
        tree.insertElement(tree.root, "child1");
        tree.insertElement(tree.root, "child2");
        tree.insertElement(tree.root, "child3");
        tree.insertElement(tree.findNode("child2"), "grandchild3");
        tree.insertElement(tree.findNode("child2"), "grandchild2");
        tree.insertElement(tree.findNode("child1"), "grandchild6");
        assertDoesNotThrow(() -> tree.print());
    }

    @Test
    @DisplayName("Print tree")
    void printTree() {
        tree.insertElement(null, "root");
        SimpleTree.TreeNode<String> root = tree.getRoot();
        tree.insertElement(root, "child");
        String output = tree.toString();
        assertNotNull(output);
        assertTrue(output.contains("root"));
    }
}
