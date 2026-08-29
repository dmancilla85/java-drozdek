package org.drozdek.lists.interfaces;

import org.drozdek.commons.DataTypeInterface;

/// Core contract implemented by every list variant in this project:
/// single/double/circular linked lists and the skip list.
///
/// **Real-world use case:** Undo histories, playlist ordering, and any
/// sequential collection where positional access patterns vary by
/// implementation.
///
/// Bibliography:
///
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 3.
///
public interface ListInterface<T> extends DataTypeInterface {

    /// Tests if this list contains no elements.
    ///
    /// @return true if this list contains no elements, false otherwise
    boolean isEmpty();

    /// Returns the number of elements currently stored in this list.
    ///
    /// @return the number of elements in this list
    int size();

    /// Searches for the first occurrence of the given value in this list.
    ///
    /// @param data the value to search for
    /// @return the value if found, or null if not found or the list is empty
    T find(T data);

    /// Deletes the first occurrence of the given value from this list.
    ///
    /// Implementations must leave the list unchanged when the value is not
    /// present or the list is empty.
    ///
    /// @param data the value to search for and delete
    void delete(T data);

    /// Adds a new element to this list.
    ///
    /// The insertion position depends on the implementation: some variants
    /// add at the head, others at the tail.
    ///
    /// @param data the data value to store in the new element
    void add(T data);

    /// Returns the first element of this list without removing it.
    ///
    /// @return the first element, or null if the list is empty
    T first();
}
