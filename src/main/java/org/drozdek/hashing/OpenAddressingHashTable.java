package org.drozdek.hashing;

import java.util.Arrays;

/// Hash table that resolves collisions using open addressing with linear
/// probing.
///
/// Instead of chaining, every entry is stored directly in the underlying
/// array. When a collision occurs the probe sequence advances to the next
/// slot until a free cell is found. Lookup follows the same probe path.
/// Deletions use tombstones so that probe chains are not broken.
///
/// **Real-world use case:** High-performance symbol tables and string
/// interning where a flat, cache-friendly layout is preferred over linked
/// buckets.
///
/// Complexity Analysis:
/// Time Complexity: O(1) average for put/get/remove
/// Auxiliary Space: O(n + m) for table plus tombstones
///
/// Bibliography:
///
/// - Open addressing. *Wikipedia*. https://en.wikipedia.org/wiki/Open_addressing
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 10.
public class OpenAddressingHashTable<K, V> {

    private static final int DEFAULT_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.7;
    private static final Object TOMBSTONE = new Object();

    private final boolean hasResize;
    private Object[] keys;
    private Object[] values;
    private int size;

    /// Creates a hash table with the default capacity.
    public OpenAddressingHashTable() {
        this(DEFAULT_CAPACITY);
    }

    /// Creates a hash table with the specified capacity.
    ///
    /// @param initialCapacity initial number of slots
    public OpenAddressingHashTable(int initialCapacity) {
        hasResize = true;
        int capacity = Math.max(initialCapacity, 1);
        keys = new Object[capacity];
        values = new Object[capacity];
        size = 0;
    }

    private int hash(K key) {
        return (key == null) ? 0 : Math.floorMod(key.hashCode(), keys.length);
    }

    /// Stores a value under the given key, replacing any existing value.
    ///
    /// @param key   key
    /// @param value value to associate
    public void put(K key, V value) {
        int index = hash(key);
        int tombstoneIndex = -1;
        while (keys[index] != null) {
            if (keys[index] == TOMBSTONE) {
                if (tombstoneIndex < 0) {
                    tombstoneIndex = index;
                }
            } else if (keys[index].equals(key)) {
                values[index] = value;
                return;
            }
            index = (index + 1) % keys.length;
        }
        int target = tombstoneIndex >= 0 ? tombstoneIndex : index;
        keys[target] = key;
        values[target] = value;
        size++;
        if (hasResize && (double) size / keys.length > LOAD_FACTOR) {
            resize();
        }
    }

    /// Returns the value for the given key, or `null` if absent.
    ///
    /// @param key key to look up
    /// @return associated value or `null`
    @SuppressWarnings("unchecked")
    public V get(K key) {
        int index = hash(key);
        while (keys[index] != null) {
            if (keys[index] != TOMBSTONE && keys[index].equals(key)) {
                return (V) values[index];
            }
            index = (index + 1) % keys.length;
        }
        return null;
    }

    /// Removes the entry for the given key, if present.
    ///
    /// @param key key whose mapping to remove
    /// @return the previous value or `null`
    @SuppressWarnings("unchecked")
    public V remove(K key) {
        int index = hash(key);
        while (keys[index] != null) {
            if (keys[index] != TOMBSTONE && keys[index].equals(key)) {
                V value = (V) values[index];
                keys[index] = TOMBSTONE;
                values[index] = null;
                size--;
                return value;
            }
            index = (index + 1) % keys.length;
        }
        return null;
    }

    /// Returns the number of live entries.
    ///
    /// @return entry count
    public int size() {
        return size;
    }

    /// Returns `true` if the table is empty.
    ///
    /// @return `true` if empty
    public boolean isEmpty() {
        return size == 0;
    }

    /// Returns `true` if the given key is present.
    ///
    /// @param key key to test
    /// @return `true` if present
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    private void resize() {
        Object[] oldKeys = keys;
        Object[] oldValues = values;
        keys = new Object[oldKeys.length * 2];
        values = new Object[oldKeys.length * 2];
        size = 0;
        for (int i = 0; i < oldKeys.length; i++) {
            if (oldKeys[i] != null && oldKeys[i] != TOMBSTONE) {
                put((K) oldKeys[i], (V) oldValues[i]);
            }
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(values);
    }
}
