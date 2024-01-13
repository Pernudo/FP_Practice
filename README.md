# Functional::Programming

[Playlist del Curso](https://www.youtube.com/playlist?list=PLjJ8HhsSfskiDEwgfyF9EznmrSyEukcJa)

[Reposotorio del Curso](https://github.com/Javi3Code/YTCourse_Java-Functional-Programming)

## Interfaces funcionales principales presentadas en JDK

### Supplier

No necesitamos proporcionar nada y recibimos una respuesta.

```
Stream.generate(() -> new Random()
                .nextInt())
                .limit(5)
                .forEach(System.out::println);
```

### Consumer, BiConsumer

Recibe uno o dos argumentos, hace algo con ellos y luego no devuelve nada.

```
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
numbers.forEach(System.out::println);
```

### Predicate, BiPredicate

Recibe uno o dos argumentos y devuelve un booleano.

```
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
numbers.stream()
    .filter(n -> n % 2 == 0)
    .forEach(System.out::println);
```

```
BiPredicate<String, Integer> wordSizeIsValid = (word, size) -> {
    return word.length() == size;
};
boolean wordIsValid = wordSizeIsValid.test("Marcos", 10);
System.out.println(wordIsValid);
```

### Function, BiFunction

Recibe uno o dos argumentos y devuelve otro argumento.

```
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
numbers.stream()
.map(n -> n.doubleValue())
.forEach(System.out::println);
```

```
BiFunction<Integer, Integer, Integer> sumNumbers = (n1, n2) -> n1 + n2;
Integer sumResult = sumNumbers.apply(2, 3);
System.out.println(sumResult);
```

### UnaryOperator, BinaryOperator

Tiene el mismo comportamiento que Function, pero solo funciona con los mismos tipos.

#### Ejemplo de cómo funciona todo en conjunto

```
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
numbers.stream()
        .filter(n -> n % 2 == 0) //Predicate
        .map(n -> n.doubleValue()) //Function
        .reduce((n1, n2) -> n1 + n2) //BinaryOperator
        .ifPresent(System.out::println); //Consumer
```

[Web de Referencia](https://www.sensedia.com.es/post/interfaces-funcionales-con-java-8)