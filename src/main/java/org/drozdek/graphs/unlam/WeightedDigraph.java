/**
 *
 */
package org.drozdek.graphs.unlam;

import java.util.ArrayList;
import java.util.Calendar;

import static java.lang.System.out;

/**
 * @author David
 *
 */
public class WeightedDigraph {

    protected ArrayList<Vertex> v;
    protected ArrayList<Vertex>[] adjacencyList;
    protected byte[][] adjacencyMatrix;
    protected int[][] weightTable;
    protected int totalArcs;

    /**
     *
     */
    public WeightedDigraph(int n) {
        // TODO Auto-generated constructor stub
        this.adjacencyList = null;
        this.adjacencyMatrix = new byte[n][n];
        this.weightTable = new int[n][n];
        this.totalArcs = 0;
        this.v = new ArrayList<Vertex>();

        for (int i = 0; i < cardinality(); i++)
            v.add(new Vertex(i));

    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        WeightedDigraph dp = new WeightedDigraph(10);
        dp.createArc('a', 'e', 1);
        dp.createArc('d', 'a', 4);
        dp.createArc('a', 'h', 10);
        dp.createArc('d', 'h', 1);
        dp.createArc('h', 'e', 5);
        dp.createArc('h', 'i', 9);
        dp.createArc('e', 'f', 3);
        dp.createArc('f', 'c', 3);
        dp.createArc('f', 'b', 1);
        dp.createArc('f', 'g', 7);
        dp.createArc('b', 'c', 2);
        dp.createArc('f', 'i', 1);
        dp.createArc('g', 'j', 1);
        dp.createArc('i', 'j', 2);

        dp.printAdjacencyTable();
        dp.printArcWeightTable();

        WeightedDigraph newGraph = dp.depthFirstSearch();
        newGraph.printArcWeightTable();
    }

    public WeightedDigraph depthFirstSearch() {

        Calendar ini = Calendar.getInstance();

        WeightedDigraph result = new WeightedDigraph(cardinality());
        ArrayList<Integer> visitedVertices = new ArrayList<Integer>();

        int i = 0;

        while (i < cardinality() && visitedVertices.size() < cardinality()) {
            if (!visitedVertices.contains(i)) {
                visitedVertices.add(i);
                dfs(i, visitedVertices, result);
            }
            i++;
        }

        Calendar end = Calendar.getInstance();
        out.println("Tiempo algoritmo b�squeda primero en profundidad: "
                + (end.getTimeInMillis() - ini.getTimeInMillis()));

        return result;
    }

    public int cardinality() {
        return adjacencyMatrix[0].length;
    }

    public boolean createArc(char a1, char a2, int weight) {
        int n1 = ((int) a1) - 97;
        int n2 = ((int) a2) - 97;

        return createArc(n1, n2, weight);
    }

    public boolean createArc(int node1, int node2, int weight) {
        try {
            if (adjacencyMatrix[node1][node2] == 1 || node1 == node2)
                return false;

            adjacencyMatrix[node1][node2] = 1;
            weightTable[node1][node2] = weight;

            this.totalArcs++;
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * B�squeda primero en profundidad (Hopcroft - Tarjan)
     */
    public void dfs(int v, ArrayList<Integer> visitedVertices,
                    WeightedDigraph newGraph) {

        int j = 0;
        while (visitedVertices.size() < cardinality() && j < cardinality()) {
            if (adjacencyMatrix[v][j] == 1 && !visitedVertices.contains(j)) {
                // out.println("vertices no contiene a : " + (char)(j + 97));
                newGraph.createArc(v, j, weightTable[v][j]);
                visitedVertices.add(j);
                dfs(j, visitedVertices, newGraph);
            }
            j++;
        }
    }
	
	/*public static Integer buscarMinimo(ArrayList<Integer> lista){
		
		Integer min = lista.get(0).intValue();
		
		for(int i = 0; i < lista.size(); i++)
			if(lista.get(i) < min)
				min = lista.get(i).intValue();
		
		return min;
	}*/

    public Integer removeArc(int node1, int node2) {
        Integer aux = null;

        if (totalArcs == 0)
            return null;

        try {
            adjacencyMatrix[node1][node2] = 0;
            weightTable[node1][node2] = 0;
            this.totalArcs--;
            return aux;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	
/*	public int[] algoritmoDijkstra(int first){
		int n = cardinality();
		int []currDistV = new int[n];
		
		for(int i = 0; i < n; i++)
			currDistV[i] = 999;
		
		currDistV[first] = 0;
		
		@SuppressWarnings("unchecked")
		MinimumHeap aVerificar = new MinimumHeap(v.size());
		for(int i = 0; i < v.size(); i++)
			
		
		while(!aVerificar.estaVacio()){
			Vertex v = (Vertex)aVerificar.extraerRaiz();
		
			for(int u = 0; u < aVerificar. && aVerificar.contains(u); u++)
				if(isAdjacent(aVerificar.get(u), v)){
					if(currDistV[u] > currDistV[v] + weightTable[v][u])
						currDistV[u] = currDistV[v] + weightTable[v][u];
					u = v;
				}
		}
		
		return null;
	}*/

    public boolean isAdjacent(int i, int j) {
        return adjacencyMatrix[i][j] == 1 ||
                adjacencyMatrix[j][i] == 1;
    }

    public void printAdjacencyTable() {

        int n = this.adjacencyMatrix[0].length;

        out.println();
        out.print("\\ ");
        for (int i = 0; i < n; i++)
            out.print((char) (i + 97) + " ");
        out.println();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                if (j == 0)
                    out.print((char) (i + j + 97) + " ");

                out.print(adjacencyMatrix[i][j]);
                if (j < n)
                    out.print(" ");
            }
            out.println();
        }
    }

    public void printArcWeightTable() {

        int n = this.weightTable[0].length;

        out.println();
        out.print("\\ ");
        for (int i = 0; i < n; i++)
            out.print((char) (i + 97) + " ");
        out.println();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                if (j == 0)
                    out.print((char) (i + j + 97) + " ");

                out.print(weightTable[i][j]);
                if (j < n)
                    out.print(" ");
            }
            out.println();

        }
    }

}
