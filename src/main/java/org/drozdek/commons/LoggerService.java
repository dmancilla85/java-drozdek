package org.drozdek.commons;

import java.util.logging.Logger;

public class LoggerService {
    protected static final Logger log = Logger.getLogger("Logger");

    private static final String GREEN = "\u001B[32m";
    private static final String WHITE = "\u001B[0m";
    private static final String YELLOW = "\u001B[33m";
    private static final String RED = "\u001B[31m";

    private LoggerService() {
    }

    public static void logInfo(String message) {
        String msg = GREEN +
                message +
                WHITE;
        log.info(msg);
    }

    public static void logWarning(String message) {
        String msg = YELLOW +
                message +
                WHITE;
        log.info(msg);
    }

    public static void logError(String message) {
        String msg = RED +
                message +
                WHITE;
        log.info(msg);

    }

}
