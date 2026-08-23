package org.drozdek.dynamic;

import java.util.ArrayList;
import java.util.List;

/// Result container for the knapsack solver.
///
/// Holds the maximum profit found, the number of instructions executed
/// during the algorithm, and the list of items selected for the knapsack.
public class KnapsackSolution {
    private int maximumProfit;
    private int instructionCount;
    private List<DynamicKnapsackItem> knapsack;

    public KnapsackSolution() {
        this.maximumProfit = 0;
        this.instructionCount = 0;
        this.knapsack = new ArrayList<>();
    }

    public int getMaximumProfit() {
        return maximumProfit;
    }

    public int getInstructionCount() {
        return instructionCount;
    }

    public List<DynamicKnapsackItem> getKnapsack() {
        return knapsack;
    }

    /// Returns a multi-line summary of this solution.
    ///
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