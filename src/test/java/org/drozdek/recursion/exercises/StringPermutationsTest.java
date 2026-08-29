package org.drozdek.recursion.exercises;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StringPermutationsTest {

    @Test
    @DisplayName("Generates all 6 permutations of three distinct characters")
    void generate_three() {
        List<String> perms = StringPermutations.generate("abc");
        assertEquals(6, perms.size());
        assertTrue(perms.contains("abc"));
        assertTrue(perms.contains("acb"));
        assertTrue(perms.contains("bac"));
        assertTrue(perms.contains("bca"));
        assertTrue(perms.contains("cab"));
        assertTrue(perms.contains("cba"));
    }

    @Test
    @DisplayName("Generates 2 permutations of two characters")
    void generate_two() {
        List<String> perms = StringPermutations.generate("ab");
        assertEquals(2, perms.size());
        assertTrue(perms.contains("ab"));
        assertTrue(perms.contains("ba"));
    }

    @Test
    @DisplayName("Generates a single permutation for a single character")
    void generate_single() {
        assertEquals(List.of("a"), StringPermutations.generate("a"));
    }
}
