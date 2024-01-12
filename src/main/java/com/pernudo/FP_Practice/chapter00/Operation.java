package com.pernudo.FP_Practice.chapter00;

@FunctionalInterface
public interface Operation {

    static void interfaceStaticMethod() {
        System.out.println("Llamando al método estático de una interfaz");
    }

    float operation();

    private void interfacePrivateMethod() {
        System.out.println("Llamando al método privado de una interfaz");
        interfaceStaticMethod();
    }

    @SuppressWarnings("unused")
    default void interfaceDefaultMethod() {
        interfacePrivateMethod();
        interfaceStaticMethod();
        System.out.println("Llamando al método default de una interfaz");
    }

}
