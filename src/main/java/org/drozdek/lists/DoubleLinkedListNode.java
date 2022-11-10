package org.drozdek.lists;

/**
 * Node for double linked list.
 */
public class DoubleLinkedListNode<T> {
    protected T data;
    protected DoubleLinkedListNode<T> next;
    protected DoubleLinkedListNode<T> previous;

    /**
     * Constructor.
     *
     * @param el Data value
     */
    public DoubleLinkedListNode(T el) {
        this(el, null, null);
    }

    /**
     * Constructor.
     *
     * @param el       Data value
     * @param next     Next node
     * @param previous Previous node
     */
    public DoubleLinkedListNode(T el, DoubleLinkedListNode next, DoubleLinkedListNode previous) {
        data = el;
        this.next = next;
        this.previous = previous;
    }

    public T getData() {
        return data;
    }

    @Override
    public String toString() {
        return "{data: " + data + ", next: " + (next != null ? next.data : "<NULL>") +
                ", previous: " + (previous != null ? previous.data : "<NULL>") + "}";
    }
}
