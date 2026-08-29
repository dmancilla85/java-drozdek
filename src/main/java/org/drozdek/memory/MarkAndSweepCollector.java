package org.drozdek.memory;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/// Mark-and-sweep garbage collector that reclaims unreachable objects.
///
/// Objects are tracked in a heap table with a flag indicating reachability.
/// The collector first marks every object reachable from a set of roots by a
/// graph traversal (mark phase), then sweeps the heap freeing any object that
/// was not marked (sweep phase).
///
/// **Real-world use case:** Tracing collector used by the JVM (parallel and
/// G1 stop-the-world phases) and language runtimes such as Ruby and Lua.
///
/// Complexity Analysis:
/// Time Complexity: O(n + e) for marking plus O(n) for sweeping
/// Auxiliary Space: O(n) worst case for the traversal work list
///
/// Bibliography:
///
/// - George E. Collins, *A Method for Overlapping and Erasure of Lists*.
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 12.
public class MarkAndSweepCollector {

    /// A tracked object in the collector's heap.
    public static final class HeapObject {
        private final int id;
        private final String data;
        private final List<HeapObject> references = new ArrayList<>();
        private boolean marked;

        private HeapObject(int id, String data) {
            this.id = id;
            this.data = data;
        }

        /// Returns the object's unique identifier.
        ///
        /// @return the identifier
        public int getId() {
            return id;
        }

        /// Returns the payload associated with the object.
        ///
        /// @return the stored data
        public String getData() {
            return data;
        }

        /// Returns the list of objects this object references.
        ///
        /// @return reference list
        public List<HeapObject> getReferences() {
            return references;
        }
    }

    private final Map<Integer, HeapObject> objects = new HashMap<>();
    private int nextId;

    /// Creates a new heap object with the given payload.
    ///
    /// @param data payload for the object
    /// @return the created object
    public HeapObject addObject(String data) {
        HeapObject object = new HeapObject(nextId++, data);
        objects.put(object.id, object);
        return object;
    }

    /// Connects {@code from} to {@code to}, recording a heap reference edge.
    ///
    /// @param from owning object
    /// @param to   referenced object
    public void addReference(HeapObject from, HeapObject to) {
        from.references.add(to);
    }

    /// Runs a mark-and-sweep pass, returning the number of objects reclaimed.
    ///
    /// @param roots objects considered reachable entry points
    /// @return number of objects collected as garbage
    public int collect(List<HeapObject> roots) {
        int before = objects.size();
        unmarkAll();
        for (HeapObject root : roots) {
            mark(root);
        }
        List<Integer> garbage = new ArrayList<>();
        for (HeapObject object : objects.values()) {
            if (!object.marked) {
                garbage.add(object.id);
            }
        }
        for (Integer id : garbage) {
            objects.remove(id);
        }
        return before - objects.size();
    }

    /// Returns the object with the given identifier, or {@code null}.
    ///
    /// @param id object identifier
    /// @return the object, or null if absent
    public HeapObject getObject(int id) {
        return objects.get(id);
    }

    /// Returns the current number of live objects in the heap.
    ///
    /// @return live object count
    public int getObjectCount() {
        return objects.size();
    }

    private void unmarkAll() {
        for (HeapObject object : objects.values()) {
            object.marked = false;
        }
    }

    private void mark(HeapObject root) {
        Deque<HeapObject> workList = new ArrayDeque<>();
        workList.push(root);
        while (!workList.isEmpty()) {
            HeapObject current = workList.pop();
            if (current.marked) {
                continue;
            }
            current.marked = true;
            for (HeapObject reference : current.references) {
                if (!reference.marked) {
                    workList.push(reference);
                }
            }
        }
    }
}
