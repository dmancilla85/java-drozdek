/**
 *
 */
package org.drozdek.graphs.unlam;

import org.drozdek.commons.LoggerService;

import java.util.*;

/**
 * @author David
 *
 */
public class Graph {

    protected ArrayList<Vertice> vertices;
    protected ArrayList<Arista> edges;
    protected byte[][] adjacencyMatrix;


    public Graph() {
        this.edges = null;
        this.vertices = new ArrayList<>();
        this.adjacencyMatrix = null;
    }

    /**
     *
     */
    public Graph(int n) {
        this.adjacencyMatrix = new byte[n][n];
        this.vertices = new ArrayList<>();
        this.edges = new ArrayList<>();

        for (int i = 0; i < n; i++)
            this.vertices.add(new Vertice(i));
    }

    public static Graph crearAleatorio(int n, int conexividad) {
        Graph al = new Graph(n);

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if ((int) (Math.random() * 100 + 1) > 100 - (conexividad))
                    al.newEdge(i, j);

        return al;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        /*
         * GrafoSimple gs = crearAleatorio(10, 60); gs.tablaAdyacencias();
         *
         * GrafoSimple gs2 = gs.busquedaPrimeroEnProfundidad();
         * gs2.tablaAdyacencias();
         */

        Graph uno = new Graph(9);
        uno.crearArco('a', 'e');
        uno.crearArco('a', 'f');
        uno.crearArco('a', 'i');
        uno.crearArco('a', 'g');
        uno.crearArco('e', 'f');
        uno.crearArco('e', 'i');
        uno.crearArco('e', 'f');
        uno.crearArco('i', 'f');
        uno.crearArco('b', 'g');
        uno.crearArco('c', 'h');
        uno.crearArco('h', 'd');


		/*GrafoSimple dos = uno.busquedaPrimeroEnProfundidad();
		GrafoSimple tres = uno.busquedaPrimeroEnAmplitud();*/

    }

    public void addVertice(Vertice v) {
        if (v != null)
            vertices.add(v);
    }

    /**
     *
     * @return
     */
    public Graph busquedaPrimeroEnAmplitud() {

        Calendar ini = Calendar.getInstance();

        Graph busq = new Graph(cardinality());
        Queue<Vertice> cola = new LinkedList<Vertice>();
        ArrayList<Vertice> verticesVisitados = new ArrayList<Vertice>();

        int i = 0;

        while (i < cardinality() && verticesVisitados.size() < cardinality()) {
            if (!verticesVisitados.contains(vertices.get(i))) {
                cola.add(vertices.get(i));
                verticesVisitados.add(this.vertices.get(i));

                try {
                    while (!cola.isEmpty()) {
                        Vertice a = cola.poll();
                        int vertice = vertices.indexOf(a);

                        for (int j = 0; j < cardinality() && vertice != -1; j++) {
                            if (adjacencyMatrix[vertice][j] == 1
                                    && !verticesVisitados.contains(this.vertices.get(j))) {
                                LoggerService.logInfo("vertices Visitados contiene a : "
                                        + (char) (j + 97));
                                busq.newEdge(vertice, j);
                                cola.add(this.vertices.get(j));
                                verticesVisitados.add(this.vertices.get(j));
                            }
                        }

                    }

                } catch (ArrayIndexOutOfBoundsException e) {
                    e.printStackTrace();
                }
            }
            i++;
        }

        Calendar fin = Calendar.getInstance();
        LoggerService.logInfo("Tiempo algoritmo b�squeda primero en amplitud: "
                + (fin.getTimeInMillis() - ini.getTimeInMillis()));

        return busq;
    }

    /**
     *
     * @return
     */
    public Graph busquedaPrimeroEnProfundidad() {

        Calendar ini = Calendar.getInstance();

        Graph busq = new Graph(cardinality());
        ArrayList<Vertice> verticesVisitados = new ArrayList<Vertice>();

        int i = 0;

        while (i < cardinality() && verticesVisitados.size() < cardinality()) {
            if (!verticesVisitados.contains(i)) {
                verticesVisitados.add(this.vertices.get(i));
                deepFirstSearch(i, verticesVisitados, busq);
            }
            i++;
        }

        Calendar fin = Calendar.getInstance();
        LoggerService.logInfo("Tiempo algoritmo b�squeda primero en profundidad: "
                + (fin.getTimeInMillis() - ini.getTimeInMillis()));

        return busq;
    }

