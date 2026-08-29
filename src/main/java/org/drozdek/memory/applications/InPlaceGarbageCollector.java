package org.drozdek.memory.applications;

import java.util.ArrayList;
import java.util.List;
import org.drozdek.memory.MarkAndSweepCollector;

/// Application that demonstrates automatic memory reclamation through an
/// in-place mark-and-sweep garbage-collector cycle.
///
/// It builds a small object graph, records a reference path from the roots,
/// drops a subtree so it becomes unreachable, and then runs a collection pass
/// to show that only the still-reachable objects survive.
///
/// **Real-world use case:** A runnable illustration of how managed runtimes
/// reclaim cyclic and abandoned object graphs without manual freeing.
///
/// Complexity Analysis:
/// Time Complexity: O(n + e) for the collection cycle over n objects and e edges
/// Auxiliary Space: O(n) for the traversal work list
///
/// Bibliography:
///
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 12.
public class InPlaceGarbageCollector {

    private final MarkAndSweepCollector collector = new MarkAndSweepCollector();

    /// Runs a full allocation-plus-collection cycle on a fixed object graph and
    /// reports how many garbage objects were reclaimed.
    ///
    /// @return the number of objects collected as garbage
    public int runCollectorCycle() {
        MarkAndSweepCollector.HeapObject root = collector.addObject("root");
        MarkAndSweepCollector.HeapObject live = collector.addObject("live");
        MarkAndSweepCollector.HeapObject garbage = collector.addObject("garbage");
        MarkAndSweepCollector.HeapObject orphan = collector.addObject("orphan");

        collector.addReference(root, live);
        collector.addReference(garbage, orphan);

        List<MarkAndSweepCollector.HeapObject> roots = new ArrayList<>();
        roots.add(root);

        return collector.collect(roots);
    }

    /// Returns the underlying collector for inspection after a cycle.
    ///
    /// @return the backing {@link MarkAndSweepCollector}
    public MarkAndSweepCollector getCollector() {
        return collector;
    }
}
