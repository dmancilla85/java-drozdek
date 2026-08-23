package org.drozdek.commons;

import java.util.Comparator;

/// Natural-order comparator for integers, mirroring Integer.compareTo.
///
/// **Real-world use case:** Teaching example for Comparator contracts and
/// pluggable ordering in sorts and priority queues.
///
/// Complexity Analysis:
/// Time Complexity: O(1) per comparison
/// Auxiliary Space: O(1)
///
public class CompareExample implements Comparator<Integer> {

    @Override
    public int compare(Integer o1, Integer o2) {
        return o1.compareTo(o2);
    }

}
