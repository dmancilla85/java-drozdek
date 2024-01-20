package org.drozdek.sorting;

public class ShellSort {

    private ShellSort(){
        // do nothing
    }

    // Complexity: O(n1.25)
    // Memory: O(1)
    public static void shellSort(int[] array) {

        int n = array.length;

        // Inicializar el intervalo con la mitad del tamaño del arreglo
        int gap = n / 2;

        // Repetir hasta que el intervalo sea cero
        while (gap > 0) {

            // Recorrer el arreglo desde el intervalo hasta el final
            for (int i = gap; i < n; i++) {

                // Guardar el elemento actual como clave
                int key = array[i];

                // Encontrar la posición correcta para insertar la clave
                int j = i - gap;

                // Mover los elementos mayores que la clave a la derecha
                while (j >= 0 && array[j] > key) {
                    array[j + gap] = array[j];
                    j -= gap;
                }

                // Insertar la clave en la posición encontrada
                array[j + gap] = key;
            }
            // Reducir el intervalo a la mitad
            gap /= 2;
        }
    }
}