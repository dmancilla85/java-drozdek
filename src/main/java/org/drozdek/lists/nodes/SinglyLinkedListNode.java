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
/// Bibliography:
///
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 3.
///
public class SinglyLinkedListNode<T> {
    public final T data;

    private SinglyLinkedListNode<T> next;
    /// Constructor with a null successor node.
    ///
    /// @param data Node data
    public SinglyLinkedListNode(T data) {
        this(data, null);
    }

    /// Constructor with explicit successor node.
    ///
    /// @param data Node data
    /// @param node Next node in the list
    public SinglyLinkedListNode(T data, SinglyLinkedListNode<T> node) {
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
    public SinglyLinkedListNode<T> getNext() {
        return this.next;
    }

    /// Returns a string representation of this node for debugging purposes.
    ///
    /// @return a string in the format {data: value, next: value_or_NULL}
    @Override
    public String toString() {
        return "{data: " + data + ", next: " + (next != null ? next.getData() : "<NULL>") + "}";
    }

    public void setNext(SinglyLinkedListNode<T> next) {
        this.next = next;
    }
}
