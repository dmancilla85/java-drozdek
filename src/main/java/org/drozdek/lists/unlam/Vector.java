/**
 *
 */
package org.drozdek.lists.unlam;

import static java.lang.System.out;

/**
 * @author David A. Mancilla
 * @title Tipos de datos gen�ricos
 */
public class Vector<T> {

    /**
     *
     */
    private final static int TAM_INICIAL = 5;
    private final T[] vec;
    private int indice;
    private final int tam;

    public Vector() {
        // TODO Auto-generated constructor stub
        this(TAM_INICIAL);
    }

    @SuppressWarnings("unchecked")
    public Vector(int tam) {
        indice = 0;
        this.tam = tam;
        vec = (T[]) new Object[this.tam];
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //Vector<Empleado> a = new Vector<Empleado>();
        //Vector<ObraSocial> b = new Vector<ObraSocial>();
        String cadena = null;
        Vector<String> vecA = new Vector<String>();
        vecA.agregar("1 - Hola Mundo");
        vecA.agregar("2 - ProgrAvanza");
        vecA.agregar("3 - Java");
        vecA.agregar("4 - Inicio");
        vecA.agregar("5 - Prueba");
        vecA.agregar("6 - Rompe!!");

        while ((cadena = vecA.eliminar()) != null)
            out.println(cadena);

    }

    public boolean agregar(T dato) {
        return agregar(dato, indice == tam ? indice : indice++);
    }

    public boolean agregar(T dato, int pos) {

        if (pos >= 0 && pos < this.vec.length && dato != null) {
            this.vec[pos] = dato;
            return true;
        } else return false;
    }

    public Vector<?> clone() {
        Vector<T> aux = new Vector<T>(this.tam);
        for (int i = 0; i < this.vec.length; i++)
            aux.vec[i] = this.vec[i];

        return aux;
    }

    public T eliminar() {
        return eliminar(--indice);
    }

    public T eliminar(int pos) {

        T aux = null;

        if (pos >= 0 && pos < this.tam) {
            aux = vec[pos];
            vec[pos] = null;
        }

        return aux;

    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        return super.equals(obj);
    }

}
