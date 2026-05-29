package org.drozdek.lists.iterators;

import org.drozdek.lists.SingleLinkedList;
import org.drozdek.lists.nodes.SingleLinkedListNode;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iterator for traversing a singly-linked list in the forward direction.
 *
 * <p>
 * Abstract Data Type: Iterator
 *
 * <p>
 * This iterator allows traversal of a SingleLinkedList from head to tail (most recently
 * added to least recently added element). It implements the java.util.Iterator interface
 * and provides hasNext() and next() methods for iteration.
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
public class SingleLinkedListIterator<T> implements Iterator<T> {

    /**
     * The current node in the iteration
     */
    private SingleLinkedListNode<T> current;

    /**
     * Constructs a new iterator for the given singly-linked list.
     *
     * @param list the singly-linked list to iterate over
     *             <p>
     *             Time Complexity: O(1) - direct access to the head node
     */
    public SingleLinkedListIterator(SingleLinkedList<T> list) {
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
        if (!hasNext())
            throw new NoSuchElementException();

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
