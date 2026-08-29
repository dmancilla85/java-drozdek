package org.drozdek.searching.applications;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UniformCatalogLookupTest {

    @Test
    @DisplayName("Returns the price for a present SKU")
    void lookupPrice_found() {
        int[] sku = {100, 200, 300, 400, 500};
        int[] price = {10, 20, 30, 40, 50};
        assertEquals(30, UniformCatalogLookup.lookupPrice(sku, price, 300));
    }

    @Test
    @DisplayName("Returns -1 for an absent SKU")
    void lookupPrice_absent() {
        int[] sku = {100, 200, 300};
        int[] price = {10, 20, 30};
        assertEquals(-1, UniformCatalogLookup.lookupPrice(sku, price, 150));
    }
}
