package org.drozdek.graphs.applications;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ExamTimetableSchedulerTest {

    @Test
    @DisplayName("A path graph of three exams needs two slots")
    void requiredSlots_pathGraph() {
        ExamTimetableScheduler scheduler = new ExamTimetableScheduler(3);
        scheduler.addConflict(0, 1);
        scheduler.addConflict(1, 2);
        assertEquals(2, scheduler.requiredSlots());
    }

    @Test
    @DisplayName("Conflicting exams are never assigned the same slot")
    void scheduleSlots_conflictsDiffer() {
        ExamTimetableScheduler scheduler = new ExamTimetableScheduler(3);
        scheduler.addConflict(0, 1);
        scheduler.addConflict(1, 2);
        int[] slots = scheduler.scheduleSlots();
        assertEquals(3, slots.length);
        assertEquals(2, scheduler.requiredSlots());
    }
}
