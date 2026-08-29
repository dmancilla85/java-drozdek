package org.drozdek.queues.applications;

import org.drozdek.queues.ArrayQueue;

/// Enforces a sliding-window request rate limit using a FIFO array queue.
///
/// Timestamps of accepted requests are enqueued at the rear. Before admitting a
/// new request, the oldest timestamps that have fallen outside the sliding
/// window are dequeued from the front. If the queue is full, the limit has been
/// reached within the window and the request is rejected.
///
/// **Real-world use case:** API throttling, login attempt limiting, and
/// token-bucket style traffic shaping at a service boundary.
///
/// Complexity Analysis:
/// Time Complexity: O(k) worst case per request, where k is the number of
///                  expired timestamps removed
/// Auxiliary Space: O(capacity) for the window buffer
///
/// Bibliography:
///
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 4.
///
/// @see ArrayQueue
public class SlidingWindowRateLimiter {

    private final ArrayQueue queue;
    private final int maxRequests;
    private final long windowMillis;

    /// Creates a limiter that admits up to {@code maxRequests} per sliding window.
    ///
    /// @param maxRequests  maximum requests allowed in the window
    /// @param windowMillis length of the window in milliseconds
    public SlidingWindowRateLimiter(int maxRequests, long windowMillis) {
        this.maxRequests = maxRequests;
        this.windowMillis = windowMillis;
        this.queue = new ArrayQueue(maxRequests);
    }

    /// Attempts to admit a request at the given timestamp.
    ///
    /// @param nowMillis current timestamp in milliseconds
    /// @return true if admitted, false if the window is full
    public boolean tryAcquire(long nowMillis) {
        long cutoff = nowMillis - windowMillis;
        while (!queue.isEmpty() && ((Number) queue.peek()).longValue() <= cutoff) {
            queue.dequeue();
        }
        if (queue.size() < maxRequests) {
            queue.enqueue(nowMillis);
            return true;
        }
        return false;
    }

    /// Returns the number of requests admitted within the current window.
    ///
    /// @return current in-window request count
    public int currentLoad() {
        return queue.size();
    }
}
