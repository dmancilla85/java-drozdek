package org.drozdek.lists;

import java.io.PrintStream;

/**
 * Circular linked list data structure.
 */
public class CircularLinkedList<T> extends SingleLinkedList<T> {

    // @Override
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
            head = new SingleLinkedListNode<T>(el);
            head.next = head;
        } else {
            SingleLinkedListNode<T> tmp = head;

            while (tmp.next != head)
                tmp = tmp.next;

            tmp.next = new SingleLinkedListNode<T>(el, head);
        }
    }

    @Override
    public void delete(T data) {
        if (head != null) {
            if (data.equals(head.getData())) {
                head = head.next;
            } else {
                SingleLinkedListNode<T> predecessor = head;
                SingleLinkedListNode<T> tmp = head.next;
                boolean flag = true;

                while (!(tmp.getData().equals(data)) && flag) {
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
        T el = head.getData();
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

        while (flag && !data.equals(tmp.getData())) {
            tmp = tmp.next;
            flag = tmp != head;
        }

        return !flag ? null : tmp.getData();
    }

    @Override
    public void printAll(PrintStream out) {
        String line;
        int i = 0;
        int size = size();
        out.println("Size: " + size);

        for (SingleLinkedListNode<T> tmp = head; i++ < size; tmp = tmp.next) {
            line = tmp == head ? "(*)" + tmp.getData() : "- " + tmp.getData();
            out.println(line);
        }
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