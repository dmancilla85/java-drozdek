package org.drozdek.stacks.unlam;

import static java.lang.System.out;

public class PilaDinamica implements Pila{

	/**
	 * @param args
	 */
	
	public class NodoPila {

		private Object dato;
		private NodoPila sig;
	}
	
	private NodoPila nodo;
	
	
	
	public PilaDinamica() {
		this.nodo = null;
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		PilaDinamica p1 = new PilaDinamica();
		
		p1.apilar(5);
		p1.apilar("jlk");
		p1.apilar(25);
		p1.apilar("j5k6");
		p1.apilar(34);
		p1.apilar(3782.45);
		p1.apilar("dedj");
		p1.apilar(5);
		p1.apilar("jlk");
		p1.apilar(25);
		p1.apilar("j5k6");
		p1.apilar(34);
		p1.apilar(3782.45);
		p1.apilar("dedj");
		p1.apilar(25);
		p1.apilar("j5k6");
		p1.apilar(34);
		p1.apilar(3782.45);
		p1.apilar("dedj");
		p1.apilar(5);
		p1.apilar("jlk");
		p1.apilar(25);
		p1.apilar("j5k6");
		p1.apilar(34);
		p1.apilar(3782.45);
		p1.apilar("dedj");
		p1.apilar(25);
		p1.apilar("j5k6");
		p1.apilar(34);
		p1.apilar(3782.45);
		p1.apilar("dedj");
		p1.apilar(5);
		p1.apilar("jlk");
		p1.apilar(25);
		p1.apilar("j5k6");
		p1.apilar(34);
		p1.apilar(3782.45);
		p1.apilar("dedj");
		
		int i = 1;
		while(!p1.esVacia())
			out.println(i++ + "� ingresado: " + p1.desapilar());
	}


	@Override
	public boolean apilar(Object obj) {
		// TODO Auto-generated method stub
		try {
			NodoPila aux = this.nodo;
			this.nodo = new NodoPila();
			nodo.dato = obj;
			nodo.sig = aux;
		} catch(Exception e){
			return false;
		}
		
		return true;
	}


	@Override
	public Object desapilar() {
		// TODO Auto-generated method stub
		if(nodo != null){
			Object dato = nodo.dato;
			this.nodo = this.nodo.sig;
			return dato;
		}
		
		return null;
	}


	@Override
	public void vaciar() {
		// TODO Auto-generated method stub
		this.nodo = null;
		
	}


	@Override
	public Object verTope() {
		// TODO Auto-generated method stub
		return this.nodo.dato;
	}


	@Override
	public boolean esVacia() {
		// TODO Auto-generated method stub
		return this.nodo == null;
	}



}
