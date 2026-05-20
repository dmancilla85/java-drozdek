package org.drozdek.dynamic;

import java.util.ArrayList;
import java.util.Comparator;

class Task implements Comparable<Task>, Comparator<Task> {
    int start;
    int end;

    Task() {
        start = 0;
        end = 0;
    }

    Task(int i, int f) {
        start = i;
        end = f;
    }

    @Override
    public int compare(Task arg0, Task arg1) {
        return arg0.compareTo(arg1);
    }

    @Override
    public int compareTo(Task arg0) {
        return Integer.compare(this.end, arg0.end);
    }

    public String toString() {
        return "{S:" + start + ", E:" + end + "}";
    }
}

public class Exercise2Solution {
    int taskCount;
    int instructionCount;
    ArrayList<Task> solution;

    public Exercise2Solution() {
        taskCount = 0;
        instructionCount = 0;
        solution = new ArrayList<Task>();
    }

    public String toString() {
        String result = "";

        result = "Tasks: " + taskCount + "\nInstructions: "
                + instructionCount + ".\n";

        for (int i = 0; i < solution.size(); i++)
            result += solution.get(i) + "\n";
        return result;
    }

}
