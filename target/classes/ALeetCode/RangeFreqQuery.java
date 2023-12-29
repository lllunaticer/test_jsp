package ALeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2021-11-21
 */
public class RangeFreqQuery {

    private final Map<Integer, List<Integer>> record;

    public RangeFreqQuery(int[] arr) {
        record = new HashMap<>();
        int len = arr.length;

        for (int num : arr) {
            if (record.containsKey(num)) {
                continue;
            }
            List<Integer> mem = new ArrayList<>();
            for (int i = 0; i < len; i++) {
                if (arr[i] == num) {
                    mem.add(i);
                }
            }
            record.put(num, mem);
        }
    }

    public int query(int left, int right, int value) {
        List<Integer> mem = record.get(value);
        if (mem == null) {
            return 0;
        }
        int lo = find(mem, left);
        int hi = find(mem, right);

        if (hi == lo) {
            return hi == left || lo == right ? 1 : 0;
        }

        return hi - lo + 1;
    }

    private int find(List<Integer> list, int target) {
        if (list.get(0) > target) {
            return list.get(0);
        }

        if (list.get(list.size() - 1) < target) {
            return list.get(list.size() - 1);
        }

        int lo = 0;
        int hi = list.size() - 1;
        int mid;
        while (lo < hi) {
            mid = (lo + hi) / 2;
            if (list.get(mid) < target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }

    public static void main(String[] args) {
        RangeFreqQuery rangeFreqQuery = new RangeFreqQuery(new int[] {1, 1, 1, 2, 2});
        System.out.println(rangeFreqQuery.query(0, 1, 2));
        System.out.println(rangeFreqQuery.query(0, 2, 1));
        System.out.println(rangeFreqQuery.query(3, 3, 2));
        System.out.println(rangeFreqQuery.query(2, 2, 1));
    }
}
