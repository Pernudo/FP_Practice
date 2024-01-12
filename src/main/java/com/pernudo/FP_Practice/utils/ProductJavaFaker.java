package com.pernudo.FP_Practice.utils;

import com.github.javafaker.Commerce;
import com.github.javafaker.Faker;
import com.pernudo.FP_Practice.pojos.Product;

import java.time.ZoneId;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class ProductJavaFaker {

    private static final Commerce productFaker = new Faker(new Locale("es", "ES")).commerce();

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

}
