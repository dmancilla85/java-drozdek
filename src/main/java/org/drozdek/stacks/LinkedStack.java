package org.drozdek.stacks;

import org.drozdek.stacks.interfaces.StackInterface;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

/// Stack implemented as a singly-linked list.
///
/// **Real-world use case:** Function call stack simulation in
/// interpreters and runtime environments.
///
/// Complexity Analysis:
/// Time Complexity: O(1) for push and pop
/// Auxiliary Space: O(n) for storing n elements
///
/// @see <a href="https://en.wikipedia.org/wiki/Introduction_to_Algorithms">Cormen et al., Introduction to Algorithms, 4th ed. (MIT Press)</a>
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
    public String toString() {
        List<T> elements = new ArrayList<>();
        Node<T> current = top;
        while (current != null) {
            elements.add(current.data);
            current = current.next;
        }
        java.util.Collections.reverse(elements);
        return Stack.formatStackList(elements);
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
