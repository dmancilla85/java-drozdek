package queues;

import org.drozdek.commons.LoggerService;
import org.drozdek.queues.Queue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class QueueTest {
    Queue<Integer> queue;

    @BeforeEach
    void setUp() {
        queue = new Queue<>();
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
    @DisplayName("Check if size is correct")
    void size(){
        queue.enqueue(1);
        queue.enqueue(2);

        assertEquals(2, queue.size(), "The expected size doesn't match with the queue size");
    }

    @Test
    @DisplayName("Check if after cleaning the queue is empty")
    void clear(){
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(6);
        queue.clear();

        assertEquals(0, queue.size(), "The expected size doesn't match with zero");
    }

    @Test
    @DisplayName("Check if elements are correctly enqueued")
    void enqueue(){
        queue.enqueue(45);
        queue.enqueue(55);
        queue.enqueue(15);
        queue.enqueue(65);

        assertEquals(4, queue.size(), "The expected size doesn't match with the queue size");
    }

    @Test
    @DisplayName("Check if elements are correctly dequeued")
    void dequeue(){
        queue.enqueue(45);
        queue.enqueue(55);
        queue.enqueue(15);
        queue.enqueue(65);

        Integer first= queue.dequeue();

        assertEquals(45,first, "The first element dequeued doesn't match with the expected");
    }

    @Test
    @DisplayName("Check if first element is the expected")
    void firstElement(){
        queue.enqueue(45);
        queue.enqueue(55);
        queue.enqueue(15);
        queue.enqueue(65);

        Integer first= queue.firstElement();
        queue.print();

        assertEquals(4,queue.size(), "It seems that the first element has been removed");
        assertEquals(45,first, "The first element doesn't match with the expected");
    }

    @Test
    @DisplayName("Dequeue on empty queue throws NoSuchElementException")
    void dequeueOnEmpty() {
        assertThrows(NoSuchElementException.class, () -> queue.dequeue());
    }

    @Test
    @DisplayName("Peek on empty queue throws NoSuchElementException")
    void peekOnEmpty() {
        assertThrows(NoSuchElementException.class, () -> queue.peek());
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
        Integer firstBefore = queue.peek();

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
