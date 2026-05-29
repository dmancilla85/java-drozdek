package org.drozdek.stacks.interfaces;

import org.drozdek.commons.DataTypeInterface;

import java.util.EmptyStackException;

public interface StackInterface<T> extends DataTypeInterface {
    void push(T element);

    T pop() throws EmptyStackException;

    T topElement() throws EmptyStackException;

    boolean isEmpty();

    void clear();
}
