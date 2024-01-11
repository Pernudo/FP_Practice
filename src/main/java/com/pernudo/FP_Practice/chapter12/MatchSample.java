package com.pernudo.FP_Practice.chapter12;

import com.pernudo.FP_Practice.pojos.Character;
import com.pernudo.FP_Practice.utils.CharactersFromJson;

import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class MatchSample {

    private static final String regexLetter = "[a-zA-Z]{3,}";

    public static void main(String[] args) throws Exception {
        List<Character> lstCharacters = CharactersFromJson.getStarTrekCharacters();

        var pattern = Pattern.compile(regexLetter);
        Predicate<Character> characterNamePredicate = character -> pattern.matcher(character.getName()).find();
        Predicate<Character> characterAgePredicate = character -> character.getAge() > 20;

        var coincidences = lstCharacters.stream()
                .peek(character -> System.out.println("Nombre: " + character.getName() +
                        " // Edad: " + character.getAge()))
                .allMatch(character -> characterNamePredicate
                        .and(characterAgePredicate)
                        .test(character));
        var message = coincidences ? "Se ha encontrado" : "No se ha encontrado";
        System.out.println(message);
    }

}
