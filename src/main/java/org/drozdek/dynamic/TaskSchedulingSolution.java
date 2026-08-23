package org.drozdek.dynamic;

import java.util.ArrayList;
import java.util.List;

/// Result container for the Activity Selection / Weighted Interval Scheduling problem.
///
/// Holds the number of selected tasks, the instruction count used by the solving
/// algorithm, and the list of selected tasks that form the solution.
public class TaskSchedulingSolution {
    private int taskCount;
    private int instructionCount;
    private List<ScheduledTask> solution;

    public TaskSchedulingSolution() {
        taskCount = 0;
        instructionCount = 0;
        solution = new ArrayList<>();
    }

    public int getTaskCount() {
        return taskCount;
    }

    public void setTaskCount(int taskCount) {
        this.taskCount = taskCount;
    }

    public int getInstructionCount() {
        return instructionCount;
    }

    public void setInstructionCount(int instructionCount) {
        this.instructionCount = instructionCount;
    }

    public List<ScheduledTask> getSolution() {
        return solution;
    }

    /// Returns a multi-line summary of this solution.
    ///
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
