package com.pernudo.FP_Practice.chapter17;

import com.pernudo.FP_Practice.pojos.Product;
import com.pernudo.FP_Practice.utils.ProductJavaFaker;

import java.util.List;
import java.util.Scanner;

public class MainParallel {

    private static final int ROUNDS = 30;
    private static final String YES = "Y";
    private static final Scanner scanner = new Scanner(System.in);
    private static List<Product> lstProducts;
    private static long diffPar;
    private static long diffSec;

    public static void main(String[] args) throws Exception {
        System.out.println("Cargar los datos? [Y/?]");

        if (scanner.nextLine().equalsIgnoreCase(YES)) {
            lstProducts = ProductJavaFaker.getList(100000);
            System.out.println("Datos cargados. Tamaño actual le da lista: " + lstProducts.size());

            System.out.println("Mostrar lo que tarda en realizar las operaciones? [Y/?]");
            while (scanner.nextLine().equalsIgnoreCase(YES)) {
                runSec();
                runPar();
                System.out.println("Diferencia entre Seq-Par: " + (diffSec - diffPar));
                System.out.println("Mostrar lo que tarda en realizar las operaciones? [Y/?]");
            }
        } else {
            System.out.println("Cerrando");
        }
        scanner.close();
        System.out.println("Gracias");
    }

    @SuppressWarnings("unused")
    private static void runSec() {
        diffSec = 0;
        for (int i = 0; i < ROUNDS; i++) {
            var on = System.nanoTime();

            var taxes = lstProducts.stream()
                    .filter(prod -> prod.getPrice() > 50)
                    .map(prod -> prod.getPrice() * 0.21 + prod.getPrice())
                    .count();

            var diff = System.nanoTime() - on;
            diffSec += diff;
        }
    }

    @SuppressWarnings("unused")
    private static void runPar() {
        diffPar = 0;
        for (int i = 0; i < ROUNDS; i++) {
            var on = System.nanoTime();

            var taxes = lstProducts.parallelStream()
                    .filter(prod -> prod.getPrice() > 50)
                    .map(prod -> prod.getPrice() * 0.21 + prod.getPrice())
                    .count();

            var diff = System.nanoTime() - on;
            diffPar += diff;
        }
    }

}
