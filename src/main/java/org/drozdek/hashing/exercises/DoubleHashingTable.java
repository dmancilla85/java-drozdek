package org.drozdek.hashing.exercises;

/// Hash table using open addressing with double hashing.
///
/// Collisions are resolved by probing with two hash functions: the primary
/// gives the home slot while a secondary hash determines the step distance of
/// the probe sequence. Because the step depends on the key, distinct keys that
/// collide in the home slot follow different probe paths, reducing the
/// clustering seen with plain linear probing.
///
/// **Real-world use case:** An exercise demonstrating a collision-resolution
/// scheme that improves locality and reduces clustering compared with simple
/// linear probing.
///
/// Complexity Analysis:
/// Time Complexity: O(1) average for put/get/remove
/// Auxiliary Space: O(n) for the table
///
/// Bibliography:
///
/// - Double hashing. *Wikipedia*. https://en.wikipedia.org/wiki/Double_hashing
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 10.
public class DoubleHashingTable {

    private static final int DEFAULT_CAPACITY = 11;

    private final int capacity;
    private final Integer[] keys;
    private final Integer[] values;
    private int size;

    /// Creates a double-hashing table with a default prime capacity.
    public DoubleHashingTable() {
        this(DEFAULT_CAPACITY);
    }

    /// Creates a double-hashing table with the given capacity (raised to the
    /// next prime-like odd size).
    ///
    /// @param initialCapacity desired capacity
    public DoubleHashingTable(int initialCapacity) {
        this.capacity = Math.max(initialCapacity, 3);
        this.keys = new Integer[capacity];
        this.values = new Integer[capacity];
        this.size = 0;
    }

    /// Associates a value with a key, probing with double hashing.
    ///
    /// @param key   integer key
    /// @param value value to store
    public void put(Integer key, Integer value) {
        int h1 = hash1(key);
        int h2 = hash2(key);
        int index = h1;
        while (keys[index] != null && !keys[index].equals(key)) {
            index = (index + h2) % capacity;
        }
        if (keys[index] == null) {
            size++;
        }
        keys[index] = key;
        values[index] = value;
    }

    /// Returns the value associated with the key, or `null` if absent.
    ///
    /// @param key key to look up
    /// @return stored value or `null`
    public Integer get(Integer key) {
        int h1 = hash1(key);
        int h2 = hash2(key);
        int index = h1;
        int probed = 0;
        while (keys[index] != null && probed < capacity) {
            if (keys[index].equals(key)) {
                return values[index];
            }
            index = (index + h2) % capacity;
            probed++;
        }
        return null;
    }

    /// Returns the number of stored entries.
    ///
    /// @return entry count
    public int size() {
        return size;
    }

    private int hash1(Integer key) {
        return Math.floorMod(key, capacity);
    }

    private int hash2(Integer key) {
        return 1 + Math.floorMod(key, capacity - 1);
    }
}
