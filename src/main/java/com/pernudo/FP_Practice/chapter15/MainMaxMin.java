package com.pernudo.FP_Practice.chapter15;

import com.pernudo.FP_Practice.pojos.Product;
import com.pernudo.FP_Practice.utils.ProductJavaFaker;

import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.function.Predicate;

public class MainMaxMin {

    public static void main(String[] args) throws Exception {

        var lstProducts = ProductJavaFaker.getList(200);

        Comparator<? super Product> comparator = (p1, p2) -> p1.getName().compareToIgnoreCase(p2.getName());
        var max = lstProducts.stream()
                .max(comparator)
                .orElseThrow(() -> new Exception("No se ha encontrado"));
        var min = lstProducts.stream()
                .min(comparator)
                .orElseThrow(() -> new Exception("No se ha encontrado"));

        System.out.println("Producto Nombre Máximo: " + max);
        System.out.println("Producto Nombre Mínimo: " + min);

        comparator = (p1, p2) -> Float.compare(p1.getPrice(), p2.getPrice());
        max = lstProducts.stream()
                .max(comparator)
                .orElseThrow(() -> new Exception("No se ha encontrado"));
        min = lstProducts.stream()
                .min(comparator)
                .orElseThrow(() -> new Exception("No se ha encontrado"));

        System.out.println("Producto Precio Máximo: " + max);
        System.out.println("Producto Precio Mínimo: " + min);

        Predicate<? super Product> isPlastic = p -> p.getMaterial().equals("Plastic");
        max = lstProducts.stream()
                .filter(isPlastic)
                .max(comparator)
                .orElseThrow(() -> new Exception("No se ha encontrado"));
        min = lstProducts.stream()
                .filter(isPlastic)
                .min(comparator)
                .orElseThrow(() -> new Exception("No se ha encontrado"));

        System.out.println("Producto Precio Máximo Plástico: " + max);
        System.out.println("Producto Precio Mínimo Plástico: " + min);

        var maxPrimitive = lstProducts.stream()
                .filter(isPlastic)
                .mapToDouble(Product::getPrice)
                .max()
                .orElse(Double.MIN_VALUE);
        var minPrimitive = lstProducts.stream()
                .filter(isPlastic)
                .mapToDouble(Product::getPrice)
                .min()
                .orElse(Double.MIN_VALUE);

        var decimalformat = new DecimalFormat("#.##");

        System.out.println("Producto Precio Máximo Plástico Primitivo: " + decimalformat.format(maxPrimitive));
        System.out.println("Producto Precio Mínimo Plástico Primitivo: " + decimalformat.format(minPrimitive));
    }

}
