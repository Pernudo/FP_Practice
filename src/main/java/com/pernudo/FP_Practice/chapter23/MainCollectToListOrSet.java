package com.pernudo.FP_Practice.chapter23;

import com.pernudo.FP_Practice.pojos.Product;
import com.pernudo.FP_Practice.utils.ProductJavaFaker;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class MainCollectToListOrSet {

    public static List<Product> lstProducts;

    public static void main(String[] args) {
        lstProducts = ProductJavaFaker.getList(1000);

        Collector<String, ?, List<String>> collector1 = Collectors.toList();
        Collector<String, ?, List<String>> collector2 = Collectors.toCollection(LinkedList::new);
        Collector<String, ?, List<String>> collector3 = Collectors.toUnmodifiableList();
        Collector<String, ?, Set<String>> collector4 = Collectors.toSet();
        Collector<String, ?, Set<String>> collector5 = Collectors.toCollection(TreeSet::new);
        Collector<String, ?, Set<String>> collector6 = Collectors.toUnmodifiableSet();

        printTypeOfCollection(collector1);
        printTypeOfCollection(collector2);
        printTypeOfCollection(collector3);
        printTypeOfCollection(collector4);
        printTypeOfCollection(collector5);
        printTypeOfCollection(collector6);
    }

    private static <T> void printTypeOfCollection(Collector<String, ?, T> collector) {
        var collection = lstProducts.stream()
                .filter(product -> product.getPrice() < 5)
                .map(Product::getMaterial)
                .collect(collector);

        System.out.println("TypeResult :: << " + collection.getClass().getSimpleName() + " >>");

    }
}
