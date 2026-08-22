package org.drozdek.stacks;

import org.drozdek.stacks.interfaces.StackInterface;

import java.util.EmptyStackException;
import java.util.LinkedList;

/// Stack backed by Java's LinkedList (doubly-linked list).
///
/// **Real-world use case:** General-purpose LIFO storage where
/// peak memory usage is unpredictable.
///
/// Complexity Analysis:
/// Time Complexity: O(1) for push and pop
/// Auxiliary Space: O(n) for storing n elements
///
/// @see <a href="https://en.wikipedia.org/wiki/Introduction_to_Algorithms">Cormen et al., Introduction to Algorithms, 4th ed. (MIT Press)</a>
public class LinkedListStack<T> implements StackInterface<T> {
    private final LinkedList<T> pool;

    /// Default constructor.
    public LinkedListStack() {
        pool = new LinkedList<>();
    }

    /// Clear stack.
    public void clear() {
        pool.clear();
    }

    /// Is stack empty?
    ///
    /// @return True if is empty
    public boolean isEmpty() {
        return pool.isEmpty();
    }

    /// Extract the last element.
    ///
    /// @return The element removed
    public T pop() {
        if (isEmpty())
            throw new EmptyStackException();

        return pool.removeLast();
    }

    /// Add an element to stack.
    ///
    /// @param element Element to add to stack
    public void push(T element) {
        pool.addLast(element);
    }

    /// Convert to string.
    ///
    /// @return The stack as a string
    @Override
    public String toString() {
        return Stack.formatStackList(pool);
    }

    /// View element at top.
    ///
    /// @return Element at top
    public T topElement() {
        if (isEmpty())
            throw new EmptyStackException();
        return pool.getLast();
    }
}
