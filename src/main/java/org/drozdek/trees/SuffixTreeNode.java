package org.drozdek.trees;

class SuffixTreeNode {
    private static int cnt = 0; // for printing only
    protected final SuffixTreeNode[] descendants;
    protected final int[] left;
    protected final int[] right;
    protected final int id; // for printing only
    protected SuffixTreeNode suffixLink;

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
