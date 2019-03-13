package streams;

import java.util.OptionalDouble;
import java.util.stream.DoubleStream;

public class StreamAnotherExample {
    public static void main(String[] args) {
        System.out.println("REGULAR");
        DoubleStream ds = DoubleStream.iterate(1.01, (d)->d+1.01).limit(5);
        OptionalDouble od = ds.average();
        System.out.println("average = " + od.orElse(-0.0));
        System.out.println();

        System.out.println("NOW PARALLEL");
        ds = DoubleStream.iterate(1.01, (d)->d+1.01).limit(5).parallel();
        od = ds.average();
        System.out.println("average = " + od.orElse(-0.0));
        System.out.println();

        System.out.println("NOW JUST SUM");
        ds = DoubleStream.iterate(1.01, (d)->d+1.01).limit(10).parallel();
        double sum = ds.sum();
        System.out.println("sum = " + sum);
        System.out.println();

        System.out.println("UNORDERED EXAMPLE");
        ds = DoubleStream.iterate(1.01, (d)->d+1.01).limit(5).unordered().parallel();
        ds.forEach(d-> System.out.println("_"+d));
    }
}
