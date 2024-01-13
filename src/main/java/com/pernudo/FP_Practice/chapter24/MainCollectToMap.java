package com.pernudo.FP_Practice.chapter24;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.pernudo.FP_Practice.pojos.Product;
import com.pernudo.FP_Practice.utils.ProductJavaFaker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class MainCollectToMap {

    public static List<Product> lstProducts;

    public static void main(String[] args) {
        lstProducts = ProductJavaFaker.getList(1000);

        System.out.println("simple");
        simple();
        System.out.println("merge");
        merge();
        System.out.println("mergeSupp");
        mergeSupp();
        System.out.println("grouping");
        grouping();
    }

    private static void grouping() {
        Map<String, List<Product>> map = lstProducts.stream()
                .collect(Collectors.groupingBy(Product::getMaterial));
        map.entrySet().forEach(System.out::println);
    }

    private static void mergeSupp() {

        Function<Product, List<Product>> valueMap = product -> {
            var lst = new ArrayList<Product>();
            lst.add(product);
            return lst;
        };
        BinaryOperator<List<Product>> merge = (lst1, lst2) -> {
            lst2.addAll(lst1);
            return lst2;
        };
        Supplier<Map<String, List<Product>>> supplier = HashMap::new;
        Map<String, List<Product>> map = lstProducts.stream()
                .collect(Collectors.toMap(Product::getMaterial, valueMap, merge, supplier));
        
        map.get("Plastic").forEach(System.out::println);
    }

    private static void merge() {
        //Clave -> Material | Valor->List<Product>

        Function<Product, List<Product>> valueMap = product -> {
            var lst = new ArrayList<Product>();
            lst.add(product);
            return lst;
        };
        BinaryOperator<List<Product>> merge = (lst1, lst2) -> {
            lst2.addAll(lst1);
            return lst2;
        };

        Map<String, List<Product>> map = lstProducts.stream()
                .collect(Collectors.toMap(Product::getMaterial, valueMap, merge));

        map.get("Plastic").forEach(System.out::println);
    }

    private static void simple() {
        //Clave -> Product | Valor -> Json
        ObjectWriter ow = new ObjectMapper().findAndRegisterModules().writer().withDefaultPrettyPrinter();

        Map<Product, String> map = lstProducts.stream()
                .limit(5)
                .collect(Collectors.toMap(Function.identity(), product -> {
                    try {
                        return ow.writeValueAsString(product);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }));
        System.out.println(map.get(lstProducts.get(0)));
    }
}
