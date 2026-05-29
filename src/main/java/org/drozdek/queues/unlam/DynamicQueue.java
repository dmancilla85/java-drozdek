package org.drozdek.queues.unlam;

import org.drozdek.commons.LoggerService;
import org.drozdek.queues.interfaces.QueueInterface;
import org.drozdek.queues.interfaces.UnlamQueue;

import static java.lang.Math.random;
import static java.lang.System.out;

/// Dynamic queue implementation using a singly-linked list.
///
/// Abstract Data Type: Dynamic Queue (FIFO - First In, First Out)
///
/// This implementation uses a singly-linked list with head and tail pointers,
/// providing O(1) enqueue and dequeue operations.
public class DynamicQueue implements UnlamQueue, QueueInterface<Object> {

    private QueueNode first;
    private QueueNode last;

    public DynamicQueue() {
        this.first = null;
        this.last = null;
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
            LoggerService.logError(e.getMessage());
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

    @Override
    public String toString() {
        if (isEmpty()) {
            return QueueInterface.boxedQueue("[ EMPTY ]");
        }
        StringBuilder sb = new StringBuilder("FRONT");
        QueueNode current = first;
        while (current != null) {
            sb.append(" ➔ [").append(current.data).append("]");
            current = current.next;
        }
        sb.append(" ➔ REAR");
        return QueueInterface.boxedQueue(sb.toString());
    }

    @Override
    public void clear() {
        this.first = null;
        this.last = null;
    }

    /// Node for a singly-linked list used in the dynamic queue.
    private static class QueueNode {
        private final Object data;
        private QueueNode next;

        QueueNode() {
            data = null;
            next = null;
        }

        QueueNode(Object obj) {
            data = obj;
            next = null;
        }
    }
}
