package org.drozdek.dynamic;

/// Data class representing an item for the knapsack problem.
///
/// Each item has a name, a weight, and a value (profit).
public class KnapsackItem {
    public String name;
    public int weight;
    public int value;

    public KnapsackItem(String name, int weight, int value) {
        this.name = name;
        this.weight = weight;
        this.value = value;
    }
}
