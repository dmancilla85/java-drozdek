package org.drozdek.memory.exercises;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReferenceCountingCollectorTest {

    @Test
    @DisplayName("Removing the last root frees the object")
    void removeRoot_freesObject() {
        ReferenceCountingCollector collector = new ReferenceCountingCollector();
        ReferenceCountingCollector.ReferenceObject object = collector.addObject("x");
        collector.addRoot(object);
        collector.removeRoot(object);
        assertNull(collector.getObject(object.getId()));
        assertEquals(0, collector.getObjectCount());
    }

    @Test
    @DisplayName("Rooted objects survive removal of an edge")
    void referenceCounting_keepsRooted() {
        ReferenceCountingCollector collector = new ReferenceCountingCollector();
        ReferenceCountingCollector.ReferenceObject root = collector.addObject("root");
        ReferenceCountingCollector.ReferenceObject child = collector.addObject("child");
        collector.addRoot(root);
        collector.addReference(root, child);
        collector.removeRoot(root);
        assertNull(collector.getObject(root.getId()));
        assertEquals(0, collector.getObjectCount());
    }

    @Test
    @DisplayName("Reference count increments per reference")
    void addReference_incrementsCount() {
        ReferenceCountingCollector collector = new ReferenceCountingCollector();
        ReferenceCountingCollector.ReferenceObject a = collector.addObject("a");
        ReferenceCountingCollector.ReferenceObject b = collector.addObject("b");
        collector.addRoot(a);
        collector.addReference(a, b);
        collector.addReference(a, b);
        assertEquals(2, b.getReferenceCount());
    }

    @Test
    @DisplayName("Root removal cascades to referenced objects")
    void removeRoot_cascadesDecrement() {
        ReferenceCountingCollector collector = new ReferenceCountingCollector();
        ReferenceCountingCollector.ReferenceObject a = collector.addObject("a");
        ReferenceCountingCollector.ReferenceObject b = collector.addObject("b");
        collector.addRoot(a);
        collector.addReference(a, b);
        collector.removeRoot(a);
        assertEquals(0, collector.getObjectCount());
    }

    @Test
    @DisplayName("Object kept while another root exists")
    void referenceCounting_keepsObjectWithSecondRoot() {
        ReferenceCountingCollector collector = new ReferenceCountingCollector();
        ReferenceCountingCollector.ReferenceObject object = collector.addObject("x");
        collector.addRoot(object);
        collector.addRoot(object);
        collector.removeRoot(object);
        assertEquals(object, collector.getObject(object.getId()));
    }
}
