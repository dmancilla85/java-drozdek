package org.drozdek.lists.applications;

import org.drozdek.lists.CircularLinkedList;

/// Round-robin process scheduler built on a circular singly-linked list.
///
/// Ready tasks are kept head-to-tail in a circular list. Each call advances the
/// scheduler by a fixed time quantum: the head task runs, then — if it still
/// has work remaining — it is rotated to the tail so that every task is visited
/// in turn before any task is revisited.
///
/// **Real-world use case:** Time-sliced CPU schedulers and task dispatchers that
/// guarantee fair sharing of a single resource.
///
/// Complexity Analysis:
/// Time Complexity: O(1) amortized per quantum (head removal and tail append)
/// Auxiliary Space: O(n) for the ready queue
///
/// Bibliography:
///
/// - Round-robin scheduling. *Wikipedia*. https://en.wikipedia.org/wiki/Round-robin_scheduling
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 3.
///
/// @see CircularLinkedList
public class RoundRobinScheduler {

    private CircularLinkedList<Task> ready;
    private final int quantum;
    private int readyCount;
    private int executedTicks;

    /// Creates a round-robin scheduler with the given time quantum.
    ///
    /// @param quantum ticks granted to each task per pass
    public RoundRobinScheduler(int quantum) {
        this.ready = new CircularLinkedList<>();
        this.quantum = quantum;
        this.readyCount = 0;
        this.executedTicks = 0;
    }

    /// Adds a task to the tail of the ready queue.
    ///
    /// @param id         task identifier
    /// @param totalTicks total CPU ticks the task requires
    public void add(String id, int totalTicks) {
        ready.addToTail(new Task(id, totalTicks));
        readyCount++;
    }

    /// Runs one quantum for the head task, rotating it to the tail if unfinished.
    ///
    /// @return a status string, or null if no tasks are ready
    public String runQuantum() {
        if (readyCount == 0) {
            return null;
        }
        Task task = ready.deleteHead();
        int slice = Math.min(quantum, task.remaining);
        task.remaining -= slice;
        executedTicks += slice;
        if (task.remaining > 0) {
            ready.addToTail(task);
            return task.id + ":remaining=" + task.remaining;
        }
        readyCount--;
        if (readyCount == 0) {
            ready = new CircularLinkedList<>();
        }
        return task.id + ":done";
    }

    /// Returns the number of tasks currently ready.
    ///
    /// @return ready queue depth
    public int readyCount() {
        return readyCount;
    }

    /// Returns the total CPU ticks executed so far.
    ///
    /// @return executed tick count
    public int executedTicks() {
        return executedTicks;
    }

    private static final class Task {
        private final String id;
        private int remaining;

        private Task(String id, int remaining) {
            this.id = id;
            this.remaining = remaining;
        }
    }
}
