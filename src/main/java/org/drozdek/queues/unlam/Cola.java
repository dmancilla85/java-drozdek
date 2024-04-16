package org.drozdek.queues.unlam;

public interface Cola {

    Object desencolar();

    boolean encolar(Object obj);

    boolean estaVacia();

    Object tope();

    void vaciar();
}
