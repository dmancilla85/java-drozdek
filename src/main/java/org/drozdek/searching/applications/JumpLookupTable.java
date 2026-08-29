package org.drozdek.searching.applications;

import org.drozdek.searching.JumpSearch;
import org.drozdek.searching.LinearSearch;

/// Looks up a customer by phone number in a block-indexed directory.
///
/// A sorted phone directory is scanned in √n blocks: jump search locates the
/// block that could contain the number, then a linear scan finishes the search
/// within that block. This mirrors tape-drive lookups where forward movement is
/// cheap but rewinding is not.
///
/// **Real-world use case:** Browsing a sorted printed directory, tape-based
/// archival lookup, and phone-number resolution over sorted record batches.
///
/// Complexity Analysis:
/// Time Complexity: O(√n)
/// Auxiliary Space: O(1)
///
/// Bibliography:
///
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 2.
///
/// @see JumpSearch
/// @see LinearSearch
public final class JumpLookupTable {

    private JumpLookupTable() {
        // do nothing
    }

    /// Finds the name associated with a phone number in a sorted list.
    ///
    /// @param phone sorted array of phone numbers
    /// @param names parallel array of subscriber names
    /// @param target phone number to find
    /// @return the matching name, or null if absent
    public static String findSubscriber(int[] phone, String[] names, int target) {
        int index = JumpSearch.jumpSearch(phone, target);
        return index == -1 ? null : names[index];
    }

    /// Finds a phone number using a plain linear scan over potentially unsorted data.
    ///
    /// @param phone array of phone numbers
    /// @param target phone number to find
    /// @return index of the number, or -1 if absent
    public static int scanFor(int[] phone, int target) {
        return LinearSearch.linearSearch(phone, target);
    }
}
