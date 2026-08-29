package org.drozdek.recursion.applications;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HanoiBackupRotationTest {

    @Test
    @DisplayName("Three tapes require seven rotations")
    void performCycle_three() {
        assertEquals(7, HanoiBackupRotation.performCycle(3));
    }

    @Test
    @DisplayName("Four tapes require fifteen rotations")
    void performCycle_four() {
        assertEquals(15, HanoiBackupRotation.performCycle(4));
    }

    @Test
    @DisplayName("Closed-form cycle length matches two-to-the-n minus one")
    void cycleLength() {
        assertEquals(1023, HanoiBackupRotation.cycleLength(10));
    }
}
