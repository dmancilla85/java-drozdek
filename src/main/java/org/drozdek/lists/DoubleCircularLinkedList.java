package org.drozdek.basics.lists;

/**
 * Double circular linked list data structure.
 */
public class DoubleCircularLinkedList extends DoubleLinkedList {
    /**
     * Add node to the tail.
     * @param el Data object
     */
    public void addToTail(Object el) {
        if (!isEmpty()) {
            tail = new DoubleLinkedListNode(el, head, tail);
            tail.previous.next = tail;

        } else head = tail = new DoubleLinkedListNode();
    }
}
