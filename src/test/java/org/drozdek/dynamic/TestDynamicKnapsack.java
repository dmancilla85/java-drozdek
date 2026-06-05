package org.drozdek.dynamic;

import org.drozdek.dynamic.DynamicKnapsackItem;
import org.drozdek.dynamic.KnapsackSolution;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Dynamic Knapsack Tests")
class TestDynamicKnapsack {
    private ArrayList<DynamicKnapsackItem> items;

    @BeforeEach
    void setUp() {
        items = new ArrayList<>();
        items.add(new DynamicKnapsackItem(3, 4));
        items.add(new DynamicKnapsackItem(2, 6));
        items.add(new DynamicKnapsackItem(1, 9));
        items.add(new DynamicKnapsackItem(4, 1));
        items.add(new DynamicKnapsackItem(4, 10));
        items.add(new DynamicKnapsackItem(5, 2));
    }

    @Test
    @DisplayName("Items are sorted by profit ascending")
    void sortByProfit() {
        Collections.sort(items);

        for (int i = 0; i < items.size(); i++)
            out.println(items.get(i));

        assertEquals(1, items.getFirst().profit, "Lowest profit item should be first");
        assertEquals(10, items.getLast().profit, "Highest profit item should be last");
    }

    @Test
    @DisplayName("Solve exercise 5 returns a solution")
    void solveExercise5() {
        KnapsackSolution solution = DynamicKnapsackItem.solveExercise5();

        assertNotNull(solution, "Solution should not be null");
        assertEquals(0, solution.maximumProfit, "Stub solution has zero profit");
        assertTrue(solution.knapsack.isEmpty(), "Stub solution has no items");
    }

    @Test
    @DisplayName("Item creation and field access")
    void itemCreation() {
        DynamicKnapsackItem item = new DynamicKnapsackItem(7, 15);

        assertEquals(7, item.weight, "Weight should be 7");
        assertEquals(15, item.profit, "Profit should be 15");
    }

    @Test
    @DisplayName("Default item has zero weight and profit")
    void defaultItem() {
        DynamicKnapsackItem item = new DynamicKnapsackItem();

        assertEquals(0, item.weight);
        assertEquals(0, item.profit);
    }
}
