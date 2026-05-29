package org.drozdek.queues.interfaces;

import org.drozdek.commons.DataTypeInterface;

public interface QueueInterface<T> extends DataTypeInterface {

    boolean enqueue(T element);

    T dequeue();

    T peek();

    boolean isEmpty();

    int size();

    void clear();

    static String boxedQueue(String content) {
        String line = "║ " + content + " ║";
        String border = "═".repeat(line.length() - 2);
        return "╔" + border + "╗" + System.lineSeparator() +
                line + System.lineSeparator() +
                "╚" + border + "╝";
    }
}
