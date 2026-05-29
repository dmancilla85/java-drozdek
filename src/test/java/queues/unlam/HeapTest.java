package queues.unlam;

import org.drozdek.queues.unlam.Heap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class HeapTest {
    Heap heap;

    @BeforeEach
    void setUp() {
        heap = new Heap(10);
    }

    @Test
    @DisplayName("A new heap always is empty")
    void isEmpty() {
        assertTrue(heap.isEmpty(), "The heap should be empty");
    }

    @Test
    @DisplayName("Heap is not empty after enqueue")
    void isNotEmptyAfterEnqueue() {
        heap.enqueue(42);
        assertFalse(heap.isEmpty(), "Heap should not be empty after enqueue");
    }

    @Test
    @DisplayName("Check if size is correct")
    void size() {
        heap.enqueue(10);
        heap.enqueue(20);

        assertEquals(2, heap.size(), "The expected size doesn't match with the heap size");
    }

    @Test
    @DisplayName("Check if after clearing the heap is empty")
    void clear() {
        heap.enqueue(1);
        heap.enqueue(2);
        heap.enqueue(3);
        heap.clear();

        assertEquals(0, heap.size(), "Size should be zero");
        assertTrue(heap.isEmpty(), "Heap should be empty after clear");
    }

    @Test
    @DisplayName("Check if elements are correctly enqueued")
    void enqueue() {
        heap.enqueue("A");
        heap.enqueue("B");
        heap.enqueue("C");

        assertEquals(3, heap.size(), "The expected size doesn't match with the heap size");
    }

    @Test
    @DisplayName("Check if elements are correctly dequeued (natural order)")
    void dequeue() {
        heap.enqueue(30);
        heap.enqueue(10);
        heap.enqueue(20);

        assertEquals(10, heap.dequeue(), "The smallest element should be dequeued first");
        assertEquals(20, heap.dequeue(), "The next smallest element should be dequeued");
        assertEquals(30, heap.dequeue(), "The largest element should be dequeued last");
    }

    @Test
    @DisplayName("Dequeue on empty heap returns null")
    void dequeueOnEmpty() {
        assertNull(heap.dequeue(), "Dequeue on empty should return null");
    }

    @Test
    @DisplayName("Check if smallest element is returned by peek")
    void peek() {
        heap.enqueue(50);
        heap.enqueue(10);
        heap.enqueue(30);

        assertEquals(10, heap.peek(), "Peek should return the smallest element");
        assertEquals(3, heap.size(), "Peek should not remove the element");
    }

    @Test
    @DisplayName("Peek on empty heap returns null")
    void peekOnEmpty() {
        assertNull(heap.peek(), "Peek on empty should return null");
    }

    @Test
    @DisplayName("Heap with custom comparator (max-heap)")
    void customComparator() {
        @SuppressWarnings("unchecked")
        Comparator<Object> reverseCmp = (a, b) -> ((Comparable<Object>) b).compareTo(a);
        Heap maxHeap = new Heap(10, reverseCmp);

        maxHeap.enqueue(10);
        maxHeap.enqueue(30);
        maxHeap.enqueue(20);

        assertEquals(30, maxHeap.dequeue(), "The largest element should be dequeued first with reverse comparator");
        assertEquals(20, maxHeap.dequeue(), "The next largest element should be dequeued");
        assertEquals(10, maxHeap.dequeue(), "The smallest element should be dequeued last");
    }

    @Test
    @DisplayName("Heap auto-resizes when capacity is exceeded")
    void resize() {
        for (int i = 0; i < 50; i++) {
            heap.enqueue(i);
        }

        assertEquals(50, heap.size(), "Size should reflect all enqueued elements after resize");
        assertEquals(0, heap.dequeue(), "Smallest element should still be correct after resize");
    }

    @Test
    @DisplayName("String elements maintain natural order")
    void stringNaturalOrder() {
        heap.enqueue("banana");
        heap.enqueue("apple");
        heap.enqueue("cherry");

        assertEquals("apple", heap.dequeue(), "apple should come first alphabetically");
        assertEquals("banana", heap.dequeue(), "banana should come second");
        assertEquals("cherry", heap.dequeue(), "cherry should come last");
    }

    @Test
    @DisplayName("Print all does not modify the heap")
    void printAll() {
        heap.enqueue(10);
        heap.enqueue(20);
        heap.enqueue(30);

        int sizeBefore = heap.size();
        Object firstBefore = heap.peek();

        heap.print();

        assertEquals(sizeBefore, heap.size(), "Heap size unchanged after printAll");
        assertEquals(firstBefore, heap.peek(), "Smallest element unchanged after printAll");
    }

    @Test
    @DisplayName("Print all on empty heap throws no exception")
    void printAllOnEmptyQueue() {
        assertDoesNotThrow(() -> heap.print());
    }

    @Test
    @DisplayName("toString on empty heap throws no exception")
    void toStringOnEmpty() {
        assertDoesNotThrow(() -> heap.toString());
    }

    @Test
    @DisplayName("toString on non-empty heap throws no exception")
    void toStringOnNonEmpty() {
        heap.enqueue("hello");
        heap.enqueue("world");

        assertDoesNotThrow(() -> heap.toString());
    }
}
