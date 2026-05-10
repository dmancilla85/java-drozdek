package stacks;

import org.drozdek.stacks.ArrayStack;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayStackTest {
    ArrayStack<Integer> stack;

    @BeforeEach
    void setUp() {
        stack = new ArrayStack<>(5);
    }

    @Test
    @DisplayName("A new stack always is empty")
    void isEmpty() {
        assertTrue(stack.isEmpty(), "The stack should be empty");
    }

    @Test
    @DisplayName("Push an element to new stack")
    void push() {
        stack.push(34);
        assertEquals(34, stack.topElement(), "The element at top should be the last pushed");
    }

    @Test
    @DisplayName("Pop an element from stack")
    void pop() {
        stack.push(34);
        stack.push(3);
        stack.push(56);
        stack.push(2);
        stack.push(67);

        int topElement = stack.topElement();
        int popped = stack.pop();

        assertEquals(topElement, popped, "The popped element should be the top element");
    }

    @Test
    @DisplayName("Clear the stack")
    void clear() {
        stack.push(34);
        stack.push(3);
        stack.push(56);
        stack.push(2);
        stack.push(67);

        stack.clear();

        assertTrue(stack.isEmpty(), "The stack now should be empty");
    }

    @Test
    @DisplayName("Pop on empty stack throws exception")
    void popOnEmpty() {
        assertThrows(java.util.EmptyStackException.class, stack::pop, "Pop on empty should throw");
    }

    @Test
    @DisplayName("Top on empty stack throws exception")
    void topOnEmpty() {
        assertThrows(java.util.EmptyStackException.class, stack::topElement, "Top on empty should throw");
    }

    @Test
    @DisplayName("Auto-resizing when exceeding capacity")
    void autoResize() {
        ArrayStack<Integer> smallStack = new ArrayStack<>(2);
        smallStack.push(1);
        smallStack.push(2);
        smallStack.push(3);

        assertEquals(3, smallStack.topElement(), "Should auto-resize and accept more elements");
    }
}
