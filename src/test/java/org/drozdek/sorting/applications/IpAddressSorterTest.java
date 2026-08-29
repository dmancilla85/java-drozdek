package org.drozdek.sorting.applications;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class IpAddressSorterTest {

    @Test
    @DisplayName("Sorts addresses into ascending numeric order")
    void sortAddresses_ordersNumeric() {
        List<String> sorted = IpAddressSorter.sortAddresses(
            List.of("10.2.0.1", "10.1.255.254", "1.1.1.1"));
        assertEquals(List.of("1.1.1.1", "10.1.255.254", "10.2.0.1"), sorted);
    }

    @Test
    @DisplayName("Preserves the single-element case")
    void sortAddresses_single() {
        assertEquals(List.of("5.5.5.5"), IpAddressSorter.sortAddresses(List.of("5.5.5.5")));
    }
}
