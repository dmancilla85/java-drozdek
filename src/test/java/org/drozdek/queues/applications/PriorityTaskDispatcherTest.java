package org.drozdek.queues.applications;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PriorityTaskDispatcherTest {

    @Test
    @DisplayName("Dispatches the most urgent job first")
    void dispatch_priorityOrder() {
        PriorityTaskDispatcher dispatcher = new PriorityTaskDispatcher();
        dispatcher.submit("low", 5);
        dispatcher.submit("high", 1);
        dispatcher.submit("mid", 3);
        assertEquals("high", dispatcher.dispatch());
        assertEquals("mid", dispatcher.dispatch());
        assertEquals("low", dispatcher.dispatch());
        assertEquals(0, dispatcher.pending());
    }

    @Test
    @DisplayName("Ties are served in submission order")
    void dispatch_tie() {
        PriorityTaskDispatcher dispatcher = new PriorityTaskDispatcher();
        dispatcher.submit("first", 2);
        dispatcher.submit("second", 2);
        assertEquals("first", dispatcher.dispatch());
        assertEquals("second", dispatcher.dispatch());
    }

    @Test
    @DisplayName("Dispatch from an empty queue returns null")
    void dispatch_empty() {
        assertNull(new PriorityTaskDispatcher().dispatch());
    }
}
