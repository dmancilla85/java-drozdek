package org.drozdek.lists.unlam;

public interface Lista {

    int buscar(Object dato);

    Object buscar(int pos);

    boolean empty();

    boolean erase(int pos);

    boolean insert(int pos, Object dato);

    Object pop_back();

    Object pop_front();

    boolean push_back(Object dato);

    boolean push_front(Object dato);

    boolean remove(Object dato);

    boolean reverse();

    void vaciar();

}
