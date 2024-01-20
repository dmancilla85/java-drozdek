package org.drozdek.searching;

public class BinarySearch {

    private BinarySearch(){
        // do nothing
    }

    //Método para buscar un elemento usando binary search
    public static int binarySearch(int[] array, int left, int right, int target) {
        //Verificar que el arreglo no esté vacío
        if (left <= right) {

            //Calcular el índice del elemento medio
            int mid = (left + right) / 2;

            //Comparar el elemento buscado con el elemento medio
            if (target == array[mid]) {

                //La búsqueda tiene éxito y se devuelve la posición
                return mid;
            } else if (target < array[mid]) {

                //El elemento buscado es menor que el elemento medio
                //Se busca recursivamente en la primera mitad del arreglo
                return binarySearch(array, left, mid - 1, target);
            } else {

                //El elemento buscado es mayor que el elemento medio
                //Se busca recursivamente en la segunda mitad del arreglo
                return binarySearch(array, mid + 1, right, target);
            }
        } else {
            //La búsqueda falla y se devuelve -1
            return -1;
        }
    }
}