/**
 *
 */
package org.drozdek.graphs.unlam;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lab4
 *
 */
public class Set<T> {

    private List<T> elementos;

    /**
     *
     */
    public Set() {
        // TODO Auto-generated constructor stub
        elementos = new ArrayList<T>();
    }

    /**
     *
     */
    public Set(int n) {
        // TODO Auto-generated constructor stub
        if (n > 0)
            elementos = new ArrayList<T>(n);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Set<Object> A = new Set<Object>();
        A.agregar("Hola");
        A.agregar(324.4);
        A.agregar(11);
        A.agregar("Chau");


        // desarrollo 20 minutos
        // tiempo total 26 minutos

    }

    public void agregar(T e) {
        if (e != null)
            elementos.add(e);
    }

    public int cardinalidad() {
        return elementos.size();
    }

    public boolean estaIncluido(Set<T> B) {
        return this.elementos.containsAll(B.elementos);
    }

    public boolean perteneceA(T e) {
        if (e != null)
            return elementos.contains(e);

        return false;
    }

    public void quitar(T e) {
        if (e != null)
            elementos.remove(e);
    }

    public void quitarTodo(Set<T> B) {
        this.elementos.removeAll(B.elementos);
    }

    public void union(Set<T> B) {
        this.elementos.addAll(B.elementos);
    }

}
