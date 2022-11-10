package org.drozdek.stacksandqueues;

import java.util.LinkedList;
import java.util.EmptyStackException;

/**
 * Stack implemented as a linked list.
 */
public class LLStack<T> {
    private final LinkedList<T> pool;

    /**
     * Default constructor.
     */
    public LLStack() {
        pool = new LinkedList<>();
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

    /**
     * Extract the last element.
     * @return The element removed
     */
    public Object pop() {
        if (isEmpty())
            throw new EmptyStackException();

        return pool.removeLast();
    }

    /**
     * Add an element to stack.
     * @param element Element to add to stack
     */
    public void push(T element) {
        pool.addLast(element);
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
     * @return Element at top
     */
    public T topElement() {
        if (isEmpty())
            throw new EmptyStackException();
        return pool.getLast();
    }
}
