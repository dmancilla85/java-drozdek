package org.drozdek.trees;

/**
 * A Binary Heap is a Binary Tree with following properties:
 * <p>
 * 1) It’s a complete tree (All levels are completely filled except possibly the last level and the last level has
 * all keys as left as possible). This property of Binary Heap makes them suitable to be stored in an array.
 * <p>
 * 2)In a Min Binary Heap, the key at root must be minimum among all keys present in Binary Heap.
 * <p>
 * Source: <a href="https://www.geeksforgeeks.org/binary-heap/">Geeks for Geeks</a>
 */
public class MinimumHeap {
    // To store array of elements in heap
    protected int[] heapArray;

    // max size of the heap
    protected int capacity;

    // Current number of elements in the heap
    protected int currentHeapSize;

    // Constructor
    public MinimumHeap(int n) {
        capacity = n;
        heapArray = new int[capacity];
        currentHeapSize = 0;
    }

    // Driver code
    public static void main() {
        MinimumHeap h = new MinimumHeap(11);
        h.insertKey(3);
        h.insertKey(2);
        h.deleteKey(1);
        h.insertKey(15);
        h.insertKey(5);
        h.insertKey(4);
        h.insertKey(45);

        //Console.Write(h.extractMin() + " ")

        //Console.Write(h.getMin() + " ")

        h.decreaseKey(2, 1);
        //Console.Write(h.getMin())
    }

    // Changes value on a key
    public void changeValueOnAKey(int key, int newValue) {
        if (heapArray[key] == newValue) {
            return;
        }
        if (heapArray[key] < newValue) {
            increaseKey(key, newValue);
        } else {
            decreaseKey(key, newValue);
        }
    }

    // Decreases value of given key to new_val. It is assumed that new_val is smaller than heapArray[key].
    public void decreaseKey(int key, int newValue) {
        heapArray[key] = newValue;

        while (key != 0 && heapArray[key] <
                heapArray[parent(key)]) {
            int temp = heapArray[key];
            heapArray[key] = heapArray[parent(key)];
            heapArray[parent(key)] = temp;
            key = parent(key);
        }
    }

    // This function deletes key at the given index. It first reduced value to minus infinite, then calls extractMin()
    public void deleteKey(int key) {
        decreaseKey(key, Integer.MIN_VALUE);
        extractMin();
    }

    // Method to remove minimum element
    // (or root) from min heap
    public int extractMin() {
        if (currentHeapSize <= 0) {
            return Integer.MAX_VALUE;
        }

        if (currentHeapSize == 1) {
            currentHeapSize--;
            return heapArray[0];
        }

        // Store the minimum value,
        // and remove it from heap
        int root = heapArray[0];

        heapArray[0] = heapArray[currentHeapSize - 1];
        currentHeapSize--;
        minHeapify(0);

        return root;
    }

    // Returns the minimum key (key at root) from min heap
    public int getMin() {
        return heapArray[0];
    }

    // Increases value of given key to new_val. It is assumed that new_val is greater than heapArray[key].
    // Heapify from the given key
    public void increaseKey(int key, int newValue) {
        heapArray[key] = newValue;
        minHeapify(key);
    }

    // Inserts a new key
    public boolean insertKey(int key) {
        if (currentHeapSize == capacity) {
            // heap is full
            return false;
        }

        // First insert the new key at the end
        int i = currentHeapSize;
        heapArray[i] = key;
        currentHeapSize++;

        // Fix the min heap property if it is violated
        while (i != 0 && heapArray[i] <
                heapArray[parent(i)]) {
            // swap elements
            int temp = heapArray[i];
            heapArray[i] = heapArray[parent(i)];
            heapArray[parent(i)] = temp;

            i = parent(i);
        }
        return true;
    }

    // Get the Left Child index for the given index
    public int left(int key) {
        return 2 * key + 1;
    }

    // A recursive method to heapify a subtree with the root at given index
    // This method assumes that the subtrees are already heapified
    public void minHeapify(int key) {
        int l = left(key);
        int r = right(key);

        int smallest = key;
        if (l < currentHeapSize &&
                heapArray[l] < heapArray[smallest]) {
            smallest = l;
        }
        if (r < currentHeapSize &&
                heapArray[r] < heapArray[smallest]) {
            smallest = r;
        }

        if (smallest != key) {
            int temp = heapArray[key];
            heapArray[key] = heapArray[smallest];
            heapArray[smallest] = temp;
            minHeapify(smallest);
        }
    }

    // Get the Parent index for the given index
    public int parent(int key) {
        return (key - 1) / 2;
    }

    // Get the Right Child index for the given index
    public int right(int key) {
        return 2 * key + 2;
    }
}