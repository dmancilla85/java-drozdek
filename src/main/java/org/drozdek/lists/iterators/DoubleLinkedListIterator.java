package org.drozdek.lists.iterators;

import org.drozdek.lists.DoubleLinkedList;
import org.drozdek.lists.nodes.DoubleLinkedListNode;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iterator for traversing a doubly-linked list in the forward direction.
 *
 * <p>
 * Abstract Data Type: Iterator
 *
 * <p>
 * This iterator allows traversal of a DoubleLinkedList from head to tail (first to last element).
 * It implements the java.util.Iterator interface and provides hasNext() and next() methods for iteration.
 *
 * <p>
 * Bibliography:
 * <ul>
 *   <li>Joshua Bloch. <cite>Effective Java</cite>, Third Edition. Addison-Wesley, 2017.
 *       Item 80: Prefer iterators to indices.</li>
 *   <li>Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, and Clifford Stein.
 *       <cite>Introduction to Algorithms</cite>, Third Edition. MIT Press, 2009. Chapter 10:
 *       Elementary Data Structures.</li>
 * </ul>
 */
public class DoubleLinkedListIterator<T> implements Iterator<T> {
    /**
     * The current node in the iteration
     */
    private DoubleLinkedListNode<T> current;

    /**
     * Constructs a new iterator for the given doubly-linked list.
     *
     * @param list the doubly-linked list to iterate over
     *             <p>
     *             Time Complexity: O(1) - direct access to the head node
     */
    public DoubleLinkedListIterator(DoubleLinkedList<T> list) {
        // initialize cursor to the head of the list
        current = list.viewHeadNode();
    }

    /**
     * Tests if this iterator has more elements.
     *
     * @return true if the iteration has more elements, false otherwise
     * <p>
     * Time Complexity: O(1) - direct check of current node reference
     */
    public boolean hasNext() {
        return current != null;
    }

    /**
     * Returns the next element in the iteration and advances the iterator.
     *
     * @return the next element in the iteration
     * @throws NoSuchElementException if the iteration has no more elements
     *                                <p>
     *                                Time Complexity: O(1) - direct access to current node's data and next reference
     */
    public T next() {
        if (!hasNext()) throw new NoSuchElementException();

        T data = current.getData();
        current = current.getNext();
        return data;
    }

    /**
     * Removes from the underlying collection the last element returned by this iterator.
     * This operation is not supported by this iterator implementation.
     *
     * @throws UnsupportedOperationException if the remove operation is attempted
     */
    @Override
    public void remove() {
        // Default throws UnsupportedOperationException.
        throw new UnsupportedOperationException();
    }
}
