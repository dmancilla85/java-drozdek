package org.drozdek.graphs.applications;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WaypointDistanceTableTest {

    @Test
    @DisplayName("Computes shortest distance across a path of waypoints")
    void distanceBetween_path() {
        WaypointDistanceTable table = new WaypointDistanceTable(4);
        table.addSegment(0, 1, 1);
        table.addSegment(1, 2, 2);
        table.addSegment(2, 3, 3);
        assertEquals(6, table.distanceBetween(0, 3));
        assertEquals(0, table.distanceBetween(2, 2));
    }

    @Test
    @DisplayName("Returns INF for unreachable waypoints and satisfies symmetry")
    void distanceBetween_unreachable() {
        WaypointDistanceTable table = new WaypointDistanceTable(3);
        table.addSegment(0, 1, 5);
        assertEquals(table.distanceBetween(0, 1), table.distanceBetween(1, 0));
        assertEquals(WaypointDistanceTable.INF, table.distanceBetween(1, 2));
    }

    @Test
    @DisplayName("Out-of-range waypoint indices are rejected")
    void distanceBetween_outOfRange() {
        WaypointDistanceTable table = new WaypointDistanceTable(3);
        assertThrows(IllegalArgumentException.class, () -> table.distanceBetween(-1, 1));
        assertThrows(IllegalArgumentException.class, () -> table.distanceBetween(1, 3));
    }
}
