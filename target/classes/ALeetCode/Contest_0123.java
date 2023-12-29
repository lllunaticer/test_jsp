package ALeetCode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.junit.Test;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2022-01-23
 */
public class Contest_0123 {
    public int maximumGood(int[][] statements) {

        int len = statements.length;
        int colLen = statements[0].length;

        List<int[]> goodRecord = new ArrayList<>();
        for (int i = 0; i < colLen; i++) {
            int[] r = new int[2];
            r[0] = i;
            for (int j = 0; j < len; j++) {
                if (statements[j][i] == 0) {
                    r[1]++;
                }
            }
            goodRecord.add(r);
        }

        List<int[]> sortedGoodRecord = goodRecord.stream()
                .sorted(Comparator.comparingInt(r -> r[1]))
                .collect(Collectors.toList());

        Set<Integer> good = new HashSet<>();
        Set<Integer> bad = new HashSet<>();

        for (int[] r : sortedGoodRecord) {
            if (bad.contains(r[0])) {
                continue;
            }
            good.add(r[0]);

            int row = r[0];
            for (int i = 0; i < colLen; i++) {
                if (statements[row][i] == 0) {
                    if (good.contains(i)) {
                        bad.add(row);
                        good.remove(row);
                        break;
                    }
                    bad.add(i);
                }
            }
        }
        return good.size();
    }

    @Test
    public void testGood() {
        int[][] statements = new int[][] {{2, 2, 2, 2}, {1, 2, 1, 0}, {0, 2, 2, 2}, {0, 0, 0, 2}};
        System.out.println(maximumGood(statements));
    }
}
