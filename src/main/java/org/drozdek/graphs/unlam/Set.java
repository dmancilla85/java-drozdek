package org.drozdek.graphs.unlam;

import java.util.LinkedHashSet;

/// @author lab4
public class Set<T> {

    private final java.util.Set<T> elements;

    public Set() {
        elements = new LinkedHashSet<>();
    }

    /// @param n
    @SuppressWarnings("unchecked")
    public Set(int n) {
        elements = n > 0 ? new LinkedHashSet<>(n) : null;
    }

    /// @param args
    static void main(String[] args) {
        Set<Object> A = new Set<>();
        A.add("Hola");
        A.add(324.4);
        A.add(11);
        A.add("Chau");
    }

    public void add(T e) {
        if (e != null)
            elements.add(e);
    }

    public int size() {
        return elements.size();
    }

    public boolean isSubsetOf(Set<T> b) {
        return this.elements.containsAll(b.elements);
    }

    public boolean contains(T e) {
        if (e != null)
            return elements.contains(e);

        return false;
    }

    public void remove(T e) {
        if (e != null)
            elements.remove(e);
    }

    public void removeAll(Set<T> b) {
        this.elements.removeAll(b.elements);
    }

    public void union(Set<T> b) {
        this.elements.addAll(b.elements);
    }

}
