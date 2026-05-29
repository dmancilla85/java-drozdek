package org.drozdek.dynamic;

import java.util.ArrayList;

/// Result container for the knapsack solver.
///
/// Holds the maximum profit found, the number of instructions executed
/// during the algorithm, and the list of items selected for the knapsack.
public class KnapsackSolution {
    public int maximumProfit;
    public int instructionCount;
    public ArrayList<DynamicKnapsackItem> knapsack;

    /// Creates an empty solution with zero profit and no selected items.
    public KnapsackSolution() {
        this.maximumProfit = 0;
        this.instructionCount = 0;
        this.knapsack = new ArrayList<DynamicKnapsackItem>();
    }

    /// @return A formatted string with total profit, instruction count, and selected items
    public String toString() {
        String result = "Total profit: " + maximumProfit +
                "\nTotal instructions: " + instructionCount + ".\n";

        for (int i = 0; i < knapsack.size(); i++) {
            result += knapsack.get(i) + "\n";
        }

        return result;
    }

}
