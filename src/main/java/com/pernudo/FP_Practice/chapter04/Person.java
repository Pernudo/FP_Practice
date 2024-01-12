package com.pernudo.FP_Practice.chapter04;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Person {

    private final String name = "Marcos";
    private final Integer age;

}
