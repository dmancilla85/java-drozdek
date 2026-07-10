package org.drozdek.dynamic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KnapsackItemTest {

    @Test
    @DisplayName("Create item and verify fields")
    void createItem() {
        KnapsackItem item = new KnapsackItem("gold", 10, 100);
        assertEquals("gold", item.getName());
        assertEquals(10, item.getWeight());
        assertEquals(100, item.getValue());
    }

    @Test
    @DisplayName("Create item with zero values")
    void zeroValues() {
        KnapsackItem item = new KnapsackItem("empty", 0, 0);
        assertEquals(0, item.getWeight());
        assertEquals(0, item.getValue());
    }

    @Test
    @DisplayName("Create item with negative values")
    void negativeValues() {
        KnapsackItem item = new KnapsackItem("debt", -5, -10);
        assertEquals(-5, item.getWeight());
        assertEquals(-10, item.getValue());
    }
}
