/**
 * 
 */
package org.drozdek.graphs.unlam;

import static java.lang.System.out;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * @author David
 *
 */
public class DigrafoPesado {

	protected ArrayList<Vertice> v;
	protected ArrayList<Vertice> []listaAdyacencias;
	protected byte [][]matrizAdyacencias;
	protected int [][]tablaDePesos;
	protected int totalArcos;
	
	/**
	 * 
	 */
	public DigrafoPesado(int n) {
		// TODO Auto-generated constructor stub
		this.listaAdyacencias = null;
		this.matrizAdyacencias = new byte[n][n];
		this.tablaDePesos = new int[n][n];
		this.totalArcos = 0;
		this.v = new ArrayList<Vertice>();
		
		for(int i = 0; i < cardinal(); i++)
			v.add(new Vertice(i));
				
	}
	
	public int cardinal() {
		return matrizAdyacencias[0].length;
	}

	public boolean crearArco(int nodo1, int nodo2, int peso) {
		try {
			if (matrizAdyacencias[nodo1][nodo2] == 1 || nodo1 == nodo2)
				return false;

			matrizAdyacencias[nodo1][nodo2] = 1;
			tablaDePesos[nodo1][nodo2] = peso;

			this.totalArcos++;
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	public boolean crearArco(char a1, char a2, int peso) {
		int n1 = ((int) a1) - 97;
		int n2 = ((int) a2) - 97;

		return crearArco(n1, n2, peso);
	}
	
	
	public Integer eliminarArco(int nodo1, int nodo2) {
		Integer aux = null;

		if (totalArcos == 0)
			return null;

		try {
			matrizAdyacencias[nodo1][nodo2] = 0;
			tablaDePesos[nodo1][nodo2] = 0;
			this.totalArcos--;
			return aux;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void tablaAdyacencias() {

		int n = this.matrizAdyacencias[0].length;

		out.println();
		out.print("\\ ");
		for (int i = 0; i < n; i++)
			out.print((char) (i + 97) + " ");
		out.println();

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {

				if (j == 0)
					out.print((char) (i + j + 97) + " ");

				out.print(matrizAdyacencias[i][j]);
				if (j < n)
					out.print(" ");
			}
			out.println();
		}
	}
	
	public void tablaPesoDeArcos() {

		int n = this.tablaDePesos[0].length;

		out.println();
		out.print("\\ ");
		for (int i = 0; i < n; i++)
			out.print((char) (i + 97) + " ");
		out.println();

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {

				if (j == 0)
					out.print((char) (i + j + 97) + " ");

				out.print(tablaDePesos[i][j]);
				if (j < n)
					out.print(" ");
			}
			out.println();
	
		}
	}
	
	/*public static Integer buscarMinimo(ArrayList<Integer> lista){
		
		Integer min = lista.get(0).intValue();
		
		for(int i = 0; i < lista.size(); i++)
			if(lista.get(i) < min)
				min = lista.get(i).intValue();
		
		return min;
	}*/
	
	public boolean esAdyacente(int i, int j){
		return matrizAdyacencias[i][j] == 1 ||
				matrizAdyacencias[j][i] == 1;
	}
	
/*	public int[] algoritmoDijkstra(int first){
		int n = cardinal();
		int []currDistV = new int[n];
		
		for(int i = 0; i < n; i++)
			currDistV[i] = 999;
		
		currDistV[first] = 0;
		
		@SuppressWarnings("unchecked")
		MonticuloMinimo aVerificar = new MonticuloMinimo(v.size());
		for(int i = 0; i < v.size(); i++)
			
		
		while(!aVerificar.estaVacio()){
			Vertice v = (Vertice)aVerificar.extraerRaiz();
		
			for(int u = 0; u < aVerificar. && aVerificar.contains(u); u++)
				if(esAdyacente(aVerificar.get(u), v)){
					if(currDistV[u] > currDistV[v] + tablaDePesos[v][u])
						currDistV[u] = currDistV[v] + tablaDePesos[v][u];
					u = v;
				}
		}
		
		return null;
	}*/
	
	
	/**
	 * B�squeda primero en profundidad (Hopcroft - Tarjan)
	 */
	public void dfs(int v, ArrayList<Integer> verticesVisitados,
			DigrafoPesado nuevo) {

		int j = 0;
		while (verticesVisitados.size() < cardinal() && j < cardinal()) {
			if (matrizAdyacencias[v][j] == 1 && !verticesVisitados.contains(j)) {
				// out.println("vertices no contiene a : " + (char)(j + 97));
				nuevo.crearArco(v, j, tablaDePesos[v][j]);
				verticesVisitados.add(j);
				dfs(j, verticesVisitados, nuevo);
			}
			j++;
		}
	}

	public DigrafoPesado busquedaPrimeroEnProfundidad() {

		Calendar ini = Calendar.getInstance();

		DigrafoPesado busq = new DigrafoPesado(cardinal());
		ArrayList<Integer> verticesVisitados = new ArrayList<Integer>();

		int i = 0;

		while (i < cardinal() && verticesVisitados.size() < cardinal()) {
			if (!verticesVisitados.contains(i)) {
				verticesVisitados.add(i);
				dfs(i, verticesVisitados, busq);
			}
			i++;
		}

		Calendar fin = Calendar.getInstance();
		out.println("Tiempo algoritmo b�squeda primero en profundidad: "
				+ (fin.getTimeInMillis() - ini.getTimeInMillis()));

		return busq;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DigrafoPesado dp = new DigrafoPesado(10);
		dp.crearArco('a', 'e', 1);
		dp.crearArco('d', 'a', 4);
		dp.crearArco('a', 'h', 10);
		dp.crearArco('d', 'h', 1);
		dp.crearArco('h', 'e', 5);
		dp.crearArco('h', 'i', 9);
		dp.crearArco('e', 'f', 3);
		dp.crearArco('f', 'c', 3);
		dp.crearArco('f', 'b', 1);
		dp.crearArco('f', 'g', 7);
		dp.crearArco('b', 'c', 2);
		dp.crearArco('f', 'i', 1);
		dp.crearArco('g', 'j', 1);
		dp.crearArco('i', 'j', 2);
		
		dp.tablaAdyacencias();
		dp.tablaPesoDeArcos();
		
		DigrafoPesado nuev = dp.busquedaPrimeroEnProfundidad();
		nuev.tablaPesoDeArcos();
	}

}
