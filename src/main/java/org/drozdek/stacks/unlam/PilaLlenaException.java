package org.drozdek.stacks.unlam;

public class PilaLlenaException extends ArrayIndexOutOfBoundsException  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PilaLlenaException(){
		super("La pila no dispone de m�s capacidad para almacenar datos");
	}
}
