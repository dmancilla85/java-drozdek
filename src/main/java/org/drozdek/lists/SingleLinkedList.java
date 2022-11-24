package org.drozdek.lists;

import org.drozdek.commons.LoggerService;
import org.drozdek.lists.iterators.SingleLinkedListIterator;

import java.util.Iterator;

/**
 * Single linked list data structure.
 * The head node is always the last added element.
 */
public class SingleLinkedList<T> implements Iterable<T> {
    protected SingleLinkedListNode<T> head;

    /**
     * Default constructor.
     */
    public SingleLinkedList() {
        head = null;
    }

    /**
     * Add a node to list.
     *
     * @param data Data object
     */
    public void add(T data) {
        head = new SingleLinkedListNode<>(data, head);
    }

    /**
     * Delete the matching node.
     *
     * @param data Node to be erased
     */
    public void delete(T data) {
        if (head != null) {
            if (data.equals(head.data)) {
                deleteHead();
            } else {
                SingleLinkedListNode<T> predecessor = head;
                SingleLinkedListNode<T> tmp = head.next;
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
     * Delete head node.
     *
     * @return Node erased
     */
    public T deleteHead() {
        T el = head.data;
        head = head.next;
        return el;
    }

    /**
     * Search for a node in the list.
     *
     * @param data Data object
     * @return Matching node
     */
    public T find(T data) {
        SingleLinkedListNode<T> tmp = head;
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
     * @return Data object
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
        return new SingleLinkedListIterator<>(this);
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
     * Get the list size
     *
     * @return Number of elements contained
     */
    public int size() {
        int size = 0;
        SingleLinkedListNode<T> tmp = head;
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
    public SingleLinkedListNode<T> viewHeadNode() {
        return head;
    }
}

