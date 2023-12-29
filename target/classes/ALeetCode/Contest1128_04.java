package ALeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2021-11-28
 */
public class Contest1128_04 {
    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        TreeMap<Integer, List<List<Integer>>> records = new TreeMap<>();

        for (int[] record : meetings) {
            Integer key = record[2];
            if (records.containsKey(key)) {
                records.get(key).add(Arrays.asList(record[0], record[1]));
            } else {
                List<List<Integer>> mem = new ArrayList<>();
                mem.add(Arrays.asList(record[0], record[1]));
                records.put(key, mem);
            }
        }


        Set<Integer> res = new HashSet<>();
        res.add(firstPerson);
        res.add(0);
        records.forEach(
                (time, members) -> {
                    for (List<Integer> mem : members) {
                        if (!Collections.disjoint(res, mem)) {
                            res.addAll(mem);
                        }
                    }
                }
        );

        return new ArrayList<>(res);
    }

    public static void main(String[] args) {
        Contest1128_04 contest1128_04 = new Contest1128_04();
        contest1128_04.findAllPeople(6, new int[][] {{1, 2, 5}, {2, 3, 8}, {1, 5, 10}}, 1);
    }
}
