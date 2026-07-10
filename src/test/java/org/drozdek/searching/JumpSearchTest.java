package org.drozdek.searching;

import org.drozdek.commons.ArrayUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.assertEquals;

class JumpSearchTest {

    @Test
    @DisplayName("Run jump search algorithm.")
    void printJumpSearch() {
        int[] array = {10, 20, 30, 40, 50, 60, 70, 80, 90};
        out.println("Arreglo original:");
        ArrayUtils.printArray(array);

        out.println("Buscando el 50:");
        int index = JumpSearch.jumpSearch(array, 50);
        out.println("Elemento encontrado en la posición " + index);
        assertEquals(4, index, "Element 50 should be at index 4");

        out.println("Buscando el 25:");
        index = JumpSearch.jumpSearch(array, 25);
        assertEquals(-1, index, "Element 25 should not be found");

        out.println("Buscando el 10 (primer elemento):");
        index = JumpSearch.jumpSearch(array, 10);
        assertEquals(0, index, "Element 10 should be at index 0");

        out.println("Buscando el 90 (último elemento):");
        index = JumpSearch.jumpSearch(array, 90);
        assertEquals(8, index, "Element 90 should be at index 8");

        out.println("Buscando el 100 (mayor que el máximo):");
        index = JumpSearch.jumpSearch(array, 100);
        assertEquals(-1, index, "Element 100 should not be found");
    }

    @Test
    @DisplayName("Jump search in single-element array.")
    void jumpSearchSingleElement() {
        int[] array = {42};

        int index = JumpSearch.jumpSearch(array, 42);
        assertEquals(0, index, "Element 42 should be at index 0");

        index = JumpSearch.jumpSearch(array, 99);
        assertEquals(-1, index, "Element 99 should not be found");

        index = JumpSearch.jumpSearch(array, 1);
        assertEquals(-1, index, "Element 1 should not be found");
    }
}
