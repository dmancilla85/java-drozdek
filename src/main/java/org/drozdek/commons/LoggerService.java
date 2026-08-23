package org.drozdek.commons;

import java.util.logging.Logger;

/// Central logging facade wrapping java.util.logging with ANSI-colored
/// info/warning/error output.
///
/// **Real-world use case:** Single point for console diagnostics across
/// every algorithm demo and unit test in the project.
///
/// Complexity Analysis:
/// Time Complexity: O(1) per log call
/// Auxiliary Space: O(1)
///
public final class LoggerService {
    protected static final Logger log = Logger.getLogger("Logger");

    private static final String GREEN = "\u001B[32m";
    private static final String WHITE = "\u001B[0m";
    private static final String YELLOW = "\u001B[33m";
    private static final String RED = "\u001B[31m";

    private LoggerService() {
    }

    /// Logs a message at INFO level rendered in green.
    ///
    /// @param message text to log
    public static void logInfo(String message) {
        String msg = GREEN +
                message +
                WHITE;
        log.info(msg);
    }

    /// Logs a message at WARNING level rendered in yellow.
    ///
    /// @param message text to log
    public static void logWarning(String message) {
        String msg = YELLOW +
                message +
                WHITE;
        log.warning(msg);
    }

    /// Logs a message at SEVERE level rendered in red.
    ///
    /// @param message text to log
    public static void logError(String message) {
        String msg = RED +
                message +
                WHITE;
        log.severe(msg);

    }

}
