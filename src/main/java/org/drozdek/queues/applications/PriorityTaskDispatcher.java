package org.drozdek.queues.applications;

import org.drozdek.queues.unlam.Heap;

/// Dispatches tasks in strict priority order using a binary-heap priority queue.
///
/// Jobs are enqueued with a numeric priority where a smaller number indicates a
/// more urgent task. The heap keeps the most urgent job at its root, so the
/// dispatcher always serves the highest-priority job first in O(log n) per
/// operation.
///
/// **Real-world use case:** Real-time task sizing, interrupt handling, triage
/// queues, and Dijkstra-style event processing.
///
/// Complexity Analysis:
/// Time Complexity: O(log n) for enqueue and dispatch
/// Auxiliary Space: O(n) for the pending jobs
///
/// Bibliography:
///
/// - Binary heap. *Wikipedia*. https://en.wikipedia.org/wiki/Binary_heap
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 6.
///
/// @see Heap
public class PriorityTaskDispatcher {

    private final Heap<Job> queue;

    /// Creates an empty priority dispatcher.
    public PriorityTaskDispatcher() {
        this.queue = new Heap<>(16);
    }

    /// Submits a job with the given urgency (lower number = more urgent).
    ///
    /// @param name     job name
    /// @param priority urgency, smaller is more urgent
    public void submit(String name, int priority) {
        queue.insert(new Job(name, priority));
    }

    /// Dispatches the most urgent pending job.
    ///
    /// @return the most urgent job name, or null if the queue is empty
    public String dispatch() {
        Job job = queue.extract();
        return job == null ? null : job.name;
    }

    /// Returns the number of pending jobs.
    ///
    /// @return pending job count
    public int pending() {
        return queue.size();
    }

    /// A comparable job ordered by ascending priority.
    private static final class Job implements Comparable<Job> {
        private final String name;
        private final int priority;

        private Job(String name, int priority) {
            this.name = name;
            this.priority = priority;
        }

        @Override
        public int compareTo(Job other) {
            return Integer.compare(this.priority, other.priority);
        }
    }
}
