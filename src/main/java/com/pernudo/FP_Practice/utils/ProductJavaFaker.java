package com.pernudo.FP_Practice.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.github.javafaker.Commerce;
import com.github.javafaker.Faker;
import com.pernudo.FP_Practice.pojos.Product;
import com.pernudo.FP_Practice.pojos.ProductDto;

import java.time.ZoneId;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class ProductJavaFaker {

    private static final Commerce productFaker = new Faker(new Locale("es", "ES")).commerce();
    private static final ObjectWriter objectWriter = new ObjectMapper().findAndRegisterModules().writer().withDefaultPrettyPrinter();

    public static List<Product> getList(int limit) {
        return Stream.generate(ProductJavaFaker::createProduct)
                .limit(limit).toList();
    }

    public static Product createProduct() {
        var date = Faker.instance(new Random())
                .date()
                .future(1000, TimeUnit.DAYS)
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        var price = productFaker.price().replace(',', '.');
        return new Product(productFaker.productName(),
                productFaker.material(), productFaker.promotionCode(), Float.parseFloat(price), date);
    }

    public static String productToJson(Product product) {
        try {
            return objectWriter.writeValueAsString(product);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String productDtoToJson(ProductDto productDto) {
        try {
            return objectWriter.writeValueAsString(productDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
