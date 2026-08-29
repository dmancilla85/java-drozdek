package org.drozdek.lists;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SparseTableTest {

    @Test
    @DisplayName("Range minimum over the whole array")
    void rangeMinimum_wholeArray() {
        SparseTable table = new SparseTable(new int[]{4, 2, 8, 1, 6});
        assertEquals(1, table.rangeMinimum(0, 4));
    }

    @Test
    @DisplayName("Range minimum over a sub-range")
    void rangeMinimum_subRange() {
        SparseTable table = new SparseTable(new int[]{4, 2, 8, 1, 6});
        assertEquals(2, table.rangeMinimum(0, 1));
        assertEquals(1, table.rangeMinimum(2, 4));
    }

    @Test
    @DisplayName("Single-element range returns that element")
    void rangeMinimum_singleElementRange() {
        SparseTable table = new SparseTable(new int[]{4, 2, 8, 1, 6});
        assertEquals(8, table.rangeMinimum(2, 2));
    }

    @Test
    @DisplayName("Minimum at either boundary is handled")
    void rangeMinimum_boundaryMinimum() {
        SparseTable table = new SparseTable(new int[]{1, 3, 5, 7, 0, 9});
        assertEquals(1, table.rangeMinimum(0, 2));
        assertEquals(0, table.rangeMinimum(0, 5));
    }

    @Test
    @DisplayName("Range of length two is minimum of the pair")
    void rangeMinimum_lengthTwo() {
        SparseTable table = new SparseTable(new int[]{9, 3, 7, 1});
        assertEquals(3, table.rangeMinimum(0, 1));
        assertEquals(1, table.rangeMinimum(2, 3));
    }
}
