package org.drozdek.basics.lists;

import java.io.PrintStream;

/**
 * Single linked list data structure.
 */
public class SingleLinkedList {
    protected SingleLinkedListNode head;

    /**
     * Default constructor.
     */
    public SingleLinkedList() {
        head = null;
    }

    /**
     * Add a node to list.
     * @param el Data object
     */
    public void add(Object el) {
        head = new SingleLinkedListNode(el, head);
    }

    /**
     * Delete the matching node.
     * @param el Node to be erased
     */
    public void delete(Object el) {
        if (head != null) {
            if (el.equals(head.data)) {
                head = head.next;
            } else {
                SingleLinkedListNode pred = head;
                SingleLinkedListNode tmp = head.next;
                for (; tmp != null && !(tmp.data.equals(el)); pred = pred.next, tmp = tmp.next) ;

                if (tmp != null)
                    pred.next = tmp.next;
            }
        }
    }

    /**
     * Delete head node.
     * @return Node erased
     */
    public Object deleteHead() {
        Object el = head.data;
        head = head.next;
        return el;
    }

    /**
     * Search for a node in the list.
     * @param el Data object
     * @return Matching node
     */
    public Object find(Object el) {
        SingleLinkedListNode tmp = head;
        for (; tmp != null && !el.equals(tmp.data); tmp = tmp.next) ;
        if (tmp == null)
            return null;

        else return tmp.data;
    }

    /**
     * First node in list.
     * @return Data object
     */
    public Object first() {
        return head != null ? head.data : null;
    }

    /**
     * List is empty?
     * @return
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Print all nodes in the list.
     * @param out Print stream
     */
    public void printAll(PrintStream out) {
        for (SingleLinkedListNode tmp = head; tmp != null; tmp = tmp.next) {
            out.println(tmp.data);
        }
    }
}
