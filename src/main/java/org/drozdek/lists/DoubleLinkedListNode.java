package org.drozdek.lists;

/**
 * Node for double linked list.
 */
public class DoubleLinkedListNode<T> {
    protected final T data;
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
    public DoubleLinkedListNode(T el, DoubleLinkedListNode<T> next, DoubleLinkedListNode<T> previous) {
        data = el;
        this.next = next;
        this.previous = previous;
    }

    /**
     * Get the data value
     *
     * @return Data value
     */
    public T getData() {
        return data;
    }

    /**
     * Get next node in list.
     *
     * @return Next node.
     */
    public DoubleLinkedListNode<T> getNext() {
        return this.next;
    }

    @Override
    public String toString() {
        return "{data: " + data + ", next: " + (next != null ? next.data : "<NULL>") +
                ", previous: " + (previous != null ? previous.data : "<NULL>") + "}";
    }
}
