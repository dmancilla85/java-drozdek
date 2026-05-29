package org.drozdek.dynamic;

import java.util.ArrayList;

/// Result container for the Activity Selection / Weighted Interval Scheduling problem.
///
/// Holds the number of selected tasks, the instruction count used by the solving
/// algorithm, and the list of selected tasks that form the solution.
public class TaskSchedulingSolution {
    public int taskCount;
    public int instructionCount;
    public ArrayList<ScheduledTask> solution;

    public TaskSchedulingSolution() {
        taskCount = 0;
        instructionCount = 0;
        solution = new ArrayList<ScheduledTask>();
    }

    /// @return A formatted string with task count, instruction count, and all selected tasks
    public String toString() {
        String result = "";

        result = "Tasks: " + taskCount + "\nInstructions: "
                + instructionCount + ".\n";

        for (int i = 0; i < solution.size(); i++)
            result += solution.get(i) + "\n";
        return result;
    }

}
