package com.fing.util;

/**
 * Created by jmsmuy on 20/08/16.
 */
public class PhraseGenerator {

    private static String[] wordList = {"hola", "soy", "un", "producto", "loco", "rojo", "vago", "blabla"};

    /**
     * Genera un String de i palabras
     *
     * @return
     */
    public static String generateString(int i) {
        if (i < 1) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        String firstWord = wordList[RandomGenerator.randomGen(0, wordList.length - 1)];
        String lowerCase = firstWord.substring(1);
        String upperCase = firstWord.substring(0, 1);
        builder.append(upperCase.toUpperCase() + lowerCase);
        for (int p = 0; p < i; p++) {
            builder.append(" ");
            builder.append(wordList[RandomGenerator.randomGen(0, wordList.length - 1)]);
            builder.append(" ");
            builder.append(wordList[RandomGenerator.randomGen(0, wordList.length - 1)]);
        }
        return builder.toString();
    }
}
