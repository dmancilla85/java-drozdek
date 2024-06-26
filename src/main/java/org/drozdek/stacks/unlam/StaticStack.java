package org.drozdek.stacks.unlam;

import static java.lang.System.err;
import static java.lang.System.out;

public class StaticStack implements SimpleStack {

    private static final int TAM_DET = 5;
    private Object[] vec;
    private int tope, tam;

    public StaticStack() {
        vec = new Object[TAM_DET];
        tam = TAM_DET;
        tope = -1;
    }

    public StaticStack(int tam) {
        vec = new Object[tam];
        this.tam = tam;
        tope = -1;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        StaticStack p1 = new StaticStack();
        p1.apilar(5);
        p1.apilar("jlk");
        p1.apilar(25);
        p1.apilar("j5k6");
        p1.apilar(34);
        p1.apilar(3782.45);
        p1.apilar("dedj");
        p1.apilar(5);
        p1.apilar("jlk");
        p1.apilar(25);
        p1.apilar("j5k6");
        p1.apilar(34);
        p1.apilar(3782.45);
        p1.apilar("dedj");
        p1.apilar(5);
        p1.apilar("jlk");
        p1.apilar(25);
        p1.apilar("j5k6");
        p1.apilar(34);
        p1.apilar(3782.45);
        p1.apilar("dedj");
        p1.apilar(5);
        p1.apilar("jlk");
        p1.apilar(25);
        p1.apilar("j5k6");
        p1.apilar(34);
        p1.apilar(3782.45);
        p1.apilar("dedj");
        p1.apilar(5);
        p1.apilar("jlk");
        p1.apilar(25);
        p1.apilar("j5k6");
        p1.apilar(34);
        p1.apilar(3782.45);
        p1.apilar("dedj");
        p1.apilar(5);
        p1.apilar("jlk");
        p1.apilar(25);
        p1.apilar("j5k6");
        p1.apilar(34);
        p1.apilar(3782.45);
        p1.apilar("dedj");

        while (!p1.isEmpty())
            out.println(p1.desapilar());
    }

    @Override
    public boolean apilar(Object obj) {
        // TODO Auto-generated method stub
        try {
            if (esLlena())
                throw new FullStackException();
        } catch (FullStackException e) {
            err.println(e.getMessage() + " (Tama�o: " + tam + ")");
            reDim();

            if (this == null)
                return false;

        } finally {
            this.vec[++tope] = obj;
        }

        return true;
    }

    @Override
    public Object desapilar() {
        // TODO Auto-generated method stub
        if (isEmpty())
            return null;
        return this.vec[tope--];
    }

    public boolean esLlena() {
        // TODO Auto-generated method stub
        return tope + 1 == tam;
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return tope == -1;
    }

    private void reDim() {
        // TODO Auto-generated method stub
        Object[] aux = new Object[2 * tam];
        if (tam >= 0) System.arraycopy(vec, 0, aux, 0, tam);
        tam = 2 * tam;
        vec = aux;
    }

    @Override
    public void vaciar() {
        // TODO Auto-generated method stub
        tope = -1;
    }

    @Override
    public Object verTope() {
        // TODO Auto-generated method stub
        if (!isEmpty())
            return this.vec[tope];
        return null;
    }

}

