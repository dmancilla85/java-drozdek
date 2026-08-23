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
    /// Pushes an element onto the top of this stack.
    ///
    /// @param element the element to add on top of the stack
    void push(T element);

    /// Removes and returns the element at the top of this stack.
    ///
    /// @return the element most recently pushed onto this stack
    /// @throws EmptyStackException if this stack is empty
    T pop() throws EmptyStackException;

    /// Returns the element at the top of this stack without removing it.
    ///
    /// @return the element most recently pushed onto this stack
    /// @throws EmptyStackException if this stack is empty
    T topElement() throws EmptyStackException;

    /// Tests if this stack contains no elements.
    ///
    /// @return true if this stack contains no elements; false otherwise
    boolean isEmpty();

    /// Removes all elements from this stack, leaving it empty.
    void clear();
}
