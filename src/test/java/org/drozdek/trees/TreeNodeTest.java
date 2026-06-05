package org.drozdek.trees;

import org.drozdek.trees.nodes.TreeNode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TreeNodeTest {
    @Test
    @DisplayName("Default constructor sets null label and children")
    void defaultConstructor() {
        TreeNode node = new TreeNode();
        assertNull(node.getLabel());
        assertNull(node.getLeftChild());
        assertNull(node.getRightChild());
    }

    @Test
    @DisplayName("Constructor with label")
    void constructorWithLabel() {
        TreeNode node = new TreeNode("test");
        assertEquals("test", node.getLabel());
    }

    @Test
    @DisplayName("Constructor with label and children")
    void constructorWithLabelAndChildren() {
        TreeNode left = new TreeNode("left");
        TreeNode right = new TreeNode("right");
        TreeNode node = new TreeNode("root", left, right);
        assertEquals("root", node.getLabel());
        assertSame(left, node.getLeftChild());
        assertSame(right, node.getRightChild());
    }

    @Test
    @DisplayName("Constructor with children only")
    void constructorWithChildrenOnly() {
        TreeNode left = new TreeNode("left");
        TreeNode right = new TreeNode("right");
        TreeNode node = new TreeNode(left, right);
        assertNull(node.getLabel());
        assertSame(left, node.getLeftChild());
        assertSame(right, node.getRightChild());
    }

    @Test
    @DisplayName("Set and get label")
    void setAndGetLabel() {
        TreeNode node = new TreeNode();
        node.setLabel("newLabel");
        assertEquals("newLabel", node.getLabel());
    }

    @Test
    @DisplayName("Set and get left child")
    void setAndGetLeftChild() {
        TreeNode node = new TreeNode();
        TreeNode child = new TreeNode("child");
        node.setLeftChild(child);
        assertSame(child, node.getLeftChild());
    }

    @Test
    @DisplayName("Set and get right child")
    void setAndGetRightChild() {
        TreeNode node = new TreeNode();
        TreeNode child = new TreeNode("child");
        node.setRightChild(child);
        assertSame(child, node.getRightChild());
    }
}
