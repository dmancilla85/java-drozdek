package org.drozdek.sorting;

import org.drozdek.commons.ArrayUtils;

public class CountingSort {

    private CountingSort() {
        // do nothing
    }

    // Complexity: O(n+k)
    // Memory: O(n+k)
    public static void countingSort(int[] array) {
        int n = array.length;

        // find the maximum array value
        int max = ArrayUtils.getMaxValue(array);

        // Crear un arreglo auxiliar para contar la frecuencia de cada elemento
        int[] count = new int[max + 1];

        for (int j : array) {
            count[j]++;
        }

        // Acumular la frecuencia de cada elemento para obtener su posición final
        for (int i = 1; i <= max; i++) {
            count[i] += count[i - 1];
        }

        // Crear un arreglo de salida para almacenar los elementos ordenados
        int[] output = new int[n];

        for (int i = n - 1; i >= 0; i--) {
            // Colocar el elemento actual en la posición indicada por el arreglo auxiliar
            output[count[array[i]] - 1] = array[i];
        }

        // Copiar el arreglo de salida al arreglo original
        System.arraycopy(output, 0, array, 0, n);
    }
}