package org.drozdek.trees.interfaces;

import org.drozdek.commons.DataTypeInterface;

/// Base interface for all tree data structures in this package.
/// Every tree provides isEmpty() and size() operations.
public interface TreeInterface extends DataTypeInterface {
    /// Reports whether the tree holds no elements.
    ///
    /// @return true when the tree is empty
    boolean isEmpty();

    /// Counts the elements currently stored in the tree.
    ///
    /// @return number of stored elements
    int size();
}
