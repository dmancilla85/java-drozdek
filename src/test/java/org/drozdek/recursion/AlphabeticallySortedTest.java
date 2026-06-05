package org.drozdek.recursion;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AlphabeticallySortedTest {

    @Test
    @DisplayName("Empty list is sorted")
    void emptyList() {
        List<Character> list = new ArrayList<>();
        assertTrue(AlphabeticallySorted.run(list, 0));
    }

    @Test
    @DisplayName("Single character list is sorted")
    void singleElement() {
        List<Character> list = new ArrayList<>(List.of('a'));
        assertTrue(AlphabeticallySorted.run(list, 0));
    }

    @Test
    @DisplayName("Two characters in order")
    void twoCharactersSorted() {
        List<Character> list = new ArrayList<>(Arrays.asList('a', 'c'));
        assertTrue(AlphabeticallySorted.run(list, 0));
    }

    @Test
    @DisplayName("Two characters out of order")
    void twoCharactersUnsorted() {
        List<Character> list = new ArrayList<>(Arrays.asList('z', 'a'));
        assertFalse(AlphabeticallySorted.run(list, 0));
    }

    @Test
    @DisplayName("All characters ascending")
    void allAscending() {
        List<Character> list = new ArrayList<>(Arrays.asList('a', 'b', 'c', 'd', 'e'));
        assertTrue(AlphabeticallySorted.run(list, 0));
    }

    @Test
    @DisplayName("Characters in descending order")
    void descendingOrder() {
        List<Character> list = new ArrayList<>(Arrays.asList('e', 'd', 'c', 'b', 'a'));
        assertFalse(AlphabeticallySorted.run(list, 0));
    }

    @Test
    @DisplayName("Case-insensitive same letter returns true")
    void sameLetterDifferentCase() {
        List<Character> list = new ArrayList<>(Arrays.asList('a', 'A'));
        assertTrue(AlphabeticallySorted.run(list, 0));
    }

    @Test
    @DisplayName("Sorted with mixed case")
    void sortedMixedCase() {
        List<Character> list = new ArrayList<>(Arrays.asList('a', 'c', 'e', 'y'));
        assertTrue(AlphabeticallySorted.run(list, 0));
    }

    @Test
    @DisplayName("Unsorted with mixed case")
    void unsortedMixedCase() {
        List<Character> list = new ArrayList<>(Arrays.asList('y', 'G', 't', 'T', 'V', 'e'));
        assertFalse(AlphabeticallySorted.run(list, 0));
    }

    @Test
    @DisplayName("All same character returns true")
    void allSameCharacter() {
        List<Character> list = new ArrayList<>(Arrays.asList('b', 'b', 'b', 'b'));
        assertTrue(AlphabeticallySorted.run(list, 0));
    }

    @Test
    @DisplayName("One pair unsorted in middle")
    void unsortedInMiddle() {
        List<Character> list = new ArrayList<>(Arrays.asList('a', 'b', 'z', 'c', 'd'));
        assertFalse(AlphabeticallySorted.run(list, 0));
    }
}
