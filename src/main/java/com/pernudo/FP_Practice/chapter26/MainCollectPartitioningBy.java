package com.pernudo.FP_Practice.chapter26;

import com.pernudo.FP_Practice.pojos.Product;
import com.pernudo.FP_Practice.pojos.ProductDto;
import com.pernudo.FP_Practice.utils.ProductJavaFaker;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class MainCollectPartitioningBy {

    public static List<Product> lstProducts;

    public static void main(String[] args) {
        lstProducts = ProductJavaFaker.getList(100);

        var isPlastic = true;

        Predicate<Product> partition = product -> product.getMaterial()
                .equalsIgnoreCase("Plastic");

        var map1 = lstProducts.stream()
                .collect(Collectors.partitioningBy(partition));

        map1.get(isPlastic)
                .stream()
                .map(ProductJavaFaker::productToJson)
                .forEach(System.out::println);
        System.out.println("**************************************************");
        map1.get(!isPlastic)
                .stream()
                .map(ProductJavaFaker::productToJson)
                .forEach(System.out::println);

        System.out.println("==================================================");
        System.out.println("==================================================");
        System.out.println("==================================================");

        Function<Product, ProductDto> mapper = ProductDto::new;
        Predicate<ProductDto> filter = productDto -> productDto.getPrice() > 50;
        Collector<ProductDto, ?, List<ProductDto>> filtering = Collectors.filtering(filter, Collectors.toUnmodifiableList());
        Collector<Product, ?, List<ProductDto>> mapping = Collectors.mapping(mapper, filtering);
        Collector<Product, ?, Map<Boolean, List<ProductDto>>> partitioningBy = Collectors.partitioningBy(partition, mapping);
        var map2 = lstProducts.stream()
                .collect(partitioningBy);

        map2.get(isPlastic)
                .stream()
                .map(ProductJavaFaker::productDtoToJson)
                .forEach(System.out::println);
        System.out.println("**************************************************");
        map2.get(!isPlastic)
                .stream()
                .map(ProductJavaFaker::productDtoToJson)
                .forEach(System.out::println);
    }
}
