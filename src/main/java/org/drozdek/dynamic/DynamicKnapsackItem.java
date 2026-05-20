/**
 *
 */
package org.drozdek.dynamic;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * @author david
 *
 */

class Exercise5Solution {
    int maximumProfit;
    int instructionCount;
    ArrayList<DynamicKnapsackItem> knapsack;


    Exercise5Solution() {
        this.maximumProfit = 0;
        this.instructionCount = 0;
        this.knapsack = new ArrayList<DynamicKnapsackItem>();
    }

    public String toString() {
        String result = "Total profit: " + maximumProfit +
                "\nTotal instructions: " + instructionCount + ".\n";

        for (int i = 0; i < knapsack.size(); i++) {
            result += knapsack.get(i) + "\n";
        }

        return result;
    }

}

public class DynamicKnapsackItem implements Comparable<DynamicKnapsackItem>, Comparator<DynamicKnapsackItem> {

    int weight;
    int profit;

    public DynamicKnapsackItem() {
        this.weight = 0;
        this.profit = 0;
    }

    public DynamicKnapsackItem(int weight, int profit) {
        this.weight = weight;
        this.profit = profit;
    }

    public static Exercise5Solution exercise5(ArrayList<DynamicKnapsackItem> knapsack) {

        Exercise5Solution sol = new Exercise5Solution();

        System.out.println(sol);
        return sol;
    }

    @Override
    public int compare(DynamicKnapsackItem arg0, DynamicKnapsackItem arg1) {
        return arg0.compareTo(arg1);
    }

    @Override
    public int compareTo(DynamicKnapsackItem arg0) {
        return Integer.compare(this.profit, arg0.profit);
    }

    public String toString() {
        return "[W: " + weight + ", P: " + profit + "]";
    }

}
