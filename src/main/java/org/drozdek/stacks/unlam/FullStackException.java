package org.drozdek.stacks.unlam;

public class FullStackException extends ArrayIndexOutOfBoundsException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public FullStackException() {
        super("La pila no dispone de m�s capacidad para almacenar datos");
    }
}
