package org.drozdek.dynamic.applications;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.drozdek.dynamic.ScheduledTask;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CpuTaskSchedulerTest {

    @Test
    @DisplayName("Selects the maximum set of non-overlapping tasks")
    void scheduleMax_optimal() {
        List<ScheduledTask> tasks = List.of(
            new ScheduledTask(1, 3),
            new ScheduledTask(2, 5),
            new ScheduledTask(4, 6),
            new ScheduledTask(6, 8));
        List<ScheduledTask> selected = CpuTaskScheduler.scheduleMax(tasks);
        assertEquals(3, selected.size());
        assertEquals(1, selected.get(0).getStart());
        assertEquals(4, selected.get(1).getStart());
        assertEquals(6, selected.get(2).getStart());
    }

    @Test
    @DisplayName("Overlapping tasks reduce the selection to one")
    void scheduleMax_allOverlap() {
        List<ScheduledTask> tasks = List.of(
            new ScheduledTask(1, 5),
            new ScheduledTask(2, 4),
            new ScheduledTask(3, 6));
        assertEquals(1, CpuTaskScheduler.scheduleMax(tasks).size());
    }

    @Test
    @DisplayName("Empty input yields an empty selection")
    void scheduleMax_empty() {
        assertEquals(0, CpuTaskScheduler.scheduleMax(List.of()).size());
    }
}
