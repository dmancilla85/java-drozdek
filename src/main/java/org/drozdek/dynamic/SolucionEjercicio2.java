package org.drozdek.dynamic;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * 2. Greedy
 * Resolver el problema de selecciÃ³n de actividades. Es un problema de optimizaciÃ³n
 * combinatoria donde se deben seleccionar, dentro de un marco de tiempo determinado,
 * actividades de modo que no exista superposiciÃ³n entre las mismas. De cada una se
 * conoce un instante de inicio (si) y un instante de finalizaciÃ³n (fi). El problema
 * es seleccionar el nÃºmero mÃ¡ximo de actividades que pueden ser realizadas asumiendo
 * que sÃ³lo puede trabajar en una sola actividad a la vez.
 * <p>
 * <p>
 * ----------------------------------------
 * Elementos de los que consta la tÃ©cnica
 * <p>
 * -El conjunto C {\displaystyle C} C de candidatos, entradas del problema.
 * -FunciÃ³n soluciÃ³n. Comprueba, en cada paso, si el subconjunto actual de candidatos
 * elegidos forma una soluciÃ³n (no importa si es Ã³ptima o no lo es).
 * -FunciÃ³n de selecciÃ³n. Informa cuÃ¡l es el elemento mÃ¡s prometedor para completar la
 * soluciÃ³n. Ã‰ste no puede haber sido escogido con anterioridad. Cada elemento es
 * considerado una sola vez. Luego, puede ser rechazado o aceptado y pertenecerÃ¡ a C.
 * -FunciÃ³n de factibilidad. Informa si a partir de un conjunto se puede llegar a una
 * soluciÃ³n. Lo aplicaremos al conjunto de seleccionados unido con el elemento mÃ¡s
 * prometedor.
 * -FunciÃ³n objetivo. Es aquella que queremos maximizar o minimizar, el nÃºcleo del
 * problema.
 * <p>
 * Funcionamiento
 * <p>
 * El algoritmo escoge en cada paso al mejor elemento x âˆˆ C
 * posible, conocido como el elemento mÃ¡s prometedor. Se elimina ese elemento del
 * conjunto de candidatos ( C â†� C âˆ– { x } ) y, acto seguido, comprueba si la inclusiÃ³n
 * de este elemento en el conjunto de elementos seleccionados ( S âˆª { x } produce
 * una soluciÃ³n factible.
 * <p>
 * En caso de que asÃ­ sea, se incluye ese elemento en S. Si la
 * inclusiÃ³n no fuera factible, se descarta el elemento. Iteramos el bucle, comprobando
 * si el conjunto de seleccionados es una soluciÃ³n y, si no es asÃ­, pasando al siguiente
 * elemento del conjunto de candidatos.
 */

class Tarea implements Comparable<Tarea>, Comparator<Tarea> {
    int inicio;
    int fin;

    Tarea() {
        inicio = 0;
        fin = 0;
    }

    Tarea(int i, int f) {
        inicio = i;
        fin = f;
    }

    @Override
    public int compare(Tarea arg0, Tarea arg1) {
        // TODO Auto-generated method stub
        return arg0.compareTo(arg1);
    }

    @Override
    public int compareTo(Tarea arg0) {
        // TODO Auto-generated method stub
        return (this.fin) - (arg0.fin);
    }

    public String toString() {
        return "{S:" + inicio + ", E:" + fin + "}";
    }
}

/*- 	Crear el paquete ejercicio1
- 	Realizar una clase Tarea que tenga un entero de Inicio y otro de Fin
- 	El algoritmo debe devolver una clase SolucionEjercicio2 que tiene la cantidad de 
	tareas a ejecutar y un entero con la cantidad de instrucciones ejecutadas
-	Un metodo estatico Ejercicio 2 que reciba una lista de Tareas y devuelva un objeto 
	de la clase SolucionEjercicio2
-	Una prueba unitaria que a partir de 16 tareas pre-generadas verifique que devuelva 
	los puntos esperados y verifique que la cantidad de instrucciones ejecutadas*/

public class SolucionEjercicio2 {
    int cantidadTareas;
    int cantidadInstrucciones;
    ArrayList<Tarea> solucion;

    public SolucionEjercicio2() {
        cantidadTareas = 0;
        cantidadInstrucciones = 0;
        solucion = new ArrayList<Tarea>();
    }

    public String toString() {
        String aux = "";

        aux = "Tareas: " + cantidadTareas + "\nInstrucciones: "
                + cantidadInstrucciones + ".\n";

        for (int i = 0; i < solucion.size(); i++)
            aux += solucion.get(i) + "\n";
        return aux;
    }

}