package org.drozdek.dynamic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DynamicKnapsackItemTest {

    @Test
    @DisplayName("Default constructor")
    void defaultConstructor() {
        DynamicKnapsackItem item = new DynamicKnapsackItem();
        assertEquals(0, item.getWeight());
        assertEquals(0, item.getProfit());
    }

    @Test
    @DisplayName("Parameterized constructor")
    void parameterizedConstructor() {
        DynamicKnapsackItem item = new DynamicKnapsackItem(10, 100);
        assertEquals(10, item.getWeight());
        assertEquals(100, item.getProfit());
    }

    @Test
    @DisplayName("CompareTo by profit")
    void compareTo() {
        DynamicKnapsackItem a = new DynamicKnapsackItem(10, 50);
        DynamicKnapsackItem b = new DynamicKnapsackItem(20, 100);
        assertTrue(a.compareTo(b) < 0);
        assertTrue(b.compareTo(a) > 0);
        assertEquals(0, a.compareTo(new DynamicKnapsackItem(5, 50)));
    }

    @Test
    @DisplayName("Compare via Comparator delegates to compareTo")
    void compare() {
        DynamicKnapsackItem a = new DynamicKnapsackItem(10, 50);
        DynamicKnapsackItem b = new DynamicKnapsackItem(20, 100);
        assertTrue(a.compare(a, b) < 0);
        assertTrue(a.compare(b, a) > 0);
    }

    @Test
    @DisplayName("Equals by profit")
    void equalsSame() {
        DynamicKnapsackItem a = new DynamicKnapsackItem(10, 50);
        DynamicKnapsackItem b = new DynamicKnapsackItem(99, 50);
        assertEquals(a, b);
    }

    @Test
    @DisplayName("Equals false for different profit")
    void equalsDifferent() {
        DynamicKnapsackItem a = new DynamicKnapsackItem(10, 50);
        DynamicKnapsackItem b = new DynamicKnapsackItem(20, 100);
        assertNotEquals(a, b);
    }

    @Test
    @DisplayName("Equals false for null")
    void equalsNull() {
        DynamicKnapsackItem a = new DynamicKnapsackItem(10, 50);
        assertNotEquals(null, a);
    }

    @Test
    @DisplayName("Equals true for same reference")
    void equalsReflexive() {
        DynamicKnapsackItem a = new DynamicKnapsackItem(10, 50);
        assertEquals(a, a);
    }

    @Test
    @DisplayName("Hash code consistent with equals")
    void hashCodeConsistent() {
        DynamicKnapsackItem a = new DynamicKnapsackItem(10, 50);
        DynamicKnapsackItem b = new DynamicKnapsackItem(99, 50);
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    @DisplayName("Hash code differs for different profits")
    void hashCodeDifferent() {
        DynamicKnapsackItem a = new DynamicKnapsackItem(10, 50);
        DynamicKnapsackItem b = new DynamicKnapsackItem(20, 100);
        assertNotEquals(a.hashCode(), b.hashCode());
    }

    @Test
    @DisplayName("ToString format")
    void testToString() {
        DynamicKnapsackItem item = new DynamicKnapsackItem(10, 100);
        assertEquals("[W: 10, P: 100]", item.toString());
    }

    @Test
    @DisplayName("SolveExercise5 executes without error")
    void solveExercise5() {
        KnapsackSolution sol = DynamicKnapsackItem.solveExercise5();
        assertNotNull(sol);
    }
}
