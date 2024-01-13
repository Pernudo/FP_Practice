package com.pernudo.FP_Practice.chapter27;

import com.pernudo.FP_Practice.pojos.Product;
import com.pernudo.FP_Practice.pojos.ProductDto;
import com.pernudo.FP_Practice.utils.ProductJavaFaker;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainCollectFlatMapping {

    public static List<Product> lstProducts;

    public static void main(String[] args) {
        lstProducts = ProductJavaFaker.getList(100);

        Predicate<Product> partition = product -> product.getMaterial().equalsIgnoreCase("Plastic");
        Function<Product, ProductDto> mapper = ProductDto::new;
        Predicate<ProductDto> filter = productDto -> productDto.getPrice() > 50;
        Collector<ProductDto, ?, List<ProductDto>> filtering = Collectors.filtering(filter, Collectors.toUnmodifiableList());
        Collector<Product, ?, List<ProductDto>> mapping = Collectors.mapping(mapper, filtering);
        Collector<Product, ?, Map<Boolean, List<ProductDto>>> partitioningBy = Collectors.partitioningBy(partition, mapping);
        var map = lstProducts.stream()
                .collect(partitioningBy);

        Function<List<ProductDto>, Stream<String>> flatMapper = productDtoList -> productDtoList.stream()
                .map(ProductJavaFaker::productDtoToJson);
        var result = Stream.of(map.get(true), map.get(false))
                .peek(System.out::println)
                .collect(Collectors.flatMapping(flatMapper, Collectors.toList()));

        System.out.println();

        result.forEach(System.out::println);
    }
}
