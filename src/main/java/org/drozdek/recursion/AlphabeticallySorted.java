package org.drozdek.recursion;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Character.getNumericValue;
import static java.lang.Character.toUpperCase;

import org.drozdek.commons.LoggerService;

public final class AlphabeticallySorted {
private AlphabeticallySorted() {
        // do nothing
    }

    // Check if a sequence of characters is alphabetically sorted (case-insensitive)
    public static boolean run(List<Character> s, int llamada) {

        // Base case: a single character (or empty) is always sorted
        if (s.size() <= 1)
            return true;

        if (getNumericValue(toUpperCase(s.get(0))) <= getNumericValue(toUpperCase(s.get(1)))) {
            LoggerService.logInfo("llamada " + llamada + " s1: "
                    + getNumericValue(toUpperCase(s.get(0)))
                    + " y s2:" + getNumericValue(toUpperCase(s.get(1))));

            s.remove(0);

            // Recurse on the remaining characters
            return run(s, ++llamada);
        } else
            return false;

    }

    public static void test() {

        ArrayList<Character> si = new ArrayList<>();
        ArrayList<Character> no = new ArrayList<>();

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

        if (run(si, 0))
            LoggerService.logInfo("SIp está ordenada");
        else
            LoggerService.logInfo("SIp no está ordenada");

        if (run(no, 0))
            LoggerService.logInfo("NOp está ordenada");
        else
            LoggerService.logInfo("NOp no está ordenada");
    }
}
