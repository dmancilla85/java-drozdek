package org.drozdek.basics.lists;

/**
 * Node for single linked list.
 */
public class SingleLinkedListNode {
    public Object data;
    protected SingleLinkedListNode next;

    /**
     * Default constructor.
     */
    public SingleLinkedListNode() {
        next = null;
    }

    /**
     * Constructor.
     * @param el Object data
     */
    public SingleLinkedListNode(Object el) {
        this(el, null);
    }

    /**
     * Constructor.
     * @param el Object data
     * @param node Next node
     */
    public SingleLinkedListNode(Object el, SingleLinkedListNode node) {
        data = el;
        next = node;
    }
}
