package org.drozdek.lists;

import java.io.PrintStream;

/**
 * Single linked list data structure.
 */
public class SingleLinkedList<T> {
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
        head = new SingleLinkedListNode<T>(data, head);
    }

    /**
     * Delete the matching node.
     *
     * @param data Node to be erased
     */
    public void delete(T data) {
        if (head != null) {
            if (data.equals(head.getData())) {
                deleteHead();
            } else {
                SingleLinkedListNode<T> predecessor = head;
                SingleLinkedListNode<T> tmp = head.next;
                while (tmp != null && !(tmp.getData().equals(data))) {
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
        T el = head.getData();
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
        while (tmp != null && !data.equals(tmp.getData())) {
            tmp = tmp.next;
        }
        if (tmp == null)
            return null;

        else return tmp.getData();
    }

    /**
     * First node in list.
     *
     * @return Data object
     */
    public T first() {
        return head != null ? head.getData() : null;
    }

    /**
     * List is empty?
     *
     * @return True if list is empty
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Print all nodes in the list.
     *
     * @param out Print stream
     */
    public void printAll(PrintStream out) {
        String line;

        for (SingleLinkedListNode<T> tmp = head; tmp != null; tmp = tmp.next) {
            line = tmp == head ? "(*)" + tmp.getData() : "- " + tmp.getData();
            out.println(line);
        }

        out.println();
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
