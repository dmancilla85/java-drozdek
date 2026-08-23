package org.drozdek.commons;

import java.util.UUID;

/// Root interface granting every stored element a unique identifier and a
/// default print routine through LoggerService.
///
/// **Real-world use case:** Uniform tracing of elements across ADTs during
/// demos and manual test runs.
///
/// @see LoggerService
public interface DataTypeInterface {
    UUID id = UUID.randomUUID();

    /// Builds the identifier fragment shared by all implementing types.
    ///
    /// @return the id wrapped in brackets, ready for logging
    default String showId() {
        return "[id: " + id.toString() + ']';
    }

    /// Logs this element's id followed by its own string form.
    default void print() {
        LoggerService.logInfo(this.showId() +
                System.lineSeparator() +
                this);
    }
}
