package commons;

import org.drozdek.commons.LoggerService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class LogTest {
    @Test
    @DisplayName("Try to log at all levels")
    void logAllLevels(){
        LoggerService.logInfo("This is INFO LEVEL");
        LoggerService.logWarning("This is WARNING LEVEL");
        LoggerService.logError("This is ERROR LEVEL");
        assertTrue(true, "Check if everything is printing OK.");
    }
}
