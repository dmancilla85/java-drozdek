package org.drozdek.lists;

import org.drozdek.commons.LoggerService;

/**
 * Circular linked list data structure.
 * The head node is always the last added element.
 */
public class CircularLinkedList<T> extends SingleLinkedList<T> {

    @Override
    public void add(T data) {
        addToTail(data);
    }

    /**
     * Add node to the tail.
     *
     * @param el Data object
     */
    public void addToTail(T el) {
        if (isEmpty()) {
            head = new SingleLinkedListNode<>(el);
            head.next = head;
        } else {
            SingleLinkedListNode<T> tmp = head;

            while (tmp.next != head)
                tmp = tmp.next;

            tmp.next = new SingleLinkedListNode<>(el, head);
        }
    }

    @Override
    public void delete(T data) {
        if (head != null) {
            if (data.equals(head.data)) {
                head = head.next;
            } else {
                SingleLinkedListNode<T> predecessor = head;
                SingleLinkedListNode<T> tmp = head.next;
                boolean flag = true;

                while (!(tmp.data.equals(data)) && flag) {
                    predecessor = predecessor.next;
                    tmp = tmp.next;
                    flag = tmp != head;
                }

                predecessor.next = tmp.next;
            }
        }
    }

    @Override
    public T deleteHead() {
        T el = head.data;
        SingleLinkedListNode<T> tmp = head;

        while (tmp.next != head)
            tmp = tmp.next;

        head = head.next;
        tmp.next = head;

        return el;
    }

    @Override
    public T find(T data) {
        SingleLinkedListNode<T> tmp = head;
        boolean flag = true;

        while (flag && !data.equals(tmp.data)) {
            tmp = tmp.next;
            flag = tmp != head;
        }

        return !flag ? null : tmp.data;
    }

    @Override
    public void printAll() {
        StringBuilder line = new StringBuilder();
        int i = 0;
        int size = size();
        line.append("[");

        for (SingleLinkedListNode<T> tmp = head; i++ < size; tmp = tmp.next) {
            line.append(tmp == head ? "*" + tmp.data : tmp.data);
            line.append(i != size ? ", " : "");
        }
        line.append("]");

        LoggerService.logInfo(line.toString());
    }

    @Override
    public int size() {
        int size = 0;
        SingleLinkedListNode<T> tmp = head;
        boolean flag = true;

        while (flag && tmp != null) {
            size++;
            tmp = tmp.next;
            flag = tmp != head;
        }
        return size;
    }
}
