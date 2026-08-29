package org.drozdek.dynamic.applications;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.drozdek.dynamic.KnapsackItem;
import org.drozdek.dynamic.ZeroOneKnapsack;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CargoLoadOptimizerTest {

    @Test
    @DisplayName("Computes the optimal load value")
    void maxLoadValue_optimal() {
        List<KnapsackItem> goods = List.of(
            new KnapsackItem("a", 10, 60),
            new KnapsackItem("b", 20, 100),
            new KnapsackItem("c", 30, 120));
        assertEquals(220, CargoLoadOptimizer.maxLoadValue(goods, 50));
    }

    @Test
    @DisplayName("Capacity too small for any item yields zero")
    void maxLoadValue_empty() {
        List<KnapsackItem> goods = List.of(new KnapsackItem("a", 10, 60));
        assertEquals(0, CargoLoadOptimizer.maxLoadValue(goods, 5));
    }

    @Test
    @DisplayName("Plans a load and reports the selected goods")
    void planLoad_selects() {
        List<KnapsackItem> goods = List.of(
            new KnapsackItem("a", 10, 60),
            new KnapsackItem("b", 20, 100),
            new KnapsackItem("c", 30, 120));
        ZeroOneKnapsack.Result result = CargoLoadOptimizer.planLoad(goods, 50);
        assertEquals(220, result.getMaxValue());
        assertEquals(2, result.getSelectedIndices().size());
    }
}
