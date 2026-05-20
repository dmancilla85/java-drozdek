package org.drozdek.stacks;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Objects;

import org.drozdek.stacks.interfaces.StackInterface;

public class ArrayStack<T> implements StackInterface<T> {

    private static final int DEFAULT_CAPACITY = 5;
    private Object[] array;
    private int top;
    private int capacity;

    public ArrayStack() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public ArrayStack(int capacity) {
        this.array = new Object[capacity];
        this.capacity = capacity;
        this.top = -1;
    }

    @Override
    public void push(T element) {
        Objects.requireNonNull(element, "Element cannot be null");
        if (isFull()) {
            resize();
        }
        array[++top] = element;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return (T) array[top--];
    }

    @Override
    @SuppressWarnings("unchecked")
    public T topElement() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return (T) array[top];
    }

    @Override
    public String toString() {
        List<Object> elements = new ArrayList<>(top + 1);
        for (int i = 0; i <= top; i++) {
            elements.add(array[i]);
        }
        return Stack.formatStackList(elements);
    }

    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    @Override
    public void clear() {
        top = -1;
    }

    public boolean isFull() {
        return top + 1 == capacity;
    }

    private void resize() {
        Object[] aux = new Object[2 * capacity];
        if (capacity >= 0) System.arraycopy(array, 0, aux, 0, capacity);
        capacity = 2 * capacity;
        array = aux;
    }
}
