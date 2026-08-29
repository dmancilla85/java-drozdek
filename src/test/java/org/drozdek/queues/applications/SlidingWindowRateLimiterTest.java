package org.drozdek.queues.applications;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SlidingWindowRateLimiterTest {

    @Test
    @DisplayName("Admits requests within the window limit")
    void tryAcquire_withinLimit() {
        SlidingWindowRateLimiter limiter = new SlidingWindowRateLimiter(3, 100);
        assertTrue(limiter.tryAcquire(1));
        assertTrue(limiter.tryAcquire(2));
        assertTrue(limiter.tryAcquire(3));
        assertEquals(3, limiter.currentLoad());
    }

    @Test
    @DisplayName("Rejects a request once the window is full")
    void tryAcquire_full() {
        SlidingWindowRateLimiter limiter = new SlidingWindowRateLimiter(2, 100);
        assertTrue(limiter.tryAcquire(1));
        assertTrue(limiter.tryAcquire(2));
        assertFalse(limiter.tryAcquire(3));
        assertEquals(2, limiter.currentLoad());
    }

    @Test
    @DisplayName("Expired timestamps free up capacity")
    void tryAcquire_slidesWindow() {
        SlidingWindowRateLimiter limiter = new SlidingWindowRateLimiter(2, 100);
        limiter.tryAcquire(1);
        limiter.tryAcquire(2);
        assertTrue(limiter.tryAcquire(150));
        assertEquals(1, limiter.currentLoad());
    }
}
