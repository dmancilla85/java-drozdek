package org.drozdek.trees;

class SuffixTreeNode {
    private static int cnt = 0; // for printing only
    protected SuffixTreeNode[] descendants;
    protected int[] left;
    protected int[] right;
    protected SuffixTreeNode suffixLink;
    protected int id; // for printing only

    public SuffixTreeNode() {
        this(128);
    }

    public SuffixTreeNode(int sz) {
        id = cnt++;
        suffixLink = null;
        descendants = new SuffixTreeNode[sz];
        left = new int[sz];
        right = new int[sz];

        for (int i = 0; i < sz; i++)
            left[i] = -1;
    }
}
