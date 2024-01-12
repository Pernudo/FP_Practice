package com.pernudo.FP_Practice.chapter03;

import java.time.LocalDate;
import java.util.Random;
import java.util.function.Supplier;

public class SupplierExample {

    public static void main(String[] args) {
        Supplier<LocalDate> localDateSupplier = LocalDate::now;
        var random = new Random();
        Supplier<Integer> integerSupplier = random::nextInt;
        Supplier<HotDog> hotDogSupplier = HotDog::new;

        usingSupplier(localDateSupplier);
        usingSupplier(integerSupplier);
        usingSupplier(integerSupplier);
        usingSupplier(integerSupplier);
        usingSupplier(integerSupplier);
        usingSupplier(hotDogSupplier);
    }

    static <T> void usingSupplier(Supplier<T> supplier) {
        System.out.println("Hacemos cierto código");
        System.out.println("Se obtiene: " + supplier.get());
    }

}
