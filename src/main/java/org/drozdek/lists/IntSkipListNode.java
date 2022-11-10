package org.drozdek.lists;

/**
 * Skip list node for integer values.
 */
public class IntSkipListNode {
    public final int key;
    public final IntSkipListNode[] next;

    /**
     * Constructor.
     *
     * @param i Data value
     * @param n Number of referenced nodes
     */
    public IntSkipListNode(int i, int n) {
        key = i;
        next = new IntSkipListNode[n];

        for (int j = 0; j < n; j++)
            next[j] = null;
    }

    @Override
    public String toString() {
        return "{data: " + key + "}";
    }
}
