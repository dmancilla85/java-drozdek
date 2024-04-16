/**
 *
 */
package org.drozdek.graphs.unlam;

import java.util.Comparator;

/**
 * @author David
 */
public class Vertice implements Comparable<Object>, Comparator<Object> {

    protected static final int A_MINUSC = 97;
    protected int clave;
    protected String nombre;
    protected Integer color;
    protected int grado;


    public Vertice(int clave, String nombre) {
        this.clave = clave;
        this.nombre = nombre;
        this.grado = 0;
        // Sin color
        this.color = 0;
    }

    public Vertice(int clave) {
        this(clave, "");
        this.nombre = Character.toString(generaNombre(clave));
    }

    /**
     *
     */
    public Vertice() {
        this(0, null);
    }

    public static char generaNombre(int i) {
        return (char) (A_MINUSC + i);
    }

    public void aumentarGrado() {
        grado++;
    }

    @Override
    public int compare(Object arg0, Object arg1) {
        return Integer.compare(((Vertice) arg0).grado, ((Vertice) arg1).grado);
    }

    @Override
    public int compareTo(Object arg0) {
        return this.grado - ((Vertice) arg0).grado;
    }

    public void disminuirGrado() {
        if (grado > 0)
            grado--;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (!(obj instanceof Vertice other))
            return false;

        if (clave != other.clave)
            return false;

        if (grado != other.grado)
            return false;

        if (nombre == null) {
            return other.nombre == null;
        } else
            return nombre.equals(other.nombre);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + clave;
        result = prime * result + grado;
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        return result;
    }

    public int obtenerClave() {
        return clave;
    }

    public int obtenerGrado() {
        return grado;
    }

    public String obtenerNombre() {
        return nombre;
    }

    public void pintar(Integer color) {
        if (color > 0)
            this.color = color;
    }

    public String toString() {
        return "{ Clave = " + clave + ", Nombre = "
                + nombre + ", Grado = " + grado + "}";
    }


}
