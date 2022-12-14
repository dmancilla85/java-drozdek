package org.drozdek.lists.iterators;

import org.drozdek.lists.SingleLinkedList;
import org.drozdek.lists.SingleLinkedListNode;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SingleLinkedListIterator<T> implements Iterator<T> {

    private SingleLinkedListNode<T> current;

    // constructor
    public SingleLinkedListIterator(SingleLinkedList<T> list) {
        // initialize cursor
        current = list.viewHeadNode();
    }

    // Checks if the next element exists
    public boolean hasNext() {
        return current != null;
    }

    // moves the cursor/iterator to next element
    public T next() {
        if(!hasNext())
            throw new NoSuchElementException();

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
