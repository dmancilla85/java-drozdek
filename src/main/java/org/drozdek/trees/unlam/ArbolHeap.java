package org.drozdek.trees.unlam;

/*********************************************************
* Archivo:     ArbolHeap.java
* Lenguaje:    Java
* Estudiantes: Qaser Awan Ruiz
* Instituci�n: Instituto Tecnol�gico de Costa Rica
* Notas:
*				-Restricciones:
*					No me hago responsable del uso que le den
*					a los algoritmos.
*
*        		-Observaciones:
*		  			El prototipo del trabajo fue brindado
*					en el curso de estructuras de datos.
*********************************************************/

import java.io.*;

class ArbolHeap{
	// Arreglo en el cual se almacenan los elementos.
	int[] llave;
	// Posici�n en la cual se va a insertar.
	int posicion;
	
	//Contructor
	public ArbolHeap(){
		llave = new int[10];
		posicion = 0;
	}
	
	//Obtiene el hijo derecho seg�n la posicion del padre
	public int hDer(int posPadre){
		return (2 * posPadre) + 1;
	}
	
	//Obtiene el hijo izquierdo seg�n la posicion del padre
	public int hIzq(int posPadre){
		return (2 * posPadre);
	}
	
	//Inserta un elemento dentro del arbol de Heap.
	public void inserta(int nLlave){
		int padre;
		int auxiliar;
		int siguiente;
		siguiente = posicion;
		padre = (siguiente / 2);
		if(padre < 0)
			padre = 0;
		llave[siguiente] = nLlave;
		// se acomodan los elementos para que el padre sea mayor que cualquiera de los hijos.
		while((siguiente != 0) && (llave[padre] <= llave[siguiente])){
			auxiliar = llave[padre];
			llave[padre] = llave[siguiente];
			llave[siguiente] = auxiliar;
			siguiente = padre;
			padre = (siguiente / 2);
		}
		posicion++;
	}
	
	//Ordena el �rbol de manera que quede en una cola de prioridad.
	public void HeapSort(){
		int padre, hijo, llaveAnterior;
		int ultima = posicion-1;				
		for(int i = 10; i >= 1;i--){	
			llaveAnterior = llave[ultima];
			llave[ultima] = llave[0];
			ultima = ultima - 1; 
			padre = 0;
			if((ultima >= 2) && (llave[2] > llave[1]))
				hijo = 2;	
			else
				hijo = 1;
			while((hijo <= ultima) && (llave[hijo] > llaveAnterior)){
				llave[padre] = llave[hijo];
				padre = hijo;
				hijo = padre * 2;
				if(((hijo + 1) <= ultima) && (llave[hijo + 1] > llave[hijo]))
					hijo++;	
				this.mostrar();
				
			}
			llave[padre] = llaveAnterior;
			this.mostrar();
		}
	}
	
	//Muestra al arreglo
	public void mostrar(){
		try{
	    	FileWriter fw = new FileWriter ("ArbolHeap.txt", true); //$NON-NLS-1$
	    	BufferedWriter bw = new BufferedWriter (fw);
	    	PrintWriter salida = new PrintWriter (bw);
			int i = 0;	
			while( i <= 9){			
				salida.print(llave[i] +" ");	 //$NON-NLS-1$
				i++;
			}
			salida.println(""); //$NON-NLS-1$
			salida.println("-------------------------"); //$NON-NLS-1$
			salida.close();
		}
		catch(Exception e){
		}
	}
	
	//Muestra sin mandarlo al archivo
	public String muestra(){
		String impresor=""; //$NON-NLS-1$
		int i = 0;	
		while( i <= 9){	
			if(llave[i]==0){
				impresor=impresor+"--" +"  "; //$NON-NLS-1$ //$NON-NLS-2$
			}
			else{		
				impresor=impresor+llave[i] +"  "; //$NON-NLS-1$
			}	
			i++;
		}
		return impresor;
	}
}

class ArbolHeapA{
	public static void main(String[] args){
		ArbolHeap nuevo=new ArbolHeap();
		nuevo.inserta(2);
		nuevo.inserta(7);
		nuevo.inserta(1);
		nuevo.inserta(9);
		nuevo.inserta(16);
		nuevo.inserta(3);
		nuevo.inserta(18);
		nuevo.inserta(10);
		nuevo.inserta(11);
		nuevo.inserta(22);
		System.out.println("Heap: "+nuevo.muestra()); //$NON-NLS-1$
		nuevo.HeapSort();
		System.out.println("Heap ordenado: "+nuevo.muestra()); //$NON-NLS-1$
	}
}