package com.pernudo.FP_Practice.chapter7;

import java.util.function.Predicate;
import java.util.function.BiPredicate;

public class MainPredicate {

    @SuppressWarnings("unused")
    public static void main (String[] args){
        Predicate<Object> isString = e -> e instanceof String;
        var resultPredicate = usingPredicate(isString, "Hola") ? "Es un String" : "No es un String";
        System.out.println(resultPredicate);

        Predicate<Float> isGreaterThenZero = e -> e > 0;
        Predicate<Float> isLessThen3000 = e -> e < 3000;
        Predicate<Float> isInfinite = e -> Float.isInfinite(e);
        Predicate<Float> notIsGreaterThenZero = Predicate.not(isGreaterThenZero);
        var resultPredicates = usingPredicates(isGreaterThenZero, isLessThen3000, isInfinite, 1234f) ? "Valor aceptado" : "Valor rechazado";
        System.out.println(resultPredicates);

        BiPredicate<Integer, Integer> biPredicate = MainPredicate::isGreater;
        System.out.println("¿Es mayor el primer número? " + biPredicate.test(1234, 64));
    }

    @SuppressWarnings("SameParameterValue")
    private static <T> boolean usingPredicate(Predicate<T> predicate, T value){
        return predicate.test(value);
    }

    @SuppressWarnings("SameParameterValue")
    private static <T> boolean usingPredicates(Predicate<T> predicate,
                                               Predicate<T> predicateAnd,
                                               Predicate<T> predicateOr,T value){
        return predicate.and(predicateAnd).or(predicateOr).test(value);
    }

    public static boolean isGreater(Integer greater, Integer less){
        return greater > less;
    }

}
