package org.drozdek.lists.iterators;

import org.drozdek.lists.DoubleLinkedList;
import org.drozdek.lists.DoubleLinkedListNode;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoubleLinkedListIterator<T> implements Iterator<T> {
    private DoubleLinkedListNode<T> current;

    // constructor
    public DoubleLinkedListIterator(DoubleLinkedList<T> list) {
        // initialize cursor
        current = list.viewHeadNode();
    }

    // Checks if the next element exists
    public boolean hasNext() {
        return current != null;
    }

    // moves the cursor/iterator to next element
    public T next() {
        if (!hasNext()) throw new NoSuchElementException();

        T data = current.getData();
        current = current.getNext();
        return data;
    }

    // Used to remove an element. Implement only if needed
    @Override
    public void remove() {
        // Default throws UnsupportedOperationException.
        throw new UnsupportedOperationException();
    }
}
