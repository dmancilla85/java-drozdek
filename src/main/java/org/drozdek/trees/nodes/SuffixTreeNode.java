package org.drozdek.trees.nodes;

import java.util.Arrays;
import java.util.Iterator;

public class SuffixTreeNode {
    private static int cnt = 0;
    private final SuffixTreeNode[] descendants;
    private final int[] left;
    private final int[] right;
    private final int id;
    private SuffixTreeNode suffixLink;

    public SuffixTreeNode(int sz) {
        id = cnt++;
        suffixLink = null;
        descendants = new SuffixTreeNode[sz];
        left = new int[sz];
        right = new int[sz];

        for (int i = 0; i < sz; i++)
            left[i] = -1;
    }

    public SuffixTreeNode[] getDescendants() {
        return descendants;
    }

    public int[] getLeft() {
        return left;
    }

    public int[] getRight() {
        return right;
    }

    public int getId() {
        return id;
    }

    public SuffixTreeNode getSuffixLink() {
        return suffixLink;
    }

    public void setSuffixLink(SuffixTreeNode suffixLink) {
        this.suffixLink = suffixLink;
    }

    private void print(StringBuilder buffer, String prefix, String childrenPrefix) {
        buffer.append(prefix);
        buffer.append(this.id);
        buffer.append(System.lineSeparator());

        int count = 0;
        for (SuffixTreeNode d : descendants) {
            if (d != null) count++;
        }

        int seen = 0;
        for (SuffixTreeNode next : descendants) {
            if (next == null) continue;
            seen++;
            if (seen < count) {
                next.print(buffer, childrenPrefix + "├── ", childrenPrefix + "│   ");
            } else {
                next.print(buffer, childrenPrefix + "└── ", childrenPrefix + "    ");
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder(50);
        buffer.append(System.lineSeparator());
        print(buffer, "", "");
        return buffer.toString();
    }
}
