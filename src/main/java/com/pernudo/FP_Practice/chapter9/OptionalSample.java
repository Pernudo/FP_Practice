package com.pernudo.FP_Practice.chapter9;

import java.util.Optional;

public class OptionalSample {

    public static void main (String[] args) throws Exception {

        /*
         * Ayuda a eliminar NullPointerException
         * Puede utilizarse de diferentes formas para el manejo condicional complejo
         * Podemos rechazar ciertos valores del Optional --> filter()
         * Podemos transformar un valor si existe --> map() --> flatMap()
         *  Optional<T>
         */

        //Optional<String> stringOptional = Optional.ofNullable(null);
        Optional<String> stringOptional = Optional.of("Hola");
        stringOptional.ifPresent(System.out::println);
        Runnable runnable = () -> System.out.println("El valor es nulo");
        stringOptional.ifPresentOrElse(System.out::println, runnable);

        //Optional<String> stringOptionalName = Optional.ofNullable(null);
        Optional<String> stringOptionalName = Optional.of("Marcos");
        var name = stringOptionalName.orElse("Desconocido");
        System.out.println("Nombre del sujeto: " + name);

        System.out.println(resultThrow("Hola1", "Hola2"));
        //System.out.println(resultThrow("Hola1", null));

        System.out.println(resultBlank("       ", "Default"));
        System.out.println(resultBlank("   Hola   ", "Default"));

        System.out.println(resultInteger(5, 2));
        System.out.println(resultInteger(-5, 2));
    }

    public static String resultThrow(String str1, String str2) throws Exception {
        var str2ValueOrElseThrow = Optional.ofNullable(str2)
                                            .orElseThrow(Exception::new);
        return Optional.ofNullable(str1).orElse(str2ValueOrElseThrow);
    }

    public static String resultBlank(String str1, String str2) {
        return Optional.ofNullable(str1).filter(String::isBlank).orElse(str2);
    }

    public static Integer resultInteger(Integer int1, Integer int2) {
        return Optional.of(Integer.sum(int1, int2))
                .filter(sum -> sum > 0).orElse(-1);
    }

}
