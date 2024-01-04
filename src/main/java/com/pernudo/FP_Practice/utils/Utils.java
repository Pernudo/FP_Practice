package com.pernudo.FP_Practice.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pernudo.FP_Practice.chapter10.Character;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Utils {

    private static final String jsonStarTrekCharacters = "StarTrekCharacters.json";

    public static List<Character> getCharacters() throws IOException {
        File file = new ClassPathResource(jsonStarTrekCharacters).getFile();
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(file.getAbsolutePath()),new TypeReference<>() {});
    }

}
