package org.drozdek.graphs;

import java.util.Comparator;

/// Graph vertex holding an integer key, an optional display name, a color
/// mark used by traversal and coloring algorithms, and its degree.
///
/// **Real-world use case:** Intersections in road networks, users in
/// social graphs, and web pages in link-analysis models.
///
/// Complexity Analysis:
/// Time Complexity: O(1) for all operations
/// Auxiliary Space: O(1)
///
/// Bibliography:
///
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 8.
///
public class Vertex implements Comparable<Object>, Comparator<Object> {

    protected static final int A_MINUSC = 97;
    protected int key;
    protected String name;
    protected Integer color;
    protected int degree;


    /// Creates a vertex with an explicit key and display name.
    ///
    /// @param key  integer identifier of the vertex
    /// @param name display name used by printouts; may be null
    public Vertex(int key, String name) {
        this.key = key;
        this.name = name;
        this.degree = 0;
        this.color = 0;
    }

    /// Creates a vertex whose display name is derived from its key,
    /// mapping key 0 to 'a', 1 to 'b', and so on.
    ///
    /// @param key integer identifier of the vertex
    public Vertex(int key) {
        this(key, "");
        this.name = Character.toString(generateName(key));
    }

    /// Creates a default vertex with key 0 and no display name.
    public Vertex() {
        this(0, null);
    }

    /// Maps a vertex index to its letter name, where 0 becomes 'a'.
    ///
    /// @param i vertex index
    /// @return the letter corresponding to the index
    public static char generateName(int i) {
        return (char) (A_MINUSC + i);
    }

    /// Increments the degree counter by one.
    public void increaseDegree() {
        degree++;
    }

    @Override
    public int compare(Object arg0, Object arg1) {
        return Integer.compare(((Vertex) arg0).degree, ((Vertex) arg1).degree);
    }

    @Override
    public int compareTo(Object arg0) {
        return this.degree - ((Vertex) arg0).degree;
    }

    /// Decrements the degree counter, never letting it go below zero.
    public void decreaseDegree() {
        if (degree > 0)
            degree--;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (!(obj instanceof Vertex other))
            return false;

        if (key != other.key)
            return false;

        if (degree != other.degree)
            return false;

        if (name == null) {
            return other.name == null;
        } else
            return name.equals(other.name);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + key;
        result = prime * result + degree;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    public int getKey() {
        return key;
    }

    public int getDegree() {
        return degree;
    }

    public String getName() {
        return name;
    }

    public void setColor(Integer color) {
        if (color > 0)
            this.color = color;
    }

    /// Renders the key, name, and degree of the vertex.
    ///
    /// @return printable representation of the vertex
    public String toString() {
        return "{ Key = " + key + ", Name = "
                + name + ", Degree = " + degree + "}";
    }


}
