package org.drozdek.lists;

/**
 * Node for a singly-linked list data structure.
 * 
 * <p>
 * Abstract Data Type: Singly-linked list node
 * 
 * <p>
 * This class represents a node in a singly-linked list, containing a data element
 * and a reference to the next node in the sequence. This forms the fundamental
 * building block for singly-linked list implementations.
 * 
 * <p>
 * Bibliography:
 * <ul>
 *   <li>Donald E. Knuth. <cite>The Art of Computer Programming, Volume 1: Fundamental Algorithms</cite>, 
 *       Third Edition. Addison-Wesley, 1997. Section 2.2.3: Linked lists.</li>
 *   <li>Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, and Clifford Stein. 
 *       <cite>Introduction to Algorithms</cite>, Third Edition. MIT Press, 2009. Chapter 10: 
 *       Elementary Data Structures.</li>
 * </ul>
 */
public class SingleLinkedListNode<T> {
    protected final T data;
    protected SingleLinkedListNode<T> next;

    /**
     * Constructs a new singly-linked list node with the given data and null next reference.
     * Creates a tail node (end of list).
     * 
     * @param data the data value to store in this node
     */
    public SingleLinkedListNode(T data) {
        this(data, null);
    }

    /**
     * Constructs a new singly-linked list node with the given data and next node reference.
     * 
     * @param data the data value to store in this node
     * @param node the next node in the list (may be null for tail node)
     */
    public SingleLinkedListNode(T data, SingleLinkedListNode<T> node) {
        this.data = data;
        next = node;
    }

    /**
     * Gets the data value stored in this node.
     * 
     * @return the data value stored in this node
     */
    public T getData() {
        return data;
    }

    /**
     * Gets the reference to the next node in the list.
     * 
     * @return the next node, or null if this node is the tail of the list
     */
    public SingleLinkedListNode<T> getNext() {
        return this.next;
    }

    /**
     * Returns a string representation of this node for debugging purposes.
     * 
     * @return a string in the format {data: value, next: value_or_NULL}
     */
    @Override
    public String toString() {
        return "{data: " + data + ", next: " + (next != null ? next.getData() : "<NULL>") + "}";
    }
}
