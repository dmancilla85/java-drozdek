package org.drozdek.lists.applications;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RoundRobinSchedulerTest {

    @Test
    @DisplayName("Rotates unfinished tasks and completes in order")
    void runQuantum_roundRobin() {
        RoundRobinScheduler scheduler = new RoundRobinScheduler(3);
        scheduler.add("A", 7);
        scheduler.add("B", 2);
        assertEquals("A:remaining=4", scheduler.runQuantum());
        assertEquals("B:done", scheduler.runQuantum());
        assertEquals("A:remaining=1", scheduler.runQuantum());
        assertEquals("A:done", scheduler.runQuantum());
        assertEquals(9, scheduler.executedTicks());
        assertEquals(0, scheduler.readyCount());
    }

    @Test
    @DisplayName("Returns null when the ready queue is empty")
    void runQuantum_empty() {
        assertNull(new RoundRobinScheduler(2).runQuantum());
    }

    @Test
    @DisplayName("A task smaller than the quantum completes immediately")
    void runQuantum_smallTask() {
        RoundRobinScheduler scheduler = new RoundRobinScheduler(10);
        scheduler.add("X", 4);
        assertEquals("X:done", scheduler.runQuantum());
        assertEquals(4, scheduler.executedTicks());
    }
}
