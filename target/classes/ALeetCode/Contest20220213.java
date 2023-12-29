package ALeetCode;

import static java.util.concurrent.TimeUnit.DAYS;
import static java.util.concurrent.TimeUnit.HOURS;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2022-02-13
 */
public class Contest20220213 {
    public int countOperations(int num1, int num2) {
        int res = 0;
        int min = Math.min(num1, num2);
        int max = Math.max(num1, num2);
        while (min > 0) {
            res += max / min;
            int left = max % min;
            max = Math.max(left, min);
            min = Math.min(left, min);
        }

        return res;
    }

    public int minimumOperations(int[] nums) {
        int res = 0;
        Map<Integer, Integer> oddCount = new HashMap<>();
        Map<Integer, Integer> evenCount = new HashMap<>();

        int oddMax = 0;
        int oddSecondMax = 0;

        int evenMax = 0;
        int evenSecondMax = 0;

        int len = nums.length;

        int evenFlag = 1;
        for (int i = 0; i < len; i++) {
            if (evenFlag == 1) {
                int cnt = evenCount.getOrDefault(nums[i], 0) + 1;
                evenCount.put(nums[i], cnt);
                if (cnt > evenCount.getOrDefault(evenMax, 0)) {
                    evenSecondMax = evenMax;
                    evenMax = nums[i];
                } else if (cnt > evenCount.getOrDefault(evenSecondMax, 0)) {
                    evenSecondMax = nums[i];
                }
            } else {
                int cnt = oddCount.getOrDefault(nums[i], 0) + 1;
                oddCount.put(nums[i], cnt);
                if (cnt > oddCount.getOrDefault(oddMax, 0)) {
                    oddSecondMax = oddMax;
                    oddMax = nums[i];
                } else if (cnt > oddCount.getOrDefault(oddSecondMax, 0)) {
                    oddSecondMax = nums[i];
                }
            }
            evenFlag *= -1;
        }

        int oddMaxCount = oddCount.get(oddMax);
        int evenMaxCount = evenCount.get(evenMax);

        int oddSecondMaxCount = oddCount.get(oddSecondMax);
        int evenSecondMaxCount = evenCount.get(evenSecondMax);

        int oddNums = len / 2;
        int evenNums = len % 2 == 0 ? len / 2 : len / 2 + 1;


        if (oddNums - oddMaxCount < evenNums - evenMaxCount) {
            res += oddNums - oddMaxCount;
            if (oddMax != evenMax) {
                res += evenNums - evenMaxCount;
            } else {
                res += evenNums - evenSecondMaxCount;
            }
        } else if (oddNums - oddMaxCount > evenNums - evenMaxCount) {
            res += evenNums - evenMaxCount;
            if (evenMax != oddMax) {
                res += oddNums - oddMaxCount;
            } else {
                res += oddNums - oddSecondMaxCount;
            }
        } else {
            res += oddNums - oddMaxCount;
            if (oddNums - oddSecondMaxCount > evenNums - evenSecondMaxCount) {
                res += evenNums - evenSecondMaxCount;
            } else {
                res += oddNums - oddSecondMaxCount;
            }
        }
        return res;
    }

    @Test
    public void testCount() {
        System.out.println(countOperations(2, 3));
    }

    @Test
    public void testDays() {
        String sql =
                "ALTER TABLE `ugc_copyright_recognition_%d` ADD INDEX idx_create_time_status ( `create_time`, "
                        + "`status`);";
        for (int i = 0; i < 100; i++) {
            System.out.println(String.format(sql, i));
        }
    }
}
