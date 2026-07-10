package org.drozdek.dynamic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KnapsackSolutionTest {

    @Test
    @DisplayName("Default constructor initializes values to zero and empty list")
    void defaultConstructor() {
        KnapsackSolution sol = new KnapsackSolution();
        assertEquals(0, sol.getMaximumProfit());
        assertEquals(0, sol.getInstructionCount());
        assertTrue(sol.getKnapsack().isEmpty());
    }

    @Test
    @DisplayName("ToString returns formatted output")
    void testToString() {
        KnapsackSolution sol = new KnapsackSolution();
        String str = sol.toString();
        assertTrue(str.contains("Total profit: 0"));
        assertTrue(str.contains("Total instructions: 0"));
    }

    @Test
    @DisplayName("ToString with items in knapsack")
    void toStringWithItems() {
        KnapsackSolution sol = new KnapsackSolution();
        sol.getKnapsack().add(new DynamicKnapsackItem(10, 20));
        sol.getKnapsack().add(new DynamicKnapsackItem(5, 15));
        String str = sol.toString();
        assertTrue(str.contains("Total profit: 0"));
        assertTrue(str.contains("[W: 10, P: 20]"));
        assertTrue(str.contains("[W: 5, P: 15]"));
    }
}
