package org.drozdek.dynamic.applications;

import java.util.List;
import org.drozdek.dynamic.KnapsackItem;
import org.drozdek.dynamic.ZeroOneKnapsack;

/// Plans an optimal cargo load under a weight budget using 0/1 knapsack.
///
/// Given a set of indivisible goods, each with a weight and a value, this
/// service selects the subset of goods that maximizes total value without
/// exceeding the cargo weight capacity. Every good is taken at most once,
/// matching the 0/1 knapsack formulation.
///
/// **Real-world use case:** Freight and truck-load planning, cloud resource
/// allocation where jobs are accepted or rejected wholesale, and budget
/// constrained project selection.
///
/// Complexity Analysis:
/// Time Complexity: O(n * W)
/// Auxiliary Space: O(n * W)
///
/// Bibliography:
///
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 12.
///
/// @see ZeroOneKnapsack
public final class CargoLoadOptimizer {

    private CargoLoadOptimizer() {
        // do nothing
    }

    /// Computes the maximum-value subset of goods fitting within the capacity.
    ///
    /// @param goods    list of available indivisible goods
    /// @param capacity maximum total weight
    /// @return the optimal total value
    public static int maxLoadValue(List<KnapsackItem> goods, int capacity) {
        return ZeroOneKnapsack.solveSpaceOptimised(goods, capacity);
    }

    /// Computes the optimal load and which goods were selected.
    ///
    /// @param goods    list of available indivisible goods
    /// @param capacity maximum total weight
    /// @return the zero/one knapsack result with value and selected indices
    public static ZeroOneKnapsack.Result planLoad(List<KnapsackItem> goods, int capacity) {
        return ZeroOneKnapsack.solve(goods, capacity);
    }
}
