package org.drozdek.trees;

import org.drozdek.trees.interfaces.TreeInterface;

import java.util.ArrayList;
import java.util.List;

/// Maximum heap data structure implemented with an ArrayList. Maintains the max-heap property
/// where the parent node is always greater than or equal to its children.
///
/// **Real-world use case:** Job scheduling in operating systems where the highest-priority
/// task must be processed first.
///
/// Complexity Analysis:
/// Time Complexity: O(log n) for insert/extractMax, O(n) for heapify
/// Auxiliary Space: O(n) for storage
///
/// @see <a href="https://doi.org/10.1145/512274.3734138">Williams, 1964, Heapsort (ACM)</a>
public class MaximumHeap<T extends Comparable<T>> implements TreeInterface {

    private static final int DEFAULT_CAPACITY = 10;
    private List<T> heap;

    public MaximumHeap() {
        this(DEFAULT_CAPACITY);
    }

    /// Creates a heap backed by a list with the given initial capacity.
    ///
    /// @param capacity initial capacity of the backing list
    /// @throws IllegalArgumentException when capacity is not positive
    public MaximumHeap(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive");
        }
        this.heap = new ArrayList<>(capacity);
    }

    /// Adds an element and restores the max-heap property by sifting it up.
    ///
    /// @param element value to add
    /// @throws IllegalArgumentException when element is null
    public void insert(T element) {
        if (element == null) {
            throw new IllegalArgumentException("Element cannot be null");
        }
        heap.add(element);
        siftUp(heap.size() - 1);
    }

    /// Alias for insert(Object).
    ///
    /// @param key value to add
    /// @throws IllegalArgumentException when key is null
    public void insertKey(T key) {
        insert(key);
    }

    /// Removes and returns the largest element.
    ///
    /// The last element replaces the root and is sifted down. Runs in O(log n).
    ///
    /// @return previous maximum, or null when the heap is empty
    public T extractMax() {
        if (heap.isEmpty()) {
            return null;
        }

        if (heap.size() == 1) {
            return heap.remove(0);
        }

        T root = heap.get(0);
        T last = heap.remove(heap.size() - 1);
        heap.set(0, last);
        maxHeapify(0);

        return root;
    }

    /// Returns, without removing, the largest element.
    ///
    /// @return current maximum, or null when the heap is empty
    public T getMax() {
        if (heap.isEmpty()) {
            return null;
        }
        return heap.get(0);
    }

    /// Returns the number of levels of the subtree rooted at the given index.
    ///
    /// @param node index of the subtree root
    /// @return subtree height counted in nodes, where a leaf counts as 1
    public int height(int node) {
        if (!isLeaf(node))
            return 1 + height(leftChild(node));
        return 1;
    }

    /// Checks whether the given index refers to a leaf.
    ///
    /// @param node index to test
    /// @return true when the index is valid and has no children
    public boolean isLeaf(int node) {
        return node >= 0 && node < heap.size() && (node * 2 + 1) >= heap.size();
    }

    @Override
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    @Override
    public int size() {
        return heap.size();
    }

    public Object[] toArray() {
        return heap.toArray();
    }


    /// Removes the element stored at the given index.
    ///
    /// The last element takes the vacated slot and is sifted down; invalid indexes are ignored.
    /// Runs in O(log n).
    ///
    /// @param index position of the element to remove
    public void deleteKey(int index) {
        if (index < 0 || index >= heap.size())
            return;

        if (index == heap.size() - 1) {
            heap.remove(index);
            return;
        }

        T last = heap.remove(heap.size() - 1);
        heap.set(index, last);
        maxHeapify(index);
    }


    /// Replaces the value at the given index and repairs the heap in the needed direction.
    ///
    /// Invalid indexes are ignored.
    ///
    /// @param index    position whose value changes
    /// @param newValue replacement value
    public void changeValueOnAKey(int index, T newValue) {
        if (index < 0 || index >= heap.size())
            return;

        T oldValue = heap.get(index);
        int cmp = newValue.compareTo(oldValue);
        if (cmp == 0)
            return;
        if (cmp > 0) {
            increaseKey(index, newValue);
        } else {
            decreaseKey(index, newValue);
        }
    }

    /// Raises the value at the given index and sifts it up towards the root.
    ///
    /// No-op when the index is invalid or the new value is not greater. Runs in O(log n).
    ///
    /// @param index    position whose value increases
    /// @param newValue value strictly greater than the current one
    public void increaseKey(int index, T newValue) {
        if (index < 0 || index >= heap.size())
            return;

        if (newValue.compareTo(heap.get(index)) <= 0)
            return;

        heap.set(index, newValue);

        while (index != 0 && heap.get(index).compareTo(heap.get(parent(index))) > 0) {
            swap(index, parent(index));
            index = parent(index);
        }
    }

    /// Lowers the value at the given index and sifts it down towards the leaves.
    ///
    /// No-op when the index is invalid or the new value is not smaller. Runs in O(log n).
    ///
    /// @param index    position whose value decreases
    /// @param newValue value strictly smaller than the current one
    public void decreaseKey(int index, T newValue) {
        if (index < 0 || index >= heap.size())
            return;

        if (newValue.compareTo(heap.get(index)) >= 0)
            return;

        heap.set(index, newValue);
        maxHeapify(index);
    }

    private void maxHeapify(int index) {
        int left = 2 * index + 1;
        int right = 2 * index + 2;
        int largest = index;

        if (left < heap.size() && heap.get(left).compareTo(heap.get(largest)) > 0)
            largest = left;
        if (right < heap.size() && heap.get(right).compareTo(heap.get(largest)) > 0)
            largest = right;

        if (largest != index) {
            swap(index, largest);
            maxHeapify(largest);
        }
    }

    private void siftUp(int index) {
        while (index > 0 && heap.get(index).compareTo(heap.get(parent(index))) > 0) {
            swap(index, parent(index));
            index = parent(index);
        }
    }

    private static int parent(int node) {
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
