package com.pernudo.FP_Practice.chapter14;

import com.pernudo.FP_Practice.pojos.Product;
import com.pernudo.FP_Practice.utils.ProductJavaFaker;

import java.text.DecimalFormat;

public class MainNumberResult {

    public static void main(String[] args) {

        var lstProducts = ProductJavaFaker.getList(200);

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
