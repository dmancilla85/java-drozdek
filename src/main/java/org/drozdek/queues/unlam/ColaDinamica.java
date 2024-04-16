package org.drozdek.queues.unlam;

import static java.lang.Math.random;
import static java.lang.System.out;

public class ColaDinamica implements Cola {

    private NodoCola primer, ultimo;

    public ColaDinamica() {
        this.primer = null;
        this.ultimo = null;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Cola c1 = new ColaDinamica();

        c1.encolar("PRIMER OBJETO");
        c1.encolar("B");
        c1.encolar("C");

        int i = 0, n = 500;
        while (i++ < n)
            c1.encolar(random() * 100 * i);

        c1.encolar("ULTIMO OBJETO N�" + i);

        while (!c1.estaVacia())
            out.println(c1.desencolar());


    }

    @Override
    public Object desencolar() {

        Object aux = null;

        // TODO Auto-generated method stub
        if (!estaVacia()) {
            aux = primer.dato;
            primer = primer.sig;
        }

        return aux;
    }

    @Override
    public boolean encolar(Object obj) {
        // TODO Auto-generated method stub
        try {
            NodoCola aux = new NodoCola(obj);
            aux.dato = obj;

            if (primer == null) {
                primer = ultimo = aux;
            } else {
                ultimo.sig = aux;
                ultimo = aux;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean estaVacia() {
        // TODO Auto-generated method stub
        return primer == null;
    }

    @Override
    public Object tope() {
        // TODO Auto-generated method stub
        return primer.dato;
    }

    @Override
    public void vaciar() {
        // TODO Auto-generated method stub
        this.primer = null;
        this.ultimo = null;
    }

    class NodoCola {
        private Object dato;
        private NodoCola sig;

        public NodoCola() {
            dato = null;
            sig = null;
        }

        public NodoCola(Object obj) {
            dato = obj;
            sig = null;
        }
    }

}
