package org.drozdek.sorting;

public class HeapSort {

    private HeapSort(){
        // do nothing
    }

    // Complexity O(n log n)
    // Memory O(1)
    public static void heapSort(int[] array) {
        int n = array.length;

        // Construir el montículo máximo inicial
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n, i);
        }

        // Extraer los elementos del montículo uno por uno
        for (int i = n - 1; i > 0; i--) {

            // Intercambiar el elemento de la raíz con el último elemento
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            // Restaurar la propiedad de montículo máximo en la nueva raíz
            heapify(array, i, 0);
        }
    }

    // Método para restaurar la propiedad de montículo máximo en un subárbol
    private static void heapify(int[] array, int n, int i) {
        // Encontrar el índice del mayor elemento entre la raíz, el hijo izquierdo y el hijo derecho
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && array[left] > array[largest]) {
            largest = left;
        }

        if (right < n && array[right] > array[largest]) {
            largest = right;
        }

        // Si el mayor no es la raíz, intercambiarlos y aplicar recursivamente heapify al subárbol afectado
        if (largest != i) {
            int temp = array[i];
            array[i] = array[largest];
            array[largest] = temp;
            heapify(array, n, largest);
        }
    }
}