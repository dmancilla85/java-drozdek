package org.drozdek.lists;

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
    protected final T data;
    protected SinglyLinkedListNode<T> next;

    /// Constructs a new singly-linked list node with the given data and null next reference.
    /// Creates a tail node (end of list).
    ///
    /// @param data the data value to store in this node
    public SinglyLinkedListNode(T data) {
        this(data, null);
    }

    /// Constructs a new singly-linked list node with the given data and next node reference.
    ///
    /// @param data the data value to store in this node
    /// @param node the next node in the list (may be null for tail node)
    public SinglyLinkedListNode(T data, SinglyLinkedListNode<T> node) {
        this.data = data;
        next = node;
    }

    /// Gets the data value stored in this node.
    ///
    /// @return the data value stored in this node
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
}
