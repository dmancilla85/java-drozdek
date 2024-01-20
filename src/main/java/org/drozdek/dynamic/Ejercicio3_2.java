package org.drozdek.dynamic;

import java.util.ArrayList;
import java.util.List;
import static java.lang.System.*;

class Objeto{
	String name;
	int p; // Peso
	int v; // Valor
	
	Objeto(String name, int p, int v){
		this.name = name;
		this.p = p;
		this.v = v;
	}
}

public class Ejercicio3_2 {
	
	/*	Problema de la Mochila: Se tienen n objetos y una mochila.  
	 * Para i = 1,2,..n , el objeto i tiene un peso positivo p(i) y 
	 * un valor positivo v(i) . 
	 * La mochila puede llevar un peso que no sobrepase P . 
	 * El objetivo es llenar la mochila de tal manera que se maximice 
	 * el valor de los objetos transportados, respetando la limitación de 
	 * capacidad impuesta. Los objetos pueden ser fraccionados, si una fracción 
	 * x i ( 0 ≤ ≤≤ ≤ x i ≤ ≤≤ ≤ 1 ) del objeto i es ubicada en la mochila 
	 * contribuye en x i *p i al peso total de la mochila y en x i *v i al 
	 * valor de la carga. 		
	 * */
	
	public static double getMaximum( double a, double b ){
		if (a > b)
			return a;
		else
			return b;
	}
	
	public static double getMinimum( double a, double b ){
		if (a < b)
			return a;
		else
			return b;
	}
	
	public static Double mochilaMaximizar(List<Objeto> mochila, int pesoMaximo){
		
		List<Double> r = new ArrayList<Double>();
		double suma;
		int objeto;
		
		// Inicializar en 0
		for(int i = 0; i < mochila.size(); i++)
			r.add(0.00);
		
		suma = 0;
		objeto = 0;
		
		while( suma < pesoMaximo){
			r.set(objeto,  getMinimum(1, (pesoMaximo - suma))
					/ mochila.get(objeto).p );
			
			suma = suma + getMinimum(1, (pesoMaximo - suma))
					/ mochila.get(objeto).p  * mochila.get(objeto).p ;
		}
			
		return 0.0;
	}
	
	public static Double mochilaMaximizarDin(List<Objeto> mochila, int pesoMaximo){
		
		Double matriz[][] = new Double[mochila.size() + 1][pesoMaximo + 1];

		// Inicializar matriz
		for(int i = 0; i <= mochila.size(); i++)
			for(int j = 0; j <= pesoMaximo; j++)
				// 0 en primer columna y fila
				if(i == 0 || j == 0)
					matriz[i][j] = 0.0;
				else
					matriz[i][j] = Double.MIN_VALUE; // - Menos infinito
		
		for(int i = 1; i <= mochila.size(); i++)
			for(int j = 1; j <= pesoMaximo; j++){
				// Capacidad superada?
				if( j - mochila.get(i - 1).p < 0)
					matriz[i][j] = getMaximum(mochila.get(i - 1).v * 
									(j / mochila.get(i - 1).p) 
									+ matriz[i - 1][0],
									matriz[i - 1][j]);
				else {
					matriz[i][j] = getMaximum(matriz[i - 1][j], 
							mochila.get(i - 1).v 
							+ matriz[i - 1][j - mochila.get(i - 1).p]);
				}
				
			}
			
		return matriz[mochila.size()][pesoMaximo];
	}
	
	public static void test(){
		List<Objeto> mochila = new ArrayList<Objeto> ();
		
		mochila.add(new Objeto("1", 18, 25));
		mochila.add(new Objeto("2", 15, 24));
		mochila.add(new Objeto("3", 10, 15));
		
		double resultado = mochilaMaximizarDin(mochila, 20);
		
		out.println("Maximo beneficio es " + resultado);
	}
}
