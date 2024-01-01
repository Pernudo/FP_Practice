package com.pernudo.FP_Practice.chapter6;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

public class MainUnaryBinaryOperator {

    public static void main (String[] args){
        List<String> inmutableList = List.of(" -Juan- ", " -Laura- ", " -Rosa- ", " -Pedro- ", " -Eloy- ");

        usingUnaryOperator(String::toUpperCase, inmutableList).forEach(System.out::println);
        usingUnaryOperatorAndThen(String::toLowerCase, String::strip, inmutableList).forEach(System.out::println);

        BinaryOperator<String> binaryOperator = (text, word) -> text.replace(word, "");
        var text = "tres tristes tigres, tragaban trigo en un trigal, en tres tristes trastos, tragaban trigo tres tristes tigres";
        var word = "tres";
        System.out.println(binaryOperator.apply(text, word));

        BinaryOperator<Integer> binaryOperator2max = BinaryOperator.maxBy(Integer::compareTo);
        BinaryOperator<Integer> binaryOperator2min = BinaryOperator.minBy(Integer::compareTo);
        System.out.println(binaryOperator2max.apply(1000, 300));
        System.out.println(binaryOperator2min.apply(1000, 300));
    }

    private static <T> List<T> usingUnaryOperator(UnaryOperator<T> unaryOperator, List<T> list){
        List<T> newList = new ArrayList<>();
        list.forEach(element -> newList.add(unaryOperator.apply(element)));
        return newList;
    }

    private static <T> List<T> usingUnaryOperatorAndThen(UnaryOperator<T> unaryOperator1, UnaryOperator<T> unaryOperator2, List<T> list){
        List<T> newList = new ArrayList<>();
        list.forEach(element -> newList.add(unaryOperator1.andThen(unaryOperator2).apply(element)));
        return newList;
    }

}
