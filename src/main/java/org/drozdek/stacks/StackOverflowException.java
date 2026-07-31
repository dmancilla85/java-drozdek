package org.drozdek.stacks;

/// Exception thrown when a stack operation exceeds capacity.
///
/// <p><b>Real-world use case:</b> Bounded stack implementations that
/// enforce a maximum capacity limit.
@SuppressWarnings("java:S110")
public class StackOverflowException extends ArrayIndexOutOfBoundsException {

    @java.io.Serial
    private static final long serialVersionUID = 1L;

    public StackOverflowException() {
        super("The stack does not have enough capacity to store data");
    }
}
