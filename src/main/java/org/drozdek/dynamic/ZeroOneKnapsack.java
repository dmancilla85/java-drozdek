package org.drozdek.dynamic;

import java.util.ArrayList;
import java.util.List;

/// Solves the 0/1 knapsack problem using dynamic programming.
/// Each item may be taken at most once (hence "0/1").
///
/// **Real-world use case:** Resource allocation in cloud computing
/// where each job consumes a fixed amount of memory and must either be
/// accepted or rejected; cargo loading; budget-constrained project selection.
///
/// Complexity Analysis:
/// Time Complexity: O(n * W) where n is number of items and W is capacity
/// Auxiliary Space: O(n * W) for the DP table
///
/// @see <a href="https://en.wikipedia.org/wiki/Knapsack_problem#0/1_knapsack_problem">0/1 knapsack problem (Wikipedia)</a>
public final class ZeroOneKnapsack {
    private ZeroOneKnapsack() {
        // do nothing
    }

    /// Result container for the 0/1 knapsack solver.
    public static class Result {
        private final int maxValue;
        private final List<Integer> selectedIndices;
        private final int tableRows;
        private final int tableCols;

        Result(int maxValue, List<Integer> selectedIndices, int rows, int cols) {
            this.maxValue = maxValue;
            this.selectedIndices = selectedIndices;
            this.tableRows = rows;
            this.tableCols = cols;
        }

        /// Returns the optimal total value found by the solver.
        ///
        /// @return Maximum value achievable
        public int getMaxValue() {
            return maxValue;
        }

        /// Returns the items included in the optimal solution, in selection order.
        ///
        /// @return Indices (0-based) of selected items
        public List<Integer> getSelectedIndices() {
            return selectedIndices;
        }

        /// Returns the number of rows of the DP table built by `solve`.
        ///
        /// @return Number of items evaluated (rows in DP table)
        public int getTableRows() {
            return tableRows;
        }

        /// Returns the number of columns of the DP table built by `solve`.
        ///
        /// @return Capacity + 1 (columns in DP table)
        public int getTableCols() {
            return tableCols;
        }

        @Override
        public String toString() {
            return "Max value: " + maxValue
                    + ", selected items: " + selectedIndices;
        }
    }

    /// Solves the 0/1 knapsack problem.
    ///
    /// @param items    List of items (each with weight and value)
    /// @param capacity Maximum weight capacity
    /// @return A [Result] containing the optimal value and which items were taken
    public static Result solve(List<KnapsackItem> items, int capacity) {
        int n = items.size();
        int[][] dp = new int[n + 1][capacity + 1];

        for (int i = 1; i <= n; i++) {
            int weight = items.get(i - 1).getWeight();
            int value = items.get(i - 1).getValue();

            for (int w = 0; w <= capacity; w++) {
                if (weight <= w) {
                    dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - weight] + value);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        // Backtrack to find selected items
        List<Integer> selected = new ArrayList<>();
        int w = capacity;
        for (int i = n; i > 0 && w > 0; i--) {
            if (dp[i][w] != dp[i - 1][w]) {
                selected.add(0, i - 1);
                w -= items.get(i - 1).getWeight();
            }
        }

        return new Result(dp[n][capacity], selected, n + 1, capacity + 1);
    }

    /// Solves the 0/1 knapsack using a space-optimised 1D array.
    ///
    /// @param items    List of items
    /// @param capacity Maximum weight capacity
    /// @return Maximum value achievable (selection not tracked)
    public static int solveSpaceOptimised(List<KnapsackItem> items, int capacity) {
        int[] dp = new int[capacity + 1];

        for (KnapsackItem item : items) {
            int weight = item.getWeight();
            int value = item.getValue();
            for (int w = capacity; w >= weight; w--) {
                dp[w] = Math.max(dp[w], dp[w - weight] + value);
            }
        }

        return dp[capacity];
    }
}
