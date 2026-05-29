package org.drozdek.queues.unlam;

/**
 * Exception thrown when attempting to enqueue an element into a full queue.
 */
public class FullQueueException extends ArrayIndexOutOfBoundsException {

    private static final long serialVersionUID = 1L;

    public FullQueueException() {
        super("The queue size has been exceeded. It must be re-dimensioned");
    }

}
