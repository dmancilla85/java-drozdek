package searching;

import org.drozdek.commons.ArrayUtils;
import org.drozdek.searching.FibonacciSearch;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FibonacciSearchTest {

    @Test
    @DisplayName("Run fibonacci search algorithm.")
    void printFibonacciSearch() {
        int[] array = {10, 20, 30, 40, 50, 60, 70, 80, 90};
        out.println("Arreglo original:");
        ArrayUtils.printArray(array);

        out.println("Buscando el 50:");
        int index = FibonacciSearch.fibonacciSearch(array, 50);
        out.println("Elemento encontrado en la posici\u00f3n " + index);
        assertEquals(4, index, "Element 50 should be at index 4");

        out.println("Buscando el 25:");
        index = FibonacciSearch.fibonacciSearch(array, 25);
        assertEquals(-1, index, "Element 25 should not be found");

        out.println("Buscando el 10 (primer elemento):");
        index = FibonacciSearch.fibonacciSearch(array, 10);
        assertEquals(0, index, "Element 10 should be at index 0");

        out.println("Buscando el 90 (último elemento):");
        index = FibonacciSearch.fibonacciSearch(array, 90);
        assertEquals(8, index, "Element 90 should be at index 8");
    }
}
