package com.pernudo.FP_Practice.chapter14;

import com.pernudo.FP_Practice.pojos.Product;
import com.pernudo.FP_Practice.utils.ProductJavaFaker;

import java.text.DecimalFormat;
import java.util.stream.Stream;

public class MainNumberResult {

    public static void main(String[] args) throws Exception {

        var lstProducts = Stream.generate(ProductJavaFaker::createProduct)
                .limit(200).toList();

        var decimalformat = new DecimalFormat("#.##");

        var averagePrice = lstProducts.stream()
                .mapToDouble(Product::getPrice)
                .average()
                .orElseThrow(IllegalStateException::new);
        var sumPrice = lstProducts.stream()
                .mapToDouble(Product::getPrice)
                .sum();

        System.out.println("La media de precios de los productos es " + decimalformat.format(averagePrice));
        System.out.println("La suma de precios de los productos es " + decimalformat.format(sumPrice));
    }

}
