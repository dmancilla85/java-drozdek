package org.drozdek.searching.applications;

import org.drozdek.searching.InterpolationSearch;

/// Quickly probes a uniformly distributed catalog by interpolation search.
///
/// Product SKU and stock-keeping identifiers in a well-distributed catalog
/// tend to be spread fairly uniformly across the key space. Interpolation
/// search exploits that distribution by estimating the probe position from
/// the key values, giving better-than-logarithmic average behavior.
///
/// **Real-world use case:** Looking up a SKU in a price list, a ZIP code in
/// an address database, or a seat number in a uniformly populated seating map.
///
/// Complexity Analysis:
/// Time Complexity: O(log log n) average, O(n) worst case
/// Auxiliary Space: O(1)
///
/// Bibliography:
///
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 2.
///
/// @see InterpolationSearch
public final class UniformCatalogLookup {

    private UniformCatalogLookup() {
        // do nothing
    }

    /// Returns the price of a product for a sorted, uniformly distributed SKU list.
    ///
    /// @param sku    sorted array of SKU identifiers
    /// @param prices price of each SKU, parallel to the sku array
    /// @param target SKU to look up
    /// @return the matching price, or -1 if the SKU is absent
    public static int lookupPrice(int[] sku, int[] prices, int target) {
        int index = InterpolationSearch.interpolationSearch(sku, 0, sku.length - 1, target);
        return index == -1 ? -1 : prices[index];
    }
}
