package org.drozdek.trees.applications;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MedianStreamTrackerTest {

    @Test
    @DisplayName("Odd stream size yields the middle value")
    void median_oddCount() {
        MedianStreamTracker tracker = new MedianStreamTracker();
        for (int value : new int[] {1, 2, 3, 4, 5}) {
            tracker.add(value);
        }
        assertEquals(3.0, tracker.median());
        assertEquals(5, tracker.size());
    }

    @Test
    @DisplayName("Even stream size yields the average of the two middles")
    void median_evenCount() {
        MedianStreamTracker tracker = new MedianStreamTracker();
        for (int value : new int[] {5, 15, 1, 3}) {
            tracker.add(value);
        }
        assertEquals(4.0, tracker.median());
    }

    @Test
    @DisplayName("Maintains the median as values arrive out of order")
    void median_streams() {
        MedianStreamTracker tracker = new MedianStreamTracker();
        tracker.add(10);
        assertEquals(10.0, tracker.median());
        tracker.add(1);
        assertEquals(5.5, tracker.median());
        tracker.add(100);
        assertEquals(10.0, tracker.median());
    }
}
