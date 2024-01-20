package org.drozdek.trees.unlam;
import static java.lang.System.out;

public class NodoVector implements Comparable<NodoVector> {

	private Integer valor;
	
	/**
	 * @return the valor
	 */
	public Integer getValor() {
		return valor;
	}

	/**
	 * @param valor the valor to set
	 */
	public void setValor(Integer valor) {
		this.valor = valor;
	}

	public NodoVector(){
		this(0);
	}
	
	public NodoVector(Integer valor){
		this.valor = valor;
	}
	
	public String toString(){
		return valor.toString();
	}
	
	public NodoVector clone(){
		return new NodoVector(this.valor);
	}
	
	public int compareTo(NodoVector o) {
		// TODO Auto-generated method stub
		/* 
		 * LINEAMIENTOS
		 * ============
		 * Si obj.llamador == obj.comp => 0
		 * Si obj.llamador < obj.comp => < 0
		 * Si obj.llamador > obj.comp => > 0
		*/
		
		return this.valor.compareTo(o.valor);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		NodoVector a = new NodoVector(34);
		NodoVector b = new NodoVector(23);
		NodoVector c = new NodoVector(2);
		NodoVector d = new NodoVector(23);
		out.println(a.compareTo(b));
		out.println(b.compareTo(c));
		out.println(c.compareTo(a));
		out.println(b.compareTo(b));
		out.println(b.compareTo(d));
	
	}
}
