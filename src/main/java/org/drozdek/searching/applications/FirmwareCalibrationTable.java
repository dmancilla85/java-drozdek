package org.drozdek.searching.applications;

import org.drozdek.searching.FibonacciSearch;

/// Looks up calibration coefficients from a division-free fibonacci lookup table.
///
/// Many microcontrollers lack hardware divide instructions, making binary
/// search's repeated halving comparatively expensive. Fibonacci search locates
/// a target using only additions and subtractions, which is a better fit for
/// firmware-grade sensor calibration tables stored in ROM.
///
/// **Real-world use case:** Sensor calibration curves, embedded-system lookup
/// tables, and any memory-mapped table where division is costly.
///
/// Complexity Analysis:
/// Time Complexity: O(log n)
/// Auxiliary Space: O(1)
///
/// Bibliography:
///
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 2.
///
/// @see FibonacciSearch
public final class FirmwareCalibrationTable {

    private FirmwareCalibrationTable() {
        // do nothing
    }

    /// Returns the calibration coefficient for a given sensor reading.
    ///
    /// @param samples sorted array of sensor readings
    /// @param values  parallel array of calibration values
    /// @param reading sensor reading to look up
    /// @return the matching calibration value, or -1 if not found
    public static int calibrate(int[] samples, int[] values, int reading) {
        int index = FibonacciSearch.fibonacciSearch(samples, reading);
        return index == -1 ? -1 : values[index];
    }
}
