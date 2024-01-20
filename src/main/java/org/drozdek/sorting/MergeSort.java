package org.drozdek.sorting;


import static java.lang.System.out;

public class MergeSort {

    private MergeSort(){
        // do nothing
    }

    // Complexity O(n log n)
    // Memory O(n)
    public static void mergeSort(int[] array, int left, int right) {

        //Verificar que el arreglo tenga al menos dos elementos
        if (left < right) {

            //Encontrar el punto medio para dividir el arreglo en dos mitades
            int middle = (left + right) / 2;

            //Ordenar recursivamente la primera mitad
            mergeSort(array, left, middle);

            //Ordenar recursivamente la segunda mitad
            mergeSort(array, middle + 1, right);

            //Unir las dos mitades ordenadas
            merge(array, left, middle, right);
        }
    }

    //Método para unir dos subarreglos ordenados
    public static void merge(int[] array, int left, int middle, int right) {

        //Encontrar el tamaño de los subarreglos para unirlos
        int n1 = middle - left + 1;
        int n2 = right - middle;

        //Crear arreglos temporales
        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        //Copiar los datos a los arreglos temporales
        System.arraycopy(array, left + 0, leftArray, 0, n1);
        System.arraycopy(array, middle + 1, rightArray, 0, n2);

        //Unir los arreglos temporales

        //Índices iniciales de los subarreglos
        int i = 0, j = 0;

        //Índice inicial del subarreglo resultante
        int k = left;

        //Ordenar los elementos comparándolos entre los subarreglos
        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }

        //Copiar los elementos restantes de los subarreglos si quedan
        while (i < n1) {
            array[k] = leftArray[i];
            i++;
            k++;
        }
        while (j < n2) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }
}
