package org.drozdek.dynamic;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

/// Implements the Fractional Knapsack problem using two approaches:
///
/// 1. **Sequential greedy** (`sequentialGreedyKnapsack`) — iterates items in list order and
///    takes whole items or a fraction of the last one.
/// 2. **Dynamic programming** (`dpKnapsack`) — builds a value matrix with a
///    hybrid recurrence that blends fractional and 0/1 knapsack ideas.
public final class FractionalKnapsack {
private FractionalKnapsack() {  }

    /// Returns the larger of two doubles.
    private static double max(double a, double b) {
        return Math.max(a, b);
    }

    /// Returns the smaller of two doubles.
    private static double min(double a, double b) {
        return Math.min(a, b);
    }

    /// Sequential greedy fractional knapsack.
    ///
    /// Processes items in their insertion order (not sorted by value/weight ratio,
    /// so this is **not** the optimal greedy algorithm). Takes each whole item if it
    /// fits in the remaining capacity, otherwise takes a fraction of it and stops.
    ///
    /// @param items     List of available items
    /// @param maxWeight Maximum weight capacity
    /// @return The total value obtained
    public static Double sequentialGreedyKnapsack(List<KnapsackItem> items, int maxWeight) {

        List<Double> r = new ArrayList<Double>();

        for (int i = 0; i < items.size(); i++)
            r.add(0.00);

        double sum = 0;
        int currentItem = 0;

        while (sum < maxWeight && currentItem < items.size()) {
            double fraction = min(1, (maxWeight - sum) / (double) items.get(currentItem).weight);
            r.set(currentItem, fraction);

            sum += fraction * items.get(currentItem).weight;
            currentItem++;
        }

        double totalValue = 0;
        for (int i = 0; i < items.size(); i++) {
            totalValue += r.get(i) * items.get(i).value;
        }
        return totalValue;
    }

    /// DP-based knapsack solver.
    ///
    /// Builds an (n+1) x (capacity+1) matrix where `matrix[i][j]` is the maximum value
    /// achievable using the first `i` items with capacity `j`. The recurrence is:
    ///
    /// - If item `i` does not fit (`j - weight &lt; 0`): uses a fractional valuation plus
    ///   `matrix[i-1][0]`.
    /// - Otherwise: standard 0/1 knapsack max of skipping vs taking the item.
    ///
    /// @param items     List of available items
    /// @param maxWeight Maximum weight capacity
    /// @return The maximum total value achievable
    public static Double dpKnapsack(List<KnapsackItem> items, int maxWeight) {

        Double[][] matrix = new Double[items.size() + 1][maxWeight + 1];

        for (int i = 0; i <= items.size(); i++)
            for (int j = 0; j <= maxWeight; j++)
                if (i == 0 || j == 0)
                    matrix[i][j] = 0.0;
                else
                    matrix[i][j] = Double.NEGATIVE_INFINITY;

        for (int i = 1; i <= items.size(); i++)
            for (int j = 1; j <= maxWeight; j++) {
                if (j - items.get(i - 1).weight < 0)
                    matrix[i][j] = max(items.get(i - 1).value *
                                    ((double) j / items.get(i - 1).weight)
                                    + matrix[i - 1][0],
                            matrix[i - 1][j]);
                else {
                    matrix[i][j] = max(matrix[i - 1][j],
                            items.get(i - 1).value
                                    + matrix[i - 1][j - items.get(i - 1).weight]);
                }

            }

        return matrix[items.size()][maxWeight];
    }

    /// Test harness for the knapsack solvers.
    ///
    /// Creates three items with weights (18, 15, 10) and values (25, 24, 15),
    /// sets capacity to 20, and prints the result from `dpKnapsack`.
    public static void runDemo() {
        List<KnapsackItem> items = new ArrayList<KnapsackItem>();

        items.add(new KnapsackItem("1", 18, 25));
        items.add(new KnapsackItem("2", 15, 24));
        items.add(new KnapsackItem("3", 10, 15));

        double result = dpKnapsack(items, 20);

        out.println("Maximum profit is " + result);
    }
}
