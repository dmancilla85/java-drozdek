package org.drozdek.hashing.applications;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CompilerKeywordTableTest {

    @Test
    @DisplayName("Every keyword resolves to a distinct slot")
    void slotOf_distinctPositive() {
        CompilerKeywordTable table = new CompilerKeywordTable(List.of("if", "for", "do", "of"));
        Set<Integer> slots = new HashSet<>();
        for (String keyword : List.of("if", "for", "do", "of")) {
            int slot = table.slotOf(keyword);
            assertTrue(slot >= 0, keyword + " must resolve to a non-negative slot");
            slots.add(slot);
        }
        assertEquals(4, slots.size());
    }

    @Test
    @DisplayName("A keyword outside the set resolves to -1")
    void slotOf_absent() {
        CompilerKeywordTable table = new CompilerKeywordTable(List.of("if", "for"));
        assertEquals(-1, table.slotOf("while"));
    }
}
