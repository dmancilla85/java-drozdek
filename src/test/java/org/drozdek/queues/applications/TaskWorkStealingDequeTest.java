package org.drozdek.queues.applications;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TaskWorkStealingDequeTest {

    @Test
    @DisplayName("Owner pops its most recent task (LIFO)")
    void popLocal_lifo() {
        TaskWorkStealingDeque deque = new TaskWorkStealingDeque();
        deque.pushLocal("a");
        deque.pushLocal("b");
        assertEquals("b", deque.popLocal());
        assertEquals("a", deque.popLocal());
        assertNull(deque.popLocal());
    }

    @Test
    @DisplayName("A thief steals the oldest task (FIFO from the other end)")
    void steal_fifo() {
        TaskWorkStealingDeque deque = new TaskWorkStealingDeque();
        deque.pushLocal("a");
        deque.pushLocal("b");
        deque.pushLocal("c");
        assertEquals("a", deque.steal());
        assertEquals(2, deque.size());
    }

    @Test
    @DisplayName("Steal from an empty deque returns null")
    void steal_empty() {
        assertNull(new TaskWorkStealingDeque().steal());
    }
}
