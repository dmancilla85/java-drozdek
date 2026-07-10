package org.drozdek.dynamic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScheduledTaskTest {

    @Test
    @DisplayName("Default constructor")
    void defaultConstructor() {
        ScheduledTask task = new ScheduledTask();
        assertEquals(0, task.getStart());
        assertEquals(0, task.getEnd());
    }

    @Test
    @DisplayName("Parameterized constructor")
    void parameterizedConstructor() {
        ScheduledTask task = new ScheduledTask(3, 7);
        assertEquals(3, task.getStart());
        assertEquals(7, task.getEnd());
    }

    @Test
    @DisplayName("CompareTo by end time")
    void compareTo() {
        ScheduledTask a = new ScheduledTask(1, 5);
        ScheduledTask b = new ScheduledTask(2, 10);
        assertTrue(a.compareTo(b) < 0);
        assertTrue(b.compareTo(a) > 0);
        assertEquals(0, a.compareTo(new ScheduledTask(0, 5)));
    }

    @Test
    @DisplayName("Compare via Comparator delegates to compareTo")
    void compare() {
        ScheduledTask a = new ScheduledTask(1, 5);
        ScheduledTask b = new ScheduledTask(2, 10);
        assertTrue(a.compare(a, b) < 0);
        assertTrue(a.compare(b, a) > 0);
    }

    @Test
    @DisplayName("Equals by end time")
    void equalsSame() {
        ScheduledTask a = new ScheduledTask(1, 5);
        ScheduledTask b = new ScheduledTask(99, 5);
        assertEquals(a, b);
    }

    @Test
    @DisplayName("Equals false for different end time")
    void equalsDifferent() {
        ScheduledTask a = new ScheduledTask(1, 5);
        ScheduledTask b = new ScheduledTask(2, 10);
        assertNotEquals(a, b);
    }

    @Test
    @DisplayName("Equals false for null")
    void equalsNull() {
        ScheduledTask a = new ScheduledTask(1, 5);
        assertNotEquals(null, a);
    }

    @Test
    @DisplayName("Equals true for same reference")
    void equalsReflexive() {
        ScheduledTask a = new ScheduledTask(1, 5);
        assertEquals(a, a);
    }

    @Test
    @DisplayName("Hash code consistent with equals")
    void hashCodeConsistent() {
        ScheduledTask a = new ScheduledTask(1, 5);
        ScheduledTask b = new ScheduledTask(99, 5);
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    @DisplayName("Hash code differs for different end times")
    void hashCodeDifferent() {
        ScheduledTask a = new ScheduledTask(1, 5);
        ScheduledTask b = new ScheduledTask(2, 10);
        assertNotEquals(a.hashCode(), b.hashCode());
    }

    @Test
    @DisplayName("ToString format")
    void testToString() {
        ScheduledTask task = new ScheduledTask(3, 7);
        assertEquals("{S:3, E:7}", task.toString());
    }
}
