package org.drozdek.stacks;

import org.drozdek.stacks.interfaces.StackInterface;

import java.util.*;

/// Stack backed by an ArrayList with dynamic resizing.
///
/// <p><b>Real-world use case:</b> Undo/redo in text editors,
/// expression evaluation in calculator applications.
///
/// Complexity Analysis:
/// Time Complexity: O(1) amortized for push and pop
/// Auxiliary Space: O(n) for storing n elements
///
/// @see <a href="https://en.wikipedia.org/wiki/Introduction_to_Algorithms">Cormen et al., Introduction to Algorithms, 4th ed. (MIT Press)</a>
public class ArrayStack<T> implements StackInterface<T> {

    private static final int DEFAULT_CAPACITY = 5;
    private List<T> list;
    private int top;

    public ArrayStack() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayStack(int capacity) {
        this.list = new ArrayList<>(capacity);
        this.top = -1;
    }

    @Override
    public void push(T element) {
        Objects.requireNonNull(element, "Element cannot be null");
        list.add(element);
        top++;
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return list.remove(top--);
    }

    @Override
    public T topElement() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return list.get(top);
    }

    @Override
    public String toString() {
        List<Object> elements = new ArrayList<>(top + 1);
        for (int i = 0; i <= top; i++) {
            elements.add(list.get(i));
        }
        return Stack.formatStackList(elements);
    }

    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    @Override
    public void clear() {
        list.clear();
        top = -1;
    }
}
