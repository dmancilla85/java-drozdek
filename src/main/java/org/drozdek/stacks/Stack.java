package org.drozdek.stacks;

import java.util.ArrayList;
import java.util.EmptyStackException;

/**
 * Stack implemented as an arraylist.
 */
public class Stack<T> {
    private final ArrayList<T> pool;

    /**
     * Default constructor.
     */
    public Stack() {
        this(100, new ArrayList<>());
    }

    /**
     * Constructor.
     *
     * @param n    size
     */
    public Stack(int n) {
        this(n,new ArrayList<>());
    }

    /**
     * Constructor.
     *
     * @param n    size
     * @param pool Storage pool
     */
    private Stack(int n, ArrayList<T> pool) {
        this.pool = pool;
        if(n > 0)
            this.pool.ensureCapacity(n);
    }

    /**
     * Clear stack.
     */
    public void clear() {
        pool.clear();
    }

    /**
     * Is stack empty?
     * @return True if is empty
     */
    public boolean isEmpty() {
        return pool.isEmpty();
    }

    private int lastIndex() {
        if (pool.isEmpty())
            return 0;
        return pool.size() - 1;
    }

    /**
     * Extract the last element.
     * @return The element removed
     */
    public T pop() {
        if (isEmpty())
            throw new EmptyStackException();

        return pool.remove(lastIndex());
    }

    /**
     * Add an element to stack.
     * @param element Element to add
     */
    public void push(T element) {
        pool.add(element);
    }

    /**
     * Convert to string.
     * @return The stack as a string
     */
    @Override
    public String toString() {
        StringBuilder content=new StringBuilder();

        for (T element:pool) {
            content.append(element.toString());
            content.append(System.lineSeparator());
        }
        return content.toString();
    }

    /**
     * View element at top.
     * @return Element at top of the stack
     */
    public T topElement() {
        if (isEmpty())
            throw new EmptyStackException();
        return pool.get(lastIndex());
    }
}
