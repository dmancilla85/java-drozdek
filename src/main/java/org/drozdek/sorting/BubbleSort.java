package org.drozdek.sorting;

public class BubbleSort {

    private BubbleSort(){
        // do nothing
    }

    // Complexity O(n²)
    // Memory O(1)
    public static void bubbleSort(int[] array) {
        int n = array.length;
        boolean swapped;

        // Repetir hasta que no haya intercambios
        do {
            swapped = false;

            // Recorrer el arreglo desde el inicio hasta el final
            for (int i = 0; i < n - 1; i++) {

                // Comparar el elemento actual con el siguiente
                if (array[i] > array[i + 1]) {

                    // Intercambiar los elementos si están en el orden incorrecto
                    int temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;

                    // Marcar que hubo un intercambio
                    swapped = true;
                }
            }
        } while (swapped);
    }
}
