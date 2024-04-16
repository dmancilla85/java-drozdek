package org.drozdek.trees.unlam;

public class NodoArbol {
    private Object etiqueta;
    private NodoArbol hijoIzq;
    private NodoArbol hijoDer;


    public NodoArbol() {
        etiqueta = null;
        hijoIzq = hijoDer = null;
    }

    public NodoArbol(Object etiqueta) {
        this.etiqueta = etiqueta;
        hijoIzq = hijoDer = null;
    }

    public NodoArbol(Object etiq, NodoArbol izq, NodoArbol der) {
        this.hijoDer = der;
        this.hijoIzq = izq;
        this.etiqueta = etiq;
    }

    public NodoArbol(NodoArbol izq, NodoArbol der) {
        this.hijoDer = der;
        this.hijoIzq = izq;
    }

    /**
     * @return the etiqueta
     */
    public Object getEtiqueta() {
        return etiqueta;
    }

    /**
     * @param etiqueta the etiqueta to set
     */
    public void setEtiqueta(Object etiqueta) {
        this.etiqueta = etiqueta;
    }

    /**
     * @return the hijoDer
     */
    public NodoArbol getHijoDer() {
        return hijoDer;
    }

    /**
     * @param hijoDer the hijoDer to set
     */
    public void setHijoDer(NodoArbol hijoDer) {
        this.hijoDer = hijoDer;
    }

    /**
     * @return the hijoIzq
     */
    public NodoArbol getHijoIzq() {
        return hijoIzq;
    }

    /**
     * @param hijoIzq the hijoIzq to set
     */
    public void setHijoIzq(NodoArbol hijoIzq) {
        this.hijoIzq = hijoIzq;
    }
}