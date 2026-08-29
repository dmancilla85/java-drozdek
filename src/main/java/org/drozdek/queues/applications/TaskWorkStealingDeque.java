package org.drozdek.queues.applications;

import org.drozdek.queues.Deque;

/// Models a per-thread work queue supporting work-stealing dispatch.
///
/// In a work-stealing scheduler each worker owns a deque of tasks. The owner
/// pushes and pops tasks at one end (LIFO locality), while an idle thief steals
/// a task from the opposite end. Keeping the ends separate minimizes contention
/// and preserves cache-friendly local execution.
///
/// **Real-world use case:** Java ForkJoinPool and parallel frameworks that
/// balance load by stealing tasks from busy threads.
///
/// Complexity Analysis:
/// Time Complexity: O(1) for push, pop-local, and steal
/// Auxiliary Space: O(n) for the pending tasks
///
/// Bibliography:
///
/// - Work stealing. *Wikipedia*. https://en.wikipedia.org/wiki/Work_stealing
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 4.
///
/// @see Deque
public class TaskWorkStealingDeque {

    private final Deque<String> tasks;

    /// Creates an empty work-stealing task deque.
    public TaskWorkStealingDeque() {
        this.tasks = new Deque<>();
    }

    /// Adds a local task to the tail (owner's end).
    ///
    /// @param task the task identifier
    public void pushLocal(String task) {
        tasks.addLast(task);
    }

    /// Owner pops its most recent task from the tail.
    ///
    /// @return the task, or null if the deque is empty
    public String popLocal() {
        return tasks.isEmpty() ? null : tasks.removeLast();
    }

    /// A thief takes the oldest task from the head.
    ///
    /// @return the stolen task, or null if the deque is empty
    public String steal() {
        return tasks.isEmpty() ? null : tasks.removeFirst();
    }

    /// Returns the number of tasks currently pending.
    ///
    /// @return pending task count
    public int size() {
        return tasks.size();
    }
}
