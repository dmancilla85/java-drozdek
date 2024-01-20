package org.drozdek.queues.unlam;

import static java.lang.Math.random;
import static java.lang.System.out;
import static java.lang.System.err;

public class ColaEstatica implements Cola {

	private static final int TAM_PREDET = 5;
	private Object []cola;
	private int primer, ultimo, tam;
	
	public ColaEstatica(){
		cola = new Object[TAM_PREDET];
		this.tam = TAM_PREDET;
		primer = 0;
		ultimo = -1;
	}
	
	public ColaEstatica(int tam){
		cola = new Object[tam];
		this.tam = tam;
		primer = 0;
		ultimo = -1;
	}
	
	@Override
	public boolean encolar(Object obj) {
		// TODO Auto-generated method stub
			
			try{
				if(estaLlena())
					throw new ColaLlenaException();
				
				ultimo = ++ultimo % tam; 
				cola[ultimo] = obj;
			} catch(ColaLlenaException e){
				err.println(e.getMessage() + " -- Indice: " + ultimo + 1 );
				reDim();
				encolar(obj);
			} catch(Exception e){
				return false;
			}
			
			return true;
	}

	@Override
	public Object desencolar() {
		// TODO Auto-generated method stub
		Object aux;
		
		if(!estaVacia()){
			
			aux = cola[primer];
			
			if(primer == ultimo)
				vaciar();
			else
				primer = (primer + 1) % tam;
			
			
			return aux;
		}
		else
			return null;
	}

	@Override
	public void vaciar() {
		// TODO Auto-generated method stub
		primer = 0;
		ultimo = -1;

	}

	@Override
	public boolean estaVacia() {
		// TODO Auto-generated method stub
		return (primer == 0 && ultimo == -1);

	}
	
	public boolean estaLlena(){
		return ((primer == 0 && ultimo == tam - 1) || 
				(primer == ultimo + 1 && ultimo != -1));
	}

	private void reDim(){
		tam = 2 * tam;
		Object []aux = new Object[tam];

        System.arraycopy(cola, 0, aux, 0, cola.length);
		
		cola = aux;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Cola c1 = new ColaEstatica();
		
		c1.encolar("A");
		c1.encolar("B");
		c1.encolar("C");
		
		int i = 0, n = 2600;
		while(i++ < n)
			c1.encolar(random() * 100 * i);
		
		c1.encolar("ULTIMO OBJETO");
		
		while(!c1.estaVacia())
			out.println(c1.desencolar());
		
	}

	@Override
	public Object tope() {
		// TODO Auto-generated method stub
		if(!estaVacia())
			return cola[primer];
		return null;
	}

}
