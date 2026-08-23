package org.drozdek.trees.nodes;

/// Node for static binary tree. Wraps an Integer value and implements Comparable for heap operations.
///
/// Complexity Analysis:
/// Time Complexity: O(1)
/// Auxiliary Space: O(1)
///
/// Source: [Geeks for Geeks](https://www.geeksforgeeks.org/heap-data-structure/)
public class HeapNode implements Comparable<HeapNode> {

    private Integer value;

    public HeapNode() {
        this(0);
    }

    /// Creates a node wrapping the given value.
    ///
    /// @param value integer stored in the node
    public HeapNode(Integer value) {
        this.value = value;
    }

    public int compareTo(HeapNode other) {
        return this.value.compareTo(other.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof HeapNode other)) return false;
        return java.util.Objects.equals(value, other.value);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(value);
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String toString() {
        return value.toString();
    }
}
