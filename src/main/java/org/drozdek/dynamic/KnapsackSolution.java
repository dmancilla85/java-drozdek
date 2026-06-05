package org.drozdek.dynamic;

import java.util.ArrayList;
import java.util.List;

/// Result container for the knapsack solver.
///
/// Holds the maximum profit found, the number of instructions executed
/// during the algorithm, and the list of items selected for the knapsack.
public class KnapsackSolution {
    public int maximumProfit;
    public int instructionCount;
    public List<DynamicKnapsackItem> knapsack;

    /// Creates an empty solution with zero profit and no selected items.
    public KnapsackSolution() {
        this.maximumProfit = 0;
        this.instructionCount = 0;
        this.knapsack = new ArrayList<>();
    }

    /// @return A formatted string with total profit, instruction count, and selected items
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Total profit: ").append(maximumProfit)
                .append("\nTotal instructions: ").append(instructionCount).append(".\n");

        for (int i = 0; i < knapsack.size(); i++) {
            result.append(knapsack.get(i)).append("\n");
        }

        return result.toString();
    }

}
