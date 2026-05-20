package org.drozdek.queues.unlam;

import org.drozdek.commons.LoggerService;
import org.drozdek.queues.interfaces.QueueInterface;

import static java.lang.Math.random;
import static java.lang.System.out;

/**
 * Dynamic queue implementation using a singly-linked list.
 * 
 * <p>
 * Abstract Data Type: Dynamic Queue (FIFO - First In, First Out)
 * 
 * <p>
 * This implementation uses a singly-linked list with head and tail pointers,
 * providing O(1) enqueue and dequeue operations.
 */
public class DynamicQueue implements UnlamQueue, QueueInterface<Object> {

    private QueueNode first, last;

    public DynamicQueue() {
        this.first = null;
        this.last = null;
    }

    /**
     * Main method for testing the dynamic queue implementation.
     * 
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        UnlamQueue q1 = new DynamicQueue();

        q1.enqueue("FIRST OBJECT");
        q1.enqueue("B");
        q1.enqueue("C");

        int i = 0, n = 500;
        while (i++ < n)
            q1.enqueue(random() * 100 * i);

        q1.enqueue("LAST OBJECT #" + i);

        while (!q1.isEmpty())
            out.println(q1.dequeue());
    }

    @Override
    public Object dequeue() {
        Object data = null;

        if (!isEmpty()) {
            data = first.data;
            first = first.next;
        }

        return data;
    }

    @Override
    public boolean enqueue(Object obj) {
        try {
            QueueNode node = new QueueNode(obj);

            if (first == null) {
                first = last = node;
            } else {
                last.next = node;
                last = node;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public Object peek() {
        if (isEmpty()) {
            return null;
        }
        return first.data;
    }

    public int size() {
        int count = 0;
        QueueNode current = first;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    public void printAll() {
        StringBuilder line = new StringBuilder();
        QueueNode current = first;
        while (current != null) {
            line.append(current.data);
            line.append(" ");
            current = current.next;
        }
        LoggerService.logInfo(line.toString());
    }

    @Override
    public void clear() {
        this.first = null;
        this.last = null;
    }

    /**
     * Node for a singly-linked list used in the dynamic queue.
     */
    private static class QueueNode {
        private Object data;
        private QueueNode next;

        public QueueNode() {
            data = null;
            next = null;
        }

        public QueueNode(Object obj) {
            data = obj;
            next = null;
        }
    }
}
