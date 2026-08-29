package org.drozdek.lists;

/// Node for an integer skip list data structure.
///
/// Abstract Data Type: Skip list node
///
/// This class represents a node in a skip list, a probabilistic data structure that allows
/// for fast search, insertion, and deletion operations. Each node contains a key value and
/// an array of forward pointers to other nodes at various levels.
///
/// Bibliography:
///
/// - William Pugh. *Skip Lists: A Probabilistic Alternative to Balanced Trees*.
///   Communications of the ACM, June 1990.
/// - Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, and Clifford Stein.
///   *Introduction to Algorithms*, Third Edition. MIT Press, 2009. Chapter 12:
///   Binary Search Trees, Skip Lists section.
/// - Eric W. Weisstein. *Skip List*. From MathWorld--A Wolfram Web Resource.
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 3.
public class IntSkipListNode {
    /** The key value stored in this node */
    public final int key;
    /** Array of forward pointers to next nodes at various levels */
    public final IntSkipListNode[] next;

    /// Constructs a new skip list node with the given key value and level.
    ///
    /// @param key   the integer key value to store in this node
    /// @param level the number of levels (size of the next array) for this node
    ///              Higher levels allow for faster traversal in the skip list
    public IntSkipListNode(int key, int level) {
        this.key = key;
        this.next = new IntSkipListNode[level];

        // Initialize all forward pointers to null
        for (int i = 0; i < level; i++)
            next[i] = null;
    }

    /// Returns a string representation of this node for debugging purposes.
    ///
    /// @return a string in the format {data: key_value}
    @Override
    public String toString() {
        return "{data: " + key + "}";
    }
}
