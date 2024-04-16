package org.drozdek.sorting;

import java.util.ArrayList;
import java.util.List;

class Punto {
    int x;
    int y;

    public Punto(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String toString() {
        return "(" + x + "," + y + ")";
    }

}

class SolucionEjercicio1 {
    Punto puntoA, puntoB;
    double distancia;
    int instrucciones;

    SolucionEjercicio1(Punto a, Punto b) {
        puntoA = a;
        puntoB = b;
        instrucciones = 0;
        calcularDistancia();
    }

    public static void quicksort(List<SolucionEjercicio1> lista, int izq, int der) {

        SolucionEjercicio1 pivote = lista.get(izq); // tomamos primer elemento como pivote
        int i = izq; // i realiza la búsqueda de izquierda a derecha
        int j = der; // j realiza la búsqueda de derecha a izquierda
        SolucionEjercicio1 aux;

        while (i < j) {            // mientras no se crucen las búsquedas
            while (lista.get(i).distancia <= pivote.distancia && i < j)
                i++; // busca elemento mayor que pivote
            while (lista.get(j).distancia > pivote.distancia)
                j--;         // busca elemento menor que pivote
            if (i < j) {                      // si no se han cruzado
                aux = lista.get(i);                  // los intercambia
                lista.set(i, lista.get(j));
                // Contar instrucciones
                lista.get(i).instrucciones += 3;
                lista.set(j, aux);
            }
        }

        lista.get(i).instrucciones += 3;
        lista.set(izq, lista.get(j)); // se coloca el pivote en su lugar de forma que tendremos
        lista.set(j, pivote); // los menores a su izquierda y los mayores a su derecha
        if (izq < j - 1) {
            lista.get(i).instrucciones++;
            quicksort(lista, izq, j - 1); // ordenamos subarray izquierdo
        }

        if (j + 1 < der) {
            lista.get(i).instrucciones++;
            quicksort(lista, j + 1, der); // ordenamos subarray derecho
        }
    }

    public static SolucionEjercicio1 minimaDistanciaEntrePuntos(List<SolucionEjercicio1> solucion) {

        //ArrayList<PuntoSolucion> lista = new ArrayList<PuntoSolucion>();
        // Ordeno de menor a mayor por distancia

        if (solucion.size() == 0)
            return null;

        quicksort(solucion, 0, solucion.size() - 1); // nlogn + C
        solucion.get(0).instrucciones++;
        //Devuelvo la menor distancia (elemento i=1)
        return solucion.get(0);
    }

    void calcularDistancia() {

        distancia = Math.sqrt((puntoA.x - puntoB.x) * (puntoA.x - puntoB.x)
                + (puntoA.y - puntoB.y) * (puntoA.y - puntoB.y));
    }

    public String toString() {
        return "{" + puntoA + ";" + puntoB + "}";
    }
}

public class TestEjercicio1 {

    public static ArrayList<SolucionEjercicio1> testGenerarLista(List<Punto> puntos) {

        ArrayList<SolucionEjercicio1> sol = new ArrayList<SolucionEjercicio1>();
        int instr = 0;

        if (puntos.size() <= 1) {// c1
            instr++;
            return sol;
        }

        // Hacer apareamientos de puntos
        for (int i = 1; i < puntos.size(); i++) // n
        {
            instr++;
            sol.add(new SolucionEjercicio1(puntos.get(0), puntos.get(i))); // c2
        }

        puntos.remove(0); // c3
        instr++;

        for (int i = 0; i < sol.size(); i++)
            sol.get(i).instrucciones += instr;

        sol.addAll(testGenerarLista(puntos)); // t(n-1)

        return sol;
    }

    public static void main(String[] args) {

        List<Punto> puntos = new ArrayList<Punto>();
        puntos.add(new Punto(1, 0));
        puntos.add(new Punto(3, 124));
        puntos.add(new Punto(0, 34));
        puntos.add(new Punto(3, -3));
        puntos.add(new Punto(150, 15));
        puntos.add(new Punto(2, 2));
        puntos.add(new Punto(20, -11));
        puntos.add(new Punto(3, 1));
        puntos.add(new Punto(12, 0));
        puntos.add(new Punto(-2233, -343));
        puntos.add(new Punto(10, 1345));
        puntos.add(new Punto(2, 562));
        puntos.add(new Punto(76, -32));
        puntos.add(new Punto(-23, 3234));
        puntos.add(new Punto(11, 98));
        puntos.add(new Punto(33, -3));
        puntos.add(new Punto(10, 150));
        puntos.add(new Punto(2, -56));
        puntos.add(new Punto(65, 0));
        puntos.add(new Punto(3, 1231));
        puntos.add(new Punto(0, 45));
        puntos.add(new Punto(-3, 34));
        puntos.add(new Punto(21, -34));
        puntos.add(new Punto(-4983, -3));
        puntos.add(new Punto(120, 325));
        puntos.add(new Punto(2376, -2));
        puntos.add(new Punto(111, 23));
        puntos.add(new Punto(3213, 42));
        puntos.add(new Punto(-7380, 34));
        puntos.add(new Punto(223, -233));
        puntos.add(new Punto(12310, 15));
        puntos.add(new Punto(23, 2));
        puntos.add(new Punto(21120, -121));


        // Generar lista debería aparear todos los puntos de una colección
        ArrayList<SolucionEjercicio1> solucion = testGenerarLista(puntos);

        for (int i = 0; i < solucion.size(); i++)
            System.out.println(solucion.get(i));

        SolucionEjercicio1 minima = SolucionEjercicio1.minimaDistanciaEntrePuntos(solucion); // nlogn + C

        if (minima == null) {
            System.out.println("No hay puntos para calcular la distancia.");
        } else {

            System.out.println();
            System.out.printf("La distancia minima es %.3f\n", minima.distancia);
            System.out.println();
            System.out.println("Los puntos son: " + minima.puntoA + " y " + minima.puntoB);
            System.out.println();
            System.out.println("La cantidad de pasos " + minima.instrucciones);
        }

        System.exit(0);
    }

    @SuppressWarnings("unused")
    private static double distancia(int x, int y, int x2, int y2) {
        return Math.sqrt((x2 - x) * (x2 - x) + (y2 - y) * (y2 - y));
    }
}
