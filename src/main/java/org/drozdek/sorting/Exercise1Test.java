package org.drozdek.sorting;

import java.util.ArrayList;
import java.util.List;

class Point {
    int x;
    int y;

    public Point(int x, int y) {
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

class Exercise1Solution {
    Point pointA, pointB;
    double distance;
    int instructions;

    Exercise1Solution(Point a, Point b) {
        pointA = a;
        pointB = b;
        instructions = 0;
        calculateDistance();
    }

    public static void quickSort(List<Exercise1Solution> list, int left, int right) {

        Exercise1Solution pivot = list.get(left);
        int i = left;
        int j = right;
        Exercise1Solution temp;

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

    public static Exercise1Solution minimumDistanceBetweenPoints(List<Exercise1Solution> solutions) {

        if (solutions.size() == 0)
            return null;

        quickSort(solutions, 0, solutions.size() - 1);
        solutions.get(0).instructions++;
        return solutions.get(0);
    }

    void calculateDistance() {

        distance = Math.sqrt((pointA.x - pointB.x) * (pointA.x - pointB.x)
                + (pointA.y - pointB.y) * (pointA.y - pointB.y));
    }

    public String toString() {
        return "{" + pointA + ";" + pointB + "}";
    }
}

public class Exercise1Test {

    public static ArrayList<Exercise1Solution> testGenerateList(List<Point> points) {

        ArrayList<Exercise1Solution> sol = new ArrayList<Exercise1Solution>();
        int instr = 0;

        if (points.size() <= 1) {
            instr++;
            return sol;
        }

        for (int i = 1; i < points.size(); i++) {
            instr++;
            sol.add(new Exercise1Solution(points.get(0), points.get(i)));
        }

        points.remove(0);
        instr++;

        for (int i = 0; i < sol.size(); i++)
            sol.get(i).instructions += instr;

        sol.addAll(testGenerateList(points));

        return sol;
    }

    public static void main(String[] args) {

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


        // Generar lista debería aparear todos los points de una colección
        ArrayList<Exercise1Solution> solutions = testGenerateList(points);

        for (int i = 0; i < solutions.size(); i++)
            System.out.println(solutions.get(i));

        Exercise1Solution closest = Exercise1Solution.minimumDistanceBetweenPoints(solutions); // nlogn + C

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

        // System.exit(0) removed -- not appropriate in a library class
    }

    @SuppressWarnings("unused")
    private static double distance(int x, int y, int x2, int y2) {
        return Math.sqrt((x2 - x) * (x2 - x) + (y2 - y) * (y2 - y));
    }
}
