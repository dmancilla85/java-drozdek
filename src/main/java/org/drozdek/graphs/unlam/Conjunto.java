/**
 * 
 */
package org.drozdek.graphs.unlam;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lab4
 *
 */
public class Conjunto<T> {

	private List<T> elementos;
	
	/**
	 * 
	 */
	public Conjunto() {
		// TODO Auto-generated constructor stub
		elementos = new ArrayList<T>();
	}
	
	/**
	 * 
	 */
	public Conjunto(int n) {
		// TODO Auto-generated constructor stub
		if(n > 0)
			elementos = new ArrayList<T>(n);
	}
	
	public void agregar(T e){
		if(e != null)
			elementos.add(e);
	}
	
	public void quitar(T e){
		if(e != null)
			elementos.remove(e);
	}
	
	public void union(Conjunto<T> B){
		this.elementos.addAll(B.elementos);
	}
	
	public void quitarTodo(Conjunto<T> B){
		this.elementos.removeAll(B.elementos);
	}
	
	public boolean estaIncluido(Conjunto<T> B){
		return this.elementos.containsAll(B.elementos);
	}
	
	public int cardinalidad(){
		return elementos.size();
	}
	
	public boolean perteneceA(T e){
		if(e != null)
			return elementos.contains(e);
		
		return false;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Conjunto<Object> A =  new Conjunto<Object>();
		A.agregar("Hola");
		A.agregar(324.4);
		A.agregar(11);
		A.agregar("Chau");
		
		
		// desarrollo 20 minutos
		// tiempo total 26 minutos

	}

}
