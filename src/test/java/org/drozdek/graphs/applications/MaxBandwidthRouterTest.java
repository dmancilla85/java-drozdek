package org.drozdek.graphs.applications;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MaxBandwidthRouterTest {

    @Test
    @DisplayName("Computes the maximum source-to-sink throughput")
    void maxThroughput_classicFlow() {
        MaxBandwidthRouter router = new MaxBandwidthRouter(4);
        router.addLink(0, 1, 3);
        router.addLink(0, 2, 2);
        router.addLink(1, 2, 1);
        router.addLink(1, 3, 2);
        router.addLink(2, 3, 3);
        assertEquals(5, router.maxThroughput(0, 3));
    }
}
