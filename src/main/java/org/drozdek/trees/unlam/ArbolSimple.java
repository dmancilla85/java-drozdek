package org.drozdek.trees.unlam;

import java.util.ArrayList;

public class ArbolSimple<T> {

    protected NodoArbol padre;

    public ArbolSimple() {
        padre = null;
    }

    public void insertarElemento(NodoArbol padre, T etiqueta) {

        if (!padre.equals(padre)) {
            for (int i = 0; i < padre.hijos.size(); i++)
                insertarElemento(padre.hijos.get(i), etiqueta);
        }


    }

    class NodoArbol {
        protected T etiqueta;
        protected ArrayList<NodoArbol> hijos;

        public NodoArbol(T etiqueta) {
            this.etiqueta = etiqueta;
            hijos = new ArrayList<NodoArbol>();
        }


        /* (non-Javadoc)
         * @see java.lang.Object#equals(java.lang.Object)
         */
        @Override
        public boolean equals(Object obj) {
            // TODO Auto-generated method stub
            return super.equals(obj);
        }

        /**
         * @return the etiqueta
         */
        public T getEtiqueta() {
            return etiqueta;
        }

        /**
         * @param etiqueta the etiqueta to set
         */
        public void setEtiqueta(T etiqueta) {
            this.etiqueta = etiqueta;
        }

        /* (non-Javadoc)
         * @see java.lang.Object#hashCode()
         */
        @Override
        public int hashCode() {
            // TODO Auto-generated method stub
            return super.hashCode();
        }

    }

}
