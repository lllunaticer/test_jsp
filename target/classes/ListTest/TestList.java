package ListTest;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;

import org.junit.Test;

import streamTest.Person;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2021-12-09
 */
public class TestList {
    public static void main(String[] args) {

        List<Integer> sortedPhotoIds =
                Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25,
                        26, 27, 28, 29, 30);

        int FIRST_SHARD_MIN_CAPACITY = 8;
        int FOLLOWING_SHARD_CAPACITY = 15;

        List<Integer> topIds = new ArrayList<>();
        int start = FIRST_SHARD_MIN_CAPACITY;
        topIds.add(sortedPhotoIds.get(start));
        start += FOLLOWING_SHARD_CAPACITY;
        while (start < sortedPhotoIds.size()) {
            topIds.add(sortedPhotoIds.get(start));
            start += FOLLOWING_SHARD_CAPACITY;
        }

        System.out.println(topIds);
    }

    @Test
    public void testMax() {
        Person p1 = new Person("1", 1);
        Person p2 = new Person("2", 2);
        Person p3 = new Person("3", 3);

        List<Person> list = Arrays.asList(p1, p2, p3);
        Person person = list.stream()
                .max(Comparator.comparingInt(Person::getAge))
                .get();
        System.out.println(person);

        System.out.println("min:" + list.stream().min(Comparator.comparingInt(Person::getAge)).get());

        Deque<String> queue = new ArrayDeque<>();
        
    }

    @Test
    public void testMinNumber(){
        int[] testArray = new int[]{3,30,34,5,9};
        System.out.println(minNumber(testArray));
    }

    public String minNumber(int[] nums) {
        Long l = null;
        String s = String.valueOf(l);

        return Arrays.stream(nums)
                .boxed()
                .sorted((n1, n2)->{
                    int nn1 = n1;
                    int nn2 = n2;
                    while (nn1 % 10 == nn2 %10){
                        nn1/=10;
                        nn2/=10;
                    }
                    return nn1 %10 -nn2%10;
                })
                .map(String::valueOf)
                .reduce("",(n1, n2)-> n1 + n2);
    }
}
