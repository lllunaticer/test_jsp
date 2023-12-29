package streamTest;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.google.common.collect.ImmutableMap;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2021-11-06
 */
public class MapSortTest {
    @Test
    public void testMapSort() {
        Map<String, String> redisResult = ImmutableMap.<String, String> builder()
                .put("1", "20")
                .put("2", "19")
                .put("3", "18")
                .put("4", "17")
                .build();

        List<Long> sortedPhotoIds = redisResult.entrySet()
                .stream()
                .sorted((o1, o2) -> (int) (Long.parseLong(o1.getValue().trim()) - Long.parseLong(o2.getValue().trim())))
                .map(e -> Long.valueOf(e.getKey().trim()))
                .collect(toList());

        System.out.println(sortedPhotoIds);
    }
}
