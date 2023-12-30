package com.pernudo.FP_Practice.chapter2;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class ConsumerExamples {

    static List<Object> lst = new ArrayList<>();

    public static void main (String[] args){
        lst.add("Hola Mundo");
        lst.add(64);
        lst.add(true);
        lst.add(12.34f);

        /* noinspection => to suppress warning "Commented out code (n lines)
        usingConsumer("Hola soy un valor del tipo T",
                System.out::println,
                lst::add,
                ConsumerExamples::printList
                );
        */

        usingBiConsumer(4, 1000,
                ConsumerExamples::printBefore,
                lst::add,
                ConsumerExamples::printAfter
                );
    }

    @SuppressWarnings({"SameParameterValue", "unused"}) // => Actual value of parameter 'value' is always 'Hola soy un valor del tipo T'
    private static <T> void usingConsumer(T value, Consumer<T> consumer, Consumer<T> consumer2, Consumer<T> consumer3) {
        /* noinspection => to suppress warning "Commented out code (n lines)
        consumer.accept(value);
        consumer2.accept(value);
        consumer3.accept(value);
        */

        var consumerCompeted = consumer.andThen(consumer2).andThen(consumer3);
        consumerCompeted.accept(value);
    }

    @SuppressWarnings("SameParameterValue") // => Actual value of parameter 'value1' is always '4' & 'value2' is always '1000'
    private static <T, U> void usingBiConsumer(T value1, U value2, BiConsumer<T, U> biConsumer, BiConsumer<T, U> biConsumer2, BiConsumer<T, U> biConsumer3) {
        biConsumer.andThen(biConsumer2).andThen(biConsumer3).accept(value1, value2);
    }

    @SuppressWarnings("unused")
    private static <T> void printList(T value) {
        System.out.println("Se añadió a la lista el valor: "+value+" -- Total de valores de la lista:");
        lst.forEach(System.out::println);
    }

    private static void printBefore(Integer e, Integer v) {
        System.out.println("En el index: " + e + " se intenta añadir el elemento " + v);
    }

    private static void printAfter(Integer e, Integer v) {
        System.out.println("En el index: " + e + " se intenta añadió el elemento " + v);
        lst.forEach(System.out::println);
    }
}
