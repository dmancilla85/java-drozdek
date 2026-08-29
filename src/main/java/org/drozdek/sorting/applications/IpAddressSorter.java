package org.drozdek.sorting.applications;

import java.util.ArrayList;
import java.util.List;
import org.drozdek.sorting.RadixSort;

/// Sorts IPv4 addresses numerically using radix sort.
///
/// Each dotted-quad address is converted to a non-negative integer key and
/// ordered by radix sort, so addresses are grouped by network prefix and finally
/// by host with no comparison-based overhead. The sorted keys are converted back
/// to dotted-quad strings for the result.
///
/// Because radix sort operates on non-negative integer keys, this indexer is
/// limited to addresses whose first octet is at most 127; leading-edge octets in
/// 128..255 are not representable as non-negative keys.
///
/// **Real-world use case:** IP geolocation and access logs that need addresses
/// presented in ascending order for range scans and whitelist generation.
///
/// Complexity Analysis:
/// Time Complexity: O(d * n) where d is the number of key digits
/// Auxiliary Space: O(n + k) for the counting buckets
///
/// Bibliography:
///
/// - Radix sort. *Wikipedia*. https://en.wikipedia.org/wiki/Radix_sort
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 11.
///
/// @see RadixSort
public final class IpAddressSorter {

    private IpAddressSorter() {
        // do nothing
    }

    /// Sorts addresses into ascending numeric order.
    ///
    /// @param addresses dotted-quad IPv4 strings, first octet at most 127
    /// @return the addresses in ascending order
    public static List<String> sortAddresses(List<String> addresses) {
        int[] keys = new int[addresses.size()];
        for (int i = 0; i < addresses.size(); i++) {
            keys[i] = pack(addresses.get(i));
        }
        RadixSort.radixSort(keys);
        List<String> result = new ArrayList<>(keys.length);
        for (int key : keys) {
            result.add(unpack(key));
        }
        return result;
    }

    private static int pack(String address) {
        String[] parts = address.split("\\.");
        int value = 0;
        for (String part : parts) {
            value = value * 256 + Integer.parseInt(part);
        }
        return value;
    }

    private static String unpack(int key) {
        int a = (key >> 24) & 0xFF;
        int b = (key >> 16) & 0xFF;
        int c = (key >> 8) & 0xFF;
        int d = key & 0xFF;
        return a + "." + b + "." + c + "." + d;
    }
}
