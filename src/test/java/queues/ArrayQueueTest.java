package queues;

import org.drozdek.commons.LoggerService;
import org.drozdek.queues.ArrayQueue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArrayQueueTest {
    ArrayQueue queue;

    @BeforeEach
    void setUp() {
        queue = new ArrayQueue(6);
    }

    @Test
    @DisplayName("A new stack always is empty")
    void isEmpty() {
        assertTrue(queue.isEmpty(), "The stack should be empty");
    }

    @Test
    @DisplayName("A full stack should be informed as full")
    void isFull() {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(6);

        assertTrue(queue.isFull(), "The stack should be marked as full");
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

        assertEquals(4, queue.size(), "The expected size doesn't match with the queue size");
    }

    @Test
    @DisplayName("Check if elements are correctly enqueued")
    void dequeue(){
        queue.enqueue(45);
        queue.enqueue(55);
        queue.enqueue(15);
        queue.enqueue(65);

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

        LoggerService.logInfo(queue.toString());

        assertEquals(4,queue.size(), "It seems that the first element has been removed");
        assertEquals(45,first, "The first element doesn't match with the expected");
    }
}
