package org.drozdek.lists.applications;

import java.util.List;
import org.drozdek.lists.SelfOrganizingList;

/// Least-recently-used cache backed by a self-organizing (move-to-front) list.
///
/// Every {@code get} promotes the touched entry to the front of the
/// self-organizing list, so the most frequently requested keys are found first.
/// When the cache reaches its capacity, the entry at the tail — the least
/// recently used — is evicted.
///
/// **Real-world use case:** Browser caches, in-memory memoization, and
/// address-translation or database page caches.
///
/// Complexity Analysis:
/// Time Complexity: O(n) worst case for get and put
/// Auxiliary Space: O(capacity) for the stored entries
///
/// Bibliography:
///
/// - Self-organizing list. *Wikipedia*. https://en.wikipedia.org/wiki/Self-organizing_list
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 3.
///
/// @see SelfOrganizingList
public class LruCache<K, V> {

    private final SelfOrganizingList<Entry<K, V>> entries;
    private final int capacity;

    /// Creates a new LRU cache holding at most {@code capacity} entries.
    ///
    /// @param capacity maximum number of cached entries
    public LruCache(int capacity) {
        this.entries = new SelfOrganizingList<>();
        this.capacity = capacity;
    }

    /// Reads a cached value, promoting its key to the front of the list.
    ///
    /// @param key the lookup key
    /// @return the cached value, or null if absent
    public V get(K key) {
        for (Entry<K, V> entry : entries.snapshot()) {
            if (entry.key.equals(key)) {
                entries.access(entry);
                return entry.value;
            }
        }
        return null;
    }

    /// Stores or replaces a value, evicting the least recently used entry when full.
    ///
    /// @param key   the lookup key
    /// @param value the value to cache
    public void put(K key, V value) {
        for (Entry<K, V> entry : entries.snapshot()) {
            if (entry.key.equals(key)) {
                entries.remove(entry);
                break;
            }
        }
        List<Entry<K, V>> snapshot = entries.snapshot();
        if (snapshot.size() >= capacity && !snapshot.isEmpty()) {
            entries.remove(snapshot.get(snapshot.size() - 1));
        }
        entries.insert(new Entry<>(key, value));
    }

    /// Returns the number of entries currently cached.
    ///
    /// @return cache size
    public int size() {
        return entries.size();
    }

    private static final class Entry<K, V> {
        private final K key;
        private final V value;

        private Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
