package org.drozdek.sorting.exercises;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BruteForceClosestPairTest {

    private List<Point> points;

    @BeforeEach
    void setUp() {
        points = new ArrayList<>();
    }

    @Test
    @DisplayName("Enumerating pairs from empty list returns empty")
    void enumeratePairs_emptyList() {
        List<PointPair> pairs = BruteForceClosestPair.enumeratePairs(points);
        assertTrue(pairs.isEmpty());
    }

    @Test
    @DisplayName("Enumerating pairs from single point returns empty")
    void enumeratePairs_singlePoint() {
        points.add(new Point(0, 0));
        List<PointPair> pairs = BruteForceClosestPair.enumeratePairs(points);
        assertTrue(pairs.isEmpty());
    }

    @Test
    @DisplayName("Enumerating pairs from two points returns one pair")
    void enumeratePairs_twoPoints() {
        points.add(new Point(0, 0));
        points.add(new Point(3, 4));
        List<PointPair> pairs = BruteForceClosestPair.enumeratePairs(points);
        assertEquals(1, pairs.size());
        assertEquals(5.0, pairs.getFirst().distance, 0.001);
    }

    @Test
    @DisplayName("Enumerating pairs from three points returns three pairs")
    void enumeratePairs_threePoints() {
        points.add(new Point(0, 1));
        points.add(new Point(2, 3));
        points.add(new Point(4, 5));
        List<PointPair> pairs = BruteForceClosestPair.enumeratePairs(points);
        assertEquals(3, pairs.size());
    }

    @Test
    @DisplayName("Enumerating pairs from four points returns six pairs")
    void enumeratePairs_fourPoints() {
        points.add(new Point(1, 1));
        points.add(new Point(2, 2));
        points.add(new Point(3, 3));
        points.add(new Point(4, 4));
        List<PointPair> pairs = BruteForceClosestPair.enumeratePairs(points);
        assertEquals(6, pairs.size());
    }

    @Test
    @DisplayName("Minimum distance between points returns correct closest pair")
    void minimumDistance_multiPoints() {
        points.add(new Point(0, 0));
        points.add(new Point(10, 10));
        points.add(new Point(1, 0));
        points.add(new Point(100, 100));

        List<PointPair> pairs = BruteForceClosestPair.enumeratePairs(points);
        PointPair closest = PointPair.minimumDistanceBetweenPoints(pairs);

        assertNotNull(closest);
        assertEquals(1.0, closest.distance, 0.001);
        assertEquals(0, closest.pointA.x);
        assertEquals(0, closest.pointA.y);
    }

    @Test
    @DisplayName("Minimum distance returns null for empty pairs")
    void minimumDistance_emptyPairs() {
        List<PointPair> pairs = new ArrayList<>();
        PointPair closest = PointPair.minimumDistanceBetweenPoints(pairs);
        assertNull(closest);
    }

    @Test
    @DisplayName("Point constructor and getters work correctly")
    void point_constructor() {
        Point p = new Point(5, -3);
        assertEquals(5, p.getX());
        assertEquals(-3, p.getY());
    }

    @Test
    @DisplayName("Point toString returns formatted string")
    void point_toString() {
        Point p = new Point(7, 2);
        assertEquals("(7,2)", p.toString());
    }

    @Test
    @DisplayName("PointPair toString returns formatted string")
    void pointPair_toString() {
        Point a = new Point(1, 2);
        Point b = new Point(3, 4);
        PointPair pair = new PointPair(a, b);

        String str = pair.toString();
        assertTrue(str.contains("(1,2)"));
        assertTrue(str.contains("(3,4)"));
    }

    @Test
    @DisplayName("PointPair calculates Euclidean distance correctly")
    void pointPair_distance() {
        Point a = new Point(0, 0);
        Point b = new Point(3, 4);
        PointPair pair = new PointPair(a, b);

        assertEquals(5.0, pair.distance, 0.001);
    }

    @Test
    @DisplayName("PointPair quickSort sorts pairs by distance")
    void pointPair_quickSort() {
        List<PointPair> pairs = new ArrayList<>();

        pairs.add(new PointPair(new Point(0, 0), new Point(10, 0)));
        pairs.add(new PointPair(new Point(0, 0), new Point(1, 0)));
        pairs.add(new PointPair(new Point(0, 0), new Point(5, 0)));

        PointPair.quickSort(pairs, 0, pairs.size() - 1);

        assertTrue(pairs.get(0).distance <= pairs.get(1).distance);
        assertTrue(pairs.get(1).distance <= pairs.get(2).distance);
    }
}
