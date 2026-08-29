package org.drozdek.lists.exercises;

import java.util.ArrayList;
import java.util.List;

/// Solves the Josephus problem using a circular list simulation.
///
/// In the classic puzzle, `n` people stand in a circle and every `k`-th
/// person is eliminated until one remains. The exercise simulates the
/// elimination using a circular list, removing one person at a time by
/// advancing `k` steps, which is a direct illustration of circular-list
/// traversal.
///
/// **Real-world use case:** A classic educational demonstration of circular
/// lists and the reduction of a puzzle to a simple index-advancing loop.
///
/// Complexity Analysis:
/// Time Complexity: O(n * k) worst case for the elimination loop
/// Auxiliary Space: O(n) for the circle
///
/// Bibliography:
///
/// - Josephus problem. *Wikipedia*. https://en.wikipedia.org/wiki/Josephus_problem
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 3.
public final class JosephusProblem {

    private JosephusProblem() {
        // do nothing
    }

    /// Returns the survivors of the Josephus elimination in removal order,
    /// with the final survivor listed last.
    ///
    /// @param n number of people in the circle (>= 1)
    /// @param k step size, eliminating every k-th person (>= 1)
    /// @return elimination order ending with the sole survivor (1-indexed)
    public static List<Integer> eliminationOrder(int n, int k) {
        List<Integer> circle = new ArrayList<>(n);
        for (int i = 1; i <= n; i++) {
            circle.add(i);
        }
        List<Integer> order = new ArrayList<>(n);
        int index = 0;
        while (!circle.isEmpty()) {
            index = (index + k - 1) % circle.size();
            order.add(circle.remove(index));
        }
        return order;
    }

    /// Returns the single survivor using the closed-form recurrence.
    ///
    /// @param n number of people (>= 1)
    /// @param k step size (>= 1)
    /// @return the 1-indexed position of the survivor
    public static int survivor(int n, int k) {
        int result = 0;
        for (int i = 2; i <= n; i++) {
            result = (result + k) % i;
        }
        return result + 1;
    }
}
