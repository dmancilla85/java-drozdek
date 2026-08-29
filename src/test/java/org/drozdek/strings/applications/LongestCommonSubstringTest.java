package org.drozdek.strings.applications;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LongestCommonSubstringTest {

    @Test
    @DisplayName("Finds the longest common substring")
    void find_findsLongest() {
        LongestCommonSubstring app = new LongestCommonSubstring();
        assertEquals("able", app.find("capable", "table"));
    }

    @Test
    @DisplayName("Returns empty string when nothing is shared")
    void find_noCommon() {
        LongestCommonSubstring app = new LongestCommonSubstring();
        assertEquals("", app.find("abc", "xyz"));
    }

    @Test
    @DisplayName("Identical strings share the whole string")
    void find_identical() {
        LongestCommonSubstring app = new LongestCommonSubstring();
        assertEquals("hello", app.find("hello", "hello"));
    }

    @Test
    @DisplayName("Single shared character is found")
    void find_singleChar() {
        LongestCommonSubstring app = new LongestCommonSubstring();
        assertEquals("a", app.find("beta", "drama"));
    }
}
