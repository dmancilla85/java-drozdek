package org.drozdek.dynamic;

import org.drozdek.dynamic.FractionalKnapsack;
import org.drozdek.dynamic.KnapsackItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Fractional Knapsack Tests")
class FractionalKnapsackTest {

    @Test
    @DisplayName("Empty item list returns zero")
    void emptyList() {
        List<KnapsackItem> items = new ArrayList<>();

        double greedy = FractionalKnapsack.sequentialGreedyKnapsack(items, 10);
        double dp = FractionalKnapsack.dpKnapsack(items, 10);

        assertEquals(0.0, greedy, "Greedy on empty list should return 0");
        assertEquals(0.0, dp, "DP on empty list should return 0");
    }

    @Test
    @DisplayName("Zero capacity returns zero")
    void zeroCapacity() {
        List<KnapsackItem> items = new ArrayList<>();
        items.add(new KnapsackItem("A", 10, 100));

        double greedy = FractionalKnapsack.sequentialGreedyKnapsack(items, 0);
        double dp = FractionalKnapsack.dpKnapsack(items, 0);

        assertEquals(0.0, greedy, "Greedy with zero capacity should return 0");
        assertEquals(0.0, dp, "DP with zero capacity should return 0");
    }

    @Test
    @DisplayName("Single item fits entirely")
    void singleItemFits() {
        List<KnapsackItem> items = new ArrayList<>();
        items.add(new KnapsackItem("A", 10, 100));

        double greedy = FractionalKnapsack.sequentialGreedyKnapsack(items, 20);
        double dp = FractionalKnapsack.dpKnapsack(items, 20);

        assertEquals(100.0, greedy, "Greedy should take the whole item");
        assertEquals(100.0, dp, "DP should take the whole item");
    }

    @Test
    @DisplayName("Single item partially fits (fractional)")
    void singleItemPartial() {
        List<KnapsackItem> items = new ArrayList<>();
        items.add(new KnapsackItem("A", 10, 100));

        double greedy = FractionalKnapsack.sequentialGreedyKnapsack(items, 5);
        double dp = FractionalKnapsack.dpKnapsack(items, 5);

        assertEquals(50.0, greedy, 0.001, "Greedy should take half the item (5/10 * 100)");
        assertEquals(50.0, dp, 0.001, "DP should take half the item (5/10 * 100)");
    }

    @Test
    @DisplayName("Multiple items with partial last item")
    void multipleItemsWithPartial() {
        List<KnapsackItem> items = new ArrayList<>();
        items.add(new KnapsackItem("A", 10, 100));
        items.add(new KnapsackItem("B", 5, 50));

        double result = FractionalKnapsack.sequentialGreedyKnapsack(items, 12);

        // A: whole (10/10) → 100, capacity left = 2
        // B: fraction = 2/5 = 0.4 → 0.4 * 50 = 20
        assertEquals(120.0, result, 0.001, "Should take whole A and 0.4 of B");
    }

    @Test
    @DisplayName("Multiple items all fit entirely")
    void allItemsFit() {
        List<KnapsackItem> items = new ArrayList<>();
        items.add(new KnapsackItem("A", 5, 50));
        items.add(new KnapsackItem("B", 5, 100));

        double greedy = FractionalKnapsack.sequentialGreedyKnapsack(items, 20);
        double dp = FractionalKnapsack.dpKnapsack(items, 20);

        assertEquals(150.0, greedy, "Greedy should take both items");
        assertEquals(150.0, dp, "DP should take both items");
    }

    @Test
    @DisplayName("Minimum profit is a fractional value when no item fits")
    void smallCapacityFractional() {
        List<KnapsackItem> items = new ArrayList<>();
        items.add(new KnapsackItem("A", 100, 200));

        double greedy = FractionalKnapsack.sequentialGreedyKnapsack(items, 1);
        double dp = FractionalKnapsack.dpKnapsack(items, 1);

        assertEquals(2.0, greedy, 0.001, "Should take 1/100 of the item value = 2");
        assertEquals(2.0, dp, 0.001, "Should take 1/100 of the item value = 2");
    }

    @Test
    @DisplayName("DP handles the demo scenario from runDemo")
    void demoSzenario() {
        List<KnapsackItem> items = new ArrayList<>();
        items.add(new KnapsackItem("1", 18, 25));
        items.add(new KnapsackItem("2", 15, 24));
        items.add(new KnapsackItem("3", 10, 15));

        double result = FractionalKnapsack.dpKnapsack(items, 20);

        // Item 3 (10,15) fits entirely → dp[3][20] should include it
        assertTrue(result > 0, "Result should be positive");
    }
}
