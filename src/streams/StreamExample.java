package streams;

import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class StreamExample {
    public static void main(String[] args) {
        Stream<Integer> si = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        si.filter((i)->i%2==0).map(i->i*10).unordered().parallel().forEach(System.out::println);
        System.out.println();
        System.out.println();
        System.out.println();

        si = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        si.mapToDouble((i)->Double.valueOf(""+i)).forEach(System.out::println);
        System.out.println();
        System.out.println();
        System.out.println();

        si = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        DoubleStream ds = si.mapToDouble(i->(double)i);
        System.out.println(ds.sum());
        System.out.println();
        System.out.println();
        System.out.println();

        si = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Optional<Integer> reduced = si.reduce((i1, i2) -> i1+i2);
        System.out.println(reduced.get());
    }
}
