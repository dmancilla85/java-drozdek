package org.drozdek.lists.applications;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StockPriceRangeLookupTest {

    @Test
    @DisplayName("Returns the overall minimum")
    void minPrice_fullRange() {
        StockPriceRangeLookup lookup = new StockPriceRangeLookup(new int[] {5, 3, 8, 1, 9});
        assertEquals(1, lookup.minPrice(0, 4));
    }

    @Test
    @DisplayName("Returns the minimum over a sub-range")
    void minPrice_subRange() {
        StockPriceRangeLookup lookup = new StockPriceRangeLookup(new int[] {5, 3, 8, 1, 9});
        assertEquals(3, lookup.minPrice(0, 2));
        assertEquals(1, lookup.minPrice(1, 3));
    }

    @Test
    @DisplayName("Single-element range returns that element")
    void minPrice_single() {
        StockPriceRangeLookup lookup = new StockPriceRangeLookup(new int[] {5, 3, 8});
        assertEquals(8, lookup.minPrice(2, 2));
    }
}
