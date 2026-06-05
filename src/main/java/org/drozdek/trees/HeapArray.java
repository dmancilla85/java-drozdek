package org.drozdek.trees;

import org.drozdek.commons.LoggerService;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

/// Array-based heap sort implementation. Uses an array to represent a binary heap and sorts elements in-place.
///
/// Complexity Analysis:
/// Time Complexity: O(n*log(n)) for heap sort, O(log n) for insert, O(n) for heapify
/// Auxiliary Space: O(1)
///
/// Source: [Geeks for Geeks](https://www.geeksforgeeks.org/heap-data-structure/)
public class HeapArray {

    private final int[] keys;
    private int position;

    public HeapArray() {
        keys = new int[10];
        position = 0;
    }

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
            if ((last >= 2) && (keys[2] > keys[1]))
                child = 2;
            else
                child = 1;
            while ((child <= last) && (keys[child] > previousKey)) {
                keys[parent] = keys[child];
                parent = child;
                child = parent * 2;
                if (((child + 1) <= last) && (keys[child + 1] > keys[child]))
                    child++;
                displayToFile();
            }
            keys[parent] = previousKey;
            displayToFile();
        }
    }

    public int rightChild(int parentPosition) {
        return (2 * parentPosition) + 1;
    }

    public int leftChild(int parentPosition) {
        return 2 * parentPosition;
    }

    public void insert(int key) {
        int parent;
        int temp;
        int next;
        next = position;
        parent = (next / 2);
        if (parent < 0)
            parent = 0;
        keys[next] = key;
        while ((next != 0) && (keys[parent] <= keys[next])) {
            temp = keys[parent];
            keys[parent] = keys[next];
            keys[next] = temp;
            next = parent;
            parent = (next / 2);
        }
        position++;
    }

    public void displayToFile() {
        try {
            FileWriter fw = new FileWriter("HeapArray.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw);
            int i = 0;
            while (i <= 9) {
                out.print(keys[i] + " ");
                i++;
            }
            out.println("");
            out.println("-------------------------");
            out.close();
        } catch (Exception ignored) {
            // Intentionally ignored
        }
    }

    public String display() {
        String result = "";
        int i = 0;
        while (i <= 9) {
            if (keys[i] == 0) {
                result = result + "--" + "  ";
            } else {
                result = result + keys[i] + "  ";
            }
            i++;
        }
        return result;
    }

    public void print() {
        LoggerService.logInfo(System.lineSeparator() + display());
    }
}
