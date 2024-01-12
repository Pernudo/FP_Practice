package com.pernudo.FP_Practice.chapter20;

import com.pernudo.FP_Practice.pojos.Product;
import com.pernudo.FP_Practice.utils.ProductJavaFaker;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.LongStream;

public class MainWhenUseParallelStream {

    private static final String RANGE = "range";
    private static final String ITERATE = "iterate";
    private static final String YES = "Y";
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Product> arrayLst = new ArrayList<>();
    private static final List<Product> linkedLst = new LinkedList<>();
    private static final int LIMIT = 100000;

    public static void main(String[] args) {
        System.out.println("cargar los datos [Y/?]");

        if (ifUWant()) {
            loadAll();
            question();
            while (ifUWant()) {

                System.out.println(diffOperation(arrayLst));
                System.out.println(diffOperation(linkedLst));

                LongStream iterate = LongStream.iterate(0, i -> i <= 100000000, i -> i + 1);
                LongStream range = LongStream.rangeClosed(0, 100000000);

                System.out.println(numberOperation(iterate, ITERATE));
                System.out.println(numberOperation(range, RANGE));

                question();
            }
            scanner.close();
            System.out.println("Cerrando");
        } else {
            System.out.println("Cerrando");
        }
        System.out.println("Gracias");
    }

    private static String numberOperation(LongStream stream, String name) {
        var diff = 0L;
        var on = System.nanoTime();

        var sum = stream.parallel()
                .reduce(0, Long::sum);

        diff = System.nanoTime() - on;

        return name + " ha tardado: " + (double) diff / 1000000 + " ms :: Suma-> " + sum;

    }

    private static String diffOperation(List<Product> lst) {
        var diff = 0L;
        var on = System.nanoTime();

        var sumPrice = lst.parallelStream()
                .map(Product::getPrice)
                .reduce(0f, Float::sum);

        diff = System.nanoTime() - on;

        return lst.getClass()
                .getSimpleName() + " ha tardado: " + (double) diff / 1000000 + " ms :: Suma de precios-> " + sumPrice;
    }

    private static boolean ifUWant() {
        return scanner.nextLine()
                .equalsIgnoreCase(YES);
    }

    private static void loadAll() {
        List<Product> data = ProductJavaFaker.getList(LIMIT);
        System.out.println("Cargados los datos");
        for (var i = 0; i < 1250; i++) {
            arrayLst.addAll(data);
        }
        linkedLst.addAll(arrayLst);
        System.out.println("Tamaño actual de arraylist->" + arrayLst.size() + "\nTamaño actual de linkedList->" + linkedLst.size());
    }

    private static void question() {
        System.out.println("Mostrar lo que tarda en hacerse las operaciones  [Y/?]");
    }
}
