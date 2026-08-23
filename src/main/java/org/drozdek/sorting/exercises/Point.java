package org.drozdek.sorting.exercises;

/// A simple 2D point value class with integer coordinates.
///
/// Used as a building block for geometric algorithms such as closest-pair
/// of points, convex hull, and triangulation.
class Point {
    int x;
    int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String toString() {
        return "(" + x + "," + y + ")";
    }

}
