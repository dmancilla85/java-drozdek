package org.drozdek.trees;

public class MinimumHeap<T extends Comparable<T>> {

    private static final int DEFAULT_CAPACITY = 10;
    private Object[] heap;
    private int size;

    public MinimumHeap() {
        this(DEFAULT_CAPACITY);
    }

    public MinimumHeap(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive");
        }
        this.heap = new Object[capacity];
        this.size = 0;
    }

    public void insert(T element) {
        if (element == null) {
            throw new IllegalArgumentException("Element cannot be null");
        }
        if (size >= heap.length) {
            resize();
        }
        heap[size] = element;
        siftUp(size);
        size++;
    }

    public boolean insertKey(T key) {
        insert(key);
        return true;
    }

    @SuppressWarnings("unchecked")
    public T extractMin() {
        if (size <= 0) {
            return null;
        }

        if (size == 1) {
            size--;
            return (T) heap[0];
        }

        T root = (T) heap[0];
        heap[0] = heap[size - 1];
        size--;
        minHeapify(0);

        return root;
    }

    @SuppressWarnings("unchecked")
    public T getMin() {
        if (size <= 0) {
            return null;
        }
        return (T) heap[0];
    }

    public int height(int node) {
        if (!isLeaf(node))
            return 1 + height(leftChild(node));
        return 1;
    }

    public boolean isLeaf(int node) {
        if (node > size - 1)
            return false;

        if (node * 2 + 1 > size - 1 || node < 0)
            return true;

        return heap[node * 2 + 1] == null && heap[node * 2 + 2] == null;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public Object[] toArray() {
        Object[] copy = new Object[heap.length];
        System.arraycopy(heap, 0, copy, 0, heap.length);
        return copy;
    }

    @SuppressWarnings("unchecked")
    public void deleteKey(int index) {
        if (index < 0 || index >= size)
            return;

        if (index == size - 1) {
            size--;
            return;
        }

        heap[index] = heap[size - 1];
        size--;
        minHeapify(index);
    }

    @SuppressWarnings("unchecked")
    public void changeValueOnAKey(int index, T newValue) {
        if (index < 0 || index >= size)
            return;

        T oldValue = (T) heap[index];
        int cmp = newValue.compareTo(oldValue);
        if (cmp == 0)
            return;
        if (cmp < 0) {
            decreaseKey(index, newValue);
        } else {
            increaseKey(index, newValue);
        }
    }

    @SuppressWarnings("unchecked")
    public void decreaseKey(int index, T newValue) {
        if (index < 0 || index >= size)
            return;

        if (newValue.compareTo((T) heap[index]) >= 0)
            return;

        heap[index] = newValue;

        while (index != 0 && ((T) heap[index]).compareTo((T) heap[parent(index)]) < 0) {
            swap(index, parent(index));
            index = parent(index);
        }
    }

    @SuppressWarnings("unchecked")
    public void increaseKey(int index, T newValue) {
        if (index < 0 || index >= size)
            return;

        if (newValue.compareTo((T) heap[index]) <= 0)
            return;

        heap[index] = newValue;
        minHeapify(index);
    }

    @SuppressWarnings("unchecked")
    private void minHeapify(int index) {
        int left = 2 * index + 1;
        int right = 2 * index + 2;
        int smallest = index;

        if (left < size && ((T) heap[left]).compareTo((T) heap[smallest]) < 0)
            smallest = left;
        if (right < size && ((T) heap[right]).compareTo((T) heap[smallest]) < 0)
            smallest = right;

        if (smallest != index) {
            swap(index, smallest);
            minHeapify(smallest);
        }
    }

    @SuppressWarnings("unchecked")
    private void siftUp(int index) {
        while (index > 0 && ((T) heap[index]).compareTo((T) heap[parent(index)]) < 0) {
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
        if (0 <= node && node < (heap.length - 1) / 2 && !isLeaf(node))
            return node * 2 + 1;
        return -1;
    }


    private void swap(int i, int j) {
        Object temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    private void resize() {
        Object[] newHeap = new Object[heap.length * 2];
        System.arraycopy(heap, 0, newHeap, 0, heap.length);
        heap = newHeap;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(heap[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
