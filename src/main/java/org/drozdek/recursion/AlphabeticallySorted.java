package org.drozdek.recursion;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Character.*;
import static java.lang.System.out;

public class AlphabeticallySorted {
	
	// Determinar si una secuencia de n caracteres está ordenada alfabéticamente.
	public static boolean run(List<Character> s, int llamada){
		

		// Solución trivial
		if(s.size()<= 1)
			return true;

		if(getNumericValue(toUpperCase(s.get(0)))  <= getNumericValue(toUpperCase(s.get(1))))
		{
			out.println("llamada " + llamada + " s1: " 
					+ getNumericValue(toUpperCase(s.get(0))) 
					+ " y s2:" +  getNumericValue(toUpperCase(s.get(1))));
		
			s.remove(0);

			// Combinar resultados
			return run( s, ++llamada);
		}
		else
			return false;
		
	}
	
	public static void test(){
		
		ArrayList<Character> si = new ArrayList<Character>();
		ArrayList<Character> no = new ArrayList<Character>();
		
		si.add('a');
		si.add('c');
		si.add('e');
		si.add('a');
		si.add('y');
		si.add('Y');
		si.add('e');
		
		no.add('e');
		no.add('G');
		no.add('t');
		no.add('T');
		no.add('V');
		no.add('y');
		
		if( run(si, 0) )
			out.println("SIp está ordenada");
		else
			out.println("SIp no está ordenada");
		
		if( run(no, 0) )
			out.println("NOp está ordenada");
		else
			out.println("NOp no está ordenada");
	}
}
