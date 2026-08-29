package org.drozdek.memory.applications;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InPlaceGarbageCollectorTest {

    @Test
    @DisplayName("Collector cycle reclaims unreachable objects")
    void runCollectorCycle_reclaimsGarbage() {
        InPlaceGarbageCollector app = new InPlaceGarbageCollector();
        assertEquals(2, app.runCollectorCycle());
        assertEquals(2, app.getCollector().getObjectCount());
    }
}
