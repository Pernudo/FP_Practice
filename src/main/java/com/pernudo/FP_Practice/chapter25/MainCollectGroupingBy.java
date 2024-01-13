package com.pernudo.FP_Practice.chapter25;

import com.pernudo.FP_Practice.pojos.Product;
import com.pernudo.FP_Practice.utils.ProductJavaFaker;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class MainCollectGroupingBy {

    public static List<Product> lstProducts;

    public static void main(String[] args) {
        lstProducts = ProductJavaFaker.getList(1000);

        System.out.println("===***===groupingBySimple===***===");
        groupingBySimple().entrySet()
                .forEach(System.out::println);

        System.out.println("===***===groupingByMedium===***===");
        groupingByMedium().entrySet()
                .forEach(System.out::println);

        System.out.println("===***===groupingByComplete===***===");
        groupingByComplete().entrySet()
                .forEach(System.out::println);

        System.out.println("===***===groupingByComplex===***===");
        groupingByComplex().entrySet()
                .forEach(System.out::println);
    }

    private static Map<String, List<Product>> groupingBySimple() {
        return lstProducts.stream()
                .collect(Collectors.groupingBy(Product::getMaterial));
    }

    private static Map<String, Long> groupingByMedium() {
        return lstProducts.stream()
                .collect(Collectors.groupingBy(Product::getMaterial, Collectors.counting()));
    }

    private static LinkedHashMap<String, Set<Product>> groupingByComplete() {
        return lstProducts.stream()
                .collect(Collectors.groupingBy(Product::getMaterial, LinkedHashMap::new, Collectors.toSet()));
    }

    private static Map<Integer, String> groupingByComplex() {
        Function<Product, Integer> classifier = product -> product.getPrice() > 50 ? 1 : 0;
        Collector<Product, ?, String> downStream = Collectors.mapping(Product::getName, Collectors.joining(", "));
        return lstProducts.stream()
                .collect(Collectors.groupingBy(classifier, downStream));
    }
}
