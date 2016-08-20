package com.fing.grupo09;

import com.fing.grupo09.util.OrderGenerator;

public class GenSys1 {

    public static void main(String[] args) {
        System.out.println("Test");
        System.out.println("Este sistema genera ordenes aleatorias");
        for (int i = 0; i < 100; i++) {
            System.out.println(OrderGenerator.generateOrder().toString());
        }
    }
}
