package org.drozdek.graphs;

import java.util.LinkedHashSet;

/// Set abstraction backed by a LinkedHashSet supporting union, subset
/// tests, membership, and element removal with insertion-order iteration.
///
/// **Real-world use case:** Permission-group merging and tag-collection
/// operations in course exercises on set theory.
///
/// Complexity Analysis:
/// Time Complexity: O(1) expected add/contains/remove, O(n) subset/union
/// Auxiliary Space: O(n)
///
public class Set<T> {

    private final java.util.Set<T> elements;

    /// Creates an empty set backed by a LinkedHashSet.
    public Set() {
        elements = new LinkedHashSet<>();
    }

    /// Constructor with initial capacity hint.
    ///
    /// @param n initial capacity; non-positive yields an unset backing store
    @SuppressWarnings("unchecked")
    public Set(int n) {
        elements = n > 0 ? LinkedHashSet.newLinkedHashSet(n) : null;
    }

    /// Demo entry point populating a sample set.
    ///
    /// @param args unused
    static void main(@SuppressWarnings("unused") String[] args) {
        Set<Object> a = new Set<>();
        a.add("Hola");
        a.add(324.4);
        a.add(11);
        a.add("Chau");
    }

    /// Adds an element to the set, ignoring null values.
    ///
    /// @param e element to add
    public void add(T e) {
        if (e != null)
            elements.add(e);
    }

    /// Returns the number of elements currently stored.
    ///
    /// @return element count
    public int size() {
        return elements.size();
    }

    /// Tests whether every element of this set is contained in b.
    ///
    /// Complexity: O(n) containment checks against the other set.
    ///
    /// @param b candidate superset
    /// @return true if this set is a subset of b
    public boolean isSubsetOf(Set<T> b) {
        return this.elements.containsAll(b.elements);
    }

    /// Checks membership of an element in the set.
    ///
    /// @param e element to look up
    /// @return true if the element is present
    public boolean contains(T e) {
        if (e != null)
            return elements.contains(e);

        return false;
    }

    /// Removes an element from the set, ignoring null values.
    ///
    /// @param e element to remove
    public void remove(T e) {
        if (e != null)
            elements.remove(e);
    }

    /// Removes from this set every element found in b.
    ///
    /// @param b set whose elements are removed from this one
    public void removeAll(Set<T> b) {
        this.elements.removeAll(b.elements);
    }

    /// Extends this set with every element of b, keeping insertion
    /// order.
    ///
    /// @param b set merged into this one
    public void union(Set<T> b) {
        this.elements.addAll(b.elements);
    }

}
