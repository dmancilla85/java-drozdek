package org.drozdek.graphs.unlam;

import java.util.ArrayList;
import java.util.List;

/// @author lab4
public class Set<T> {

    private List<T> elements;

    public Set() {
        elements = new ArrayList<T>();
    }

    /// @param n
    public Set(int n) {
        if (n > 0)
            elements = new ArrayList<T>(n);
    }

    /// @param args
    static void main(String[] args) {
        Set<Object> A = new Set<Object>();
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

    public boolean isSubsetOf(Set<T> B) {
        return this.elements.containsAll(B.elements);
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

    public void removeAll(Set<T> B) {
        this.elements.removeAll(B.elements);
    }

    public void union(Set<T> B) {
        this.elements.addAll(B.elements);
    }

}
