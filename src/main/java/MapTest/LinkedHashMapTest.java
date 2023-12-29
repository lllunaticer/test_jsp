package MapTest;

import java.util.Collection;
import java.util.LinkedHashMap;

/**
 * @author longxingjian <longxingjian@kuaishou.com>
 * Created on 2021-05-06
 */
public class LinkedHashMapTest {
    public static void main(String[] args) {
        LinkedHashMap<Long, Integer> test = new LinkedHashMap<>();
        test.put(1L, 1);
        test.put(2L, 2);
        test.put(3L, 3);
        test.put(4L, 4);
        test.put(5L, 5);
        test.put(6L, 6);
        test.put(7L, 7);
        test.put(8L, 8);
        Collection<Integer> values = test.values();
        System.out.println(values);
        test.forEach(
                (k, v) -> System.out.println(k + "-" + v)
        );
    }
}
