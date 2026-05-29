package org.drozdek.sorting.exercises;

import java.util.ArrayList;
import java.util.List;

class PointPair {
    Point pointA;
    Point pointB;
    double distance;
    int instructions;

    PointPair(Point a, Point b) {
        pointA = a;
        pointB = b;
        instructions = 0;
        calculateDistance();
    }

    public static void quickSort(List<PointPair> list, int left, int right) {

        PointPair pivot = list.get(left);
        int i = left;
        int j = right;
        PointPair temp;

        while (i < j) {
            while (list.get(i).distance <= pivot.distance && i < j)
                i++;
            while (list.get(j).distance > pivot.distance)
                j--;
            if (i < j) {
                temp = list.get(i);
                list.set(i, list.get(j));
                list.get(i).instructions += 3;
                list.set(j, temp);
            }
        }

        list.get(i).instructions += 3;
        list.set(left, list.get(j));
        list.set(j, pivot);
        if (left < j - 1) {
            list.get(i).instructions++;
            quickSort(list, left, j - 1);
        }

        if (j + 1 < right) {
            list.get(i).instructions++;
            quickSort(list, j + 1, right);
        }
    }

    public static PointPair minimumDistanceBetweenPoints(List<PointPair> pairs) {

        if (pairs.isEmpty())
            return null;

        quickSort(pairs, 0, pairs.size() - 1);
        pairs.getFirst().instructions++;
        return pairs.getFirst();
    }

    void calculateDistance() {

        distance = Math.sqrt((pointA.x - pointB.x) * (pointA.x - pointB.x)
                + (pointA.y - pointB.y) * (pointA.y - pointB.y));
    }

    public String toString() {
        return "{" + pointA + ";" + pointB + "}";
    }
}

/// Brute-force closest pair of points: generates every pair of 2D points,
/// computes Euclidean distances, then sorts by distance using quicksort
/// to find the minimum. Also tracks instruction counts for complexity
/// analysis.
///
/// Point pairs are built recursively: take the first point, pair it with
/// every remaining point, then recurse on the rest. The resulting list is
/// sorted via `PointPair::quickSort` and the closest pair is reported.
public final class BruteForceClosestPair {
private BruteForceClosestPair() {  }

    /// Generates all unordered point pairs recursively.
    ///
    /// For each call, pairs `points[0]` with every other point,
    /// removes the first element, and recurses on the shortened list.
    ///
    /// @param points list of points (modified during recursion)
    /// @return list of PointPair for every pair
    public static ArrayList<PointPair> enumeratePairs(List<Point> points) {

        ArrayList<PointPair> pairs = new ArrayList<PointPair>();
        int stepCount = 0;

        if (points.size() <= 1) {
            stepCount++;
            return pairs;
        }

        for (int i = 1; i < points.size(); i++) {
            stepCount++;
            pairs.add(new PointPair(points.getFirst(), points.get(i)));
        }

        points.removeFirst();
        stepCount++;

        for (int i = 0; i < pairs.size(); i++)
            pairs.get(i).instructions += stepCount;

        pairs.addAll(enumeratePairs(points));

        return pairs;
    }

    static void main(String[] args) {

        List<Point> points = new ArrayList<Point>();
        points.add(new Point(1, 0));
        points.add(new Point(3, 124));
        points.add(new Point(0, 34));
        points.add(new Point(3, -3));
        points.add(new Point(150, 15));
        points.add(new Point(2, 2));
        points.add(new Point(20, -11));
        points.add(new Point(3, 1));
        points.add(new Point(12, 0));
        points.add(new Point(-2233, -343));
        points.add(new Point(10, 1345));
        points.add(new Point(2, 562));
        points.add(new Point(76, -32));
        points.add(new Point(-23, 3234));
        points.add(new Point(11, 98));
        points.add(new Point(33, -3));
        points.add(new Point(10, 150));
        points.add(new Point(2, -56));
        points.add(new Point(65, 0));
        points.add(new Point(3, 1231));
        points.add(new Point(0, 45));
        points.add(new Point(-3, 34));
        points.add(new Point(21, -34));
        points.add(new Point(-4983, -3));
        points.add(new Point(120, 325));
        points.add(new Point(2376, -2));
        points.add(new Point(111, 23));
        points.add(new Point(3213, 42));
        points.add(new Point(-7380, 34));
        points.add(new Point(223, -233));
        points.add(new Point(12310, 15));
        points.add(new Point(23, 2));
        points.add(new Point(21120, -121));


        // Generate all point pairs and find the one with minimum distance
        ArrayList<PointPair> pairs = enumeratePairs(points);

        for (PointPair pair : pairs)
            System.out.println(pair);

        PointPair closest = PointPair.minimumDistanceBetweenPoints(pairs);

        if (closest == null) {
            System.out.println("No points to calculate distance.");
        } else {
            System.out.println();
            System.out.printf("Minimum distance is %.3f\n", closest.distance);
            System.out.println();
            System.out.println("The points are: " + closest.pointA + " and " + closest.pointB);
            System.out.println();
            System.out.println("Number of steps: " + closest.instructions);
        }
    }
}
