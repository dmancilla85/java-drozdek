package org.drozdek.lists.nodes;

/**
 * Node for a doubly-linked list data structure.
 *
 * <p>
 * Abstract Data Type: Doubly-linked list node
 *
 * <p>
 * This class represents a node in a doubly-linked list, containing a data element,
 * a reference to the next node, and a reference to the previous node in the sequence.
 * This allows for efficient traversal in both directions.
 *
 * <p>
 * Bibliography:
 * <ul>
 *   <li>Donald E. Knuth. <cite>The Art of Computer Programming, Volume 1: Fundamental Algorithms</cite>,
 *       Third Edition. Addison-Wesley, 1997. Section 2.2.3: Linked lists.</li>
 *   <li>Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, and Clifford Stein.
 *       <cite>Introduction to Algorithms</cite>, Third Edition. MIT Press, 2009. Chapter 10:
 *       Elementary Data Structures.</li>
 *   <li>Peter Brass. <cite>Advanced Data Structures</cite>. Cambridge University Press, 2008.
 *       Section 2.2: Doubly-linked lists.</li>
 * </ul>
 */
public class DoubleLinkedListNode<T> {
    public final T data;
    public DoubleLinkedListNode<T> next;
    public DoubleLinkedListNode<T> previous;

    /**
     * Constructs a new doubly-linked list node with the given data and null next/previous references.
     * Creates an isolated node (not connected to any list).
     *
     * @param el the data value to store in this node
     */
    public DoubleLinkedListNode(T el) {
        this(el, null, null);
    }

    /**
     * Constructs a new doubly-linked list node with the given data and node references.
     *
     * @param el       the data value to store in this node
     * @param next     the next node in the list (may be null for tail node)
     * @param previous the previous node in the list (may be null for head node)
     */
    public DoubleLinkedListNode(T el, DoubleLinkedListNode<T> next, DoubleLinkedListNode<T> previous) {
        data = el;
        this.next = next;
        this.previous = previous;
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
    public DoubleLinkedListNode<T> getNext() {
        return this.next;
    }

    /**
     * TODO: Fill comments
     *
     * @param next
     */
    public void setNext(DoubleLinkedListNode<T> next) {
        this.next = next;
    }

    /**
     * Gets the reference to the previous node in the list.
     *
     * @return the previous node, or null if this node is the head of the list
     */
    public DoubleLinkedListNode<T> getPrevious() {
        return previous;
    }

    /**
     * TODO: Fill comments
     *
     * @param previous
     */
    public void setPrevious(DoubleLinkedListNode<T> previous) {
        this.previous = previous;
    }

    /**
     * Returns a string representation of this node for debugging purposes.
     *
     * @return a string in the format {data: value, next: value_or_NULL, previous: value_or_NULL}
     */
    @Override
    public String toString() {
        return "{data: " + data + ", next: " + (next != null ? next.data : "<NULL>") +
                ", previous: " + (previous != null ? previous.data : "<NULL>") + "}";
    }
}