    public int cardinality() {

        if (adjacencyMatrix == null)
            return 0;

        return adjacencyMatrix[0].length;
    }

    /**
     *
     * @return
     */
    public int countEdges() {
        return this.edges.size();
    }

    /**
     *
     * @param a1
     * @param a2
     * @return
     */
    public boolean crearArco(char a1, char a2) {
        int n1 = ((int) a1) - 97;
        int n2 = ((int) a2) - 97;

        return newEdge(n1, n2);
    }

    /**
     * B�squeda primero en profundidad (Hopcroft - Tarjan)
     * @param v
     * @param verticesVisitados
     * @param nuevo
     */
    public void deepFirstSearch(int v, ArrayList<Vertice> verticesVisitados, Graph nuevo) {

        int j = 0;
        while (verticesVisitados.size() < cardinality() && j < cardinality()) {
            if (adjacencyMatrix[v][j] == 1 && !verticesVisitados.contains(this.vertices.get(j))) {
                LoggerService.logInfo("vertices no contiene a : " + (char) (j + 97));
                nuevo.newEdge(v, j);
                verticesVisitados.add(this.vertices.get(j));
                deepFirstSearch(j, verticesVisitados, nuevo);
            }
            j++;
        }
    }

    /**
     *
     * @param vertice
     * @return
     */
    public List<Vertice> getVerticesAdyacentes(int vertice) {
        ArrayList<Vertice> ady = new ArrayList<>();

        if (vertice < 0 || vertice >= cardinality())
            return null;

        for (int i = 0; i < cardinality(); i++)
            if (this.adjacencyMatrix[vertice][i] == 1)
                ady.add(this.vertices.get(i));

        return ady;
    }

    /**
     *
     * @param vertice
     * @return
     */
    public List<Vertice> getVerticesNoAdyacentes(int vertice) {
        ArrayList<Vertice> ady = new ArrayList<Vertice>();

        if (vertice < 0 || vertice >= cardinality())
            return null;

        for (int i = 0; i < cardinality(); i++)
            if (this.adjacencyMatrix[vertice][i] == 0 && vertice != i)
                ady.add(this.vertices.get(i));

        return ady;
    }

    /**
     *
     * @param nodo1
     * @param nodo2
     * @return
     */
    public boolean newEdge(int nodo1, int nodo2) {
        try {

            if (adjacencyMatrix == null)
                adjacencyMatrix = new byte[vertices.size()][vertices.size()];

            if (adjacencyMatrix[nodo1][nodo2] == 1 || nodo1 == nodo2)
                return false;

            edges.add(new Arista(new Vertice(nodo1), new Vertice(nodo2)));

            adjacencyMatrix[nodo1][nodo2] = 1;
            adjacencyMatrix[nodo2][nodo1] = 1;
            vertices.get(nodo1).aumentarGrado();
            vertices.get(nodo2).aumentarGrado();

            return true;

        } catch (Exception e) {
            LoggerService.logError(e.getMessage());
            return false;
        }
    }

    public String porcentajeConexividad() {
        double max = (cardinality() * (cardinality() - 1)) / 2;
        return String.format("Conexividad: %3.2f", ((edges.size() / max) * 100))
                + "%";
    }

    public void removeEdge(int nodo1, int nodo2) {

        if (edges.isEmpty())
            return;

        try {
            adjacencyMatrix[nodo1][nodo2] = 0;
            adjacencyMatrix[nodo2][nodo1] = 0;

            edges.remove(new Arista(new Vertice(nodo1), new Vertice(nodo2)));

            vertices.get(nodo1).disminuirGrado();
            vertices.get(nodo2).disminuirGrado();

        } catch (Exception e) {
            LoggerService.logError(e.getMessage());
        }
    }

    public String toString() {
        return verTablaAdyacencias().toString();
    }

    public StringBuffer verTablaAdyacencias() {

        int n = this.adjacencyMatrix[0].length;

        StringBuffer tabla = new StringBuffer();
        tabla.append("Tabla de Adyacencias\n\\ ");
        for (int i = 0; i < n; i++)
            tabla.append((char) (i + 97) + " ");
        tabla.append("\n");

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                if (j == 0)
                    tabla.append((char) (i + j + 97) + " ");

                tabla.append(adjacencyMatrix[i][j]);
                if (j < n)
                    tabla.append(" ");
            }
            tabla.append("\n");

        }

        return tabla;
    }

}
