package org.drozdek.basics.lists;

import java.io.PrintStream;

/**
 * Double linked list data structure.
 */
public class DoubleLinkedList {
    protected DoubleLinkedListNode head;
    protected DoubleLinkedListNode tail;

    /**
     * Default constructor.
     */
    public DoubleLinkedList() {
        head = tail = null;
    }

    /**
     * Add a node to the tail.
     *
     * @param el Data object
     */
    public void addToTail(Object el) {
        if (!isEmpty()) {
            tail = new DoubleLinkedListNode(el, null, tail);
            tail.previous.next = tail;
        } else head = tail = new DoubleLinkedListNode(el);
    }

    /**
     * Delete the matching node.
     *
     * @param el Node to be erased
     */
    public void delete(Object el) {
        if (head != null) {
            if (el.equals(head.data)) {
                head = head.next;
            } else {
                if (el.equals(tail.data)) {
                    removeFromTail();
                } else {
                    DoubleLinkedListNode pred = head;
                    DoubleLinkedListNode tmp = head.next;
                    for (; tmp != null && !(tmp.data.equals(el)); pred = pred.next, tmp = tmp.next) ;

                    if (tmp != null)
                        pred.next = tmp.next;
                }
            }
        }
    }

    /**
     * Search for a node in the list.
     *
     * @param el Data object
     * @return Matching node
     */
    public Object find(Object el) {
        DoubleLinkedListNode tmp = head;
        for (; tmp != null && !el.equals(tmp.data); tmp = tmp.next) ;
        if (tmp == null)
            return null;

        else return tmp.data;
    }

    /**
     * First node in list.
     *
     * @return Data object
     */
    public Object first() {
        return head != null ? head.data : null;
    }

    /**
     * List is empty?
     *
     * @return
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Last node in list.
     *
     * @return Data object
     */
    public Object last() {
        return tail != null ? tail.data : null;
    }

    /**
     * Print all nodes in the list.
     *
     * @param out Print stream
     */
    public void printAll(PrintStream out) {
        String line;
        for (DoubleLinkedListNode tmp = head; tmp != null; tmp = tmp.next) {
            line = tmp == head ? "(*)" + tmp.data : "- " + tmp.data;
            out.println(line);
        }
    }

    /**
     * Print all nodes in the list from the tail.
     *
     * @param out Print stream
     */
    public void printReverse(PrintStream out) {
        String line;
        for (DoubleLinkedListNode tmp = tail; tmp != null; tmp = tmp.previous) {
            line = tmp == head ? "(*)" + tmp.data : "- " + tmp.data;
            out.println(line);
        }
    }

    /**
     * Remove node from the tail.
     *
     * @return Data object
     */
    public Object removeFromTail() {
        Object el = tail.data;
        if (head == tail) {
            head = tail = null;
        } else {
            tail = tail.previous;
            tail.next = null;
        }

        return el;
    }
}
