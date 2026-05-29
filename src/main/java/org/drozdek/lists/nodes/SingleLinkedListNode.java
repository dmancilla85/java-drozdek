package org.drozdek.lists.nodes;

/// Node for a single linked list.
public class SingleLinkedListNode<T> {
    public final T data;

    public SingleLinkedListNode<T> next;
    /// Set next node.
    ///
    /// @param next Next node
    public SingleLinkedListNode(T data) {
        this(data, null);
    }

    /// Constructor.
    ///
    /// @param data Node data
    public SingleLinkedListNode(T data, SingleLinkedListNode<T> node) {
        this.data = data;
        next = node;
    }

    /// Get data.
    ///
    /// @return data
    public T getData() {
        return data;
    }

    /// Set data.
    ///
    /// @param data Data to set
    public SingleLinkedListNode<T> getNext() {
        return this.next;
    }

    /// Get next node.
    ///
    /// @return Next node
    @Override
    public String toString() {
        return "{data: " + data + ", next: " + (next != null ? next.getData() : "<NULL>") + "}";
    }

    public void setNext(SingleLinkedListNode<T> next) {
        this.next = next;
    }
}
