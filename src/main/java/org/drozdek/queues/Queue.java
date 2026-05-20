package org.drozdek.queues;

import org.drozdek.commons.LoggerService;
import org.drozdek.queues.interfaces.QueueInterface;

import java.util.LinkedList;

public class Queue<T> implements QueueInterface<T> {
    private final LinkedList<T> list;

    public Queue() {
        list = new LinkedList<>();
    }

    public void clear() {
        list.clear();
    }

    public T dequeue() {
        return list.removeFirst();
    }

    public boolean enqueue(T element) {
        list.addLast(element);
        return true;
    }

    public T firstElement() {
        return list.getFirst();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public T peek() {
        return list.getFirst();
    }

    public void printAll() {
        StringBuilder line = new StringBuilder();
        for (T element : list) {
            line.append(element);
            line.append(" ");
        }
        LoggerService.logInfo(line.toString());
    }

    public int size() {
        return list.size();
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
