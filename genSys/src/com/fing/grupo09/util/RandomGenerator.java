package com.fing.grupo09.util;

import java.util.Random;

/**
 * Created by jmsmuy on 20/08/16.
 */
public class RandomGenerator {

    private static Random random = null;

    /**
     * Genera un número entero randomico entre start y finish (incluyendolos)
     *
     * @param start
     * @param finish
     * @return
     */
    public static int randomGen(int start, int finish) {
        if (random == null) {
            random = new Random();
        }
        int randomInt = random.nextInt(finish - start + 1) + start;
        return randomInt;
    }

    /**
     * Genera un número double randomico entre start y finish (incluyendolos)
     *
     * @param start
     * @param finish
     * @return
     */
    public static Double randomGenDbl(Double start, Double finish) {
        if (random == null) {
            random = new Random();
        }
        Double randomDbl = random.nextDouble() * (finish - start) + start;
        return randomDbl;
    }
}
