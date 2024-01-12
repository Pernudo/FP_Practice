package com.pernudo.FP_Practice.chapter16;

import com.pernudo.FP_Practice.pojos.Product;
import com.pernudo.FP_Practice.utils.ProductJavaFaker;

import java.util.Comparator;

public class MainSorted {

    public static void main(String[] args) {

        var lstProducts = ProductJavaFaker.getList(200);

        //Ordenamos por el método compareTo de Product extendido de Comparable
        lstProducts.stream()
                .sorted()
                .limit(10)
                .forEach(System.out::println);

        System.out.println();

        //Con skip nos saltamos toda la lista menos el último registro
        lstProducts.stream()
                .sorted()
                .skip(lstProducts.size() - 1)
                .forEach(System.out::println);

        System.out.println();

        //Ordenamos por nuestro propio Comparator.
        //(p1, p2) -> p1.getExpirationDate().compareTo(p2.getExpirationDate()) => Comparator.comparing(Product::getExpirationDate
        Comparator<? super Product> comparator = Comparator.comparing(Product::getExpirationDate);
        lstProducts.stream()
                .sorted(comparator)
                .limit(10)
                .forEach(System.out::println);

        System.out.println();

        //Utilizamos Comparator para ordenar mediante una Function.
        lstProducts.stream()
                .sorted(Comparator.comparing(Product::getName))
                .limit(10)
                .forEach(System.out::println);
    }

}
