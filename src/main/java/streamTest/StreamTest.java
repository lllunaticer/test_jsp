package streamTest;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

import streamTest.java8FunctionCoding.StringCollector;

/**
 * @author Xingjian LONG
 * Created on 2021-06-08
 */
public class StreamTest {
    @Test
    public void testStream() {
        List<Person> personList = Arrays.asList(
                new Person(Arrays.asList(1L, 2L), "lxj", 26),
                new Person(Arrays.asList(3L, 4L), "ltp", 24),
                new Person(Arrays.asList(5L, 6L), "lk", 24),
                new Person(Arrays.asList(7L, 8L), "cwc", 26));

        Set<Long> set = personList.stream().map(Person::getId)
                .map(Collection::stream)
                .reduce(Stream::concat)
                .map(longStream -> longStream.collect(Collectors.toSet()))
                .orElse(Collections.EMPTY_SET);

        System.out.println(set);

        Map collect = personList.stream().collect(groupingBy(Person::getAge, mapping(Person::getName, toList())));
        System.out.println(collect);

        String personName = personList.stream()
                .map(Person::getName)
                .reduce("[", (last, name) ->
                {
                    if (last.length() > 1) {
                        last = last + ", ";
                    }
                    last = last + name;
                    return last;
                });
        personName = personName + "]";
        System.out.println(personName);

        System.out.println("use string combiner ++++++++++++++++++++");
        String personNames = personList.stream()
                .map(Person::getName)
                .collect(new StringCollector("[", "]", ","));

        System.out.println(personNames);

        System.out.println("use string combiner ++++++++++++++++++++");
    }

    @Test
    public void test1() {
        String[] array = new String[] {"beef", "chicken", "rice", "carrot"};
        List<Stream<String>> result = Stream.of(array)
                .map(s -> s.split(""))
                .map(Arrays::stream) // 将每个数组变成一个单独的流
                .distinct()
                .collect(Collectors.toList());
        System.out.println(result);
    }

    @Test
    public void test2() {
        String[] array = new String[] {"beef", "chicken", "rice", "carrot"};
        List<String[]> result = Stream.of(array)
                .map(s -> s.split(""))
                .distinct()
                .collect(Collectors.toList());
        result.forEach(e -> System.out.println(Arrays.deepToString(e)));
    }

    @Test
    public void test3() {
        String[] array = new String[] {"beef", "chicken", "rice", "carrot"};
        List<String> result = Stream.of(array)
                .map(s -> s.split(""))
                .flatMap(Arrays::stream) // 将每个数组变成一个单独的流后再合并
                .distinct()
                .collect(Collectors.toList());
        System.out.println(result);
    }

    @Test
    public void reduceTest() {
        List<Dish> menu = Arrays.asList(
                Dish.builder().name("pork").calories(800).type(Dish.Type.MEAT).build(),
                Dish.builder().name("beef").calories(700).type(Dish.Type.MEAT).build(),
                Dish.builder().name("chicken").calories(400).type(Dish.Type.MEAT).build(),
                Dish.builder().name("chicken").calories(400).type(Dish.Type.MEAT).build(),
                Dish.builder().name("french fries").calories(530).type(Dish.Type.OTHER).build(),
                Dish.builder().name("rice").calories(350).type(Dish.Type.OTHER).build(),
                Dish.builder().name("carrot").calories(200).type(Dish.Type.VEGETABLE).build());
        //形式1
        Integer sum = menu.stream()
                .map(Dish::getCalories)
                .reduce(Integer::sum)
                .orElse(0);
        System.out.println(sum);

        //形式2
        Integer sum1 = menu.stream()
                .map(Dish::getCalories)
                .reduce(0, Integer::sum);
        System.out.println(sum1);

        //形式3
        String sum2 = menu.stream()
                .map(Dish::getCalories)
                .reduce("0", StreamTest::getSum, StreamTest::combiner);
        System.out.println(sum2);

        //形式3.1
        Integer sum3 = menu.stream()
                .map(Dish::getCalories)
                .reduce(0, Integer::sum, Integer::sum);

        System.out.println(sum3);


    }

    @Test
    public void findLongestName(){
        Stream<String> names = Stream.of("longxingjian", "litianpeng", "likang", "zhangyu25");
        System.out.println(names.max(Comparator.comparing(String::length)).get());
    }

    private static String combiner(String a, String b) {
        System.out.println("combiner is execute");
        return Integer.valueOf(a) + Integer.valueOf(b) + "";
    }

    private static String getSum(String a, Integer b) {
        return String.valueOf(Integer.parseInt(a) + b);
    }
}
