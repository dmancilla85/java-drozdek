package org.drozdek.trees;

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

    public HeapNode(Integer value) {
        this.value = value;
    }

    public HeapNode clone() {
        return new HeapNode(this.value);
    }

    public int compareTo(HeapNode other) {
        return this.value.compareTo(other.value);
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
