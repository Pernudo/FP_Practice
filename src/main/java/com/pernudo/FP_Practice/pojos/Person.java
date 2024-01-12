package com.pernudo.FP_Practice.pojos;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Person {

    private final String name = "Marcos";
    private final Integer age;

}
