package org.fcilito.codigo.streams;

import java.net.Inet4Address;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorUser implements Collector<User, Map<String, Integer>, HashMap<String, Integer>> {

    @Override
    public Supplier<Map<String, Integer>> supplier() {
        return HashMap::new;
    }

    @Override
    public BiConsumer<Map<String, Integer>, User> accumulator() {
        return (map, user) -> map.put(user.getName(), user.getAge());
    }
    @Override
    public BinaryOperator<Map<String, Integer>> combiner() {
        return (stringIntegerMap, stringIntegerMap2) -> Stream.of(stringIntegerMap, stringIntegerMap2)
                .flatMap(map -> map.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public Function<Map<String, Integer>, HashMap<String, Integer>> finisher() {
        return HashMap::new;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Set.of(Characteristics.CONCURRENT, Characteristics.UNORDERED);
    }
}
