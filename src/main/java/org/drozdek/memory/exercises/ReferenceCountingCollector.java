package org.drozdek.memory.exercises;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/// Reference-counting garbage collector that reclaims objects as soon as
/// their reference count drops to zero.
///
/// Each object tracks how many other objects (or roots) reference it. Adding a
/// reference increments the count; removing a root or an edge decrements it.
/// When a count reaches zero the object is freed immediately and any objects
/// it referenced are also decremented, propagating reclamation transitively.
///
/// **Real-world use case:** Python's refcounting, PHP, Swift ARC, and COM
/// objects; complementary to tracing collectors, though it cannot reclaim
/// cyclic garbage on its own.
///
/// Complexity Analysis:
/// Time Complexity: O(1) amortized per reference mutation, O(n) per reclaim cascade
/// Auxiliary Space: O(n) for the object table and reference edges
///
/// Bibliography:
///
/// - G. Collins, *A Method for Overlapping and Erasure of Lists* (1960).
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 12.
public class ReferenceCountingCollector {

    /// A reference-counted heap object.
    public static final class ReferenceObject {
        private final int id;
        private final String data;
        private int count;
        private final List<ReferenceObject> outgoing = new ArrayList<>();

        private ReferenceObject(int id, String data) {
            this.id = id;
            this.data = data;
        }

        /// Returns the object's unique identifier.
        ///
        /// @return the identifier
        public int getId() {
            return id;
        }

        /// Returns the current reference count.
        ///
        /// @return number of live references
        public int getReferenceCount() {
            return count;
        }
    }

    private final Map<Integer, ReferenceObject> objects = new HashMap<>();
    private int nextId;

    /// Creates a new object, initially unreferenced.
    ///
    /// @param data payload for the object
    /// @return the created object
    public ReferenceObject addObject(String data) {
        ReferenceObject object = new ReferenceObject(nextId++, data);
        objects.put(object.id, object);
        return object;
    }

    /// Adds a root reference to an object, increasing its count.
    ///
    /// @param object object to retain
    public void addRoot(ReferenceObject object) {
        object.count++;
    }

    /// Removes a root reference, freeing the object if its count reaches zero.
    ///
    /// @param object object to release
    public void removeRoot(ReferenceObject object) {
        decode(object);
    }

    /// Connects {@code from} to {@code to} with a strong reference.
    ///
    /// @param from referencing object
    /// @param to   referenced object
    public void addReference(ReferenceObject from, ReferenceObject to) {
        from.outgoing.add(to);
        to.count++;
    }

    /// Breaks the connection between {@code from} and {@code to}, freeing any
    /// objects that become unreferenced.
    ///
    /// @param from referencing object
    /// @param to   referenced object
    public void removeReference(ReferenceObject from, ReferenceObject to) {
        if (!from.outgoing.remove(to)) {
            return;
        }
        decode(to);
    }

    private void decode(ReferenceObject object) {
        object.count--;
        if (object.count > 0 || !objects.containsKey(object.id)) {
            return;
        }
        objects.remove(object.id);
        for (ReferenceObject child : new ArrayList<>(object.outgoing)) {
            decode(child);
        }
    }

    /// Number of objects still present in the heap.
    ///
    /// @return live object count
    public int getObjectCount() {
        return objects.size();
    }

    /// Returns the object with the given identifier, or {@code null}.
    ///
    /// @param id object identifier
    /// @return the object, or null if absent
    public ReferenceObject getObject(int id) {
        return objects.get(id);
    }
}
