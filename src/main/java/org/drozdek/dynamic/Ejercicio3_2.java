package org.drozdek.dynamic;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

class Objeto {
    String name;
    int p; // Peso
    int v; // Valor

    Objeto(String name, int p, int v) {
        this.name = name;
        this.p = p;
        this.v = v;
    }
}

public class Ejercicio3_2 {

    /*	Problema de la Mochila: Se tienen n objetos y una mochila.
     * Para i = 1,2,..n , el objeto i tiene un peso positivo p(i) y
     * un valor positivo v(i) .
     * La mochila puede llevar un peso que no sobrepase P .
     * El objetivo es llenar la mochila de tal manera que se maximice
     * el valor de los objetos transportados, respetando la limitación de
     * capacidad impuesta. Los objetos pueden ser fraccionados, si una fracción
     * x i ( 0 ≤ ≤≤ ≤ x i ≤ ≤≤ ≤ 1 ) del objeto i es ubicada en la mochila
     * contribuye en x i *p i al peso total de la mochila y en x i *v i al
     * valor de la carga.
     * */

    public static double getMaximum(double a, double b) {
        return Math.max(a, b);
    }

    public static double getMinimum(double a, double b) {
        return Math.min(a, b);
    }

    public static Double mochilaMaximizar(List<Objeto> mochila, int pesoMaximo) {

        List<Double> r = new ArrayList<Double>();

        // Inicializar en 0
        for (int i = 0; i < mochila.size(); i++)
            r.add(0.00);

        double suma = 0;
        int objeto = 0;

        while (suma < pesoMaximo && objeto < mochila.size()) {
            double fraccion = getMinimum(1, (pesoMaximo - suma) / (double) mochila.get(objeto).p);
            r.set(objeto, fraccion);

            suma += fraccion * mochila.get(objeto).p;
            objeto++;
        }

        double valorTotal = 0;
        for (int i = 0; i < mochila.size(); i++) {
            valorTotal += r.get(i) * mochila.get(i).v;
        }
        return valorTotal;
    }

    public static Double mochilaMaximizarDin(List<Objeto> mochila, int pesoMaximo) {

        Double[][] matriz = new Double[mochila.size() + 1][pesoMaximo + 1];

        // Inicializar matriz
        for (int i = 0; i <= mochila.size(); i++)
            for (int j = 0; j <= pesoMaximo; j++)
                // 0 en primer columna y fila
                if (i == 0 || j == 0)
                    matriz[i][j] = 0.0;
                else
                    matriz[i][j] = Double.NEGATIVE_INFINITY;

        for (int i = 1; i <= mochila.size(); i++)
            for (int j = 1; j <= pesoMaximo; j++) {
                // Capacidad superada?
                if (j - mochila.get(i - 1).p < 0)
                    matriz[i][j] = getMaximum(mochila.get(i - 1).v *
                                    ((double) j / mochila.get(i - 1).p)
                                    + matriz[i - 1][0],
                            matriz[i - 1][j]);
                else {
                    matriz[i][j] = getMaximum(matriz[i - 1][j],
                            mochila.get(i - 1).v
                                    + matriz[i - 1][j - mochila.get(i - 1).p]);
                }

            }

        return matriz[mochila.size()][pesoMaximo];
    }

    public static void test() {
        List<Objeto> mochila = new ArrayList<Objeto>();

        mochila.add(new Objeto("1", 18, 25));
        mochila.add(new Objeto("2", 15, 24));
        mochila.add(new Objeto("3", 10, 15));

        double resultado = mochilaMaximizarDin(mochila, 20);

        out.println("Maximo beneficio es " + resultado);
    }
}
