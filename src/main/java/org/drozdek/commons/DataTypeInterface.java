package org.drozdek.commons;

import java.util.UUID;

public interface DataTypeInterface {
    UUID id = UUID.randomUUID();

    default String showId() {
        return "[id: " + id.toString() + ']';
    }

    default void print() {
        LoggerService.logInfo(this.showId() +
                System.lineSeparator() +
                this);
    }
}
