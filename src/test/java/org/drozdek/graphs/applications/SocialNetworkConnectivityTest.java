package org.drozdek.graphs.applications;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SocialNetworkConnectivityTest {

    @Test
    @DisplayName("Tracks directed follow arcs")
    void addFollow_arcs() {
        SocialNetworkConnectivity net = new SocialNetworkConnectivity(3);
        assertTrue(net.addFollow(0, 1));
        assertTrue(net.hasFollow(0, 1));
        assertFalse(net.hasFollow(1, 0));
        assertEquals(1, net.followCount());
    }

    @Test
    @DisplayName("Resolves a user's set representative")
    void managerOf_disjointSet() {
        SocialNetworkConnectivity net = new SocialNetworkConnectivity(2);
        assertEquals(0, net.managerOf(0));
        assertEquals(1, net.managerOf(1));
    }
}
