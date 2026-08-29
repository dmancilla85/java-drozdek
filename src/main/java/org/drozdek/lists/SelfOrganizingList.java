package org.drozdek.lists;

import java.util.ArrayList;
import java.util.List;

/// Self-organizing list that moves each accessed element to the front.
///
/// Elements are stored in a list ordered by recency of access: whenever a
/// value is looked up, it is moved to the head so that frequently accessed
/// items are found faster in subsequent searches. This "move-to-front"
/// heuristic adapts to access patterns with no additional data.
///
/// **Real-world use case:** Caching frequently requested records, such as
/// recently opened files, DNS entries, or MRU caches.
///
/// Complexity Analysis:
/// Time Complexity: O(n) worst case for a search-then-promote operation
/// Auxiliary Space: O(n) for the list storage
///
/// Bibliography:
///
/// - Self-organizing list. *Wikipedia*. https://en.wikipedia.org/wiki/Self-organizing_list
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 3.
public class SelfOrganizingList<T> {

    private final List<T> elements = new ArrayList<>();

    /// Inserts a value at the front of the list.
    ///
    /// @param value value to insert
    public void insert(T value) {
        elements.add(0, value);
    }

    /// Looks up a value, moving it to the front when present.
    ///
    /// @param value value to find
    /// @return {@code true} if the value was found and promoted
    public boolean access(T value) {
        int index = elements.indexOf(value);
        if (index < 0) {
            return false;
        }
        if (index != 0) {
            elements.remove(index);
            elements.add(0, value);
        }
        return true;
    }

    /// Removes a value from the list.
    ///
    /// @param value value to remove
    /// @return {@code true} if the value was removed
    public boolean remove(T value) {
        return elements.remove(value);
    }

    /// Returns the current number of entries in the list.
    ///
    /// @return list size
    public int size() {
        return elements.size();
    }

    /// Returns a snapshot of the list in current access order.
    ///
    /// @return elements from head to tail
    public List<T> snapshot() {
        return new ArrayList<>(elements);
    }
}
