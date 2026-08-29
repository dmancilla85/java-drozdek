package org.drozdek.dynamic.applications;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.drozdek.dynamic.ScheduledTask;

/// Selects the maximum number of non-overlapping CPU tasks via greedy
/// interval scheduling.
///
/// Given tasks with start and end times, this service chooses the largest set
/// of tasks that can be executed without overlap. Sorting tasks by end time and
/// greedily picking each compatible task yields the optimal solution.
///
/// **Real-world use case:** Single-CPU job scheduling where the goal is to
/// maximize throughput, conference-room booking, and event hall assignment.
///
/// Complexity Analysis:
/// Time Complexity: O(n log n) dominated by the sort
/// Auxiliary Space: O(n) for the result list
///
/// Bibliography:
///
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 12.
///
/// @see ScheduledTask
public final class CpuTaskScheduler {

    private CpuTaskScheduler() {
        // do nothing
    }

    /// Returns the maximum-sized set of mutually non-overlapping tasks.
    ///
    /// @param tasks candidate tasks with start and end times
    /// @return the selected compatible tasks, ordered by end time
    public static List<ScheduledTask> scheduleMax(List<ScheduledTask> tasks) {
        List<ScheduledTask> sorted = new ArrayList<>(tasks);
        Collections.sort(sorted);
        List<ScheduledTask> selected = new ArrayList<>();
        int lastEnd = Integer.MIN_VALUE;
        for (ScheduledTask task : sorted) {
            if (task.getStart() >= lastEnd) {
                selected.add(task);
                lastEnd = task.getEnd();
            }
        }
        return selected;
    }
}
