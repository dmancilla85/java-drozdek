package org.drozdek.dynamic;

import java.util.Comparator;

/// Represents a scheduled task with a start and end time.
///
/// Tasks are sorted by their end time (ascending), which is the standard
/// preprocessing step for interval scheduling / activity selection algorithms.
public class ScheduledTask implements Comparable<ScheduledTask>, Comparator<ScheduledTask> {
    public int start;
    public int end;

    public ScheduledTask() {
        start = 0;
        end = 0;
    }

    public ScheduledTask(int i, int f) {
        start = i;
        end = f;
    }

    /// Compares two tasks by end time (delegates to compareTo).
    @Override
    public int compare(ScheduledTask arg0, ScheduledTask arg1) {
        return arg0.compareTo(arg1);
    }

    /// Compares this task to another by end time (ascending).
    @Override
    public int compareTo(ScheduledTask arg0) {
        return Integer.compare(this.end, arg0.end);
    }

    /// @return A string in the format {S:start, E:end}
    public String toString() {
        return "{S:" + start + ", E:" + end + "}";
    }
}
