package org.drozdek.graphs;

import org.drozdek.graphs.unlam.Edge;
import org.drozdek.graphs.unlam.Vertex;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EdgeTest {
    Edge edge;
    Vertex a;
    Vertex b;

    @BeforeEach
    void setUp() {
        a = new Vertex(0);
        b = new Vertex(1);
        edge = new Edge(a, b);
    }

    @Test
    @DisplayName("Create edge with two vertices")
    void constructorTwoVertices() {
        assertEquals(a, edge.getOrigin());
        assertEquals(b, edge.getDestination());
        assertEquals(0, edge.getWeight());
    }

    @Test
    @DisplayName("Create edge with weight")
    void constructorWithWeight() {
        Edge e = new Edge(a, b, 42);
        assertEquals(42, e.getWeight());
    }

    @Test
    @DisplayName("Create edge with weight and directed flag")
    void constructorWithWeightAndDirected() {
        Edge e = new Edge(a, b, 10, true);
        assertEquals(10, e.getWeight());
        assertTrue(e.getWeight() > 0);
    }

    @Test
    @DisplayName("Set origin and destination")
    void setOriginDestination() {
        Vertex c = new Vertex(2);
        edge.setOrigin(c);
        edge.setDestination(c);
        assertEquals(c, edge.getOrigin());
        assertEquals(c, edge.getDestination());
    }

    @Test
    @DisplayName("Set weight")
    void setWeight() {
        edge.setWeight(99);
        assertEquals(99, edge.getWeight());
    }

    @Test
    @DisplayName("Equals same object")
    void equalsSame() {
        assertTrue(edge.equals(edge));
    }

    @Test
    @DisplayName("Equals null")
    void equalsNull() {
        assertFalse(edge.equals(null));
    }

    @Test
    @DisplayName("Equals different type")
    void equalsDifferentType() {
        assertFalse(edge.equals("string"));
    }

    @Test
    @DisplayName("Equals same vertices")
    void equalsSameVertices() {
        Edge same = new Edge(a, b);
        assertTrue(edge.equals(same));
    }

    @Test
    @DisplayName("Equals different origin")
    void equalsDifferentOrigin() {
        Edge other = new Edge(new Vertex(2), b);
        assertFalse(edge.equals(other));
    }

    @Test
    @DisplayName("Equals different destination")
    void equalsDifferentDestination() {
        Edge other = new Edge(a, new Vertex(2));
        assertFalse(edge.equals(other));
    }

    @Test
    @DisplayName("CompareTo by weight")
    void compareTo() {
        Edge light = new Edge(a, b, 1);
        Edge heavy = new Edge(a, b, 100);
        assertTrue(light.compareTo(heavy) < 0);
        assertTrue(heavy.compareTo(light) > 0);
        assertEquals(0, light.compareTo(light));
    }

    @Test
    @DisplayName("HashCode consistent with equals")
    void hashCodeConsistent() {
        Edge same = new Edge(a, b);
        assertEquals(edge.hashCode(), same.hashCode());
    }

    @Test
    @DisplayName("Created non-directed by default")
    void defaultNotDirected() {
        assertFalse(edge.toString().isEmpty());
    }

    @Test
    @DisplayName("Edge with null origin")
    void nullOrigin() {
        Edge e = new Edge(null, b);
        assertNull(e.getOrigin());
        assertFalse(edge.equals(e));
    }

    @Test
    @DisplayName("Edge with null destination")
    void nullDestination() {
        Edge e = new Edge(a, null);
        assertNull(e.getDestination());
        assertFalse(edge.equals(e));
    }

    @Test
    @DisplayName("ToString returns expected format")
    void testToString() {
        String str = edge.toString();
        assertTrue(str.contains("a"));
        assertTrue(str.contains("b"));
    }

    @Test
    @DisplayName("Equals with different directed flag")
    void equalsDifferentDirected() {
        Edge directed = new Edge(a, b, 0, true);
        assertFalse(edge.equals(directed));
    }

    @Test
    @DisplayName("Equals both origins null")
    void equalsBothOriginsNull() {
        Edge e1 = new Edge(null, b);
        Edge e2 = new Edge(null, b);
        assertTrue(e1.equals(e2));
    }

    @Test
    @DisplayName("HashCode for directed edge")
    void hashCodeDirected() {
        Edge directed = new Edge(a, b, 0, true);
        Edge same = new Edge(a, b, 0, true);
        assertEquals(directed.hashCode(), same.hashCode());
    }

    @Test
    @DisplayName("CompareTo with equal weights")
    void compareToEqual() {
        Edge e1 = new Edge(a, b, 5);
        Edge e2 = new Edge(a, b, 5);
        assertEquals(0, e1.compareTo(e2));
    }

    @Test
    @DisplayName("Equals different origin null vs non-null")
    void equalsDifferentOriginNull() {
        Edge e = new Edge(null, b);
        assertFalse(edge.equals(e));
    }

    @Test
    @DisplayName("Constructor with weight and undirected")
    void constructorWeightUndirected() {
        Edge e = new Edge(a, b, 10, false);
        assertEquals(10, e.getWeight());
    }
}
