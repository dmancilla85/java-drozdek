package org.drozdek.lists.iterators;

import org.drozdek.lists.DoubleLinkedList;
import org.drozdek.lists.nodes.DoubleLinkedListNode;

import java.util.Iterator;
import java.util.NoSuchElementException;

    /// Check whether there is a further element.
    ///
    /// @return true/false
public class DoubleLinkedListIterator<T> implements Iterator<T> {
    /// Get the next element.
    ///
    /// @return Next element in list
    private DoubleLinkedListNode<T> current;

/// Iterator for a double-linked list.
    public DoubleLinkedListIterator(DoubleLinkedList<T> list) {
        // initialize cursor to the head of the list
        current = list.viewHeadNode();
    }

    /// Delete the iterated element from the list.
    public boolean hasNext() {
        return current != null;
    }

    /// Constructor.
    ///
    /// @param list Linked list to iterate
    public T next() {
        if (!hasNext()) throw new NoSuchElementException();

        T data = current.getData();
        current = current.getNext();
        return data;
    }

    /// Insert element after the iterated element.
    ///
    /// @param element Element to insert
    @Override
    public void remove() {
        // Default throws UnsupportedOperationException.
        throw new UnsupportedOperationException();
    }
}
