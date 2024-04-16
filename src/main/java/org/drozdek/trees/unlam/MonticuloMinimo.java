/**
 *
 */
package org.drozdek.trees.unlam;

import static java.lang.System.out;

/**
 * @author David
 *
 */
public class MonticuloMinimo extends MonticuloMaximo {

    /**
     *
     */
    public MonticuloMinimo() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param tam
     */
    public MonticuloMinimo(int tam) {
        super(tam);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        MonticuloMinimo a = new MonticuloMinimo(10);
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
    public void acomodarRaiz(int nodo) {
        if (nodo >= 0 && nodo < n && heap[nodo] != null) {

            if (((Comparable<Object>) heap[padre(nodo)]).compareTo(heap[nodo]) > 0)
                intercambiar(padre(nodo), nodo, heap);

            if (hijoIzquierdo(nodo) != -1 && !esHoja(nodo))
                acomodarRaiz(hijoIzquierdo(nodo));

            if (hijoDerecho(nodo) != -1 && !esHoja(nodo))
                acomodarRaiz(hijoDerecho(nodo));
        }

    }

    /* *
     * @param obj
     */
    @SuppressWarnings("unchecked")
    public void agregar(Object obj) {
        // Inserto el obj al final del mont�culo
        int ultimo = insertar(obj);

        // Verifico que haya sido insertado
        if (ultimo == -1)
            return;

        // Mientras que obj no est� en la ra�z y es mayor que su padre
        while (!heap[0].equals(heap[ultimo])
                && (((Comparable<Object>) heap[ultimo])
                .compareTo(heap[padre(ultimo)])) < 0) {
            // Intercambia con el padre
            intercambiar(ultimo, padre(ultimo), heap);
            ultimo = padre(ultimo);
        }
    }

}
