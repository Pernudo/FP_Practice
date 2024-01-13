package com.pernudo.FP_Practice.chapter28;

import com.pernudo.FP_Practice.pojos.Product;
import com.pernudo.FP_Practice.utils.ProductJavaFaker;

import java.util.ArrayList;
import java.util.List;

public class MainPersonalCollector {

    public static List<Product> lstProducts;

    public static void main(String[] args) {
        lstProducts = ProductJavaFaker.getList(100);

        lstProducts.parallelStream()
                .collect(new JsonProductDtoCollector(ArrayList::new))
                .forEach(System.out::println);
    }
}
