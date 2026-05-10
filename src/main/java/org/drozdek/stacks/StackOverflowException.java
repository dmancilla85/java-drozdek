package org.drozdek.stacks;

public class StackOverflowException extends ArrayIndexOutOfBoundsException {

    private static final long serialVersionUID = 1L;

    public StackOverflowException() {
        super("The stack does not have enough capacity to store data");
    }
}
