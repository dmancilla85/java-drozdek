package org.drozdek.dynamic;

import java.util.ArrayList;
import java.util.List;

/// Result container for the Activity Selection / Weighted Interval Scheduling problem.
///
/// Holds the number of selected tasks, the instruction count used by the solving
/// algorithm, and the list of selected tasks that form the solution.
public class TaskSchedulingSolution {
    public int taskCount;
    public int instructionCount;
    public List<ScheduledTask> solution;

    public TaskSchedulingSolution() {
        taskCount = 0;
        instructionCount = 0;
        solution = new ArrayList<>();
    }

    /// @return A formatted string with task count, instruction count, and all selected tasks
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Tasks: ").append(taskCount).append("\nInstructions: ")
                .append(instructionCount).append(".\n");

        for (int i = 0; i < solution.size(); i++)
            result.append(solution.get(i)).append("\n");
        return result.toString();
    }

}
