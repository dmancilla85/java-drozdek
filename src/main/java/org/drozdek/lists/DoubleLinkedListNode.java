package org.drozdek.basics.lists;

/**
 * Node for double linked list.
 */
public class DoubleLinkedListNode {
    public Object data;
    protected DoubleLinkedListNode next;
    protected DoubleLinkedListNode previous;

    /**
     * Default constructor.
     */
    public DoubleLinkedListNode() {
        next = null;
        previous = null;
    }

    /**
     * Constructor.
     * @param el Object data
     */
    public DoubleLinkedListNode(Object el) {
        this(el, null, null);
    }

    /**
     * Constructor.
     * @param el Object data
     * @param next Next node
     * @param previous Previous node
     */
    public DoubleLinkedListNode(Object el, DoubleLinkedListNode next, DoubleLinkedListNode previous) {
        data = el;
        this.next = next;
        this.previous = previous;
    }
}
