package org.drozdek.trees;

import org.drozdek.trees.interfaces.TreeInterface;

import java.util.ArrayList;
import java.util.List;

public class MinimumHeap<T extends Comparable<T>> implements TreeInterface {

    private static final int DEFAULT_CAPACITY = 10;
    private List<T> heap;

    public MinimumHeap() {
        this(DEFAULT_CAPACITY);
    }

    public MinimumHeap(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive");
        }
        this.heap = new ArrayList<>(capacity);
    }

    public void insert(T element) {
        if (element == null) {
            throw new IllegalArgumentException("Element cannot be null");
        }
        heap.add(element);
        siftUp(heap.size() - 1);
    }

    public boolean insertKey(T key) {
        insert(key);
        return true;
    }

    public T extractMin() {
        if (heap.isEmpty()) {
            return null;
        }

        if (heap.size() == 1) {
            return heap.remove(0);
        }

        T root = heap.get(0);
        T last = heap.remove(heap.size() - 1);
        heap.set(0, last);
        minHeapify(0);

        return root;
    }

    public T getMin() {
        if (heap.isEmpty()) {
            return null;
        }
        return heap.get(0);
    }

    public int height(int node) {
        if (!isLeaf(node))
            return 1 + height(leftChild(node));
        return 1;
    }

    public boolean isLeaf(int node) {
        return node >= 0 && node < heap.size() && (node * 2 + 1) >= heap.size();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public int size() {
        return heap.size();
    }

    public Object[] toArray() {
        return heap.toArray();
    }

    public void deleteKey(int index) {
        if (index < 0 || index >= heap.size())
            return;

        if (index == heap.size() - 1) {
            heap.remove(index);
            return;
        }

        T last = heap.remove(heap.size() - 1);
        heap.set(index, last);
        minHeapify(index);
    }

    public void changeValueOnAKey(int index, T newValue) {
        if (index < 0 || index >= heap.size())
            return;

        T oldValue = heap.get(index);
        int cmp = newValue.compareTo(oldValue);
        if (cmp == 0)
            return;
        if (cmp < 0) {
            decreaseKey(index, newValue);
        } else {
            increaseKey(index, newValue);
        }
    }

     public void decreaseKey(int index, T newValue) {
        if (index < 0 || index >= heap.size())
            return;

        if (newValue.compareTo(heap.get(index)) >= 0)
            return;

        heap.set(index, newValue);

        while (index != 0 && heap.get(index).compareTo(heap.get(parent(index))) < 0) {
            swap(index, parent(index));
            index = parent(index);
        }
    }

    public void increaseKey(int index, T newValue) {
        if (index < 0 || index >= heap.size())
            return;

        if (newValue.compareTo(heap.get(index)) <= 0)
            return;

        heap.set(index, newValue);
        minHeapify(index);
    }

    private void minHeapify(int index) {
        int left = 2 * index + 1;
        int right = 2 * index + 2;
        int smallest = index;

        if (left < heap.size() && heap.get(left).compareTo(heap.get(smallest)) < 0)
            smallest = left;
        if (right < heap.size() && heap.get(right).compareTo(heap.get(smallest)) < 0)
            smallest = right;

        if (smallest != index) {
            swap(index, smallest);
            minHeapify(smallest);
        }
    }

    private void siftUp(int index) {
        while (index > 0 && heap.get(index).compareTo(heap.get(parent(index))) < 0) {
            swap(index, parent(index));
            index = parent(index);
        }
    }

    private int parent(int node) {
        if (node <= 0)
            return node;
        return (node - 1) / 2;
    }

    private int leftChild(int node) {
        if (!isLeaf(node))
            return node * 2 + 1;
        return -1;
    }

    private void swap(int i, int j) {
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < heap.size(); i++) {
            sb.append(heap.get(i));
            if (i < heap.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
