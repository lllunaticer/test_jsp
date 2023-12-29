package GroupByTest;

import java.util.Map;
import java.util.stream.Collectors;

import org.elasticsearch.common.collect.MapBuilder;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2021-12-17
 */
public class TestGroupingBy {
    public static void main(String[] args) {
        Map<Integer, Integer> data = MapBuilder.<Integer, Integer> newMapBuilder()
                .put(1, 1)
                .put(2, 2)
                .put(3, 3)
                .put(4, 4)
                .put(5, 5)
                .map();

        data.entrySet()
                .stream()
                .collect(Collectors.groupingBy(e->e.getKey() %2, Collectors.toList()));
    }
}
