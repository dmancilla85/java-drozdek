package org.drozdek.recursion;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MajorityElementTest {

    @Test
    @DisplayName("Empty list returns null")
    void emptyList() {
        List<Integer> list = new ArrayList<>();
        assertNull(MajorityElement.run(list, 0));
    }

    @Test
    @DisplayName("Single element list returns null")
    void singleElement() {
        List<Integer> list = new ArrayList<>(List.of(5));
        assertNull(MajorityElement.run(list, 0));
    }

    @Test
    @DisplayName("Two element list returns null")
    void twoElements() {
        List<Integer> list = new ArrayList<>(List.of(1, 2));
        assertNull(MajorityElement.run(list, 0));
    }

    @Test
    @DisplayName("No majority element returns null")
    void noMajority() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3));
        assertNull(MajorityElement.run(list, 0));
    }

    @Test
    @DisplayName("Majority element at first candidate")
    void majorityAtFirstCandidate() {
        List<Integer> list = new ArrayList<>(Arrays.asList(3, 3, 3, 1, 2));
        assertEquals(Integer.valueOf(3), MajorityElement.run(list, 0));
    }

    @Test
    @DisplayName("Majority element at later candidate")
    void majorityAtLaterCandidate() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 3, 3));
        assertEquals(Integer.valueOf(3), MajorityElement.run(list, 0));
    }

    @Test
    @DisplayName("All elements same returns that element")
    void allSame() {
        List<Integer> list = new ArrayList<>(Arrays.asList(5, 5, 5, 5));
        assertEquals(Integer.valueOf(5), MajorityElement.run(list, 0));
    }

    @Test
    @DisplayName("Index out of bounds returns null")
    void indexOutOfBounds() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3));
        assertNull(MajorityElement.run(list, 5));
    }

    @Test
    @DisplayName("Large list with clear majority")
    void largeListWithMajority() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 6, 3, 3, 3, 6, 6, 6, 1, 6, 6));
        assertEquals(Integer.valueOf(6), MajorityElement.run(list, 0));
    }
}
