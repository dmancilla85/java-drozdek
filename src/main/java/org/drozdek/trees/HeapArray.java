package org.drozdek.trees;

import org.drozdek.commons.LoggerService;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

/// Array-based heap sort implementation. Uses an array to represent a binary heap and sorts elements in-place.
///
/// **Real-world use case:** Priority-queue backed schedulers and the heapsort
/// algorithm itself, widely used in embedded systems where in-place sorting is required.
///
/// Complexity Analysis:
/// Time Complexity: O(n*log(n)) for heap sort, O(log n) for insert, O(n) for heapify
/// Auxiliary Space: O(1)
///
/// @see <a href="https://doi.org/10.1145/512274.3734138">Williams, 1964, Heapsort (ACM)</a>
public class HeapArray {

    private static final int DEFAULT_CAPACITY = 10;

    private int[] keys;
    private int position;

    public HeapArray() {
        keys = new int[DEFAULT_CAPACITY];
        position = 0;
    }

    /// Sorts the stored keys in ascending order using in-place heapsort.
    ///
    /// Repeatedly swaps the current maximum to the end and sifts the displaced value down;
    /// every intermediate array state is appended to a trace file. Runs in O(n log n) with
    /// O(1) extra space.
    public void heapSort() {
        int parent;
        int child;
        int previousKey;
        int last = position - 1;
        for (int i = position; i >= 1; i--) {
            previousKey = keys[last];
            keys[last] = keys[0];
            last = last - 1;
            parent = 0;
            if ((last >= 2) && (keys[2] > keys[1])) {
                child = 2;
            } else {
                child = 1;
            }
            while ((child <= last) && (keys[child] > previousKey)) {
                keys[parent] = keys[child];
                parent = child;
                child = parent * 2;
                if (((child + 1) <= last) && (keys[child + 1] > keys[child])) {
                    child++;
                }
                displayToFile();
            }
            keys[parent] = previousKey;
            displayToFile();
        }
    }

    /// Computes the array index of the right child of the given position.
    ///
    /// @param parentPosition index of the parent node
    /// @return child index computed as `2 * parentPosition + 1`
    public int rightChild(int parentPosition) {
        return (2 * parentPosition) + 1;
    }

    /// Computes the array index of the left child of the given position.
    ///
    /// @param parentPosition index of the parent node
    /// @return child index computed as `2 * parentPosition`
    public int leftChild(int parentPosition) {
        return 2 * parentPosition;
    }

    /// Inserts a key and restores the max-heap property by sifting it up.
    ///
    /// The backing array doubles in size when full. Runs in O(log n) amortized.
    ///
    /// @param key value to add to the heap
    public void insert(int key) {
        int next = position;
        if (next >= keys.length) {
            keys = Arrays.copyOf(keys, keys.length * 2);
        }

        int parent = Math.max(next / 2, 0);
        keys[next] = key;
        while ((next != 0) && (keys[parent] <= keys[next])) {
            int temp = keys[parent];
            keys[parent] = keys[next];
            keys[next] = temp;
            next = parent;
            parent = (next / 2);
        }
        position++;
    }

    /// Appends the current heap array contents to target/files/HeapArray.txt.
    ///
    /// Creates the target directory when missing and reports I/O failures through the logger
    /// without throwing.
    public void displayToFile() {
        Path path = Path.of("target", "files", "HeapArray.txt");
        try {
            Files.createDirectories(path.getParent());
        } catch (IOException e) {
            LoggerService.logError("Cannot create output directory: " + e.getMessage());
            return;
        }

        try (FileWriter fw = new FileWriter(path.toString(), true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            for (int i = 0; i < keys.length; i++) {
                out.print(keys[i]);
                out.print(" ");
            }
            out.println();
            out.println("-------------------------");
        } catch (IOException e) {
            LoggerService.logError(e.getMessage());
        }
    }

    /// Renders the heap array as a single-line string.
    ///
    /// Unused slots (zero values) appear as "--".
    ///
    /// @return textual representation of every slot in the backing array
    public String display() {
        StringBuilder result = new StringBuilder();
        int i = 0;
        while (i < keys.length) {
            if (keys[i] == 0) {
                result.append("--").append("  ");
            } else {
                result.append(keys[i]).append(" ");
            }
            i++;
        }
        return result.toString();
    }

    /// Logs the current heap display through the application logger.
    public void print() {
        LoggerService.logInfo(System.lineSeparator() + display());
    }
}
