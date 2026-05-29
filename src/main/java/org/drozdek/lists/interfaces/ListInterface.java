package org.drozdek.lists.interfaces;

import org.drozdek.commons.DataTypeInterface;

public interface ListInterface<T> extends DataTypeInterface {

    boolean isEmpty();

    int size();

    T find(T data);

    void delete(T data);

    void add(T data);

    T first();
}
