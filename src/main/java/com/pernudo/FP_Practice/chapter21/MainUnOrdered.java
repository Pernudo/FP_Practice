package com.pernudo.FP_Practice.chapter21;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@State(Scope.Benchmark)
public class MainUnOrdered {

    // Prueba de eficiencia para ver cómo puede mejorar el performance el método unordered() en los parallelStream, usamos la librería JMH Benchmark.
    // Extraer el package a un proyecto independiente para ejecutar **AÑADIR al POM las Dependencias
    /*
        <!-- jmh es un arnés de Java para crear, ejecutar y analizar puntos de referencia nano/micro/macro escritos en Java y otros lenguajes dirigidos a la JVM. -->
        <dependency>
            <groupId>org.openjdk.jmh</groupId>
            <artifactId>jmh-core</artifactId>
            <version>1.37</version>
        </dependency>
     */

    @Param("5000000")
    @SuppressWarnings({"UnusedDeclaration"})
    private long NUMBER;
    private List<Long> lst;

    public static void main(String[] args) throws RunnerException {
        var options = new OptionsBuilder().include(MainUnOrdered.class.getSimpleName())
                .forks(1)
                .warmupIterations(3)
                .timeUnit(TimeUnit.MILLISECONDS)
                .measurementIterations(4)
                .build();

        new Runner(options).run();
    }

    @Setup
    @SuppressWarnings("unused")
    public void loadData() {
        lst = new ArrayList<>();
        for (long i = 0; i < NUMBER; i++) {
            lst.add(i);
        }
        for (long i = 0; i < NUMBER; i++) {
            lst.add(i);
        }
    }

    @Benchmark
    @SuppressWarnings("unused")
    public Long ordered() {
        return lst.parallelStream()
                .distinct()
                .reduce(0L, Long::sum);
    }

    @Benchmark
    @SuppressWarnings("unused")
    public Long unOrdered() {
        return lst.parallelStream()
                .unordered()
                .distinct()
                .reduce(0L, Long::sum);
    }
}
