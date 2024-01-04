package com.pernudo.FP_Practice.chapter10;

import com.pernudo.FP_Practice.utils.Utils;

import java.util.Arrays;
import java.util.List;
import java.util.function.IntConsumer;
import java.util.function.IntUnaryOperator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MainStreamIntroSample {
    public static void main(String[] args) throws Exception {
        List<Character> lstCharacters = Utils.getCharacters();
        lstCharacters.forEach(System.out::println);
        System.out.println("----------Arrays----------");
        var arrayCharacters = lstCharacters.toArray();
        Arrays.asList(arrayCharacters).forEach(System.out::println);
        System.out.println("----------Arrays Con Stream----------");
        Stream.of(arrayCharacters).forEach(System.out::println);
        System.out.println("----------Arrays Con Stream2----------");
        Arrays.stream(arrayCharacters).forEach(System.out::println);

        //Tipos primitivos Streams -> int, double, long
        double[] doubleArray = new double[]{1.23, 2.34, 3.45, 4.56, 5.67};
        Arrays.stream(doubleArray).forEach(System.out::println);

        IntUnaryOperator operatorInfinite = intValue -> intValue + 1;
        IntConsumer intConsumer = System.out::println;
        IntStream.iterate(0, intValue -> intValue <= 1000, operatorInfinite)
                .forEach(intConsumer);
    }

}
