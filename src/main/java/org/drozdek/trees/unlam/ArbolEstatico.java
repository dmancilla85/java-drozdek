/**
 * 
 */
package org.drozdek.trees.unlam;

/**
 * @author David
 *
 */
public class ArbolEstatico {

	private static final int TAM = 10;
	private final NodoVector[] arbol;
	private int n;
	
	
	/**
	 * 
	 */
	public ArbolEstatico() {
		// TODO Auto-generated constructor stub
		arbol = (NodoVector[]) new Object[TAM];
		n = 0;
	}
	
	
	/**
	 * 
	 * @param padre
	 */
	public ArbolEstatico(NodoVector padre) {
		// TODO Auto-generated constructor stub
		arbol = (NodoVector[]) new Object[TAM];
		arbol[0] = padre;
		n = 1;
	}
	
	
	/**
	 * 
	 * @param obj
	 */
	public void definePadre(NodoVector obj){
		
		if(arbol[0] == null && obj != null){
			arbol[0] = obj;
			n++;
		}
	}
	
	
	/**
	 * 
	 * @param padre
	 * @param obj
	 * @return
	 */
	public boolean defineHijoDerecho(int padre, NodoVector obj){
		try{
			if(((padre * 2) + 1) <= TAM - 1 && !estaCompleto()){
				arbol[(padre * 2) + 1] = obj;
				n++;
				return true;
			}
		} catch (ArrayIndexOutOfBoundsException e){
			e.printStackTrace();
			return false;
		}
		
		return false;
	}
	
	
	/**
	 * 
	 * @param padre
	 * @param obj
	 * @return
	 */
	public boolean defineHijoIzquierdo(int padre, NodoVector obj){
		try{
			if((padre * 2) <= TAM - 1 && !estaCompleto()){
				arbol[padre * 2] = obj;
				n++;
				return true;
			}
		} catch (ArrayIndexOutOfBoundsException e){
			e.printStackTrace();
			return false;
		}
		
		return false;
	}
	
	
	/**
	 * 
	 * @param padre
	 * @param obj
	 * @return
	 */
	/*public NodoVector hijoDerecho(int padre, NodoVector obj){
		try{
			return arbol[(padre * 2)];
		} catch (ArrayIndexOutOfBoundsException e){
			e.printStackTrace();
			return false;
		}
		
		return false;
	}*/
	
	
	/**
	 * 
	 * @param padre
	 * @param obj
	 * @return
	 */
	public NodoVector hijoIzquierdo(int padre, NodoVector obj){
		try{
			return arbol[(padre * 2) + 1];
		} catch (ArrayIndexOutOfBoundsException e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	/**
	 * 
	 * @param padre
	 * @param obj
	 * @return
	 */
	public boolean defineHijoMasDerecho(int padre, NodoVector obj){
		try{
			if((n - 1) % 2 != 0)
				arbol[n -1] = obj;
			else
				arbol[n -2] = obj;
		} catch (ArrayIndexOutOfBoundsException e){
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	
	/**
	 * 
	 * @param padre
	 * @param obj
	 * @return
	 */
	public boolean defineHijoMasIzquierdo(int padre, NodoVector obj){
		try{
			if((n - 1) % 2 == 0)
				arbol[n -1] = obj;
			else
				arbol[n -2] = obj;
		} catch (ArrayIndexOutOfBoundsException e){
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	
	/**
	 * 
	 * @return
	 */
	public Object hijoMasIzquierdo(){
		if((n - 1) % 2 == 0)
			return arbol[n -1];
		else
			return arbol[n -2];
	}
	
	
	/**
	 * 
	 * @return
	 */
	public Object hijoMasIzquierdo(int nodo){
		if((n - 1) % 2 == 0)
			return arbol[n -1];
		else
			return arbol[n -2];
	}
	
	
	/**
	 * 
	 * @return
	 */
	public Object hijoMasDerecho(){
		if((n - 1) % 2 != 0)
			return arbol[n -1];
		else
			return arbol[n -2];
	}
	
	
	/**
	 * 
	 * @return
	 */
	public boolean estaVacio(){
		return n == 0;
	}
	
	
	/**
	 * 
	 * @return
	 */
	public boolean estaCompleto(){
		return n == (TAM - 1);
	}
	
	
	/**
	 * 
	 * @param obj
	 * @return
	 */
	/*public boolean insertar(NodoVector obj){
		
		if(arbol[0].compareTo(obj) > 0){
			
		}
	}*/
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
