package TestGuava;

import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.junit.Test;

import com.google.common.collect.ImmutableMap;
import com.kuaishou.framework.util.ObjectMapperUtils;

/**
 * @author Xingjian LONG
 * Created on 2021-08-07
 */
public class TestGuava {
    @Test
    public void testGuava() {
        List<People> peopleList = Arrays.asList(
                new People(10, "A"),
                new People(10, "B"),
                new People(20, "c"),
                new People(20, "d"),
                new People(30, "e"),
                new People(30, "f"),
                new People(30, "g"),
                new People(40, "h"),
                new People(40, "k"),
                new People(50, "l"),
                new People(50, "m"),
                new People(60, "n"),
                new People(60, "q"));

        Map<Integer, List<String>> collectByAgeWithName = peopleList.stream().collect(Collectors.groupingBy(
                People::getAge,
                HashMap::new,
                mapping(People::getName, Collectors.toList())
        ));
        System.out.println(collectByAgeWithName);

        Map<Integer, List<People>> peopleMap = peopleList.stream().collect(Collectors.groupingBy(
                People::getAge,
                TreeMap::new,
                mapping(Function.identity(), toList())
        ));

        System.out.println(peopleMap);
    }

    @Test
    public void ttt() throws Exception {
        Map<Integer, Integer> map = ImmutableMap.of(1, 1, 2, 2, 3, 3);

        map = map.entrySet().stream().filter(e -> e.getKey() != 1)
                .collect(Collectors.toMap(Entry::getKey, Entry::getValue));

        System.out.println(map);
    }

    @Test
    public void tttt() {
        Map<Long, String> ss = new HashMap<>();
        ss.put(150000409001066L,
                "This user already has a valid inviteCode, he/she may be a real user in our site, please check uid "
                        + "carefully! The existed inviteCode is:304920362");
        System.out.println(ObjectMapperUtils.toJSON(ss));
    }
}
