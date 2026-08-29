package org.drozdek.memory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MarkAndSweepCollectorTest {

    @Test
    @DisplayName("Reachable objects survive a collection")
    void collect_keepsReachable() {
        MarkAndSweepCollector collector = new MarkAndSweepCollector();
        MarkAndSweepCollector.HeapObject root = collector.addObject("root");
        collector.collect(List.of(root));
        assertEquals(root, collector.getObject(root.getId()));
    }

    @Test
    @DisplayName("Unreferenced objects are reclaimed")
    void collect_reclaimsGarbage() {
        MarkAndSweepCollector collector = new MarkAndSweepCollector();
        MarkAndSweepCollector.HeapObject root = collector.addObject("root");
        MarkAndSweepCollector.HeapObject garbage = collector.addObject("garbage");
        collector.collect(List.of(root));
        assertEquals(1, collector.getObjectCount());
        assertNull(collector.getObject(garbage.getId()));
    }

    @Test
    @DisplayName("Objects reachable transitively are kept")
    void collect_keepsTransitiveReachability() {
        MarkAndSweepCollector collector = new MarkAndSweepCollector();
        MarkAndSweepCollector.HeapObject root = collector.addObject("root");
        MarkAndSweepCollector.HeapObject mid = collector.addObject("mid");
        MarkAndSweepCollector.HeapObject leaf = collector.addObject("leaf");
        collector.addReference(root, mid);
        collector.addReference(mid, leaf);
        collector.collect(List.of(root));
        assertEquals(3, collector.getObjectCount());
    }

    @Test
    @DisplayName("Garbage count returned matches reclaimed objects")
    void collect_returnsReclaimedCount() {
        MarkAndSweepCollector collector = new MarkAndSweepCollector();
        MarkAndSweepCollector.HeapObject root = collector.addObject("root");
        MarkAndSweepCollector.HeapObject a = collector.addObject("a");
        MarkAndSweepCollector.HeapObject b = collector.addObject("b");
        collector.addReference(root, a);
        int reclaimed = collector.collect(List.of(root));
        assertEquals(1, reclaimed);
    }

    @Test
    @DisplayName("Empty roots reclaim everything")
    void collect_emptyRootsReclaimAll() {
        MarkAndSweepCollector collector = new MarkAndSweepCollector();
        collector.addObject("x");
        collector.addObject("y");
        collector.collect(List.of());
        assertEquals(0, collector.getObjectCount());
    }

    @Test
    @DisplayName("Cyclic garbage without roots is reclaimed")
    void collect_reclaimsCycle() {
        MarkAndSweepCollector collector = new MarkAndSweepCollector();
        MarkAndSweepCollector.HeapObject a = collector.addObject("a");
        MarkAndSweepCollector.HeapObject b = collector.addObject("b");
        collector.addReference(a, b);
        collector.addReference(b, a);
        collector.collect(List.of());
        assertEquals(0, collector.getObjectCount());
    }
}
