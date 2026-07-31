package org.drozdek.queues.unlam;

/// Exception thrown when attempting to enqueue an element into a full queue.
///
/// <p><b>Real-world use case:</b> Bounded queue implementations that
/// enforce a maximum capacity limit.
@SuppressWarnings("java:S110")
public class FullQueueException extends ArrayIndexOutOfBoundsException {

    @java.io.Serial
    private static final long serialVersionUID = 1L;

    public FullQueueException() {
        super("The queue size has been exceeded. It must be re-dimensioned");
    }

}
