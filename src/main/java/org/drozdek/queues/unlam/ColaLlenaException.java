package org.drozdek.queues.unlam;

public class ColaLlenaException extends ArrayIndexOutOfBoundsException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ColaLlenaException(){
		super("El tama�o de la cola ha sido excedido. Ser� re-dimensionado");
	}

}
