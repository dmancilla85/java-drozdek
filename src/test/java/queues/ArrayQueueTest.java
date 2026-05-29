package queues;

import org.drozdek.commons.LoggerService;
import org.drozdek.queues.ArrayQueue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayQueueTest {
    ArrayQueue queue;

    @BeforeEach
    void setUp() {
        queue = new ArrayQueue(6);
    }

    @Test
    @DisplayName("A new queue always is empty")
    void isEmpty() {
        assertTrue(queue.isEmpty(), "The queue should be empty");
    }

    @Test
    @DisplayName("Queue is not empty after enqueue")
    void isNotEmptyAfterEnqueue() {
        queue.enqueue(1);
        assertFalse(queue.isEmpty(), "Queue should not be empty after enqueue");
    }

    @Test
    @DisplayName("A full queue should be informed as full")
    void isFull() {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(6);

        assertTrue(queue.isFull(), "The queue should be marked as full");
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
    @DisplayName("Check if size is correct")
    void size(){
        queue.enqueue(1);
        queue.enqueue(2);

        assertEquals(2, queue.size(), "The expected size doesn't match with the queue size");
    }

    @Test
    @DisplayName("Check if elements are correctly enqueued")
    void enqueue(){
        queue.enqueue(45);
        queue.enqueue(55);
        queue.enqueue(15);
        queue.enqueue(65);
        LoggerService.logInfo(queue.showStorage());
        assertEquals(4, queue.size(), "The expected size doesn't match with the queue size");
    }

    @Test
    @DisplayName("Check if elements are correctly dequeued")
    void dequeue(){
        queue.enqueue(45);
        queue.enqueue(55);
        queue.enqueue(15);
        queue.enqueue(65);

        LoggerService.logInfo(queue.showStorage());
        Integer first= (Integer) queue.dequeue();

        assertEquals(45,first, "The first element dequeued doesn't match with the expected");
    }

    @Test
    @DisplayName("Check if first element is the expected")
    void firstElement(){
        queue.enqueue(45);
        queue.enqueue(55);
        queue.enqueue(15);
        queue.enqueue(65);

        Integer first= (Integer) queue.firstElement();

        queue.print();

        assertEquals(4,queue.size(), "It seems that the first element has been removed");
        assertEquals(45,first, "The first element doesn't match with the expected");
    }

    @Test
    @DisplayName("Dequeue on empty queue returns null")
    void dequeueOnEmpty() {
        assertNull(queue.dequeue(), "Dequeue on empty should return null");
    }

    @Test
    @DisplayName("Peek on empty queue returns null")
    void peekOnEmpty() {
        assertNull(queue.peek(), "Peek on empty should return null");
    }

    @Test
    @DisplayName("Circular wrap-around preserves order")
    void wrapAround() {
        // Fill queue to capacity
        for (int i = 1; i <= 6; i++) {
            queue.enqueue(i);
        }
        // Dequeue 3, enqueue 3 more to trigger wrap-around
        assertEquals(1, queue.dequeue());
        assertEquals(2, queue.dequeue());
        assertEquals(3, queue.dequeue());
        queue.enqueue(7);
        queue.enqueue(8);
        queue.enqueue(9);

        assertEquals(4, queue.dequeue(), "First after wrap should be 4");
        assertEquals(5, queue.dequeue(), "Second after wrap should be 5");
        assertEquals(6, queue.dequeue(), "Third after wrap should be 6");
        assertEquals(7, queue.dequeue(), "Fourth after wrap should be 7");
        assertEquals(8, queue.dequeue(), "Fifth after wrap should be 8");
        assertEquals(9, queue.dequeue(), "Sixth after wrap should be 9");
        assertTrue(queue.isEmpty(), "Queue should be empty after draining");
    }

    @Test
    @DisplayName("toString on empty queue throws no exception")
    void toStringOnEmpty() {
        assertDoesNotThrow(() -> queue.toString());
    }

    @Test
    @DisplayName("toString on non-empty queue throws no exception")
    void toStringOnNonEmpty() {
        queue.enqueue(1);
        queue.enqueue(2);
        assertDoesNotThrow(() -> queue.toString());
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
}
