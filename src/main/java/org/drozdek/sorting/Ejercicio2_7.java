package org.drozdek.sorting;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public class Ejercicio2_7 {

    public static void mergeSortAlter(List<Integer> a, int inicio, int fin) {
        if (fin - inicio < 1) {
            return;
        }

        if (fin - inicio == 1) {
            if (a.get(inicio) > a.get(fin)) {
                int aux = a.get(inicio);
                a.set(inicio, a.get(fin));
                a.set(fin, aux);
            }
            return;
        }

        int tercio1 = inicio + (fin - inicio) / 3;
        int tercio2 = inicio + 2 * (fin - inicio) / 3;

        mergeSortAlter(a, inicio, tercio1);
        mergeSortAlter(a, tercio1 + 1, tercio2);
        mergeSortAlter(a, tercio2 + 1, fin);

        merge(a, inicio, tercio1, tercio2, fin);
    }

    public static void merge(List<Integer> a, int inicio, int tercio1, int tercio2, int fin) {
        int n = fin - inicio + 1;
        List<Integer> result = new ArrayList<>();
        for (int k = 0; k < n; k++)
            result.add(0);

        int i = inicio, j = tercio1 + 1, k = tercio2 + 1;

        for (int idx = 0; idx < n; idx++) {
            if (i <= tercio1 && (j > tercio2 || a.get(i) <= a.get(j))
                    && (k > fin || a.get(i) <= a.get(k))) {
                result.set(idx, a.get(i++));
            } else if (j <= tercio2 && (k > fin || a.get(j) <= a.get(k))) {
                result.set(idx, a.get(j++));
            } else {
                result.set(idx, a.get(k++));
            }
        }

        for (int idx = 0; idx < n; idx++)
            a.set(inicio + idx, result.get(idx));
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
