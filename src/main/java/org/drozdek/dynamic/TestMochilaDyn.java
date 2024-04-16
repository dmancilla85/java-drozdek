/**
 *
 */
package org.drozdek.dynamic;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author david
 *
 */
public class TestMochilaDyn {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        ArrayList<ElementoMochilaDyn> mochila = new ArrayList<ElementoMochilaDyn>();

        mochila.add(new ElementoMochilaDyn(3, 4));
        mochila.add(new ElementoMochilaDyn(2, 6));
        mochila.add(new ElementoMochilaDyn(1, 9));
        mochila.add(new ElementoMochilaDyn(4, 1));
        mochila.add(new ElementoMochilaDyn(4, 10));
        mochila.add(new ElementoMochilaDyn(5, 2));

        Collections.sort(mochila);

        for (int i = 0; i < mochila.size(); i++)
            System.out.println(mochila.get(i));

        ElementoMochilaDyn.ejercicio5(mochila);
    }

}
