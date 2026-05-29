package org.drozdek.graphs.unlam;

import java.util.Comparator;

/// @author David
public class Vertex implements Comparable<Object>, Comparator<Object> {

    protected static final int A_MINUSC = 97;
    protected int key;
    protected String name;
    protected Integer color;
    protected int degree;


    public Vertex(int key, String name) {
        this.key = key;
        this.name = name;
        this.degree = 0;
        this.color = 0;
    }

    public Vertex(int key) {
        this(key, "");
        this.name = Character.toString(generateName(key));
    }

    public Vertex() {
        this(0, null);
    }

    public static char generateName(int i) {
        return (char) (A_MINUSC + i);
    }

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

    public String toString() {
        return "{ Key = " + key + ", Name = "
                + name + ", Degree = " + degree + "}";
    }


}
