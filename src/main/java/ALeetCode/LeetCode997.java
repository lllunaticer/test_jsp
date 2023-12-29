package ALeetCode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2021-12-19
 */
public class LeetCode997 {
    public int findJudge(int n, int[][] trust) {
        Map<Integer, Set<Integer>> candidate = new HashMap<>();
        Set<Integer> notOnes = new HashSet<>();
        for (int[] record : trust) {
            candidate.computeIfAbsent(record[1], a -> new HashSet<>());
            candidate.get(record[1]).add(record[0]);
            notOnes.add(record[0]);
        }

        for (Map.Entry<Integer, Set<Integer>> entry : candidate.entrySet()) {
            if (entry.getValue().size() != n - 1) {
                continue;
            }
            if (notOnes.contains(entry.getKey())) {
                continue;
            }
            return entry.getKey();
        }

        return -1;
    }
}
