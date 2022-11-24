package org.drozdek.trees;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * TODO: Is the same as the binary search node with height. Replace it.
 */
public class AvlTreeNode {
    protected int height;
    protected int key;
    protected AvlTreeNode left;
    protected AvlTreeNode right;
    public AvlTreeNode(int data) {
        this(data, null, null);
    }

    public AvlTreeNode(int data, AvlTreeNode left, AvlTreeNode right) {
        this.key = data;
        this.left = left;
        this.right = right;
    }

    private void print(StringBuilder buffer, String prefix, String childrenPrefix) {
        LinkedList<AvlTreeNode> children = new LinkedList<>();
        children.add(this.left);
        children.add(this.right);

        buffer.append(prefix);
        buffer.append(this.key);
        buffer.append('\n');

        for (Iterator<AvlTreeNode> it = children.iterator(); it.hasNext(); ) {
            AvlTreeNode next = it.next();

            if(next== null)
                continue;

            if (it.hasNext()) {
                next.print(buffer, childrenPrefix + "├── ", childrenPrefix + "│   ");
            } else {
                next.print(buffer, childrenPrefix + "└── ", childrenPrefix + "    ");
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder(50);
        print(buffer, "", "");
        return buffer.toString();
    }

}
