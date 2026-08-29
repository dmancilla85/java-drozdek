package org.drozdek.lists.iterators;

import org.drozdek.lists.DoublyLinkedList;
import org.drozdek.lists.nodes.DoublyLinkedListNode;

import java.util.Iterator;
import java.util.NoSuchElementException;

/// Iterator for a doubly linked list, walking from head to tail.
///
/// **Real-world use case:** Transparent for-each traversal of list
/// contents while hiding node pointers from client code.
///
/// Complexity Analysis:
/// Time Complexity: O(1) for hasNext/next
/// Auxiliary Space: O(1)
///
public class DoublyLinkedListIterator<T> implements Iterator<T> {
    private DoublyLinkedListNode<T> current;

    /// Constructor.
    ///
    /// @param list Linked list to iterate
    public DoublyLinkedListIterator(DoublyLinkedList<T> list) {
        // initialize cursor to the head of the list
        current = list.viewHeadNode();
    }

    /// Check whether there is a further element.
    ///
    /// @return true if the iteration has more elements
    public boolean hasNext() {
        return current != null;
    }

    /// Get the next element.
    ///
    /// @return next element in the list
    /// @throws NoSuchElementException if no elements remain
    public T next() {
        if (!hasNext()) throw new NoSuchElementException();

        T data = current.getData();
        current = current.getNext();
        return data;
    }

    /// Removal is not supported by this iterator.
    ///
    /// @throws UnsupportedOperationException always
    @Override
    public void remove() {
        // Default throws UnsupportedOperationException.
        throw new UnsupportedOperationException();
    }
}
