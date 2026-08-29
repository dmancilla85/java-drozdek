package org.drozdek.hashing;

import java.util.LinkedList;

import org.drozdek.commons.DataTypeInterface;

/// Hash table with separate chaining. Uses Java's [LinkedList] for each
/// bucket to handle collisions.
///
/// **Real-world use case:** Symbol tables in compilers, database
/// indexing, in-memory caches, and associative arrays in scripting languages.
///
/// Complexity Analysis:
/// Time Complexity: O(1) average for put/get/remove; O(n) worst-case
/// Auxiliary Space: O(n + m) where n is entries and m is capacity
///
/// @param <K> Key type (must implement [Object#hashCode()] and
///            [Object#equals(Object)])
/// @param <V> Value type
///
/// Bibliography:
///
/// - Hash table. *Wikipedia*. https://en.wikipedia.org/wiki/Hash_table
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 10.
public class HashTable<K, V> implements DataTypeInterface {
    private static final int DEFAULT_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;

    private LinkedList<Entry<K, V>>[] buckets;
    private int size;

    private static class Entry<K, V> {
        K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    /// Creates a hash table with the default capacity (16).
    public HashTable() {
        this(DEFAULT_CAPACITY);
    }

    /// Creates a hash table with the specified number of buckets.
    ///
    /// @param capacity initial number of buckets; values below 1 are raised to 1
    @SuppressWarnings("unchecked")
    public HashTable(int capacity) {
        buckets = new LinkedList[Math.max(capacity, 1)];
        size = 0;
    }

    private int hash(K key) {
        return (key == null) ? 0 : Math.abs(key.hashCode() % buckets.length);
    }

    /// Associates the specified value with the specified key.
    /// If the key already exists, the old value is replaced.
    ///
    /// @param key   Key with which the value is to be associated
    /// @param value Value to associate
    public void put(K key, V value) {
        int index = hash(key);
        if (buckets[index] == null) {
            buckets[index] = new LinkedList<>();
        }

        for (Entry<K, V> entry : buckets[index]) {
            if (entry.key != null && entry.key.equals(key)
                    || entry.key == null && key == null) {
                entry.value = value;
                return;
            }
        }

        buckets[index].add(new Entry<>(key, value));
        size++;

        if ((double) size / buckets.length > LOAD_FACTOR) {
            resize();
        }
    }

    /// Returns the value for the given key, or `null` if not found.
    ///
    /// @param key Key to look up
    /// @return Associated value, or `null`
    public V get(K key) {
        int index = hash(key);
        if (buckets[index] == null) {
            return null;
        }

        for (Entry<K, V> entry : buckets[index]) {
            if (entry.key != null && entry.key.equals(key)
                    || entry.key == null && key == null) {
                return entry.value;
            }
        }
        return null;
    }

    /// Removes the mapping for the specified key, if present.
    ///
    /// @param key Key whose mapping to remove
    /// @return The previous value, or `null` if none
    public V remove(K key) {
        int index = hash(key);
        if (buckets[index] == null) {
            return null;
        }

        Entry<K, V> toRemove = null;
        for (Entry<K, V> entry : buckets[index]) {
            if (entry.key != null && entry.key.equals(key)
                    || entry.key == null && key == null) {
                toRemove = entry;
                break;
            }
        }

        if (toRemove != null) {
            buckets[index].remove(toRemove);
            size--;
            return toRemove.value;
        }
        return null;
    }

    /// Returns `true` if the table contains the given key.
    ///
    /// @param key Key to test
    /// @return `true` if present
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    /// Returns the number of key-value mappings.
    ///
    /// @return Entry count
    public int size() {
        return size;
    }

    /// Returns `true` if the table is empty.
    ///
    /// @return `true` if empty
    public boolean isEmpty() {
        return size == 0;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        LinkedList<Entry<K, V>>[] oldBuckets = buckets;
        buckets = new LinkedList[oldBuckets.length * 2];
        size = 0;

        for (LinkedList<Entry<K, V>> bucket : oldBuckets) {
            if (bucket != null) {
                for (Entry<K, V> entry : bucket) {
                    put(entry.key, entry.value);
                }
            }
        }
    }

    /// Renders the table as one line per non-empty bucket, in the form
    /// `[index] -> key=value, key=value`. An empty table renders as
    /// `(empty table)`.
    @Override
    public String toString() {
        if (size == 0) {
            return "(empty table)";
        }

        StringBuilder sb = new StringBuilder();
        boolean firstBucket = true;

        for (int i = 0; i < buckets.length; i++) {
            LinkedList<Entry<K, V>> bucket = buckets[i];
            if (bucket == null || bucket.isEmpty()) {
                continue;
            }

            if (!firstBucket) {
                sb.append(System.lineSeparator());
            }
            firstBucket = false;

            sb.append('[').append(i).append("] \u2794 ");
            for (int j = 0; j < bucket.size(); j++) {
                Entry<K, V> entry = bucket.get(j);
                sb.append(entry.key).append('=').append(entry.value);
                if (j < bucket.size() - 1) {
                    sb.append(", ");
                }
            }
        }
        return sb.toString();
    }
}
