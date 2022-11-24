package org.drozdek.commons;

import java.util.logging.Logger;

public class LoggerService {
    protected static final Logger log = Logger.getLogger("Logger");

    private LoggerService(){}

    public static void logInfo(String message){
        log.info("\u001B[32m" + message+  "\u001B[0m");
    }

    public static void logWarning(String message){
        log.info("\u001B[33m" + message+  "\u001B[0m");
    }

    public static void logError(String message){
        log.info("\u001B[31m" + message+  "\u001B[0m");
    }


    public static void main(String[] args){
        logInfo("oh picvhula");
        logWarning("nOt FIne!");
        logError("nOt gODod!");
    }

}
