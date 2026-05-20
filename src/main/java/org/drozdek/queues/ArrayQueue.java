package org.drozdek.queues;

import org.drozdek.commons.LoggerService;
import org.drozdek.queues.interfaces.QueueInterface;

import java.util.Arrays;

/**
 * Array-based circular queue implementation.
 * 
 * <p>
 * Abstract Data Type: Queue (FIFO - First In, First Out)
 * 
 * <p>
 * This implementation uses a fixed-size array with circular indexing,
 * providing O(1) enqueue and dequeue operations.
 */
public class ArrayQueue implements QueueInterface<Object> {
    private final int capacity;
    private final Object[] storage;
    private int first;
    private int last;

    /**
     * Constructs an array queue with the specified capacity.
     * 
     * @param capacity the maximum number of elements this queue can hold
     */
    public ArrayQueue(int capacity) {
        this.capacity = capacity;
        storage = new Object[capacity];
        first = last = -1;
    }

    /**
     * Removes and returns the element at the front of the queue.
     * 
     * @return the element at the front of the queue, or null if the queue is empty
     */
    public Object dequeue() {
        if (isEmpty()) {
            return null;
        }
        
        Object data = storage[first];

        if (first == last) {
            // Queue becomes empty
            last = first = -1;
        } else if (first == capacity - 1) {
            // Wrap around to beginning
            first = 0;
        } else {
            // Move to next position
            first++;
        }

        return data;
    }

    /**
     * Adds an element to the rear of the queue.
     * 
     * @param element the element to add to the queue
     * @throws IllegalStateException if the queue is full
     */
    public boolean enqueue(Object element) {
        if (isFull()) {
            throw new IllegalStateException("Queue is full");
        }
        
        if (last == capacity - 1 || last == -1) {
            // Wrap around to beginning or first element
            storage[0] = element;
            last = 0;
            
            if (first == -1) {
                // First element being added
                first = 0;
            }
        } else {
            // Normal case: increment last and store
            storage[++last] = element;
        }
        return true;
    }

    public Object peek() {
        return firstElement();
    }

    /**
     * Returns the element at the front of the queue without removing it.
     * 
     * @return the element at the front of the queue, or null if the queue is empty
     */
    public Object firstElement() {
        if (isEmpty()) {
            return null;
        }
        return storage[first];
    }

    /**
     * Tests if this queue contains no elements.
     * 
     * @return true if this queue contains no elements; false otherwise
     */
    public boolean isEmpty() {
        return first == -1;
    }

    /**
     * Tests if this queue is full.
     * 
     * @return true if this queue is full; false otherwise
     */
    public boolean isFull() {
        // Queue is full if:
        // 1. first is at index 0 and last is at the last index, OR
        // 2. first is exactly one position after last (in circular sense)
        return (first == 0 && last == capacity - 1) ||
               (first == (last + 1) % capacity);
    }

    /**
     * Returns the number of elements in this queue.
     * 
     * @return the number of elements in this queue
     */
    public int size() {
        if (isEmpty()) {
            return 0;
        }
        
        if (first <= last) {
            // No wrap-around: elements are in [first, last]
            return last - first + 1;
        } else {
            // Wrap-around: elements are in [first, capacity-1] and [0, last]
            return (capacity - first) + (last + 1);
        }
    }

    public void clear() {
        first = last = -1;
    }

    /**
     * Prints all elements in the queue to the logger service.
     * Elements are printed in order from front to rear.
     */
    public void printAll() {
        StringBuilder line = new StringBuilder();
        
        for (int i = 0; i < size(); i++) {
            int index = (first + i) % capacity;
            line.append(storage[index]);
            line.append(" ");
        }
        
        LoggerService.logInfo(line.toString());
    }

    @Override
    public String toString() {
        return Arrays.toString(storage);
    }
}
