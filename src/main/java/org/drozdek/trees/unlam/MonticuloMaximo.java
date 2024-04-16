/**
 *
 */
package org.drozdek.trees.unlam;

import static java.lang.System.out;

/**
 * @author David
 *
 */
@SuppressWarnings("unused")
public class MonticuloMaximo {

    protected final static int TAM = 10;
    protected Object[] heap;
    protected int n;


    /**
     *
     */
    public MonticuloMaximo() {
        this(TAM);
    }


    /**
     *
     */
    public MonticuloMaximo(int tam) {
        // TODO Auto-generated constructor stub
        if (tam > 0)
            this.heap = new Object[tam];
        this.n = 0;
    }

    /**
     *
     * @param a
     * @param b
     * @param vec
     */
    public static void intercambiar(int a, int b, Object[] vec) {
        Object aux = vec[a];
        vec[a] = vec[b];
        vec[b] = aux;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        MonticuloMaximo a = new MonticuloMaximo(10);
        a.agregar(10);
        a.agregar(4);
        a.agregar(1);
        a.agregar(7);
        a.agregar(15);
        a.agregar(34);
        a.agregar(6);
        a.agregar(73);
        a.agregar(18);
        out.println("Altura: " + a.altura(0));
        out.println((a.listar().toString()));

        for (int i = 0; i < a.heap.length; i++) {
            out.println("heap[" + i + "]  es hoja?: " + a.esHoja(i));
            out.println("heap[" + i + "] = " + a.heap[i] + " es hijo de?: "
                    + a.padre(i));
            out.println("heap[" + i + "] = " + a.heap[i]
                    + " es padre de hijo izq?: " + a.hijoIzquierdo(i));
            out.println("heap[" + i + "] = " + a.heap[i]
                    + " es padre de hijo der?: " + a.hijoDerecho(i));
        }
        out.println("RAIZ: " + a.extraerRaiz());
        out.println("Altura: " + a.altura(0));
        out.println((a.listar().toString()));

        out.println("RAIZ: " + a.extraerRaiz());
        out.println("Altura: " + a.altura(0));
        out.println((a.listar().toString()));

        out.println("RAIZ: " + a.extraerRaiz());
        out.println("Altura: " + a.altura(0));
        out.println((a.listar().toString()));

        out.println("RAIZ: " + a.extraerRaiz());
        out.println("Altura: " + a.altura(0));
        out.println((a.listar().toString()));

        out.println("RAIZ: " + a.extraerRaiz());
        out.println("Altura: " + a.altura(0));
        out.println((a.listar().toString()));

        out.println("RAIZ: " + a.extraerRaiz());
        out.println("Altura: " + a.altura(0));
        out.println((a.listar().toString()));

        out.println("RAIZ: " + a.extraerRaiz());
        out.println("Altura: " + a.altura(0));
        out.println((a.listar().toString()));

        out.println("RAIZ: " + a.extraerRaiz());
        out.println("Altura: " + a.altura(0));
        out.println((a.listar().toString()));

    }

    /**
     *
     * @param nodo
     */
    @SuppressWarnings("unchecked")
    protected void acomodarRaiz(int nodo) {

        if (nodo >= 0 && nodo < n && heap[nodo] != null) {

            if (((Comparable<Object>) heap[padre(nodo)]).compareTo(heap[nodo]) < 0)
                intercambiar(padre(nodo), nodo, heap);

            acomodarRaiz(hijoIzquierdo(nodo));
            acomodarRaiz(hijoDerecho(nodo));
        }

    }

    /**
     *
     * @param obj
     */
    @SuppressWarnings("unchecked")
    public void agregar(Object obj) {
        // Pone obj al final del mont�culo
        int ultimo = insertar(obj);

        if (ultimo == -1)
            return;

        // Mientras obj no est� en la ra�z y es mayor que su padre
        while (!heap[0].equals(heap[ultimo])
                && (((Comparable<Object>) heap[ultimo])
                .compareTo(heap[padre(ultimo)])) > 0) {
            // Intercambia con el padre
            intercambiar(ultimo, padre(ultimo), heap);
            ultimo = padre(ultimo);
        }
    }

    /**
     *
     * @param nodo
     * @return
     */
    public int altura(int nodo) {

        if (!esHoja(nodo))
            return 1 + altura(hijoIzquierdo(nodo));

        return 1;
    }

    /**
     *
     * @param nodo
     * @return
     */
    public boolean esHoja(int nodo) {

        if (nodo > n - 1)
            return false;

        if (nodo * 2 + 1 > n - 1 || nodo < 0)
            return true;

        return heap[nodo * 2 + 1] == null && heap[nodo * 2 + 2] == null;

    }

    public boolean estaVacio() {
        return this.n == 0;
    }

    /**
     *
     * @return
     */
    public Object extraerRaiz() {
        Object raiz = heap[0];
        intercambiar(0, n - 1, heap);
        heap[n - 1] = null;
        n--;

        acomodarRaiz(0);

        return raiz;
    }

    /**
     *
     * @param nodo
     * @return
     */
    protected int hijoDerecho(int nodo) {
        if (0 <= nodo && nodo < (heap.length - 2) / 2 && !esHoja(nodo))
            return nodo * 2 + 2;

        return -1;
    }

    /**
     *
     * @param nodo
     * @return
     */
    protected int hijoIzquierdo(int nodo) {
        if (0 <= nodo && nodo < (heap.length - 1) / 2 && !esHoja(nodo))
            return nodo * 2 + 1;

        return -1;
    }

    /**
     *
     * @param obj
     * @return
     */
    protected int insertar(Object obj) {
        if (n < heap.length) {
            heap[n] = obj;
            return n++;
        }
        return -1;
    }

    /**
     *
     */
    public StringBuffer listar() {

        StringBuffer sb = new StringBuffer();

        for (int i = 0; heap[i] != null; i++) {
            //out.println("heap[" + i + "] = " + heap[i]);
            sb.append("heap[" + i + "] = " + heap[i] + "\n");
        }

        return sb;
    }

    /**
     *
     * @param nodo
     * @return
     */
    protected int padre(int nodo) {
        if (nodo <= 0)
            return nodo;

        return (nodo - 1) / 2;
    }

    public Object[] toArray() {
        return this.heap.clone();
    }

    public int totalNodos() {
        return this.n;
    }

}
