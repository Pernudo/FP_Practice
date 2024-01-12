package com.pernudo.FP_Practice.chapter08;

import java.util.Objects;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class MainReference {

    public static void main(String[] args) {

        /*
         * Formato --> referencia :: nombre del método
         * Tipos -->{
         *              nombreDeLaClase::métodoStatic
         *              nombreDeLaClase::método
         *              nombreDeLaClase::new
         *              objetoExistente::método
         *          }
         */
        var str = "Marcos";
        var instance = new MainReference();
        Supplier<String> instanceMethod1 = str::toUpperCase;
        Supplier<String> instanceMethod2 = instance::getAnything;
        Supplier<String> instanceMethod3 = instance::getReferencedMethod;
        Supplier<MainReference> constructorReference = MainReference::new;
        Predicate<String> staticMethod = Objects::isNull;

        System.out.println(instanceMethod1.get());
        System.out.println(instanceMethod2.get());
        System.out.println(instanceMethod3.get());
        System.out.println(constructorReference.get().toString());
        System.out.println(staticMethod.test(null));
    }

    public String getAnything() {
        return "Cualquier cosa";
    }

    public String getReferencedMethod() {
        Supplier<String> supplier = this::getAnything;
        return supplier.get().concat(" --> Llamado con this");
    }

    @Override
    public String toString() {
        return "Soy una instancia de MainReference";
    }
}
