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

    @Override
    public String toString() {
        if (isEmpty()) {
            return QueueInterface.boxedQueue("[ EMPTY ]");
        }
        StringBuilder sb = new StringBuilder("FRONT");
        for (T element : list) {
            sb.append(" ➔ [").append(element).append("]");
        }
        sb.append(" ➔ REAR");
        return QueueInterface.boxedQueue(sb.toString());
    }

    public int size() {
        return list.size();
    }
}
