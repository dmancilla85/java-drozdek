package org.drozdek.commons;

import java.util.Random;

public class ArrayUtils {

    private static final Random r = new Random();

    private ArrayUtils(){
        // do nothing
    }

    public static int getMaxValue(int []array){
        int max = array[0];

        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }

        return max;
    }

    public static void swap(int[] array, int i, int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void printArray(int[] array) {

        StringBuilder msg = new StringBuilder();

        for (int element : array) {
            msg.append(element).append(" ");
        }

        LoggerService.logInfo(msg.toString());
    }

    public static int[] randomIntegerArray(int n, boolean naturals){
        if(n <= 0){
            return new int[0];
        }

        int[] array = new int[n];

        for (int i = 0; i < array.length; i++) {
            array[i] = naturals ? r.nextInt(1, 1000) : r.nextInt(-1000, 1000);
        }

        return array;
    }
}
