package org.drozdek.recursion;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Towers of Hanoi Tests")
class TowersOfHanoiTest {

    @BeforeAll
    static void silenceMoveLogging() {
        Logger.getLogger("Logger").setLevel(Level.OFF);
    }

    @AfterAll
    static void restoreMoveLogging() {
        Logger.getLogger("Logger").setLevel(Level.INFO);
    }

    @Test
    @DisplayName("Zero disks moves zero times")
    void zeroDisks() {
        assertEquals(0, TowersOfHanoi.solve(0, 'A', 'C', 'B'));
    }

    @Test
    @DisplayName("One disk requires one move")
    void oneDisk() {
        assertEquals(1, TowersOfHanoi.solve(1, 'A', 'C', 'B'));
    }

    @Test
    @DisplayName("Three disks requires seven moves")
    void threeDisks() {
        assertEquals(7, TowersOfHanoi.solve(3, 'A', 'C', 'B'));
    }

    @Test
    @DisplayName("Five disks requires thirty-one moves")
    void fiveDisks() {
        assertEquals(31, TowersOfHanoi.solve(5, 'A', 'C', 'B'));
    }

    @Test
    @DisplayName("MinimumMoves closed-form matches solve result")
    void minimumMovesMatches() {
        for (int n = 1; n <= 10; n++) {
            assertEquals(TowersOfHanoi.minimumMoves(n), TowersOfHanoi.solve(n, 'A', 'C', 'B'));
        }
    }

    @Test
    @DisplayName("Minimum moves formula correct for known values")
    void minimumMovesFormula() {
        assertEquals(1, TowersOfHanoi.minimumMoves(1));
        assertEquals(3, TowersOfHanoi.minimumMoves(2));
        assertEquals(7, TowersOfHanoi.minimumMoves(3));
        assertEquals(15, TowersOfHanoi.minimumMoves(4));
        assertEquals(1023, TowersOfHanoi.minimumMoves(10));
    }
}
