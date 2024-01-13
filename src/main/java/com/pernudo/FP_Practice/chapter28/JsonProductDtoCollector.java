package com.pernudo.FP_Practice.chapter28;

import com.pernudo.FP_Practice.pojos.Product;
import com.pernudo.FP_Practice.pojos.ProductDto;
import com.pernudo.FP_Practice.utils.ProductJavaFaker;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class JsonProductDtoCollector implements Collector<Product, List<ProductDto>, Collection<String>> {

    private final Collector<String, ?, Collection<String>> collector;

    public JsonProductDtoCollector(Supplier<Collection<String>> collectionSupplier) {

        collector = Objects.requireNonNull(Collectors.toCollection(collectionSupplier), "Debes pasar un supplier válido");

    }

    @Override
    public Supplier<List<ProductDto>> supplier() {
        return ArrayList::new;
    }

    @Override
    public BiConsumer<List<ProductDto>, Product> accumulator() {
        return (accumulator, product) -> accumulator.add(new ProductDto(product));
    }

    @Override
    public BinaryOperator<List<ProductDto>> combiner() {
        return (accumulator1, accumulator2) -> {
            System.out.println("Entra en el combiner el hilo " + Thread.currentThread().getName());
            accumulator1.addAll(accumulator2);
            return accumulator1;
        };
    }

    @Override
    public Function<List<ProductDto>, Collection<String>> finisher() {
        return accumulator -> accumulator.stream()
                .map(ProductJavaFaker::productDtoToJson)
                .collect(collector);
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Set.of(Characteristics.CONCURRENT);
    }
}
