package org.drozdek.graphs;

import org.drozdek.graphs.unlam.Vertex;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VertexTest {
    Vertex vertex;

    @BeforeEach
    void setUp() {
        vertex = new Vertex(0);
    }

    @Test
    @DisplayName("Create vertex with key generates correct name")
    void constructorWithKey() {
        assertEquals(0, vertex.getKey());
        assertEquals("a", vertex.getName());
        assertEquals(0, vertex.getDegree());
    }

    @Test
    @DisplayName("Create vertex with key and name")
    void constructorWithKeyAndName() {
        Vertex v = new Vertex(1, "custom");
        assertEquals(1, v.getKey());
        assertEquals("custom", v.getName());
    }

    @Test
    @DisplayName("Default constructor")
    void defaultConstructor() {
        Vertex v = new Vertex();
        assertEquals(0, v.getKey());
        assertNull(v.getName());
    }

    @Test
    @DisplayName("Generate name from key")
    void generateName() {
        assertEquals('a', Vertex.generateName(0));
        assertEquals('b', Vertex.generateName(1));
        assertEquals('z', Vertex.generateName(25));
    }

    @Test
    @DisplayName("Increase and decrease degree")
    void degree() {
        vertex.increaseDegree();
        assertEquals(1, vertex.getDegree());
        vertex.increaseDegree();
        assertEquals(2, vertex.getDegree());
        vertex.decreaseDegree();
        assertEquals(1, vertex.getDegree());
    }

    @Test
    @DisplayName("Decrease degree below zero does nothing")
    void decreaseDegreeBelowZero() {
        vertex.decreaseDegree();
        assertEquals(0, vertex.getDegree());
    }

    @Test
    @DisplayName("Set color only if positive")
    void setColor() {
        vertex.setColor(5);
        vertex.setColor(-1);
    }

    @Test
    @DisplayName("Equals same object")
    void equalsSame() {
        assertTrue(vertex.equals(vertex));
    }

    @Test
    @DisplayName("Equals null")
    void equalsNull() {
        assertFalse(vertex.equals(null));
    }

    @Test
    @DisplayName("Equals different type")
    void equalsDifferentType() {
        assertFalse(vertex.equals("string"));
    }

    @Test
    @DisplayName("Equals same key and degree")
    void equalsSameKey() {
        Vertex v2 = new Vertex(0);
        assertTrue(vertex.equals(v2));
    }

    @Test
    @DisplayName("Equals different key")
    void equalsDifferentKey() {
        Vertex v2 = new Vertex(1);
        assertFalse(vertex.equals(v2));
    }

    @Test
    @DisplayName("HashCode consistent with equals")
    void hashCodeConsistent() {
        Vertex v2 = new Vertex(0);
        assertEquals(vertex.hashCode(), v2.hashCode());
    }

    @Test
    @DisplayName("CompareTo by degree")
    void compareTo() {
        Vertex v1 = new Vertex(0);
        Vertex v2 = new Vertex(1);
        v2.increaseDegree();
        assertTrue(v1.compareTo(v2) < 0);
        assertTrue(v2.compareTo(v1) > 0);
        assertEquals(0, v1.compareTo(v1));
    }

    @Test
    @DisplayName("ToString returns expected format")
    void testToString() {
        assertTrue(vertex.toString().contains("Key = 0"));
        assertTrue(vertex.toString().contains("Name = a"));
    }

    @Test
    @DisplayName("Vertex with null name")
    void nullNameVertex() {
        Vertex v = new Vertex(0, null);
        assertNull(v.getName());
        v.getKey();
    }
}
