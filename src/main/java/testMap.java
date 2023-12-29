import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.kuaishou.framework.util.ObjectMapperUtils;

/**
 * @author longxingjian <longxingjian@kuaishou.com>
 * Created on 2021-03-15
 */
public class testMap {
    public static void main(String[] args) {
        Map<String, String> key = new HashMap<>();
        key.put("1", "2");
        key.put("3", "4");
        System.out.println(key.values());
    }

    @Test
    public void testComputeMap() {
        Map<Integer, List<Integer>> res = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            res.computeIfAbsent(i, v -> new ArrayList<>());
            res.get(i).add(i);
        }
        System.out.println(ObjectMapperUtils.toJSON(res));
    }

    @Test
    public void testNumber() {
        for (int i = 1; i < 10000; i++) {
            if (i % 7 == 0 || String.valueOf(i).contains("7")) {
                System.out.println(i);
            }
        }
    }
}
