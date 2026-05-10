package org.drozdek.lists;

/**
 * Node for an integer skip list data structure.
 * 
 * <p>
 * Abstract Data Type: Skip list node
 * 
 * <p>
 * This class represents a node in a skip list, a probabilistic data structure that allows
 * for fast search, insertion, and deletion operations. Each node contains a key value and
 * an array of forward pointers to other nodes at various levels.
 * 
 * <p>
 * Skip lists provide O(log n) expected time complexity for search, insertion, and deletion
 * operations, with O(n) space complexity. The structure consists of multiple layers of 
 * linked lists, where higher layers act as "express lanes" for faster traversal.
 * 
 * <p>
 * Bibliography:
 * <ul>
 *   <li>William Pugh. <cite>Skip Lists: A Probabilistic Alternative to Balanced Trees</cite>. 
 *       Communications of the ACM, June 1990.</li>
 *   <li>Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, and Clifford Stein. 
 *       <cite>Introduction to Algorithms</cite>, Third Edition. MIT Press, 2009. Chapter 12: 
 *       Binary Search Trees, Skip Lists section.</li>
 *   <li>Eric W. Weisstein. <cite>Skip List</cite>. From MathWorld--A Wolfram Web Resource.</li>
 * </ul>
 */
public class IntSkipListNode {
    /** The key value stored in this node */
    public final int key;
    /** Array of forward pointers to next nodes at various levels */
    public final IntSkipListNode[] next;

    /**
     * Constructs a new skip list node with the given key value and level.
     * 
     * @param key the integer key value to store in this node
     * @param level the number of levels (size of the next array) for this node
     *              Higher levels allow for faster traversal in the skip list
     */
    public IntSkipListNode(int key, int level) {
        this.key = key;
        this.next = new IntSkipListNode[level];

        // Initialize all forward pointers to null
        for (int i = 0; i < level; i++)
            next[i] = null;
    }

    /**
     * Returns a string representation of this node for debugging purposes.
     * 
     * @return a string in the format {data: key_value}
     */
    @Override
    public String toString() {
        return "{data: " + key + "}";
    }
}
