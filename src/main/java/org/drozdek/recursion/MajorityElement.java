package org.drozdek.recursion;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public final class MajorityElement {
private MajorityElement() {  }

    // Given an integer array A, find the majority element.
    // An element x is the majority if it appears more than n/2 times.
    // At most one such element can exist.

    public static Integer run(List<Integer> a, int index) {

        int mitad = a.size() / 2;
        int count = 0;
        int number;

        // Base case: with 2 or fewer elements there is no majority
        if (a.size() <= 2)
            return null;

        // Pick the element at current index as a candidate
        if (index < a.size())
            number = a.get(index);
        else
            return null;

        for (int i = 0; i < a.size(); i++)
            if (a.get(i) == number)
                count++;

        if (mitad < count)
            return number;
        else
            return run(a, ++index);
    }

    public static void test() {
        List<Integer> a = new ArrayList<Integer>();
        Integer mayor = 0;

        a.add(1);
        a.add(6);
        a.add(3);
        a.add(3);
        a.add(3);
        a.add(6);
        a.add(6);
        a.add(6);
        a.add(1);
        a.add(6);
        a.add(6);

        mayor = run(a, 0);

        if (mayor != null)
            out.println("El elemento mayoritario es " + mayor + ".");
        else
            out.println("No hay elemento mayoritario.");
    }
}
