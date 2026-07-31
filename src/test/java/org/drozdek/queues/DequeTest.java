package org.drozdek.queues;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Deque Tests")
class DequeTest {
    private Deque<String> deque;

    @BeforeEach
    void setUp() {
        deque = new Deque<>();
    }

    @Test
    @DisplayName("New deque is empty")
    void newDequeIsEmpty() {
        assertTrue(deque.isEmpty());
        assertEquals(0, deque.size());
    }

    @Test
    @DisplayName("addFirst increases size and peekFirst returns it")
    void addFirstAndPeek() {
        deque.addFirst("a");
        assertEquals(1, deque.size());
        assertEquals("a", deque.peekFirst());
        assertEquals("a", deque.peekLast());
    }

    @Test
    @DisplayName("addLast increases size and peekLast returns it")
    void addLastAndPeek() {
        deque.addLast("z");
        assertEquals(1, deque.size());
        assertEquals("z", deque.peekLast());
        assertEquals("z", deque.peekFirst());
    }

    @Test
    @DisplayName("removeFirst returns and removes front element")
    void removeFirst() {
        deque.addLast("a");
        deque.addLast("b");
        assertEquals("a", deque.removeFirst());
        assertEquals(1, deque.size());
        assertEquals("b", deque.peekFirst());
    }

    @Test
    @DisplayName("removeLast returns and removes rear element")
    void removeLast() {
        deque.addLast("a");
        deque.addLast("b");
        assertEquals("b", deque.removeLast());
        assertEquals(1, deque.size());
        assertEquals("a", deque.peekLast());
    }

    @Test
    @DisplayName("removeFirst on empty deque throws")
    void removeFirstEmpty() {
        assertThrows(NoSuchElementException.class, () -> deque.removeFirst());
    }

    @Test
    @DisplayName("removeLast on empty deque throws")
    void removeLastEmpty() {
        assertThrows(NoSuchElementException.class, () -> deque.removeLast());
    }

    @Test
    @DisplayName("LIFO usage via addFirst/removeFirst")
    void lifoUsage() {
        deque.addFirst("x");
        deque.addFirst("y");
        deque.addFirst("z");
        assertEquals("z", deque.removeFirst());
        assertEquals("y", deque.removeFirst());
        assertEquals("x", deque.removeFirst());
        assertTrue(deque.isEmpty());
    }

    @Test
    @DisplayName("Queue interface enqueue/dequeue works")
    void queueInterface() {
        assertTrue(deque.enqueue("a"));
        assertTrue(deque.enqueue("b"));
        assertEquals("a", deque.dequeue());
        assertEquals("b", deque.dequeue());
        assertNull(deque.dequeue());
    }

    @Test
    @DisplayName("Peek returns null on empty deque")
    void peekEmpty() {
        assertNull(deque.peekFirst());
        assertNull(deque.peekLast());
        assertNull(deque.peek());
    }
}
