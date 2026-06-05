package queues.unlam;

import org.drozdek.queues.unlam.DynamicQueue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DynamicQueueTest {
    DynamicQueue queue;

    @BeforeEach
    void setUp() {
        queue = new DynamicQueue();
    }

    @Test
    @DisplayName("A new queue always is empty")
    void isEmpty() {
        assertTrue(queue.isEmpty(), "The queue should be empty");
    }

    @Test
    @DisplayName("Queue is not empty after enqueue")
    void isNotEmptyAfterEnqueue() {
        queue.enqueue("A");
        assertFalse(queue.isEmpty(), "Queue should not be empty after enqueue");
    }

    @Test
    @DisplayName("Check if size is correct")
    void size() {
        queue.enqueue(1);
        queue.enqueue(2);

        assertEquals(2, queue.size(), "The expected size doesn't match with the queue size");
    }

    @Test
    @DisplayName("Check if after clearing the queue is empty")
    void clear() {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.clear();

        assertEquals(0, queue.size(), "Size should be zero");
        assertTrue(queue.isEmpty(), "Queue should be empty after clear");
    }

    @Test
    @DisplayName("Check if elements are correctly enqueued")
    void enqueue() {
        queue.enqueue("First");
        queue.enqueue("Second");
        queue.enqueue("Third");

        assertEquals(3, queue.size(), "The expected size doesn't match with the queue size");
    }

    @Test
    @DisplayName("Check if elements are correctly dequeued")
    void dequeue() {
        queue.enqueue(45);
        queue.enqueue(55);
        queue.enqueue(15);

        assertEquals(45, queue.dequeue(), "The first element dequeued doesn't match with the expected");
    }

    @Test
    @DisplayName("Dequeue on empty queue returns null")
    void dequeueOnEmpty() {
        assertNull(queue.dequeue(), "Dequeue on empty should return null");
    }

    @Test
    @DisplayName("Check if first element is the expected")
    void peek() {
        queue.enqueue("X");
        queue.enqueue("Y");

        assertEquals("X", queue.peek(), "First element should be X");
        assertEquals(2, queue.size(), "Peek should not remove the element");
    }

    @Test
    @DisplayName("Peek on empty queue returns null")
    void peekOnEmpty() {
        assertNull(queue.peek(), "Peek on empty should return null");
    }

    @Test
    @DisplayName("Enqueue and dequeue many elements")
    void manyOperations() {
        for (int i = 0; i < 100; i++) {
            queue.enqueue(i);
        }

        assertEquals(100, queue.size(), "Size should be 100 after 100 enqueues");

        for (int i = 0; i < 100; i++) {
            assertEquals(i, queue.dequeue(), "Elements should come out in FIFO order");
        }

        assertTrue(queue.isEmpty(), "Queue should be empty after draining");
    }

    @Test
    @DisplayName("Print all does not modify the queue")
    void printAll() {
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        int sizeBefore = queue.size();
        Object firstBefore = queue.peek();

        queue.print();

        assertEquals(sizeBefore, queue.size(), "Queue size unchanged after printAll");
        assertEquals(firstBefore, queue.peek(), "First element unchanged after printAll");
    }

    @Test
    @DisplayName("Print all on empty queue throws no exception")
    void printAllOnEmptyQueue() {
        assertDoesNotThrow(() -> queue.print());
    }

    @Test
    @DisplayName("toString on empty queue throws no exception")
    void toStringOnEmpty() {
        assertDoesNotThrow(() -> queue.toString());
    }

    @Test
    @DisplayName("toString on non-empty queue throws no exception")
    void toStringOnNonEmpty() {
        queue.enqueue("hello");
        queue.enqueue("world");

        assertDoesNotThrow(() -> queue.toString());
    }
}
