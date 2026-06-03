package stacks;

import org.drozdek.stacks.AdaptiveStack;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdaptiveStackTest {
    AdaptiveStack<Integer> stack;

    @BeforeEach
    void setUp() {
        stack = new AdaptiveStack<>();
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
    @DisplayName("Adaptive stack starts in static mode")
    void startsStatic() {
        assertTrue(stack.isEmpty(), "Should start empty");
        stack.push(1);
        stack.push(2);
        assertEquals(2, stack.topElement(), "Should work like a normal stack");
    }

    @Test
    @DisplayName("Push and pop many elements (stress test)")
    void pushPopMany() {
        for (int i = 0; i < 100; i++)
            stack.push(i);
        for (int i = 99; i >= 0; i--)
            assertEquals(i, stack.pop());
        assertTrue(stack.isEmpty());
    }

    @Test
    @DisplayName("Top element after sequence of operations")
    void topElementAfterSequence() {
        stack.push(10);
        stack.push(20);
        assertEquals(20, stack.topElement());
        stack.pop();
        assertEquals(10, stack.topElement());
    }

    @Test
    @DisplayName("ToString returns non-null")
    void testToString() {
        stack.push(1);
        stack.push(2);
        assertNotNull(stack.toString());
    }

    @Test
    @DisplayName("Clear on empty stack does not throw")
    void clearEmpty() {
        assertDoesNotThrow(() -> stack.clear());
        assertTrue(stack.isEmpty());
    }

    @Test
    @DisplayName("Push more than 2000 triggers dynamic switch")
    void pushTriggersDynamicSwitch() {
        for (int i = 0; i < 2500; i++)
            stack.push(i);
        assertEquals(2499, stack.topElement());

        // clear should still work on dynamic stack
        stack.clear();
        assertTrue(stack.isEmpty());
    }

    @Test
    @DisplayName("Pop after dynamic switch works correctly")
    void popAfterDynamicSwitch() {
        for (int i = 0; i < 2500; i++)
            stack.push(i);
        for (int i = 0; i < 2500; i++)
            assertNotNull(stack.pop());
        assertTrue(stack.isEmpty());
    }

    @Test
    @DisplayName("Top element on dynamic stack")
    void topOnDynamic() {
        for (int i = 0; i < 2500; i++)
            stack.push(i);
        assertEquals(2499, stack.topElement());
    }

    @Test
    @DisplayName("ToString on dynamic stack")
    void toStringDynamic() {
        for (int i = 0; i < 2500; i++)
            stack.push(i);
        assertNotNull(stack.toString());
    }
}
