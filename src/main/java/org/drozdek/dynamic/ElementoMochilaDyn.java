/**
 * 
 */
package org.drozdek.dynamic;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * @author david
 *
 */

class SolucionEjercicio5 {
	int maximaGanancia;
	int cantidadDeInstrucciones;
	ArrayList<ElementoMochilaDyn> mochila;
	
	
	SolucionEjercicio5(){
		this.maximaGanancia= 0;
		this.cantidadDeInstrucciones = 0; 
		this.mochila = new ArrayList<ElementoMochilaDyn> ();
	}
	
	public String toString(){
		String aux =  "Ganancia total: " + maximaGanancia +
				"\nTotal instrucciones: " + cantidadDeInstrucciones + ".\n";
		
		for(int i = 0; i < mochila.size(); i++){
			aux += mochila.get(i) + "\n";
		}
		
		return aux;
	}
	
}

public class ElementoMochilaDyn implements Comparable<ElementoMochilaDyn>, Comparator<ElementoMochilaDyn>{

	int peso;
	int ganancia;
	
	public ElementoMochilaDyn(){
		this.peso = 0;
		this.ganancia = 0;
	}
	
	public ElementoMochilaDyn(int peso, int ganancia){
		this.peso = peso;
		this.ganancia = ganancia;
	}
	
	public String toString(){
		return "[P: " + peso + ", G: " + ganancia + "]";
	}

	@Override
	public int compare(ElementoMochilaDyn arg0, ElementoMochilaDyn arg1) {
		// TODO Auto-generated method stub
		return arg0.compareTo(arg1);
	}

	@Override
	public int compareTo(ElementoMochilaDyn arg0) {
		// TODO Auto-generated method stub
		return this.ganancia - arg0.ganancia;
	}
	
	public static SolucionEjercicio5 ejercicio5 (ArrayList<ElementoMochilaDyn> mochila){
		
		SolucionEjercicio5 sol = new SolucionEjercicio5();
		
		System.out.println(sol);
		return sol;
	}

}
