package org.drozdek.dynamic;

/// Data class representing an item for the knapsack problem.
///
/// Each item has a name, a weight, and a value (profit).
public class KnapsackItem {
    private final String name;
    private final int weight;
    private final int value;

    public KnapsackItem(String name, int weight, int value) {
        this.name = name;
        this.weight = weight;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public int getValue() {
        return value;
    }
}
