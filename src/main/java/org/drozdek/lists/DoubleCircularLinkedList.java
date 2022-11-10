package org.drozdek.lists;

import java.io.PrintStream;

/**
 * Double circular linked list data structure.
 */
public class DoubleCircularLinkedList<T> extends DoubleLinkedList<T> {

    @Override
    public void addToTail(T data) {
        if (isEmpty()) {
            head = new DoubleLinkedListNode<>(data);
            head.next = head;
            head.previous = head;
            tail = head;
        } else {
            DoubleLinkedListNode<T> node = new DoubleLinkedListNode<>(data, head, tail);
            head.previous = node;
            tail.next = node;
            tail = node;
        }
    }

    @Override
    public void delete(T data) {
        if (head == null)
            return;

        if (data.equals(head.data)) {
            tail.next = head.next;
            head = head.next;
            head.previous = tail;
        } else {
            if (data.equals(tail.data)) {
                removeFromTail();
            } else {
                DoubleLinkedListNode<T> predecessor = head;
                DoubleLinkedListNode<T> tmp = head.next;
                boolean flag = true;

                while (tmp != null && !(tmp.data.equals(data)) && flag) {
                    predecessor = predecessor.next;
                    tmp = tmp.next;
                    flag = tmp != tail;
                }

                if (tmp != null) {
                    tmp.next.previous = predecessor;
                    predecessor.next = tmp.next;
                }
            }
        }
    }

    @Override
    public T find(T data) {
        DoubleLinkedListNode<T> tmp = head;
        boolean flag = true;

        while (flag && !data.equals(tmp.data)) {
            tmp = tmp.next;
            flag = tmp != head;
        }

        return !flag ? null : tmp.data;
    }

    @Override
    public void printAll(PrintStream out) {
        String line;
        int i = 0;
        int size = size();

        for (DoubleLinkedListNode<T> tmp = head; i++ < size; tmp = tmp.next) {
            line = tmp == head ? "(*)" + tmp.data : "- " + tmp.data;
            out.println(line);
        }
    }

    @Override
    public T removeFromTail() {
        T el = tail.data;
        if (head == tail) {
            head = tail = null;
        } else {
            tail = tail.previous;
            tail.next = head;
        }

        return el;
    }

    @Override
    public int size() {
        int size = 0;
        DoubleLinkedListNode<T> tmp = head;
        boolean flag = true;

        while (flag && tmp != null) {
            size++;
            tmp = tmp.next;
            flag = tmp != head;
        }
        return size;
    }
}
