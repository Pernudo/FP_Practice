package com.pernudo.FP_Practice.Chapter18;

import com.pernudo.FP_Practice.utils.ProductJavaFaker;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

public class MainReduce {

    public static final String SEPARATOR = " // ";
    public static final String TIPOS_DE_MATERIALES = "Tipos de Materiales: ";
    private static final Set<String> setOfThread = new HashSet<>();

    public static void main(String[] args) throws Exception {
        var lstProducts = Stream.generate(ProductJavaFaker::createProduct)
                .limit(200).toList();

        System.out.println("Reduce Simple ->" + lstProducts.stream()
                .map(product -> product.getMaterial().concat(SEPARATOR))
                .distinct()
                .reduce(TIPOS_DE_MATERIALES, String::concat));

        System.out.println("Reduce Optional ->" + lstProducts.stream()
                .map(product -> product.getMaterial().concat(SEPARATOR))
                .distinct()
                .reduce(String::concat)
                .orElse(null));

        BiFunction<Integer, Integer, Integer> accumulator = (sum, num) -> {
            var location = "accumulator";
            var threadName = Thread.currentThread().getName();
            setOfThread.add(threadName);
            System.out.println(location + ": " + threadName + "//sum-> " + sum);
            System.out.println(location + ": " + threadName + "//num-> " + num);
            return sum + num * 2;
        };
        BinaryOperator<Integer> combiner = (sum1, sum2) -> {
            var location = "combiner";
            var threadName = Thread.currentThread().getName();
            setOfThread.add(threadName);
            var sumTotal = sum1 + sum2;
            System.out.println(location + ": " + threadName + "//sum1-> " + sum1);
            System.out.println(location + ": " + threadName + "//sum2-> " + sum2);
            System.out.println("Suma total actual: " + sumTotal);
            return sumTotal;
        };
        var result = Stream.of(628, 655, 950, 857, 985, 902, 45, 859, 221, 690, 362, 569, 214, 332, 570, 598, 509, 311, 663, 86)
                .parallel()
                .reduce(0, accumulator, combiner);
        System.out.println("[" + result + "]");
        resultSeq();
        System.out.println("Nº de hilos utilizados: " + setOfThread.size());
    }

    private static void resultSeq() {
        System.out.println(Stream.of(628, 655, 950, 857, 985, 902, 45, 859, 221, 690, 362, 569, 214, 332, 570, 598, 509, 311, 663, 86)
                .map(number -> number * 2)
                .reduce(Integer::sum));
    }
}
