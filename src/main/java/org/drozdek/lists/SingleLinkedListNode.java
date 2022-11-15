package org.drozdek.lists;

/**
 * Node for single linked list.
 */
public class SingleLinkedListNode<T> {
    protected T data;
    protected SingleLinkedListNode<T> next;

    /**
     * Constructor.
     *
     * @param data Data value
     */
    public SingleLinkedListNode(T data) {
        this(data, null);
    }

    /**
     * Constructor.
     *
     * @param data Data value
     * @param node Next node
     */
    public SingleLinkedListNode(T data, SingleLinkedListNode<T> node) {
        this.data = data;
        next = node;
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
    public SingleLinkedListNode<T> getNext() {
        return this.next;
    }

    @Override
    public String toString() {
        return "{data: " + getData() + ", next: " + (next != null ? next.getData() : "<NULL>") + "}";
    }
}
