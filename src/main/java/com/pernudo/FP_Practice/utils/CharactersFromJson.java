package com.pernudo.FP_Practice.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pernudo.FP_Practice.pojos.Character;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class CharactersFromJson {

    private static final String jsonStarTrekCharacters = "StarTrekCharacters.json";
    private static final String jsonStarWarsCharacters = "StarWarsCharacters.json";

    public static List<Character> getStarTrekCharacters() throws IOException {
        return getCharacters(jsonStarTrekCharacters);
    }

    public static List<Character> getStarWarsCharacters() throws IOException {
        return getCharacters(jsonStarWarsCharacters);
    }

    private static List<Character> getCharacters(String fileName) throws IOException {
        File file = new ClassPathResource(fileName).getFile();
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(file.getAbsolutePath()), new TypeReference<>() {
        });
    }

}
