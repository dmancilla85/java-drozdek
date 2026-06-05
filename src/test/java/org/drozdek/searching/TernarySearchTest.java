package org.drozdek.searching;

import org.drozdek.commons.ArrayUtils;
import org.drozdek.searching.TernarySearch;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TernarySearchTest {

    @Test
    @DisplayName("Run ternary search algorithm.")
    void printTernarySearch() {
        int[] array = {10, 20, 30, 40, 50, 60, 70, 80, 90};
        out.println("Arreglo original:");
        ArrayUtils.printArray(array);

        out.println("Buscando el 50:");
        int index = TernarySearch.ternarySearch(array, 0, array.length - 1, 50);
        out.println("Elemento encontrado en la posición " + index);
        assertEquals(4, index, "Element 50 should be at index 4");

        out.println("Buscando el 25:");
        index = TernarySearch.ternarySearch(array, 0, array.length - 1, 25);
        assertEquals(-1, index, "Element 25 should not be found");

        out.println("Buscando el 10 (primer elemento):");
        index = TernarySearch.ternarySearch(array, 0, array.length - 1, 10);
        assertEquals(0, index, "Element 10 should be at index 0");

        out.println("Buscando el 90 (último elemento):");
        index = TernarySearch.ternarySearch(array, 0, array.length - 1, 90);
        assertEquals(8, index, "Element 90 should be at index 8");
    }
}
