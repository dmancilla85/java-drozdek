package org.drozdek.graphs.applications;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GpsNavigationRouterTest {

    @Test
    @DisplayName("Computes shortest travel times from the origin")
    void shortestTimesFrom_relaxesNetwork() {
        GpsNavigationRouter router = new GpsNavigationRouter(3);
        router.addRoad(0, 1, 10);
        router.addRoad(1, 2, 5);
        router.addRoad(0, 2, 100);
        Integer[] times = router.shortestTimesFrom(0);
        assertEquals(0, times[0]);
        assertEquals(10, times[1]);
        assertEquals(15, times[2]);
    }
}
