package org.drozdek.queues;

import org.drozdek.commons.LoggerService;
import org.drozdek.queues.interfaces.QueueInterface;

import java.util.NoSuchElementException;

/// Double-ended queue (deque) implemented as a doubly-linked list.
/// Supports insertion and removal at both ends in constant time.
///
/// **Real-world use case:** Sliding window algorithms, undo-redo
/// buffers, palindrome checking, and job scheduling with bi-directional
/// priority.
///
/// Complexity Analysis:
/// Time Complexity: O(1) for add/remove/peek at both ends
/// Auxiliary Space: O(n) for storing n elements
///
/// @see <a href="https://en.wikipedia.org/wiki/Double-ended_queue">Double-ended queue (Wikipedia)</a>
public class Deque<T> implements QueueInterface<T> {
    private Node<T> head;
    private Node<T> tail;
    private int count;

    private static class Node<T> {
        T data;
        Node<T> prev;
        Node<T> next;

        Node(T data) {
            this.data = data;
        }
    }

    public Deque() {
        head = null;
        tail = null;
        count = 0;
    }

    /// Inserts an element at the front of the deque.
    ///
    /// @param element Element to add
    public void addFirst(T element) {
        Node<T> newNode = new Node<>(element);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        count++;
    }

    /// Inserts an element at the rear of the deque.
    ///
    /// @param element Element to add
    public void addLast(T element) {
        Node<T> newNode = new Node<>(element);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        }
        count++;
    }

    /// Removes and returns the element at the front.
    ///
    /// @return The front element
    /// @throws NoSuchElementException if the deque is empty
    public T removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }
        T data = head.data;
        head = head.next;
        if (head == null) {
            tail = null;
        } else {
            head.prev = null;
        }
        count--;
        return data;
    }

    /// Removes and returns the element at the rear.
    ///
    /// @return The rear element
    /// @throws NoSuchElementException if the deque is empty
    public T removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }
        T data = tail.data;
        tail = tail.prev;
        if (tail == null) {
            head = null;
        } else {
            tail.next = null;
        }
        count--;
        return data;
    }

    /// Returns (without removing) the front element.
    ///
    /// @return The front element, or `null` if empty
    public T peekFirst() {
        if (isEmpty()) {
            return null;
        }
        return head.data;
    }

    /// Returns (without removing) the rear element.
    ///
    /// @return The rear element, or `null` if empty
    public T peekLast() {
        if (isEmpty()) {
            return null;
        }
        return tail.data;
    }

    // ---- QueueInterface implementation ----

    @Override
    public boolean enqueue(T element) {
        addLast(element);
        return true;
    }

    /// Removes and returns the element at the front of this deque.
    ///
    /// Unlike {@link #removeFirst()}, returns null instead of throwing
    /// when this deque is empty.
    @Override
    public T dequeue() {
        try {
            return removeFirst();
        } catch (NoSuchElementException _) {
            return null;
        }
    }

    @Override
    public T peek() {
        return peekFirst();
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        count = 0;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public void print() {
        LoggerService.logInfo(showId() + System.lineSeparator() + toString());
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return QueueInterface.boxedQueue("[ EMPTY ]");
        }
        StringBuilder sb = new StringBuilder("FRONT");
        Node<T> current = head;
        while (current != null) {
            sb.append(" \u2794 [").append(current.data).append("]");
            current = current.next;
        }
        sb.append(" \u2794 REAR");
        return QueueInterface.boxedQueue(sb.toString());
    }
}
