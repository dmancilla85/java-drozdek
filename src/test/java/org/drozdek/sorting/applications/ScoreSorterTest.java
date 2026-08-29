package org.drozdek.sorting.applications;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.drozdek.sorting.applications.ScoreSorter.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoreSorterTest {

    @Test
    @DisplayName("Ranks scores from highest to lowest")
    void rankDescending_orders() {
        List<Score> ranked = ScoreSorter.rankDescending(List.of(
            new Score("alice", 70),
            new Score("bob", 90),
            new Score("carol", 80)));
        assertEquals(List.of("bob", "carol", "alice"), ranked.stream().map(Score::name).toList());
    }

    @Test
    @DisplayName("Tied scores keep their original relative order")
    void rankDescending_stable() {
        List<Score> ranked = ScoreSorter.rankDescending(List.of(
            new Score("first", 50),
            new Score("second", 50)));
        assertEquals("first", ranked.get(0).name());
        assertEquals("second", ranked.get(1).name());
    }

    @Test
    @DisplayName("Single entry is returned unchanged")
    void rankDescending_single() {
        List<Score> ranked = ScoreSorter.rankDescending(List.of(new Score("x", 1)));
        assertEquals(1, ranked.size());
    }
}
