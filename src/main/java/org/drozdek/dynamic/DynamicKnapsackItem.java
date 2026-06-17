package org.drozdek.dynamic;

import org.drozdek.commons.LoggerService;
import java.util.Comparator;

/// @author david

/// Represents an item for the knapsack problem with a weight and a profit.
///
/// Implements `Comparable` to enable sorting items by profit (ascending).
public class DynamicKnapsackItem implements Comparable<DynamicKnapsackItem>, Comparator<DynamicKnapsackItem> {

    private int weight;
    private int profit;

    /// Creates a default item with zero weight and zero profit.
    public DynamicKnapsackItem() {
        this.weight = 0;
        this.profit = 0;
    }

    /// Creates an item with the given weight and profit.
    ///
    /// @param weight The item's weight
    /// @param profit The item's profit (value)
    public DynamicKnapsackItem(int weight, int profit) {
        this.weight = weight;
        this.profit = profit;
    }

    public int getWeight() {
        return weight;
    }

    public int getProfit() {
        return profit;
    }

    /// Stub method for the Exercise 5 knapsack solver.
    ///
    /// Currently creates an empty `KnapsackSolution` and prints it
    /// without performing any computation. Intended as a template.
    ///
    /// @param knapsack The list of items to select from
    /// @return An empty solution object
    public static KnapsackSolution solveExercise5() {

        KnapsackSolution sol = new KnapsackSolution();

        LoggerService.logInfo(String.valueOf(sol));
        return sol;
    }

    /// Compares two items by profit (delegates to compareTo).
    @Override
    public int compare(DynamicKnapsackItem arg0, DynamicKnapsackItem arg1) {
        return arg0.compareTo(arg1);
    }

    /// Compares this item to another by profit (ascending).
    @Override
    public int compareTo(DynamicKnapsackItem arg0) {
        return Integer.compare(this.profit, arg0.profit);
    }

    /// @return A string in the format [W: weight, P: profit]
    public String toString() {
        return "[W: " + weight + ", P: " + profit + "]";
    }

}
