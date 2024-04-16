package org.drozdek.lists;

import org.drozdek.commons.LoggerService;
import org.drozdek.lists.iterators.DoubleLinkedListIterator;

import java.util.Iterator;

/**
 * Double linked list data structure.
 * The head is always next to the tail (last added element).
 */
public class DoubleLinkedList<T> implements Iterable<T> {
    protected DoubleLinkedListNode<T> head;
    protected DoubleLinkedListNode<T> tail;

    /**
     * Default constructor.
     */
    public DoubleLinkedList() {
        head = tail = null;
    }

    /**
     * Add a node to the tail.
     *
     * @param data Data value
     */
    public void addToTail(T data) {
        if (!isEmpty()) {
            tail = new DoubleLinkedListNode<>(data, null, tail);
            tail.previous.next = tail;
        } else head = tail = new DoubleLinkedListNode<>(data);
    }

    /**
     * Delete the matching node.
     *
     * @param data Data node to be erased
     */
    public void delete(T data) {
        if (head == null) {
            return;
        }
        if (data.equals(head.data)) {
            head = head.next;
        } else {
            if (data.equals(tail.data)) {
                removeFromTail();
            } else {
                DoubleLinkedListNode<T> predecessor = head;
                DoubleLinkedListNode<T> tmp = head.next;

                while (tmp != null && !(tmp.data.equals(data))) {
                    predecessor = predecessor.next;
                    tmp = tmp.next;
                }

                if (tmp != null)
                    predecessor.next = tmp.next;
            }

        }
    }

    /**
     * Search for a node in the list.
     *
     * @param data Data value
     * @return Matching node
     */
    public T find(T data) {
        DoubleLinkedListNode<T> tmp = head;
        while (tmp != null && !data.equals(tmp.data)) {
            tmp = tmp.next;
        }
        if (tmp == null)
            return null;

        else return tmp.data;
    }

    /**
     * First node in list.
     *
     * @return First data value
     */
    public T first() {
        return head != null ? head.data : null;
    }

    /**
     * Is list empty?
     *
     * @return True if list is empty
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * @return iterator
     */
    @Override
    public Iterator<T> iterator() {
        return new DoubleLinkedListIterator<>(this);
    }

    /**
     * Last node in list.
     *
     * @return Last data value
     */
    public T last() {
        return tail != null ? tail.data : null;
    }

    /**
     * Print all nodes in the list.
     */
    public void printAll() {
        StringBuilder line = new StringBuilder();
        line.append("[");

        for (T element : this) {
            line.append(element);
            line.append(" ");
        }
        line.append("]");

        LoggerService.logInfo(line.toString());
    }

    /**
     * Print all nodes in the list from the tail.
     */
    public void printReverse() {
        String line;
        for (DoubleLinkedListNode<T> tmp = tail; tmp != null; tmp = tmp.previous) {
            line = tmp == head ? "(*)" + tmp.data : "- " + tmp.data;
            LoggerService.logInfo(line);
        }
    }

    /**
     * Remove node from the tail.
     *
     * @return Data object
     */
    public T removeFromTail() {
        T el = tail.data;
        if (head == tail) {
            head = tail = null;
        } else {
            tail = tail.previous;
            tail.next = null;
        }

        return el;
    }

    /**
     * Get the list size
     *
     * @return Number of elements contained
     */
    public int size() {
        int size = 0;
        DoubleLinkedListNode<T> tmp = head;
        while (tmp != null) {
            ++size;
            tmp = tmp.next;
        }
        return size;
    }

    /**
     * View the first node element in the list
     *
     * @return Head element
     */
    public DoubleLinkedListNode<T> viewHeadNode() {
        return head;
    }

    /**
     * View the last element in the list
     *
     * @return Tail element
     */
    public DoubleLinkedListNode<T> viewTailNode() {
        return tail;
    }
}
