package org.drozdek.dynamic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("0/1 Knapsack Tests")
class ZeroOneKnapsackTest {

    @Test
    @DisplayName("Empty item list returns zero")
    void emptyItems() {
        List<KnapsackItem> items = new ArrayList<>();
        ZeroOneKnapsack.Result result = ZeroOneKnapsack.solve(items, 10);

        assertEquals(0, result.getMaxValue());
        assertTrue(result.getSelectedIndices().isEmpty());
    }

    @Test
    @DisplayName("Zero capacity returns zero")
    void zeroCapacity() {
        List<KnapsackItem> items = new ArrayList<>();
        items.add(new KnapsackItem("A", 10, 100));

        ZeroOneKnapsack.Result result = ZeroOneKnapsack.solve(items, 0);

        assertEquals(0, result.getMaxValue());
        assertTrue(result.getSelectedIndices().isEmpty());
    }

    @Test
    @DisplayName("Single item fits")
    void singleItemFits() {
        List<KnapsackItem> items = new ArrayList<>();
        items.add(new KnapsackItem("A", 10, 100));

        ZeroOneKnapsack.Result result = ZeroOneKnapsack.solve(items, 10);

        assertEquals(100, result.getMaxValue());
        assertEquals(1, result.getSelectedIndices().size());
        assertEquals(0, result.getSelectedIndices().get(0));
    }

    @Test
    @DisplayName("Single item does not fit")
    void singleItemTooHeavy() {
        List<KnapsackItem> items = new ArrayList<>();
        items.add(new KnapsackItem("A", 15, 100));

        ZeroOneKnapsack.Result result = ZeroOneKnapsack.solve(items, 10);

        assertEquals(0, result.getMaxValue());
        assertTrue(result.getSelectedIndices().isEmpty());
    }

    @Test
    @DisplayName("Multiple items — pick best combination")
    void multipleItems() {
        List<KnapsackItem> items = new ArrayList<>();
        items.add(new KnapsackItem("A", 10, 60));
        items.add(new KnapsackItem("B", 20, 100));
        items.add(new KnapsackItem("C", 30, 120));

        // Capacity 50 → pick A (60) + C (120) = 180; B (100) + C (120) = 220 → best: B + C = 220
        ZeroOneKnapsack.Result result = ZeroOneKnapsack.solve(items, 50);

        assertEquals(220, result.getMaxValue());
    }

    @Test
    @DisplayName("Space-optimised matches standard solver")
    void spaceOptimisedMatches() {
        List<KnapsackItem> items = new ArrayList<>();
        items.add(new KnapsackItem("A", 2, 3));
        items.add(new KnapsackItem("B", 3, 4));
        items.add(new KnapsackItem("C", 4, 5));
        items.add(new KnapsackItem("D", 5, 6));

        ZeroOneKnapsack.Result result = ZeroOneKnapsack.solve(items, 8);
        int optimised = ZeroOneKnapsack.solveSpaceOptimised(items, 8);

        assertEquals(result.getMaxValue(), optimised);
    }

    @Test
    @DisplayName("Classic textbook example")
    void classicExample() {
        List<KnapsackItem> items = new ArrayList<>();
        items.add(new KnapsackItem("A", 10, 60));
        items.add(new KnapsackItem("B", 20, 100));
        items.add(new KnapsackItem("C", 30, 120));

        // Capacity 50 => optimal is items B+C = 100+120 = 220
        assertEquals(220, ZeroOneKnapsack.solveSpaceOptimised(items, 50));
    }
}
