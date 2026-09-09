package predicate;

import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        // Predicate<Integer> isAgeGreaterThan18 = a -> a > 18;
        // List<Integer> numbers = List.of(2, 3, 65, 63, 44);
        // List<Integer> res = numbers.stream().filter(isAgeGreaterThan18).collect(Collectors.toList());
        // System.out.println(res);

        //Function

        Function<Integer, Integer> func1 = x -> 2*x;
        Function<Integer, Integer> func2 = x -> 2*x;
        Function<Integer, Integer> func3 = func1.andThen(func2);
        System.out.println(func3.apply(3));


        Supplier<Integer> supplyOdd = () -> 3;
        Consumer<Integer> consumeOdd = x -> System.out.println(x);
        consumeOdd.accept(supplyOdd.get());

        BiFunction<Integer, Integer, String> sumAndReturn = (x, y) -> Integer.toString(x+y);

        // UnaryOperator -> special type of function -> when input & output datatype is same.

        List<Integer> numbers = List.of(1, 2, 4, 5);
        numbers.stream().map(x -> x + 2).collect(Collectors.toList());
        Stream<Integer> stream1 = numbers.stream();
        Stream<Integer> stream2 = Stream.of(1, 2, 3);
        Stream<Integer> limit = Stream.iterate(0, n -> n+1);
        List<Integer> res = numbers.stream().map(x -> x * 2).distinct().sorted().collect(Collectors.toList());

        System.out.println(getName(3)); // null pointer exception
        
        Optional<String> nameOptional = getName(3);
        nameOptional.ifPresent(System.out::println);
    }

    private static Optional<String> getName(int id) {
        String name = "Ram";
        name = null;
        // name.hashCode();
        return Optional.ofNullable(name); // if it can be null

    }
}
