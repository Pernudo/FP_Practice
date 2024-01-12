package com.pernudo.FP_Practice.chapter05;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class MainBiFunction {

    public static void main(String[] args) {
        System.out.println(usingBiFunction(
                String::contains,
                "El veloz murciélago hindú comía feliz cardillo y kiwi. La cigüeña toca el saxofón detrás del palenque de paja.",
                "kiwi"
        ));

        System.out.println((String) usingBiFunction(
                List::get,
                List.of("Albaricoque", "Cereza", "Ciruela", "Higo", "Kaki", "Manzana", "Melocotón", "Nectarina", "Níspero", "Pera", "Uva"),
                5
        ));

        System.out.println(usingBiFunctionAndThen(
                String::equals,
                MainBiFunction::sendResponse,
                "Texto1",
                "Texto2"
        ));
    }

    private static <T, U, R> R usingBiFunction(BiFunction<T, U, R> biFunction, T value1, U value2) {
        return biFunction.apply(value1, value2);
    }

    @SuppressWarnings("SameParameterValue")
    private static <T, U, R, P> P usingBiFunctionAndThen(BiFunction<T, U, R> biFunction,
                                                         Function<R, P> function,
                                                         T value1, U value2) {
        return biFunction.andThen(function).apply(value1, value2);
    }

    private static String sendResponse(boolean e) {
        return e ? "Los textos son iguales" : "Los textos son diferentes";
    }

}
