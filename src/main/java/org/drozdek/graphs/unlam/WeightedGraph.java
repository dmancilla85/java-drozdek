/**
 *
 */
package org.drozdek.graphs.unlam;

import org.drozdek.commons.LoggerService;

import static java.lang.System.out;

/**
 * @author David
 *
 */
public class WeightedGraph extends Graph {

    protected int[][] tablaDePesos;

    /**
     * @param n
     */
    public WeightedGraph(int n) {
        super(n);
        this.tablaDePesos = new int[n][n];
    }

    public static WeightedGraph crearAleatorio(int n, int conexividad) {
        WeightedGraph al = new WeightedGraph(n);

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if ((int) (Math.random() * 100 + 1) > 100 - (conexividad)) {
                    al.crearArco(i, j, (int) (Math.random() * 100 + 1));
                }

        return al;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

        WeightedGraph g = new WeightedGraph(6);
        g.crearArco('a', 'b', 20);
        g.crearArco('a', 'd', 40);
        g.crearArco('a', 'c', 100);
        g.crearArco('b', 'd', 120);
        g.crearArco('b', 'e', 200);
        g.crearArco('b', 'c', 30);
        g.crearArco('c', 'e', 40);
        g.crearArco('d', 'e', 60);
        g.crearArco('d', 'f', 60);
        g.crearArco('e', 'f', 30);

        WeightedGraph e = WeightedGraph.crearAleatorio(10, 50);
        out.println(e.verTablaAdyacencias());
        out.println(e.verTablaDePesos());
        /*
         * out.println(g.verTablaAdyacencias());
         * out.println(g.verTablaDePesos());
         */

        // out.println(e.verTablaAdyacencias());
        // out.println(e.verTablaDePesos());

        // ArrayList<Vertice> min = GrafoPesado.algoritmoDeJarnik_Prim(g, 0);
		/*GrafoPesado min = Algoritmos.algoritmoPrimJarnik(e, 0);
		GrafoPesado mink = Algoritmos.algoritmoKruskal(e, 0);*/
        //GrafoPesado m = Algoritmos.algoritmoPrimJarnik(g, 0);

        // GrafoPesado m = GrafoPesado.algoritmoPrimJarnik(g, 0);

        //out.println(m.verTablaAdyacencias());
        //out.println(m.verTablaDePesos());
        out.println("Prim");
		/*out.println(min.verTablaAdyacencias());
		out.println(min.verTablaDePesos());
		out.println("Kruskal");
		out.println(mink.verTablaAdyacencias());
		out.println(mink.verTablaDePesos());*/
    }

    /**
     *
     * @param nodo1
     * @param nodo2
     * @param peso
     * @return
     */
    public boolean crearArco(int nodo1, int nodo2, int peso) {
        try {
            if (adjacencyMatrix[nodo1][nodo2] == 1 || nodo1 == nodo2)
                return false;

            adjacencyMatrix[nodo1][nodo2] = 1;
            tablaDePesos[nodo1][nodo2] = peso;
            adjacencyMatrix[nodo2][nodo1] = 1;
            tablaDePesos[nodo2][nodo1] = peso;
            edges.add(new Arista(new Vertice(nodo1), new Vertice(nodo2), peso));
            vertices.get(nodo1).aumentarGrado();
            vertices.get(nodo2).aumentarGrado();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     *
     * @param a1
     * @param a2
     * @param peso
     * @return
     */
    public boolean crearArco(char a1, char a2, int peso) {
        int n1 = ((int) a1) - 97;
        int n2 = ((int) a2) - 97;

        return crearArco(n1, n2, peso);
    }

    /**
     *
     * @param nodo1
     * @param nodo2
     * @return
     */
    public void removeEdge(int nodo1, int nodo2) {


        if (this.edges.isEmpty())
            return;

        try {
            adjacencyMatrix[nodo1][nodo2] = 0;
            tablaDePesos[nodo1][nodo2] = 0;
            adjacencyMatrix[nodo2][nodo1] = 0;
            tablaDePesos[nodo2][nodo1] = 0;
            edges.remove(new Arista(new Vertice(nodo1), new Vertice(nodo2)));
            vertices.get(nodo1).disminuirGrado();
            vertices.get(nodo2).disminuirGrado();

        } catch (Exception e) {
            LoggerService.logError(e.getMessage());
        }
    }

    /**
     *
     */
    public StringBuffer verTablaDePesos() {

        int n = this.tablaDePesos[0].length;

        StringBuffer tabla = new StringBuffer();
        tabla.append("Tabla de Pesos\n\\ ");
        for (int i = 0; i < n; i++)
            tabla.append((char) (i + 97) + " ");
        tabla.append("\n");

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                if (j == 0)
                    tabla.append((char) (i + j + 97) + " ");

                tabla.append(tablaDePesos[i][j]);
                if (j < n)
                    tabla.append(" ");
            }
            tabla.append("\n");

        }

        return tabla;
    }

}
