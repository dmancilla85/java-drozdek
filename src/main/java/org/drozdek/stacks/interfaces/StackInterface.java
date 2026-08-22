package org.drozdek.stacks.interfaces;

import org.drozdek.commons.DataTypeInterface;

import java.util.EmptyStackException;

/// Interface defining Last-In-First-Out (LIFO) stack operations.
///
/// **Real-world use case:** Expression evaluation, syntax parsing,
/// and undo/redo systems in text editors.
///
/// Complexity Analysis:
/// Time Complexity: O(1) for push, pop, and peek operations (per implementation)
/// Auxiliary Space: O(n) for storing n elements
///
/// @see <a href="https://en.wikipedia.org/wiki/Introduction_to_Algorithms">Cormen et al., Introduction to Algorithms, 4th ed. (MIT Press)</a>
public interface StackInterface<T> extends DataTypeInterface {
    void push(T element);

    T pop() throws EmptyStackException;

    T topElement() throws EmptyStackException;

    boolean isEmpty();

    void clear();
}
