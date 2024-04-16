package org.drozdek.recursion;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public class MajorityElement {

    /* Dado un vector A de números enteros, calcular elemento mayoritario.
     * Si se tiene un vector A de n enteros, un elemento x se denomina
     * mayoritario de A si x aparece en el vector A más de n/2 veces.
     * Considerar que no puede haber más de un elemento mayoritario.
     *
     * */

    public static Integer run(List<Integer> a, int index) {

        int mitad = a.size() / 2;
        int count = 0, number;

        // Caso Trivial
        if (a.size() <= 2)
            return null;

        // Descomponer
        if (index < a.size())
            number = a.get(index);
        else
            return null;

        for (int i = 0; i < a.size(); i++)
            if (a.get(i) == number)
                count++;

        if (mitad < count)
            return number;
        else
            return run(a, ++index);
    }

    public static void test() {
        List<Integer> a = new ArrayList<Integer>();
        Integer mayor = 0;

        a.add(1);
        a.add(6);
        a.add(3);
        a.add(3);
        a.add(3);
        a.add(6);
        a.add(6);
        a.add(6);
        a.add(1);
        a.add(6);
        a.add(6);

        mayor = run(a, 0);

        if (mayor != null)
            out.println("El elemento mayoritario es " + mayor + ".");
        else
            out.println("No hay elemento mayoritario.");
    }
}
