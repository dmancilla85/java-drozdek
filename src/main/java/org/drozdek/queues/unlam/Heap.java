package org.drozdek.queues.unlam;

import org.drozdek.commons.LoggerService;
import org.drozdek.queues.interfaces.QueueInterface;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Heap<E extends Comparable<? super E>> implements QueueInterface<E> {
    protected final Comparator<? super E> cmp;
    protected final List<E> nodes;
    protected int count;

    public Heap(int capacity, Comparator<? super E> cmp) {
        if (capacity <= 0)
            throw new IllegalArgumentException();
        nodes = new ArrayList<>(capacity);
        this.cmp = cmp;
    }

    public Heap(int capacity) {
        this(capacity, null);
    }

    public synchronized void clear() {
        nodes.clear();
        count = 0;
    }

    protected int compare(E a, E b) {
        if (cmp != null)
            return cmp.compare(a, b);
        return a.compareTo(b);
    }

    public synchronized E extract() {
        if (count < 1)
            return null;

        int k = 0;
        E least = nodes.get(k);
        --count;
        E x = nodes.get(count);
        nodes.set(count, null);

        boolean shouldContinue = true;
        while (shouldContinue) {
            int l = left(k);
            if (l >= count)
                shouldContinue = false;
            else {
                int r = right(k);
                int child = (r >= count || compare(nodes.get(l), nodes.get(r)) < 0) ? l : r;
                if (compare(x, nodes.get(child)) > 0) {
                    nodes.set(k, nodes.get(child));
                    k = child;
                } else
                    shouldContinue = false;
            }
        }
        nodes.set(k, x);
        return least;
    }

    public synchronized void insert(E x) {
        nodes.add(x);
        int k = count;
        ++count;
        while (k > 0) {
            int par = parent(k);
            if (compare(x, nodes.get(par)) < 0) {
                nodes.set(k, nodes.get(par));
                k = par;
            } else
                break;
        }
        nodes.set(k, x);
    }

    protected final int left(int k) {
        return 2 * k + 1;
    }

    protected final int parent(int k) {
        return (k - 1) / 2;
    }

    public synchronized E peek() {
        if (count > 0)
            return nodes.get(0);
        else
            return null;
    }

    protected final int right(int k) {
        return 2 * (k + 1);
    }

    public synchronized boolean enqueue(E x) {
        insert(x);
        return true;
    }

    public synchronized E dequeue() {
        return extract();
    }

    public synchronized boolean isEmpty() {
        return count == 0;
    }

    @Override
    public synchronized String toString() {
        if (isEmpty()) {
            return QueueInterface.boxedQueue("[ EMPTY ]");
        }
        StringBuilder sb = new StringBuilder("FRONT");
        for (int i = 0; i < count; i++) {
            sb.append(" \u2794 [").append(nodes.get(i)).append("]");
        }
        sb.append(" \u2794 REAR");
        return QueueInterface.boxedQueue(sb.toString());
    }

    @Override
    public synchronized void print() {
        LoggerService.logInfo(this.showId() +
                System.lineSeparator() +
                toString());
    }

    public synchronized int size() {
        return count;
    }
}
