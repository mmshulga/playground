package streams;

import java.util.TreeMap;
import java.util.stream.Stream;

public class StreamCollectorUsageExample {
    public static void main(String[] args) {
        Stream<Integer> si = Stream.of(1, 2, 3, 4, 5, 6, 6, 6, 6);
        TreeMap<Integer, Integer> treeMap = si.collect(TreeMap::new,
                (m, i)->m.put(i, m.getOrDefault(i, 0)+1),
                TreeMap::putAll);
        System.out.println("treeMap = " + treeMap);
    }
}
