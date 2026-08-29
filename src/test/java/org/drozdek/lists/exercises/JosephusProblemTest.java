package org.drozdek.lists.exercises;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class JosephusProblemTest {

    @Test
    @DisplayName("Closed-form survivor for n=7, k=3 is 4")
    void survivor_standard() {
        assertEquals(4, JosephusProblem.survivor(7, 3));
    }

    @Test
    @DisplayName("Closed-form survivor for n=41, k=3 is 31")
    void survivor_large() {
        assertEquals(31, JosephusProblem.survivor(41, 3));
    }

    @Test
    @DisplayName("Closed-form survivor for n=5, k=2 is 3")
    void survivor_fiveTwo() {
        assertEquals(3, JosephusProblem.survivor(5, 2));
    }

    @Test
    @DisplayName("Elimination order matches the closed-form survivor")
    void eliminationOrder_matchesSurvivor() {
        List<Integer> order = JosephusProblem.eliminationOrder(5, 2);
        assertEquals(5, order.size());
        assertEquals(JosephusProblem.survivor(5, 2), order.get(order.size() - 1));
    }

    @Test
    @DisplayName("Single person is their own survivor")
    void survivor_single() {
        assertEquals(1, JosephusProblem.survivor(1, 5));
    }
}
