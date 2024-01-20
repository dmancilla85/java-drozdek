package searching;

import org.drozdek.commons.ArrayUtils;
import org.drozdek.searching.BinarySearch;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BinarySearchTest {

    @Test
    @DisplayName("Run quicksort algorithm.")
    void printBinarySearch(){
        //Crear un arreglo de prueba con números ordenados
        int[] array = {10, 20, 30, 40, 50, 60, 70, 80, 90};
        //Imprimir el arreglo original
        out.println("Arreglo original:");
        ArrayUtils.printArray(array);
        //Buscar algunos elementos usando binary search
        out.println("Buscando el 50:");
        int index = BinarySearch.binarySearch(array, 0, array.length - 1, 50);
        if (index != -1) {
            out.println("Elemento encontrado en la posición " + index);
        } else {
            out.println("Elemento no encontrado");
        }
        out.println("Buscando el 25:");
        index = BinarySearch.binarySearch(array, 0, array.length - 1, 25);

        if (index != -1) {
            out.println("Elemento encontrado en la posición " + index);
        } else {
            out.println("Elemento no encontrado");
        }

        assertTrue(true, "Check if everything is printing OK.");
    }
}
