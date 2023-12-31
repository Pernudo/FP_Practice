package com.pernudo.FP_Practice.chapter4;

import java.time.LocalDate;
import java.util.function.Function;

public class MainFunction {

    public static void main (String[] args){

        var chain = new StringBuilder("Usando la interface");
        Function<StringBuilder, String> toString = input -> input.append(" ").append("Function").toString();
        System.out.println(usingFunction(toString, chain));

        var birth = LocalDate.of(1984, 8, 20);
        Function <LocalDate, Integer> returnAgeCompose = date -> date.until(LocalDate.now()).getYears();
        Function <Integer, Person> returnPersonCompose = Person::new;
        Function <Person, String> returnStringCompose = Person::toString;
        System.out.println(usingFunctionWithCompose(returnStringCompose, returnPersonCompose, returnAgeCompose, birth));

        Person person = new Person(64);
        Function<Person, Integer> returnAgeThen = Person::getAge;
        Function<Integer, LocalDate> returnBirthThen = age -> {
            var now = LocalDate.now();
            return LocalDate.of(now.getYear() - age, now.getMonth(), now.getDayOfMonth());
        };
        Function<LocalDate, String> returnStringThen = LocalDate::toString;
        System.out.println(usingFunctionWithAndThen(returnAgeThen, returnBirthThen, returnStringThen, person));
    }

    private static <T, R> R usingFunction(Function<T, R> function, T value) {
        return function.apply(value);
    }

    private static <T, R, V, P> R usingFunctionWithCompose(Function<T, R> function1,
                                                           Function<V, T> function2,
                                                           Function<P, V> function3, P value){
        return function1.compose(function2).compose(function3).apply(value);
    }

    private static <T, R, V, P> P usingFunctionWithAndThen(Function<T, R> function1,
                                                           Function<R, V> function2,
                                                           Function<V, P> function3, T value){
        return function1.andThen(function2).andThen(function3).apply(value);
    }
}
