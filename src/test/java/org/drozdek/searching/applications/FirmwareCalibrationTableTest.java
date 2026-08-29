package org.drozdek.searching.applications;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FirmwareCalibrationTableTest {

    @Test
    @DisplayName("Returns the calibration value for a reading")
    void calibrate_found() {
        int[] samples = {0, 20, 40, 60, 80, 100};
        int[] values = {10, 12, 15, 19, 24, 30};
        assertEquals(19, FirmwareCalibrationTable.calibrate(samples, values, 60));
    }

    @Test
    @DisplayName("Returns -1 when the reading is absent")
    void calibrate_absent() {
        int[] samples = {1, 2, 3};
        int[] values = {9, 9, 9};
        assertEquals(-1, FirmwareCalibrationTable.calibrate(samples, values, 5));
    }
}
