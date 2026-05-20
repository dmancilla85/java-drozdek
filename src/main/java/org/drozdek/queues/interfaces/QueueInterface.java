package org.drozdek.queues.interfaces;

public interface QueueInterface<T> {
    boolean enqueue(T element);
    T dequeue();
    T peek();
    boolean isEmpty();
    int size();
    void clear();
    void printAll();
}
