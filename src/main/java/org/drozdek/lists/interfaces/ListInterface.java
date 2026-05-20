package org.drozdek.lists.interfaces;

public interface ListInterface<T> {
    boolean isEmpty();
    int size();
    void printAll();
    T find(T data);
    void delete(T data);
    void add(T data);
    T first();
}
