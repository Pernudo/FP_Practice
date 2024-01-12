package com.pernudo.FP_Practice.chapter00;

public class MainExample {

    /**
     * Programación funcional: Se trabaja con funciones, que se hace, no el cómo, como la imperativa.
     * Menos propensa a errores, ya que evita los denominados efectos secundarios.
     * Más abstracta.
     * Mayor trabajo para el compilador.
     */
    public MainExample() {
        printResultOfThis(this::multiplication);
        printResultOfThis(this::division);
    }

    public static void main(String[] args) {
        new MainExample();
    }

    private float multiplication() {
        return 3.4f * 5;
    }

    private float division() {
        return (float) 4 / 2;
    }

    private void printResultOfThis(Operation operation) {
        System.out.println("Valor de la operación: " + operation.operation());
    }
}