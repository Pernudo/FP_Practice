package com.pernudo.FP_Practice.chapter1;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.TreeSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MainFIExamples {

    public static void main(String[] args){
        comparatorExample();
        /* noinspection => to suppress warning "Commented out code (n lines)
        runnableExample();
        callableExample();
        actionListenerExample();
        */

    }

    private static void comparatorExample() {
        var tree = new TreeSet<>(MainFIExamples::compare);
        tree.add(4);
        tree.add(8);
        tree.add(1);
        tree.add(5);
        tree.add(6);
        tree.add(2);
        tree.forEach(System.out::println);
    }

    private static int compare(Integer a, Integer b) {
        if (a < b){
            return 1;
        }
        if (a.equals(b)){
            return 0;
        }
        return -1;
    }

    @SuppressWarnings("unused")
    private static void runnableExample() {
        var thread = new Thread(MainFIExamples::runBehavior);
        thread.start();
    }

    private static void runBehavior() {
        var i = 0;
        while (i++ != 100){
            System.out.println("Llamada a run() Nº: " + i);
        }
    }

    @SuppressWarnings("unused")
    private static void callableExample() {
        try {
            ExecutorService executor = Executors.newSingleThreadExecutor();
            Future<String> future = executor.submit(MainFIExamples::operation);
            System.out.println(future.get());
            executor.shutdown();
        }
        catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private static String operation() {
        Character[] charArray = new Character[]{'S','o','y',' ','u','n','a',' ','c','a','d','e','n','a'};
        var strBuffer = new StringBuffer("Recomponiendo cadena: ");

        Arrays.stream(charArray).forEach(character -> {
            System.out.println(character);
            strBuffer.append(character);
            sleep();
        });

        System.out.println(Thread.currentThread().getName());
        return strBuffer.toString();
    }

    private static void sleep() {
        try {
            Thread.sleep(1000);
            System.out.println("Un segundo después...");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unused")
    private static void actionListenerExample() {
        var frame = new JFrame();
        frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

        var btn = new JButton("Exit");
        btn.setSize(frame.getSize());
        btn.addActionListener(event -> {
            System.out.println("Saliendo...");
            System.exit(0);
        });

        frame.getContentPane().add(btn);
        frame.setVisible(true);
    }

}
