package com.day3.util;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

public class GenericFilter<T> {

    // 1. FILTERING
    public List<T> filter(
            Collection<T> collection,
            Predicate<T> condition) {

        return collection.stream()
                .filter(condition)
                .collect(Collectors.toList());
    }

    // 2. SORTING
    public List<T> sort(
            Collection<T> collection,
            Comparator<T> comparator) {

        return collection.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    // 3. GROUPING
    public <K> Map<K, List<T>> groupBy(
            Collection<T> collection,
            Function<T, K> classifier) {

        return collection.stream()
                .collect(Collectors.groupingBy(
                        classifier,
                        LinkedHashMap::new,
                        Collectors.toList()
                ));
    }

    // 4. DUPLICATE DETECTION
    public <K> Map<K, List<T>> findDuplicates(
            Collection<T> collection,
            Function<T, K> keyExtractor) {

        Map<K, List<T>> grouped = collection.stream()
                .collect(Collectors.groupingBy(keyExtractor));

        return grouped.entrySet().stream()
                .filter(entry -> entry.getValue().size() > 1)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                ));
    }

    // 5. AGGREGATION - SUM
    public long sum(
            Collection<T> collection,
            ToLongFunction<T> mapper) {

        return collection.stream()
                .mapToLong(mapper)
                .sum();
    }

    // 6. AGGREGATION - AVERAGE
    public double average(
            Collection<T> collection,
            ToDoubleFunction<T> mapper) {

        return collection.stream()
                .mapToDouble(mapper)
                .average()
                .orElse(0.0);
    }

    // 7. COUNT
   
    
    public <T,K> Map<K, Long>getTotalSalary(Collection<T> collection,Function<T, K> keyExtractor,ToLongFunction<T>mapper){
    	return collection.stream().collect(Collectors.groupingBy(keyExtractor,Collectors.summingLong(mapper)));
    }
    
    public <T,K> Map<K,Double>getAverageSalary(Collection<T> collection,Function<T,K> keyExtractor,ToDoubleFunction<T>mapper){
    	return collection.stream().collect(Collectors.groupingBy(keyExtractor,Collectors.averagingDouble(mapper)));
    }
    public <T,K>Map<K,Optional<T>>getMaxSalary(Collection<T> collection,Function<T,K>keyExtractor,Comparator<T>comparator){
    	return collection.stream().collect(Collectors.groupingBy(keyExtractor,Collectors.maxBy(comparator)));
    }
    
    public <T,K>Map<K,Optional<T>>getMinSalary(Collection<T> collection,Function<T,K>keyExtractor,Comparator<T>comparator){
    	return collection.stream().collect(Collectors.groupingBy(keyExtractor,Collectors.minBy(comparator)));
    }
    
    public <T,K>Map<K,Long>getGroupCount(Collection<T> collection, Function<T,K>keyExtractor){
    	return collection.stream().collect(Collectors.groupingBy(keyExtractor,Collectors.counting()));
    }
}

