package org.drozdek.queues.unlam;

public interface Cola {
	
	boolean encolar(Object obj);
	Object desencolar();
	Object tope();
	void vaciar();
	boolean estaVacia();
}
