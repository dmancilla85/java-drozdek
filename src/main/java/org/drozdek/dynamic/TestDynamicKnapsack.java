/**
 *
 */
package org.drozdek.dynamic;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author david
 *
 */
public class TestDynamicKnapsack {

    /**
     * @param args
     */
    static void main(String[] args) {
        ArrayList<DynamicKnapsackItem> knapsack = new ArrayList<DynamicKnapsackItem>();

        knapsack.add(new DynamicKnapsackItem(3, 4));
        knapsack.add(new DynamicKnapsackItem(2, 6));
        knapsack.add(new DynamicKnapsackItem(1, 9));
        knapsack.add(new DynamicKnapsackItem(4, 1));
        knapsack.add(new DynamicKnapsackItem(4, 10));
        knapsack.add(new DynamicKnapsackItem(5, 2));

        Collections.sort(knapsack);

        for (int i = 0; i < knapsack.size(); i++)
            System.out.println(knapsack.get(i));

        DynamicKnapsackItem.exercise5(knapsack);
    }

}
