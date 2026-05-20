package org.drozdek.graphs.unlam;

import org.drozdek.commons.LoggerService;
import org.drozdek.trees.MinimumHeap;

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
    public static WeightedGraph primJarnikAlgorithm(WeightedGraph g, int inicial) {

        Calendar ini = Calendar.getInstance();

        WeightedGraph newGraph = new WeightedGraph(                g.cardinality());
        MinimumHeap<Edge> edges = new MinimumHeap<>(g.countEdges());
        ArrayList<Vertex> visitedVertices = new ArrayList<>();

        // Secuencia de todos los arcos de G ordenados por peso
        for (Edge element : g.edges)
            edges.insert(element);

        // Agrego el v inicial
        visitedVertices.add(g.vertices.get(inicial));

        for (int i = 0; i <                 g.cardinality(); i++)
            // Mientras la lista ver de vertices visitados no contenga todo V
            for (int j = 0; j < edges.size() && visitedVertices.size() <                 g.cardinality(); j++) {
                // Obtengo el arco con la m�nima distancia
                Edge aux = edges.extractMin();
                // Si uno de los dos v del arco se encuentra en "ver"
                // y la cantidad de arcos es menor a N - 1 y no forma un ciclo.
                if ((!visitedVertices.contains(aux.destination) ||
                        !visitedVertices.contains(aux.origin)) && newGraph.countEdges() <                 g.cardinality() - 1) {
                    // Agrego el vertice que no contiene "ver"
                    if (!visitedVertices.contains(aux.destination))
                        visitedVertices.add(aux.destination);
                    else
                        visitedVertices.add(aux.origin);

                    newGraph.createEdge(aux.origin.key, aux.destination.key, aux.weight);
                }
            }

        Calendar end = Calendar.getInstance();
        LoggerService.logInfo("Tiempo algoritmo de Prim para N = " +                 g.cardinality() + ": "  //$NON-NLS-1$//$NON-NLS-2$
                + (end.getTimeInMillis() - ini.getTimeInMillis()));

        return newGraph;
    }

    public static WeightedGraph kruskalAlgorithm(WeightedGraph g, int inicial) {

        WeightedGraph newGraph = new WeightedGraph(                g.cardinality());

        // Arcos ordenados por peso
        MinimumHeap<Edge> queue = new MinimumHeap<>(g.countEdges());
        ArrayList<Vertex> tree = new ArrayList<>();

        // Secuencia de todos los arcos de G ordenados por peso
        for (Edge element : g.edges) {
            queue.insert(element);
        }

        for (int i = 1; i <= g.countEdges() && tree.size() <                 g.cardinality() - 1 && !queue.isEmpty(); i++) {
            Edge e = queue.extractMin();

            if ((!tree.contains(e.destination) || !tree.contains(e.origin)) &&
                    newGraph.countEdges() <                 g.cardinality() - 1) {

                // Agrego el vertice que no contiene "ver"
                if (!tree.contains(e.destination))
                    tree.add(e.destination);
                else
                    tree.add(e.origin);

                newGraph.createEdge(e.origin.key, e.destination.key, e.weight);
            }

        }

        return newGraph;
    }

    public static Integer minimum(Integer[] v, List<Vertex> unvisited, WeightedDigraph g) {

        int minimum = Integer.MAX_VALUE, index = 0;

        try {
            for (int i = 0; i <                 g.cardinality(); i++)
                if ((i == 0 || minimum > v[i]) && unvisited.contains(g.v.get(i))) {
                    minimum = v[i];
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
     * @param inicial Vertex inicial del recorrido
     * @return Distancia
     */
    public static Integer[] dijkstraAlgorithm(WeightedDigraph g, int inicial) {

        Integer[] d;
        ArrayList<Vertex> unvisited = new ArrayList<>();
        int v0;

        /* Crear un vector D donde se guardar�n las distancias
         * de nodoInicial a todos los dem�s.
         */
        d = new Integer[                g.cardinality()];

        for (int i = 0; i <                 g.cardinality(); i++) {
            // Inicializar todos los vertices en D
            if (i != inicial)
                d[i] = Integer.MAX_VALUE;
            else
                d[i] = 0;

            // Agregar todos los vertices a la lista de verificaci�n
            unvisited.add(g.v.get(i));
        }

        // Mientras haya alg�n vertice no visitado
        while (!unvisited.isEmpty()) {

            // Obtener el v con dist. minima en D, que no fu� visitado
            v0 = minimum(d, unvisited, g);

            // Quitar v de los unvisited
            unvisited.remove(g.v.get(v0));

            // Para todos los elementos adyacentes a v que no fueron visitados
            for (int u = 0; u <                 g.cardinality(); u++) {

                // Si la distancia de nodoInicial a u es mayor que la distancia de
                if (d[u] > d[v0] + g.weightTable[v0][u] && v0 != u
                        && unvisited.contains(g.v.get(u)) && g.weightTable[v0][u] != 0) {
                    d[u] = d[v0] + g.weightTable[v0][u];
                }

            }
        }

        return d;
    }

    /**
     * El algoritmo de Floyd-Warshall compara todos los posibles caminos a
     * trav�s del grafo entre cada par de v�rtices. El algoritmo es capaz de
     * hacer esto con s�lo V3 comparaciones (esto es notable considerando que
     * puede haber hasta V2 edges en el grafo, y que cada combinaci�n de
     * edges se prueba). Lo hace mejorando paulatinamente una estimaci�n del
     * camino m�s corto entre dos v�rtices, hasta que se sabe que la estimaci�n
     * es �ptima.
     *
     * @param g
     * @return
     */
    public static int[][] floydMarshallAlgorithm(WeightedGraph g) {

        // MinimumHeap shortestPath = new MinimumHeap(                g.cardinality());

        /*
         * Una matriz bidimensional. En cada paso del algoritmo, camino[i][j] es
         * el camino m�nimo de i hasta j usando valores intermedios de (1..k-1).
         * Cada camino[i][j] es inicializado a pesoEdge(i,j)
         */
        int[][] shortestPath = g.weightTable.clone();

        /* Recorro todos los v�rtices que pueden estar en una trayectoria
         * entre el v�rtice j y el v�rtice k */
        for (int i = 0; i <                 g.cardinality(); i++) {
            /* j es mi v�rtice actual, el que voy comparando con sus adyacentes k */
            for (int j = 0; j <                 g.cardinality(); j++) {
                for (int k = 0; k <                 g.cardinality(); k++)
                    /* Si la trayectoria de j, pasando por i hasta k es mejor, que yendo de j a k
                     * me quedo con esa direcci�n */
                    if (g.weightTable[j][k] > g.weightTable[j][i] + g.weightTable[i][k])
                        shortestPath[j][k] = g.weightTable[j][i] + g.weightTable[i][k];
            }
        }

        return shortestPath;
    }


    public static boolean cycleDetector(Vertex v) {

        return false;
    }
}
