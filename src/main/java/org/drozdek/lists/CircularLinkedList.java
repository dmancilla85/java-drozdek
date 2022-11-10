package org.drozdek.basics.lists;

/**
 * Circular linked list data structure.
 */
public class CircularLinkedList extends SingleLinkedList {

    /**
     * Add node to the tail.
     * @param el Data object
     */
    public void addToTail(Object el) {
        if (isEmpty()) {
            head = new SingleLinkedListNode(el);
            head.next = head;
        } else {
            head.next = new SingleLinkedListNode(el, head.next);
            head = head.next;
        }
    }
}
