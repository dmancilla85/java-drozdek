package org.drozdek.lists.iterators;

import org.drozdek.lists.SingleLinkedList;
import org.drozdek.lists.nodes.SingleLinkedListNode;

import java.util.Iterator;
import java.util.NoSuchElementException;

    /// Check whether there is a further element.
    ///
    /// @return true/false
public class SingleLinkedListIterator<T> implements Iterator<T> {

    /// Get the next element.
    ///
    /// @return Next element in list
    private SingleLinkedListNode<T> current;

/// Iterator for a single linked list.
    public SingleLinkedListIterator(SingleLinkedList<T> list) {
        // initialize cursor to the head of the list
        current = list.viewHeadNode();
    }

    /// Delete the iterated item from the list.
    public boolean hasNext() {
        return current != null;
    }

    /// Constructor.
    ///
    /// @param list linked list
    public T next() {
        if (!hasNext())
            throw new NoSuchElementException();

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
