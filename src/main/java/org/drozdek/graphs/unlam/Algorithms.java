package org.drozdek.graphs.unlam;

import org.drozdek.commons.LoggerService;
import org.drozdek.trees.unlam.MonticuloMinimo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Algorithms {

    /**
     * Algoritmo de Prim - Jarnik.
     * Genera una cola de prioridad con todos los arcos, ordenada por distancia.
     * Luego en cada iteración extrae un arco y verifica que alguno de sus vértices no
     * haya sido visitado anteriormente y se lo añade al árbol recubridor mínimo (nuevo)
     * hasta que todos los vértices hayan sido visitados y la cantidad de arcos sea menor que N - 1
     * (mínima cantidad de arcos).
     *
     * @param g       Un grafo pesado no dirigido.
     * @param inicial Vértice inicial del recorrido.
     * @return árbol recubridor mínimo
     */
    public static WeightedGraph algoritmoPrimJarnik(WeightedGraph g, int inicial) {

        Calendar ini = Calendar.getInstance();

        WeightedGraph nuevo = new WeightedGraph(g.cardinality());
        MonticuloMinimo aristas = new MonticuloMinimo(g.countEdges());
        ArrayList<Vertice> verticesVisitados = new ArrayList<>();

        // Secuencia de todos los arcos de G ordenados por peso
        for (Arista element : g.edges)
            aristas.agregar(element);

        // Agrego el v inicial
        verticesVisitados.add(g.vertices.get(inicial));

        for (int i = 0; i < g.cardinality(); i++)
            // Mientras la lista ver de vertices visitados no contenga todo V
            for (int j = 0; j < aristas.totalNodos() && verticesVisitados.size() < g.cardinality(); j++) {
                // Obtengo el arco con la m�nima distancia
                Arista aux = (Arista) aristas.extraerRaiz();
                // Si uno de los dos v del arco se encuentra en "ver"
                // y la cantidad de arcos es menor a N - 1 y no forma un ciclo.
                if ((!verticesVisitados.contains(aux.destino) ||
                        !verticesVisitados.contains(aux.origen)) && nuevo.countEdges() < g.cardinality() - 1) {
                    // Agrego el vertice que no contiene "ver"
                    if (!verticesVisitados.contains(aux.destino))
                        verticesVisitados.add(aux.destino);
                    else
                        verticesVisitados.add(aux.origen);

                    nuevo.crearArco(aux.origen.clave, aux.destino.clave, aux.peso);
                }
            }

        Calendar fin = Calendar.getInstance();
        LoggerService.logInfo("Tiempo algoritmo de Prim para N = " + g.cardinality() + ": "  //$NON-NLS-1$//$NON-NLS-2$
                + (fin.getTimeInMillis() - ini.getTimeInMillis()));

        return nuevo;
    }

    public static WeightedGraph algoritmoKruskal(WeightedGraph g, int inicial) {

        WeightedGraph nuevo = new WeightedGraph(g.cardinality());

        // Arcos ordenados por peso
        MonticuloMinimo cola = new MonticuloMinimo(g.countEdges());
        ArrayList<Vertice> arbol = new ArrayList<>();

        // Secuencia de todos los arcos de G ordenados por peso
        for (Arista element : g.edges) {
            cola.agregar(element);
        }

        for (int i = 1; i <= g.countEdges() && arbol.size() < g.cardinality() - 1 && !cola.estaVacio(); i++) {
            Arista e = (Arista) cola.extraerRaiz();

            if ((!arbol.contains(e.destino) || !arbol.contains(e.origen)) &&
                    nuevo.countEdges() < g.cardinality() - 1) {

                // Agrego el vertice que no contiene "ver"
                if (!arbol.contains(e.destino))
                    arbol.add(e.destino);
                else
                    arbol.add(e.origen);

                nuevo.crearArco(e.origen.clave, e.destino.clave, e.peso);
            }

        }

        return nuevo;
    }

    public static Integer minimo(Integer[] v, List<Vertice> noVisitados, DigrafoPesado g) {

        int minimo = Integer.MAX_VALUE, index = 0;

        try {
            for (int i = 0; i < g.cardinal(); i++)
                if ((i == 0 || minimo > v[i]) && noVisitados.contains(g.v.get(i))) {
                    minimo = v[i];
                    index = i;
                }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return index;
    }


    /**
     * Algoritmo de Dijkstra.
     *
     * @param g       Grafo
     * @param inicial Vertice inicial del recorrido
     * @return Distancia
     */
    public static Integer[] algoritmoDijkstra(DigrafoPesado g, int inicial) {

        Integer[] d;
        ArrayList<Vertice> noVisitados = new ArrayList<>();
        int v0;

        /* Crear un vector D donde se guardar�n las distancias
         * de nodoInicial a todos los dem�s.
         */
        d = new Integer[g.cardinal()];

        for (int i = 0; i < g.cardinal(); i++) {
            // Inicializar todos los vertices en D
            if (i != inicial)
                d[i] = Integer.MAX_VALUE;
            else
                d[i] = 0;

            // Agregar todos los vertices a la lista de verificaci�n
            noVisitados.add(g.v.get(i));
        }

        // Mientras haya alg�n vertice no visitado
        while (!noVisitados.isEmpty()) {

            // Obtener el v con dist. minima en D, que no fu� visitado
            v0 = minimo(d, noVisitados, g);

            // Quitar v de los noVisitados
            noVisitados.remove(g.v.get(v0));

            // Para todos los elementos adyacentes a v que no fueron visitados
            for (int u = 0; u < g.cardinal(); u++) {

                // Si la distancia de nodoInicial a u es mayor que la distancia de
                if (d[u] > d[v0] + g.tablaDePesos[v0][u] && v0 != u
                        && noVisitados.contains(g.v.get(u)) && g.tablaDePesos[v0][u] != 0) {
                    d[u] = d[v0] + g.tablaDePesos[v0][u];
                }

            }
        }

        return d;
    }

    /**
     * El algoritmo de Floyd-Warshall compara todos los posibles caminos a
     * trav�s del grafo entre cada par de v�rtices. El algoritmo es capaz de
     * hacer esto con s�lo V3 comparaciones (esto es notable considerando que
     * puede haber hasta V2 aristas en el grafo, y que cada combinaci�n de
     * aristas se prueba). Lo hace mejorando paulatinamente una estimaci�n del
     * camino m�s corto entre dos v�rtices, hasta que se sabe que la estimaci�n
     * es �ptima.
     *
     * @param g
     * @return
     */
    public static int[][] algoritmoFloydMarshall(WeightedGraph g) {

        // MonticuloMinimo caminoMinimo = new MonticuloMinimo(g.cardinal());

        /*
         * Una matriz bidimensional. En cada paso del algoritmo, camino[i][j] es
         * el camino m�nimo de i hasta j usando valores intermedios de (1..k-1).
         * Cada camino[i][j] es inicializado a pesoArista(i,j)
         */
        int[][] caminoMinimo = g.tablaDePesos.clone();

        /* Recorro todos los v�rtices que pueden estar en una trayectoria
         * entre el v�rtice j y el v�rtice k */
        for (int i = 0; i < g.cardinality(); i++) {
            /* j es mi v�rtice actual, el que voy comparando con sus adyacentes k */
            for (int j = 0; j < g.cardinality(); j++) {
                for (int k = 0; k < g.cardinality(); k++)
                    /* Si la trayectoria de j, pasando por i hasta k es mejor, que yendo de j a k
                     * me quedo con esa direcci�n */
                    if (g.tablaDePesos[j][k] > g.tablaDePesos[j][i] + g.tablaDePesos[i][k])
                        caminoMinimo[j][k] = g.tablaDePesos[j][i] + g.tablaDePesos[i][k];
            }
        }

        return caminoMinimo;
    }


    public static boolean detectorDeCiclos(Vertice v) {

        return false;
    }
}
