package org.drozdek.dynamic;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

class Item {
    String name;
    int weight;
    int value;

    Item(String name, int weight, int value) {
        this.name = name;
        this.weight = weight;
        this.value = value;
    }
}

public class Exercise3_2 {

    public static double getMaximum(double a, double b) {
        return Math.max(a, b);
    }

    public static double getMinimum(double a, double b) {
        return Math.min(a, b);
    }

    public static Double maximizeKnapsack(List<Item> items, int maxWeight) {

        List<Double> r = new ArrayList<Double>();

        for (int i = 0; i < items.size(); i++)
            r.add(0.00);

        double sum = 0;
        int currentItem = 0;

        while (sum < maxWeight && currentItem < items.size()) {
            double fraction = getMinimum(1, (maxWeight - sum) / (double) items.get(currentItem).weight);
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

    public static Double maximizeKnapsackDynamic(List<Item> items, int maxWeight) {

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
                    matrix[i][j] = getMaximum(items.get(i - 1).value *
                                    ((double) j / items.get(i - 1).weight)
                                    + matrix[i - 1][0],
                            matrix[i - 1][j]);
                else {
                    matrix[i][j] = getMaximum(matrix[i - 1][j],
                            items.get(i - 1).value
                                    + matrix[i - 1][j - items.get(i - 1).weight]);
                }

            }

        return matrix[items.size()][maxWeight];
    }

    public static void test() {
        List<Item> items = new ArrayList<Item>();

        items.add(new Item("1", 18, 25));
        items.add(new Item("2", 15, 24));
        items.add(new Item("3", 10, 15));

        double result = maximizeKnapsackDynamic(items, 20);

        out.println("Maximum profit is " + result);
    }
}
