package org.drozdek.lists.iterators;

import org.drozdek.lists.SingleLinkedList;
import org.drozdek.lists.SingleLinkedListNode;

import java.util.Iterator;

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
        T data = current.getData();
        current = current.getNext();
        return data;
    }

    // Used to remove an element. Implement only if needed
    public void remove() {
        // Default throws UnsupportedOperationException.
        throw new UnsupportedOperationException();
    }
}
