package org.drozdek.queues.unlam;

import org.drozdek.commons.LoggerService;
import org.drozdek.queues.interfaces.QueueInterface;

import static java.lang.Math.random;
import static java.lang.System.err;
import static java.lang.System.out;

/**
 * Static array-based circular queue implementation.
 * 
 * <p>
 * Abstract Data Type: Static Queue (FIFO - First In, First Out)
 * 
 * <p>
 * This implementation uses a fixed-size array with circular indexing,
 * providing O(1) enqueue and dequeue operations when not full.
 * When the queue becomes full, it automatically doubles its capacity.
 */
public class StaticQueue implements UnlamQueue, QueueInterface<Object> {

    private static final int DEFAULT_SIZE = 5;
    private Object[] queue;
    private int first, last, size;

    public StaticQueue() {
        queue = new Object[DEFAULT_SIZE];
        this.size = DEFAULT_SIZE;
        first = 0;
        last = -1;
    }

    public StaticQueue(int capacity) {
        queue = new Object[capacity];
        this.size = capacity;
        first = 0;
        last = -1;
    }

    /**
     * Main method for testing the static queue implementation.
     * 
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        UnlamQueue q1 = new StaticQueue();

        q1.enqueue("A");
        q1.enqueue("B");
        q1.enqueue("C");

        int i = 0, n = 2600;
        while (i++ < n)
            q1.enqueue(random() * 100 * i);

        q1.enqueue("LAST OBJECT");

        while (!q1.isEmpty())
            out.println(q1.dequeue());
    }

    @Override
    public Object dequeue() {
        Object data;

        if (!isEmpty()) {
            data = queue[first];

            if (first == last)
                clear();
            else
                first = (first + 1) % size;

            return data;
        } else {
            return null;
        }
    }

    @Override
    public boolean enqueue(Object obj) {
        try {
            if (isFull())
                throw new FullQueueException();

            last = (last + 1) % size;
            queue[last] = obj;
        } catch (FullQueueException e) {
            err.println(e.getMessage() + " -- Index: " + ((last + 1) % size));
            resize();
            enqueue(obj);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    /**
     * Checks if the queue is full.
     * 
     * @return true if the queue is full, false otherwise
     */
    public boolean isFull() {
        return ((first == 0 && last == size - 1) ||
                (first == (last + 1) % size && last != -1));
    }

    @Override
    public boolean isEmpty() {
        return (first == 0 && last == -1);
    }

    /**
     * Doubles the capacity of the queue and reorganizes elements
     * to be sequential starting at index 0.
     */
    private void resize() {
        int oldCapacity = size;
        int elementCount = elementCount();
        int newCapacity = 2 * oldCapacity;
        Object[] newQueue = new Object[newCapacity];

        for (int i = 0; i < elementCount; i++) {
            newQueue[i] = queue[(first + i) % oldCapacity];
        }

        queue = newQueue;
        size = newCapacity;
        first = 0;
        last = elementCount - 1;
    }

    private int elementCount() {
        if (isEmpty()) {
            return 0;
        }
        if (first <= last) {
            return last - first + 1;
        }
        return size - first + last + 1;
    }

    @Override
    public Object peek() {
        if (!isEmpty())
            return queue[first];
        return null;
    }

    public int size() {
        return elementCount();
    }

    public void printAll() {
        StringBuilder line = new StringBuilder();
        int count = elementCount();
        for (int i = 0; i < count; i++) {
            line.append(queue[(first + i) % size]);
            line.append(" ");
        }
        LoggerService.logInfo(line.toString());
    }

    @Override
    public void clear() {
        first = 0;
        last = -1;
    }
}
