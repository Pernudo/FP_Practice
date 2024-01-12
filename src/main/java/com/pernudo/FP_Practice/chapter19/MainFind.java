package com.pernudo.FP_Practice.chapter19;

import com.pernudo.FP_Practice.pojos.Product;
import com.pernudo.FP_Practice.utils.ProductJavaFaker;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class MainFind {

    public static final String PLASTIC = "Plastic";
    public static final String IRON = "Iron";
    private static final float MIN_PRICE_LIMIT = 10;
    private static final float MAX_PRICE_LIMIT = 70;

    public static void main(String[] args) {
        var lstProducts = ProductJavaFaker.getList(1000);

        //Si el precio está entre X eY, el material es Hierro o Plástico
        Predicate<Product> isValid = getProductPredicate();

        System.out.println("Tenemos " + lstProducts.stream()
                .filter(isValid)
                .count()
                + " Productos válidos para estos parámetros.");

        Supplier<Product> SequencialProductSupplier = () -> lstProducts.stream()
                .filter(isValid)
                .findFirst()
                .orElse(null);
        Supplier<Product> ParallelProductSupplier = () -> lstProducts.parallelStream()
                .filter(isValid)
                .findAny()
                .orElse(null);

        productSet(SequencialProductSupplier).forEach(System.out::println);
        System.out.println("==========================================");
        productSet(ParallelProductSupplier).forEach(System.out::println);
    }

    private static Predicate<Product> getProductPredicate() {
        Predicate<Product> priceOverX = product -> product.getPrice() > MIN_PRICE_LIMIT;
        Predicate<Product> priceLessY = product -> product.getPrice() < MAX_PRICE_LIMIT;
        Predicate<Product> isPlastic = product -> product.getMaterial().equalsIgnoreCase(PLASTIC);
        Predicate<Product> isIron = product -> product.getMaterial().equalsIgnoreCase(IRON);
        return priceOverX.and(priceLessY).and(isPlastic.or(isIron));
    }

    private static Set<Product> productSet(Supplier<Product> productSupplier) {
        var setProducts = new HashSet<Product>();
        for (var i = 0; i < 10000; i++) {
            setProducts.add(productSupplier.get());
        }
        return setProducts;
    }
}
