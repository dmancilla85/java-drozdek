package org.drozdek.lists.nodes;

/// Node for a singly linked list, holding immutable data plus a next
/// pointer.
///
/// **Real-world use case:** Building block of singly linked lists and
/// adjacency lists in graph representations.
///
/// Complexity Analysis:
/// Time Complexity: O(1) for all operations
/// Auxiliary Space: O(1)
///
public class SingleLinkedListNode<T> {
    public final T data;

    private SingleLinkedListNode<T> next;
    /// Constructor with a null successor node.
    ///
    /// @param data Node data
    public SingleLinkedListNode(T data) {
        this(data, null);
    }

    /// Constructor with explicit successor node.
    ///
    /// @param data Node data
    /// @param node Next node in the list
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

    /// Gets the reference to the next node in the list.
    ///
    /// @return the next node, or null if this node is the tail of the list
    public SingleLinkedListNode<T> getNext() {
        return this.next;
    }

    /// Returns a string representation of this node for debugging purposes.
    ///
    /// @return a string in the format {data: value, next: value_or_NULL}
    @Override
    public String toString() {
        return "{data: " + data + ", next: " + (next != null ? next.getData() : "<NULL>") + "}";
    }

    public void setNext(SingleLinkedListNode<T> next) {
        this.next = next;
    }
}
