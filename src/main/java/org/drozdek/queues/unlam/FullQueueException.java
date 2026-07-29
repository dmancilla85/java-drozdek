package org.drozdek.queues.unlam;

/// Exception thrown when attempting to enqueue an element into a full queue.
@SuppressWarnings("java:S110")
public class FullQueueException extends ArrayIndexOutOfBoundsException {

    @java.io.Serial
    private static final long serialVersionUID = 1L;

    public FullQueueException() {
<<<<<<< Updated upstream
        super("The queue size has been exceeded. It must be re-dimensioned");
||||||| Stash base
=======
        super("The queue size has been exceeded. It will be re-dimensioned");
>>>>>>> Stashed changes
    }

}
