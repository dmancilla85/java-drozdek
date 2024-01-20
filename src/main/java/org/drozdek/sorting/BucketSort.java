package org.drozdek.sorting;

import java.util.ArrayList;
import java.util.Collections;

public class BucketSort {

    private BucketSort(){
        // do nothing
    }

    // Complexity: O(n)
    // Memory: O(n)
    public static void bucketSort(int[] array, int bucketSize) {

        // Encontrar el valor mínimo y máximo en el arreglo
        int min = array[0];
        int max = array[0];

        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            } else if (array[i] > max) {
                max = array[i];
            }
        }

        // Calcular el número de cubetas
        int bucketCount = (max - min) / bucketSize + 1;

        // Crear una lista de cubetas vacías
        ArrayList<ArrayList<Integer>> buckets = new ArrayList<>(bucketCount);
        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }

        // Insertar los elementos del arreglo en las cubetas correspondientes
        for (int j : array) {
            int bucketIndex = (j - min) / bucketSize;
            buckets.get(bucketIndex).add(j);
        }

        // Ordenar cada cubeta usando Collections.sort() y copiar los elementos al arreglo original
        int index = 0;

        for (ArrayList<Integer> bucket : buckets) {
            Collections.sort(bucket);

            for (Integer integer : bucket) {
                array[index++] = integer;
            }
        }
    }
}
