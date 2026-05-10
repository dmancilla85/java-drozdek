package searching;

import org.drozdek.commons.ArrayUtils;
import org.drozdek.searching.BinarySearch;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BinarySearchTest {

    @Test
    @DisplayName("Run binary search algorithm.")
    void printBinarySearch(){
        int[] array = {10, 20, 30, 40, 50, 60, 70, 80, 90};
        out.println("Arreglo original:");
        ArrayUtils.printArray(array);

        out.println("Buscando el 50:");
        int index = BinarySearch.binarySearch(array, 0, array.length - 1, 50);
        out.println("Elemento encontrado en la posición " + index);
        assertEquals(4, index, "Element 50 should be at index 4");

        out.println("Buscando el 25:");
        index = BinarySearch.binarySearch(array, 0, array.length - 1, 25);
        assertEquals(-1, index, "Element 25 should not be found");
    }
}
