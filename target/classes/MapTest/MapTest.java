package MapTest;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Test;

import com.google.common.collect.Iterables;

/**
 * @author longxingjian <longxingjian@kuaishou.com>
 * Created on 2021-04-18
 */
public class MapTest {
    @Test
    public void testCompute() {
        Map<String, String> test = new ConcurrentHashMap<>();
        test.put("longxingjian", "LONG");
//        test.computeIfAbsent("litianpeng", String::toUpperCase);
//        test.computeIfAbsent("likang", k -> k.toUpperCase());
        String key = "chenwenchao";
        test.computeIfAbsent(key, (name) -> name.toUpperCase());
        System.out.println(test);
    }

}
