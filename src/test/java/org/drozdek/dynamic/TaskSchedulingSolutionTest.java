package org.drozdek.dynamic;

import org.drozdek.dynamic.ScheduledTask;
import org.drozdek.dynamic.TaskSchedulingSolution;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Task Scheduling Solution Tests")
class TaskSchedulingSolutionTest {

    @Test
    @DisplayName("Create task with start and end times")
    void createTask() {
        ScheduledTask task = new ScheduledTask(1, 5);

        assertEquals(1, task.start);
        assertEquals(5, task.end);
    }

    @Test
    @DisplayName("Default task has zero start and end")
    void defaultTask() {
        ScheduledTask task = new ScheduledTask();

        assertEquals(0, task.start);
        assertEquals(0, task.end);
    }

    @Test
    @DisplayName("Tasks are sorted by end time ascending")
    void sortByEndTime() {
        ArrayList<ScheduledTask> tasks = new ArrayList<>();
        tasks.add(new ScheduledTask(3, 8));
        tasks.add(new ScheduledTask(1, 4));
        tasks.add(new ScheduledTask(5, 6));

        out.println("Before sorting:");
        for (ScheduledTask t : tasks) out.println(t);

        Collections.sort(tasks);

        out.println("After sorting:");
        for (ScheduledTask t : tasks) out.println(t);

        assertEquals(4, tasks.get(0).end, "Earliest end time should be first");
        assertEquals(6, tasks.get(1).end, "Middle end time");
        assertEquals(8, tasks.get(2).end, "Latest end time should be last");
    }

    @Test
    @DisplayName("Task toString format")
    void taskToString() {
        ScheduledTask task = new ScheduledTask(2, 7);

        assertEquals("{S:2, E:7}", task.toString());
    }

    @Test
    @DisplayName("Empty solution has zero counts and empty list")
    void emptySolution() {
        TaskSchedulingSolution solution = new TaskSchedulingSolution();

        assertEquals(0, solution.taskCount);
        assertEquals(0, solution.instructionCount);
        assertTrue(solution.solution.isEmpty());
    }

    @Test
    @DisplayName("Solution with tasks formats correctly")
    void solutionWithTasks() {
        TaskSchedulingSolution solution = new TaskSchedulingSolution();
        solution.taskCount = 2;
        solution.instructionCount = 10;
        solution.solution.add(new ScheduledTask(1, 3));
        solution.solution.add(new ScheduledTask(4, 6));

        String output = solution.toString();

        assertTrue(output.contains("Tasks: 2"), "Should show task count");
        assertTrue(output.contains("Instructions: 10"), "Should show instruction count");
        assertTrue(output.contains("{S:1, E:3}"), "Should contain first task");
        assertTrue(output.contains("{S:4, E:6}"), "Should contain second task");
    }
}
