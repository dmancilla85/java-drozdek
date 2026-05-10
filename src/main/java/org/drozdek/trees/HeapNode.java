package org.drozdek.trees;

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
