package org.drozdek.hashing.applications;

import org.drozdek.hashing.ExtendibleHashing;

/// Virtual disk page index backed by extendible hashing.
///
/// Allocated disk page numbers are tracked in an extendible hash index whose
/// directory doubles lazily as buckets fill, keeping membership probes near
/// constant time even as the volume of allocated pages grows.
///
/// **Real-world use case:** On-disk page allocation maps, distributed storage
/// directories, and hash indices that must grow gracefully under load.
///
/// Complexity Analysis:
/// Time Complexity: O(1) amortized insert and lookup
/// Auxiliary Space: O(n) for buckets plus the directory
///
/// Bibliography:
///
/// - Extendible hashing. *Wikipedia*. https://en.wikipedia.org/wiki/Extendible_hashing
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 10.
///
/// @see ExtendibleHashing
public class VirtualDiskHashIndex {

    private final ExtendibleHashing directory;

    /// Creates an index with default bucket capacity.
    public VirtualDiskHashIndex() {
        this.directory = new ExtendibleHashing();
    }

    /// Creates an index with the given bucket capacity.
    ///
    /// @param bucketSize per-bucket entry capacity
    /// @throws IllegalArgumentException if bucketSize is not positive
    public VirtualDiskHashIndex(int bucketSize) {
        this.directory = new ExtendibleHashing(bucketSize);
    }

    /// Marks a disk page as allocated in the index.
    ///
    /// @param pageId the non-negative page identifier
    public void allocatePage(int pageId) {
        directory.insert(pageId);
    }

    /// Checks whether a disk page is allocated.
    ///
    /// @param pageId the page identifier to probe
    /// @return true if the page is present in the index
    public boolean isAllocated(int pageId) {
        return directory.contains(pageId);
    }

    /// Returns the current global depth of the directory.
    ///
    /// @return global depth of the extendible hash directory
    public int globalDepth() {
        return directory.getGlobalDepth();
    }

    /// Returns the number of buckets in the index.
    ///
    /// @return bucket count
    public int bucketCount() {
        return directory.getBucketCount();
    }
}
