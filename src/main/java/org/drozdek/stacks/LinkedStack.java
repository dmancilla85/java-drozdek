package org.drozdek.stacks;

import java.util.EmptyStackException;

import org.drozdek.stacks.interfaces.StackInterface;

public class LinkedStack<T> implements StackInterface<T> {

    private Node<T> top;

    public LinkedStack() {
        this.top = null;
    }

    @Override
    public void push(T element) {
        Node<T> aux = this.top;
        this.top = new Node<>();
        top.data = element;
        top.next = aux;
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        T data = top.data;
        this.top = this.top.next;
        return data;
    }

    @Override
    public T topElement() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return this.top.data;
    }

    @Override
    public boolean isEmpty() {
        return this.top == null;
    }

    @Override
    public void clear() {
        this.top = null;
    }

    private static class Node<T> {
        private T data;
        private Node<T> next;
    }
}
