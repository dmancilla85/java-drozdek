package org.drozdek.dynamic;

import java.util.Comparator;

/// Represents a scheduled task with a start and end time.
///
/// Tasks are sorted by their end time (ascending), which is the standard
/// preprocessing step for interval scheduling / activity selection algorithms.
public class ScheduledTask implements Comparable<ScheduledTask>, Comparator<ScheduledTask> {
    private int start;
    private int end;

    public ScheduledTask() {
        start = 0;
        end = 0;
    }

    public ScheduledTask(int i, int f) {
        start = i;
        end = f;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof ScheduledTask other)) return false;
        return this.end == other.end;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(end);
    }

    /// @return A string in the format {S:start, E:end}
    public String toString() {
        return "{S:" + start + ", E:" + end + "}";
    }
}
