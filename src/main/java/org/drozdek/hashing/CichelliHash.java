package org.drozdek.hashing;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/// Cichelli's algorithm for constructing a perfect hash function over a fixed,
/// known set of text keys.
///
/// Each key is assigned a slot from a weighted combination of its first
/// character, last character, and its length. The algorithm performs a
/// backtracking search over first/last-character assignments until every key
/// maps to a distinct slot with no collisions, producing a perfect hash for
/// the given set within a range bounded by twice the number of keys.
///
/// **Real-world use case:** Compiler reserved-word tables and keyword lookup
/// tables where keys are fixed and every lookup must be O(1) with no
/// collisions.
///
/// Complexity Analysis:
/// Time Complexity: O(n^c) worst case for the backtracking search
/// Auxiliary Space: O(n) for the key set and character tables
///
/// Bibliography:
///
/// - Cichelli, R. A note on the minimal perfect hashing of keywords. *Communications of the ACM*.
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 10.
public final class CichelliHash {

    private static final int SLOT_RANGE_FACTOR = 2;

    private final List<String> keys;
    private final List<Character> edgeChars = new ArrayList<>();
    private final int[] charValues;

    private CichelliHash(List<String> keys) {
        this.keys = new ArrayList<>(keys);
        Set<Character> distinct = new LinkedHashSet<>();
        for (String key : keys) {
            distinct.add(key.charAt(0));
            distinct.add(key.charAt(key.length() - 1));
        }
        edgeChars.addAll(distinct);
        this.charValues = new int[edgeChars.size()];
    }

    /// Attempts to construct a perfect hash and returns the resulting slot for
    /// the given key, or `-1` if the search failed or the key is not in the set.
    ///
    /// @param keys fixed set of keys to hash
    /// @param key  key to look up
    /// @return a distinct slot in {@code [0, 2 * keys.size())} or `-1`
    public static int perfectHash(List<String> keys, String key) {
        CichelliHash solver = new CichelliHash(keys);
        if (!solver.keys.contains(key)) {
            return -1;
        }
        if (!solver.solve()) {
            return -1;
        }
        int slot = solver.slot(key);
        return slot < 0 ? -1 : slot;
    }

    private boolean solve() {
        return search(0);
    }

    private boolean search(int index) {
        if (index == edgeChars.size()) {
            return areSlotsDistinct();
        }
        for (int value = 0; value < keys.size(); value++) {
            charValues[index] = value;
            if (search(index + 1)) {
                return true;
            }
        }
        charValues[index] = 0;
        return false;
    }

    private boolean areSlotsDistinct() {
        List<Integer> used = new ArrayList<>();
        for (String key : keys) {
            int slot = slot(key);
            if (slot < 0 || used.contains(slot)) {
                return false;
            }
            used.add(slot);
        }
        return true;
    }

    private int slot(String key) {
        int first = charValue(key.charAt(0));
        int last = charValue(key.charAt(key.length() - 1));
        int value = first + last + key.length();
        int range = keys.size() * SLOT_RANGE_FACTOR;
        return value >= 0 && value < range ? value : -1;
    }

    private int charValue(char c) {
        for (int i = 0; i < edgeChars.size(); i++) {
            if (edgeChars.get(i) == c) {
                return charValues[i];
            }
        }
        return 0;
    }
}
