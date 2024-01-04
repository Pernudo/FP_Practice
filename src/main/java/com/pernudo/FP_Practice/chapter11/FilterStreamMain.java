package com.pernudo.FP_Practice.chapter11;

import com.pernudo.FP_Practice.chapter10.Character;
import com.pernudo.FP_Practice.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class FilterStreamMain {
    public static void main(String[] args) throws Exception {
        List<Character> lstCharacters = Utils.getCharacters();
        lstCharacters.forEach(System.out::println);

        //Eliminar duplicados
        lstCharacters.addAll(Utils.getCharacters());
        System.out.println("Tamaño lista CON duplicados: " + lstCharacters.size());

        var lstNew = new ArrayList<Character>();
        lstCharacters.stream().distinct().forEach(lstNew::add);
        System.out.println("Tamaño lista SIN duplicados: " + lstNew.size());

        //Obtener personajes mayores de 50 años
        lstCharacters = Utils.getCharacters();
        var count = lstCharacters.stream()
                .filter(character -> character.age > 50)
                .peek(System.out::println)
                .count();
        System.out.println("Personajes con más de 50 años: " + count);

    }

}
