package com.pernudo.FP_Practice.chapter13;

import com.pernudo.FP_Practice.chapter10.Character;
import com.pernudo.FP_Practice.utils.Utils;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.stream.Stream;

public class MapSample {

    public static void main(String[] args) throws Exception {

        //Lista de listas de Characters, la aplanamos e imprimimos.
        var lstComplete = new ArrayList<List<Character>>();
        lstComplete.add(Utils.getStarTrekCharacters());
        lstComplete.add(Utils.getStarWarsCharacters());
        lstComplete.stream()
                .flatMap(List::stream)
                .forEach(System.out::println);

        //Vamos a obtener un Stream de archivos class en el directorio raíz con una profundidad de 10 carpetas.
        var pathR = Paths.get(".");
        BiPredicate<Path, BasicFileAttributes> matcher = (path, att) -> String.valueOf(path).endsWith(".class");
        try (Stream<Path> pathStream = Files.find(pathR, 10, matcher, FileVisitOption.FOLLOW_LINKS)) {
            pathStream
                    .map(Path::toFile)
                    .peek(file -> System.out.println(file.getName()))
                    .mapToDouble(file -> (double) file.length() / 1024)
                    .forEach(length -> System.out.println("Tamaño del fichero " + length + " KB."));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
