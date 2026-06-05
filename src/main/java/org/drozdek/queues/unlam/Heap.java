package org.drozdek.queues.unlam;

import org.drozdek.commons.LoggerService;
import org.drozdek.queues.interfaces.QueueInterface;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Heap<E extends Comparable<? super E>> implements QueueInterface<E> {
    protected final Comparator<? super E> cmp_;
    protected final List<E> nodes_;
    protected int count_;

    public Heap(int capacity, Comparator<? super E> cmp) {
        if (capacity <= 0)
            throw new IllegalArgumentException();
        nodes_ = new ArrayList<>(capacity);
        cmp_ = cmp;
    }

    public Heap(int capacity) {
        this(capacity, null);
    }

    public synchronized void clear() {
        nodes_.clear();
        count_ = 0;
    }

    protected int compare(E a, E b) {
        if (cmp_ != null)
            return cmp_.compare(a, b);
        return a.compareTo(b);
    }

    public synchronized E extract() {
        if (count_ < 1)
            return null;

        int k = 0;
        E least = nodes_.get(k);
        --count_;
        E x = nodes_.get(count_);
        nodes_.set(count_, null);

        while (true) {
            int l = left(k);
            if (l >= count_)
                break;
            int r = right(k);
            int child = (r >= count_ || compare(nodes_.get(l), nodes_.get(r)) < 0) ? l : r;
            if (compare(x, nodes_.get(child)) > 0) {
                nodes_.set(k, nodes_.get(child));
                k = child;
            } else
                break;
        }
        nodes_.set(k, x);
        return least;
    }

    public synchronized void insert(E x) {
        nodes_.add(x);
        int k = count_;
        ++count_;
        while (k > 0) {
            int par = parent(k);
            if (compare(x, nodes_.get(par)) < 0) {
                nodes_.set(k, nodes_.get(par));
                k = par;
            } else
                break;
        }
        nodes_.set(k, x);
    }

    protected final int left(int k) {
        return 2 * k + 1;
    }

    protected final int parent(int k) {
        return (k - 1) / 2;
    }

    public synchronized E peek() {
        if (count_ > 0)
            return nodes_.get(0);
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
        return count_ == 0;
    }

    @Override
    public synchronized String toString() {
        if (isEmpty()) {
            return QueueInterface.boxedQueue("[ EMPTY ]");
        }
        StringBuilder sb = new StringBuilder("FRONT");
        for (int i = 0; i < count_; i++) {
            sb.append(" \u2794 [").append(nodes_.get(i)).append("]");
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
        return count_;
    }
}
