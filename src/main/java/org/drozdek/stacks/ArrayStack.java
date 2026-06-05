package org.drozdek.stacks;

import org.drozdek.stacks.interfaces.StackInterface;

import java.util.*;

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
