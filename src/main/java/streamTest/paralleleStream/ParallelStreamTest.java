package streamTest.paralleleStream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;

import streamTest.Person;
import streamTest.java8FunctionCoding.StringCollector;

/**
 * @author Xingjian LONG
 * Created on 2021-08-26
 */
public class ParallelStreamTest {
    @Test
    public void testParallelStream() {
        //        List<Person> personList = Arrays.asList(
        //                new Person(Arrays.asList(1L, 2L), "lxj", 26),
        //                new Person(Arrays.asList(3L, 4L), "ltp", 24),
        //                new Person(Arrays.asList(5L, 6L), "lk", 24),
        //                new Person(Arrays.asList(7L, 8L), "cwc", 26));
        List<Person> personList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            personList.add(new Person(Arrays.asList(1L, 2L), "lxj", 26));
        }

        long startParallelStream = System.currentTimeMillis();
        String peopleNameParallel =
                personList.parallelStream().map(Person::getName).collect(new StringCollector("[", "]", ", "));
        System.out.println(peopleNameParallel);
        long endParallelStream = System.currentTimeMillis();
        long parallelStreamTakeTime = endParallelStream - startParallelStream;

        //        long startStream = System.currentTimeMillis();
        //        String peopleName = personList.stream().map(Person::getName).collect(new StringCollector("[", "]",
        //                ", "));
        //        System.out.println(peopleName);
        //        long endStream = System.currentTimeMillis();
        //        long streamTakeTime = endStream - startStream;

        //        System.out.println("stream take time:" + streamTakeTime);
        //        System.out.println("parallelStream take time:" + parallelStreamTakeTime);
    }

    @Test
    public void testParallelSet() {
        double[] values = new double[10];
        Arrays.setAll(values, i -> i * 0.5);
        System.out.println(Arrays.toString(values));
    }

    //parallelPrefix计算数据的前缀和
    @Test
    public void calculatePrefixSum() {
        double[] values = new double[10];
        Arrays.setAll(values, i -> i);
        Arrays.parallelPrefix(values, Double::sum); //可以做其他形式的前缀操作，乘积也行
        System.out.println(Arrays.toString(values));

        String[] name = new String[10];
        Arrays.setAll(name, String::valueOf);
        Arrays.parallelPrefix(name, (a, b) -> a + b);
        System.out.println(Arrays.toString(name));
    }

    //计算简单滑动平均数
    @Test
    public void simpleMovingAverage() {
        double[] values = new double[10];
        Arrays.setAll(values, i -> i);
        double[] doubles = simpLeMovingAverage(values, 2);
        System.out.println(Arrays.toString(doubles));
    }

    public static double[] simpLeMovingAverage(double[] values, int n) {
        double[] sums = Arrays.copyOf(values, values.length);
        Arrays.parallelPrefix(sums, Double::sum);
        int start = n - 1;
        return IntStream.range(start, sums.length)
                .mapToDouble(i -> {
                    double prefix = i == start ? 0 : sums[i - n];
                    return (sums[i] - prefix) / n;
                }).toArray();
    }
}
