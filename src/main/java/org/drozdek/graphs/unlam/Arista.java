/**
 *
 */
package org.drozdek.graphs.unlam;

import org.drozdek.commons.LoggerService;

/**
 * @author David
 *
 */
public class Arista implements Comparable<Object> {

    protected Vertice origen;
    protected Vertice destino;
    protected int peso;
    protected boolean esDirigido;


    public Arista(Vertice v1, Vertice v2) {
        this(v1, v2, 0, false);
    }

    public Arista(Vertice v1, Vertice v2, int peso) {
        this(v1, v2, peso, false);
    }

    /**
     *
     */
    public Arista(Vertice v1, Vertice v2, int peso, boolean dirigido) {
        this.origen = v1;
        this.destino = v2;
        this.peso = peso;
        this.esDirigido = dirigido;
    }

    public static void main(String[] args) {
        Vertice a = new Vertice(0);
        Vertice b = new Vertice(1);
        Vertice c = new Vertice(2, "Juan"); //$NON-NLS-1$
        Vertice d = new Vertice(3);

        LoggerService.logInfo(a.toString());
        LoggerService.logInfo(b.toString());
        LoggerService.logInfo(c.toString());
        LoggerService.logInfo(d.toString());

        Arista ab = new Arista(a, b);
        Arista ba = new Arista(b, a);
        LoggerService.logInfo(ab.toString());
        LoggerService.logInfo(ba.toString());
        LoggerService.logInfo("ab es igual a ba: " + ab.equals(ba)); //$NON-NLS-1$

    }

    @Override
    public int compareTo(Object o) {
        // TODO Auto-generated method stub
        return this.peso - ((Arista) o).peso;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Arista other))
            return false;
        if (destino == null) {
            if (other.destino != null)
                return false;
        } else if (!destino.equals(other.destino))
            return false;
        if (esDirigido != other.esDirigido)
            return false;
        if (origen == null) {
            return other.origen == null;
        } else return origen.equals(other.origen);
    }

    /**
     * @return the destino
     */
    public Vertice getDestino() {
        return destino;
    }

    /**
     * @param destino the destino to set
     */
    public void setDestino(Vertice destino) {
        this.destino = destino;
    }

    /**
     * @return the origen
     */
    public Vertice getOrigen() {
        return origen;
    }

    /**
     * @param origen the origen to set
     */
    public void setOrigen(Vertice origen) {
        this.origen = origen;
    }

    /**
     * @return the peso
     */
    public int getPeso() {
        return peso;
    }

    /**
     * @param peso the peso to set
     */
    public void setPeso(int peso) {
        this.peso = peso;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((destino == null) ? 0 : destino.hashCode());
        result = prime * result + (esDirigido ? 1231 : 1237);
        result = prime * result + ((origen == null) ? 0 : origen.hashCode());
        return result;
    }

    public String toString() {
        return "{" + this.origen.obtenerNombre()
                + ", " + this.destino.obtenerNombre() + "}";
    }

}
