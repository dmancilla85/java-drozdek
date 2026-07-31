package org.drozdek.trees.interfaces;

import org.drozdek.commons.DataTypeInterface;

/// Base interface for all tree data structures in this package.
/// Every tree provides isEmpty() and size() operations.
public interface TreeInterface extends DataTypeInterface {
    boolean isEmpty();
    int size();
}
