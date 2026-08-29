package org.drozdek.lists.applications;

import org.drozdek.lists.SparseTable;

/// Answers constant-time minimum stock-price queries over a static price series
/// using a sparse table.
///
/// The price history is preprocessed once into a sparse table so that the
/// lowest price over any inclusive date range is reported in O(1). Because
/// range-minimum is idempotent, two overlapping precomputed intervals are
/// sufficient.
///
/// **Real-world use case:** Trading dashboards, analytics on frozen time series,
/// and competitive risk reports over historical price ranges.
///
/// Complexity Analysis:
/// Time Complexity: O(n log n) build, O(1) per range query
/// Auxiliary Space: O(n log n)
///
/// Bibliography:
///
/// - Sparse table. *Wikipedia*. https://en.wikipedia.org/wiki/Sparse_table
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 3.
///
/// @see SparseTable
public class StockPriceRangeLookup {

    private final SparseTable table;

    /// Builds a range-minimum index over the given closing price series.
    ///
    /// @param closingPrices immutable daily closing prices
    public StockPriceRangeLookup(int[] closingPrices) {
        this.table = new SparseTable(closingPrices);
    }

    /// Returns the minimum closing price over the inclusive range.
    ///
    /// @param fromIndex start index (inclusive)
    /// @param toIndex   end index (inclusive)
    /// @return the lowest price in the range
    public int minPrice(int fromIndex, int toIndex) {
        return table.rangeMinimum(fromIndex, toIndex);
    }
}
