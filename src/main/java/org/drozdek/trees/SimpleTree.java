package org.drozdek.trees;

import java.util.ArrayList;

public class SimpleTree<T> {

    protected TreeNode root;

    public SimpleTree() {
        root = null;
    }

    public void insertElement(TreeNode parent, T label) {

        if (!parent.equals(parent)) {
            for (int i = 0; i < parent.children.size(); i++)
                insertElement(parent.children.get(i), label);
        }
    }

    class TreeNode {
        protected T label;
        protected ArrayList<TreeNode> children;

        TreeNode(T label) {
            this.label = label;
            children = new ArrayList<TreeNode>();
        }

        public T getLabel() {
            return label;
        }

        public void setLabel(T label) {
            this.label = label;
        }

    }
}
