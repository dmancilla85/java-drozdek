package org.drozdek.queues.unlam;

import org.drozdek.queues.interfaces.QueueInterface;
import org.drozdek.queues.interfaces.UnlamQueue;

import static java.lang.Math.random;
import org.drozdek.commons.LoggerService;

/// Static array-based circular queue implementation with automatic resizing.
///
/// **Abstract Data Type:** Static Queue (FIFO - First In, First Out)
///
/// **Real-world use case:** Bounded buffers in network packet processing,
/// fixed-size event queues in real-time systems.
///
/// Complexity Analysis:
/// Time Complexity: O(1) amortized for enqueue and dequeue
/// Auxiliary Space: O(n) for the storage array
///
/// @see <a href="https://en.wikipedia.org/wiki/Introduction_to_Algorithms">Cormen et al., Introduction to Algorithms, 4th ed. (MIT Press)</a>
public class StaticQueue implements UnlamQueue, QueueInterface<Object> {

    private static final int DEFAULT_SIZE = 5;
    private Object[] queue;
    private int first;
    private int last;
    private int size;

    public StaticQueue() {
        queue = new Object[DEFAULT_SIZE];
        this.size = DEFAULT_SIZE;
        first = 0;
        last = -1;
    }

    /// Constructs a circular queue backed by an array of the given capacity.
    ///
    /// When the capacity is exceeded, the backing array doubles in size
    /// on the next enqueue.
    ///
    /// @param capacity initial capacity of the backing array
    public StaticQueue(int capacity) {
        queue = new Object[capacity];
        this.size = capacity;
        first = 0;
        last = -1;
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

    /// Adds an element to the rear of the queue.
    ///
    /// If the queue is full, its capacity is automatically doubled and the
    /// insertion retried, so this method never fails due to a full queue.
    @Override
    public boolean enqueue(Object obj) {
        try {
            if (isFull())
                throw new FullQueueException();

            last = (last + 1) % size;
            queue[last] = obj;
        } catch (FullQueueException e) {
            LoggerService.logError(e.getMessage() + " -- Index: " + ((last + 1) % size));
            resize();
            enqueue(obj);
        } catch (Exception _) {
            return false;
        }

        return true;
    }

    /// Checks if the queue is full.
    ///
    /// @return true if the queue is full, false otherwise
    public boolean isFull() {
        return (first == 0 && last == size - 1) ||
                (first == (last + 1) % size && last != -1);
    }

    @Override
    public boolean isEmpty() {
        return first == 0 && last == -1;
    }

    /// Doubles the capacity of the queue and reorganizes elements
    /// to be sequential starting at index 0.
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

    /// Returns the number of elements currently stored in this queue.
    ///
    /// This is the live element count, not the backing array capacity;
    /// it accounts for wrap-around positions in the circular layout.
    ///
    /// @return the number of elements in this queue
    public int size() {
        return elementCount();
    }

    @Override
    public String toString() {
        int count = elementCount();
        if (count == 0) {
            return QueueInterface.boxedQueue("[ EMPTY ]");
        }
        StringBuilder sb = new StringBuilder("FRONT");
        for (int i = 0; i < count; i++) {
            sb.append(" ➔ [").append(queue[(first + i) % size]).append("]");
        }
        sb.append(" ➔ REAR");
        return QueueInterface.boxedQueue(sb.toString());
    }

    @Override
    public void clear() {
        first = 0;
        last = -1;
    }
}
