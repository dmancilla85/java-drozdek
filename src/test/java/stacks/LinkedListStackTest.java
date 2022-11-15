package stacks;

import org.drozdek.stacks.LinkedListStack;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LinkedListStackTest {
    LinkedListStack<Integer> stack;

    @BeforeEach
    void setUp() {
        stack = new LinkedListStack<>();
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

        int topElement=stack.topElement();
        int popped = stack.pop();

        assertEquals(topElement, popped,"The popped element should be the top element");
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

        assertTrue(stack.isEmpty(),"The stack now should be empty");
    }

    @Test
    @DisplayName("Print the stack")
    void print() {

        stack.push(34);
        stack.push(3);
        stack.push(56);
        stack.push(2);
        stack.push(67);

        out.println(stack);

        assertFalse(stack.isEmpty(),"The stack should not be empty");
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

        assertEquals(67,top,"The stack should not be empty");
    }
}
