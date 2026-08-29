package org.drozdek.graphs.applications;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
