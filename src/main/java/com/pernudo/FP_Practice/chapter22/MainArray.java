package com.pernudo.FP_Practice.chapter22;

import com.pernudo.FP_Practice.pojos.Product;
import com.pernudo.FP_Practice.utils.ProductJavaFaker;

import java.util.Arrays;
import java.util.function.IntFunction;

public class MainArray {

    public static void main(String[] args) {
        var lstProducts = ProductJavaFaker.getList(200);

//        IntFunction<String[]> generator = String[]::new;
        IntFunction<String[]> generator = size -> new String[size * 2];
        String[] arr = lstProducts.stream()
                .map(Product::getMaterial)
                .distinct()
                .toArray(generator);

        System.out.println(Arrays.toString(arr));
    }
}
