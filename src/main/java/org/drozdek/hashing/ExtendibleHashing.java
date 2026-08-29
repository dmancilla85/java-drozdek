package org.drozdek.hashing;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/// Extendible hashing with directory doubling.
///
/// Keys are mapped to buckets via a global bit depth. When a bucket overflows
/// the directory is doubled (extending the hash by one low-order bit) and the
/// overflowing bucket is split, guaranteeing bounded lookup latency. This
/// dynamic hashing scheme is well suited to on-disk databases where resizing
/// cost is amortised over many inserts.
///
/// **Real-world use case:** Database indexing and file systems that need
/// predictable, bounded lookup latency under continuous insertion.
///
/// Complexity Analysis:
/// Time Complexity: O(1) expected per lookup; O(1) amortised per insert
/// Auxiliary Space: O(2^depth) for the directory plus bucket payloads
///
/// Bibliography:
///
/// - Extendible hashing. *Wikipedia*. https://en.wikipedia.org/wiki/Extendible_hashing
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 10.
public class ExtendibleHashing {

    private static final int DEFAULT_BUCKET_SIZE = 2;

    private int globalDepth;
    private final int bucketSize;
    private final List<Bucket> directory = new ArrayList<>();
    private final Set<Bucket> buckets = new HashSet<>();

    private static final class Bucket {
        private int localDepth;
        private final List<Integer> keys = new ArrayList<>();

        private Bucket(int localDepth) {
            this.localDepth = localDepth;
        }

        private boolean isFull(int maxSize) {
            return keys.size() >= maxSize;
        }
    }

    /// Creates an extendible hash table with the default bucket capacity of 2.
    public ExtendibleHashing() {
        this(DEFAULT_BUCKET_SIZE);
    }

    /// Creates an extendible hash table with the specified bucket capacity.
    ///
    /// @param bucketSize maximum number of keys per bucket
    public ExtendibleHashing(int bucketSize) {
        this.bucketSize = Math.max(bucketSize, 1);
        this.globalDepth = 0;
        Bucket initial = new Bucket(0);
        buckets.add(initial);
        directory.add(initial);
    }

    /// Inserts a non-negative integer key, splitting buckets and doubling the
    /// directory as needed.
    ///
    /// @param key key to insert
    public void insert(int key) {
        Bucket bucket = directory.get(hash(key));
        if (bucket.keys.contains(key)) {
            return;
        }
        if (!bucket.isFull(bucketSize)) {
            bucket.keys.add(key);
            return;
        }
        if (bucket.localDepth == globalDepth) {
            doubleDirectory();
        }
        split(bucket, key);
    }

    /// Returns `true` if the given key is present.
    ///
    /// @param key key to look up
    /// @return `true` if present
    public boolean contains(int key) {
        return directory.get(hash(key)).keys.contains(key);
    }

    /// Returns the current global depth of the directory.
    ///
    /// @return global depth
    public int getGlobalDepth() {
        return globalDepth;
    }

    /// Returns the number of distinct buckets currently in use.
    ///
    /// @return distinct bucket count
    public int getBucketCount() {
        return buckets.size();
    }

    private void doubleDirectory() {
        int size = directory.size();
        for (int i = 0; i < size; i++) {
            directory.add(directory.get(i));
        }
        globalDepth++;
    }

    private void split(Bucket bucket, int key) {
        bucket.localDepth++;
        Bucket sibling = new Bucket(bucket.localDepth);
        int mask = 1 << (bucket.localDepth - 1);
        List<Integer> retained = new ArrayList<>();
        for (int existing : bucket.keys) {
            if ((hash(existing) & mask) != 0) {
                sibling.keys.add(existing);
            } else {
                retained.add(existing);
            }
        }
        bucket.keys.clear();
        bucket.keys.addAll(retained);
        buckets.add(sibling);
        for (int i = 0; i < directory.size(); i++) {
            if (directory.get(i) == bucket && (i & mask) != 0) {
                directory.set(i, sibling);
            }
        }
        Bucket target = (hash(key) & mask) != 0 ? sibling : bucket;
        target.keys.add(key);
    }

    private int hash(int key) {
        return key & ((1 << globalDepth) - 1);
    }
}
