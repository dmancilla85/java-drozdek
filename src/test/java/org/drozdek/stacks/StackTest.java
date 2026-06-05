package org.drozdek.stacks;

import org.drozdek.stacks.Stack;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;
import java.util.List;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;

class StackTest {

    Stack<Integer> stack;

    @BeforeEach
    void setUp() {
        stack = new Stack<>();
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
        assertEquals(34, stack.topElement(),
                "The element at top should be the last pushed");
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
    @DisplayName("Print the stack")
    void print() {
        stack.push(34);
        stack.push(3);
        stack.push(56);
        stack.push(2);
        stack.push(67);

        assertFalse(stack.isEmpty(), "Stack should have elements before print");

        Integer topBefore = stack.topElement();
        out.println(stack);

        assertFalse(stack.isEmpty(), "Stack should still have elements after print");
        assertEquals(topBefore, stack.topElement(), "Top element unchanged after print");
    }

    @Test
    @DisplayName("Show the element at top")
    void topElement() {
        stack.push(34);
        stack.push(3);
        stack.push(56);
        stack.push(2);
        stack.push(67);

        Integer top = stack.topElement();

        assertEquals(67, top, "The stack should not be empty");
    }

    @Test
    @DisplayName("Pop on empty stack throws")
    void popOnEmpty() {
        assertThrows(EmptyStackException.class, () -> stack.pop());
    }

    @Test
    @DisplayName("Top on empty stack throws")
    void topOnEmpty() {
        assertThrows(EmptyStackException.class, () -> stack.topElement());
    }

    @Test
    @DisplayName("Print with box format when unicode disabled")
    void printWithBoxFormat() {
        stack.setPrintWithUnicode(false);
        stack.push(10);
        stack.push(20);
        String result = stack.toString();
        assertTrue(result.contains("TOP"));
        assertTrue(result.contains("BOTTOM"));
    }

    @Test
    @DisplayName("Print with unicode format")
    void printWithUnicode() {
        stack.push(10);
        stack.push(20);
        String result = stack.toString();
        assertTrue(result.contains("◄ TOP"));
    }

    @Test
    @DisplayName("LIFO order is maintained")
    void lifoOrder() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertEquals(3, stack.pop());
        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
        assertTrue(stack.isEmpty());
    }

    @Test
    @DisplayName("Clear on empty stack does not throw")
    void clearEmpty() {
        assertDoesNotThrow(() -> stack.clear());
        assertTrue(stack.isEmpty());
    }
}
