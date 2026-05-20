package org.drozdek.sorting;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public class Exercise2_7 {

    public static void mergeSortAlter(List<Integer> a, int start, int end) {
        if (end - start < 1) {
            return;
        }

        if (end - start == 1) {
            if (a.get(start) > a.get(end)) {
                int aux = a.get(start);
                a.set(start, a.get(end));
                a.set(end, aux);
            }
            return;
        }

        int tercio1 = start + (end - start) / 3;
        int tercio2 = start + 2 * (end - start) / 3;

        mergeSortAlter(a, start, tercio1);
        mergeSortAlter(a, tercio1 + 1, tercio2);
        mergeSortAlter(a, tercio2 + 1, end);

        merge(a, start, tercio1, tercio2, end);
    }

    public static void merge(List<Integer> a, int start, int tercio1, int tercio2, int end) {
        int n = end - start + 1;
        List<Integer> result = new ArrayList<>();
        for (int k = 0; k < n; k++)
            result.add(0);

        int i = start, j = tercio1 + 1, k = tercio2 + 1;

        for (int idx = 0; idx < n; idx++) {
            if (i <= tercio1 && (j > tercio2 || a.get(i) <= a.get(j))
                    && (k > end || a.get(i) <= a.get(k))) {
                result.set(idx, a.get(i++));
            } else if (j <= tercio2 && (k > end || a.get(j) <= a.get(k))) {
                result.set(idx, a.get(j++));
            } else {
                result.set(idx, a.get(k++));
            }
        }

        for (int idx = 0; idx < n; idx++)
            a.set(start + idx, result.get(idx));
    }

    public static void test(int n) {
        List<Integer> valores = new ArrayList<>();
        valores.add(2);
        valores.add(8);
        valores.add(1);
        valores.add(75);
        valores.add(3);
        valores.add(27);
        valores.add(22);
        valores.add(25);

        mergeSortAlter(valores, 0, valores.size() - 1);

        for (int i = 0; i < valores.size(); i++)
            out.println(valores.get(i));
    }
}
