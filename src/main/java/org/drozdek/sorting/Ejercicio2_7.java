package org.drozdek.sorting;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public class Ejercicio2_7 {
    /* Dado un Vector A  de números enteros, ordenarlo en forma creciente.
     * Utilizar el método de ordenamiento Merge-Sort, pero dividiendo el
     * vector en 3 subvectores y analizar el costo.
     * */

    public static void mergeSortAlter(List<Integer> a, int inicio, int fin) {

        int mitad = 0, aux;

        if (a.size() <= 1) {
            return;
        }

        // Casos triviales
        if (a.size() == 2) {
            if (a.get(0) > a.get(1)) {
                aux = a.get(0);
                a.set(0, a.get(1));
                a.set(1, aux);
            }
        } else if (a.size() > 2) {
            mitad = (fin + inicio) / 2;
            mergeSortAlter(a, inicio, mitad);
            mergeSortAlter(a, mitad + 1, fin);
            merge(a, inicio, fin);
        }
    }

    public static void merge(List<Integer> a, int inicio, int fin) {
        List<Integer> result = new ArrayList<Integer>();
        int medio = ((inicio + fin) / 2) - 1;
        int i = (inicio - 1), j = (medio + 1) - 1;

        // Inicializar vector auxiliar
        for (int k = 0; k < fin - inicio + 1; k++)
            result.add(0);

        for (int k = 0; k < fin - inicio; k++) {
            if (j > fin || a.get(i) <= a.get(j)) {
                result.set(k, a.get(i));
                i++;
            } else {
                result.set(k, a.get(j));
                j++;
            }
        }

        for (int k = 0; k <= fin - inicio - 1; k++)
            a.set(inicio + k, result.get(k));
    }

    public static void test(int n) {
        List<Integer> valores = new ArrayList<Integer>();
		
		/*for(int i = 0; i < n; i++)
			valores.add(0);*/
        valores.add(2);
        valores.add(8);
        valores.add(1);
        valores.add(75);
        valores.add(3);
        valores.add(27);
        valores.add(22);
        valores.add(25);

        mergeSortAlter(valores, 1, valores.size());

        for (int i = 0; i < valores.size(); i++)
            out.println(valores.get(i));
    }
}
