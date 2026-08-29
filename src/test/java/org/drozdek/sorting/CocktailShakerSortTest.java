package org.drozdek.sorting;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CocktailShakerSortTest {

    @Test
    @DisplayName("Sorts an unsorted array")
    void cocktailShakerSort_sortsArray() {
        int[] array = {5, 1, 4, 2, 8};
        CocktailShakerSort.cocktailShakerSort(array);
        assertArrayEquals(new int[]{1, 2, 4, 5, 8}, array);
    }

    @Test
    @DisplayName("Already sorted array is unchanged")
    void cocktailShakerSort_sortedArrayUnchanged() {
        int[] array = {1, 2, 3, 4, 5};
        CocktailShakerSort.cocktailShakerSort(array);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, array);
    }

    @Test
    @DisplayName("Reverse sorted array is fully sorted")
    void cocktailShakerSort_reverseArray() {
        int[] array = {5, 4, 3, 2, 1};
        CocktailShakerSort.cocktailShakerSort(array);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, array);
    }

    @Test
    @DisplayName("Handles a single element")
    void cocktailShakerSort_singleElement() {
        int[] array = {7};
        CocktailShakerSort.cocktailShakerSort(array);
        assertArrayEquals(new int[]{7}, array);
    }

    @Test
    @DisplayName("Handles duplicates")
    void cocktailShakerSort_duplicates() {
        int[] array = {2, 1, 2, 1, 2};
        CocktailShakerSort.cocktailShakerSort(array);
        assertArrayEquals(new int[]{1, 1, 2, 2, 2}, array);
    }
}
