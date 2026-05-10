package org.drozdek.stacks.interfaces;

import java.util.EmptyStackException;

public interface StackInterface<T> {
    void push(T element);

    T pop() throws EmptyStackException;

    T topElement() throws EmptyStackException;

    boolean isEmpty();

    void clear();
}
