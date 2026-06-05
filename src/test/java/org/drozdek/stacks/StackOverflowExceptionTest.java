package org.drozdek.stacks;

import org.drozdek.stacks.StackOverflowException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StackOverflowExceptionTest {
    @Test
    @DisplayName("Exception extends ArrayIndexOutOfBoundsException")
    void isArrayIndexOutOfBoundsException() {
        assertInstanceOf(ArrayIndexOutOfBoundsException.class, new StackOverflowException());
    }

    @Test
    @DisplayName("Exception has descriptive message")
    void message() {
        StackOverflowException e = new StackOverflowException();
        assertNotNull(e.getMessage());
        assertTrue(e.getMessage().contains("capacity"));
    }
}
