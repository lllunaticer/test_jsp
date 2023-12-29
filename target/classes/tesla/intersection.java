package tesla;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.math3.random.RandomDataGenerator;
import org.junit.Test;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2022-09-04
 */
public class intersection {
    @Test
    public void test() {
        int[][] arr = {
                {10, 1, 3, 1, 2, 2, 1, 0, 4},
                {5, 3, 1, 3, 2, 3},
                {9, 9, 9, 9},
                {1, 5, 2, 4, 3, 3}};
        for (int[] a : arr) {
            System.out.println(solution(a));
            System.out.println(solution2(a));
        }
    }

    private static final String DATE = "yyyy-MM-dd HH:mm";

    @Test
    public void testSql() {
        String template = "insert into measurements values('%s', '%s', %d);";
        long l = System.currentTimeMillis();
        RandomDataGenerator randomDataGenerator = new RandomDataGenerator();
        for (int i = 0; i < 50; i++) {
            String format =
                    DateFormatUtils.format(l - randomDataGenerator.nextLong(0, TimeUnit.DAYS.toMillis(5)), DATE);
            System.out.println(String.format(template, format.split(" ")[0], format.split(" ")[1],
                    randomDataGenerator.nextInt(0, 100)));
        }

    }

    int solution2(int[] a) {
        Map<Integer, Integer> record = new HashMap<>();
        int beforeSum = -1;
        int max = 0;
        int cnt;
        Map<Integer, Integer> record1 = new HashMap<>();
        record1.clear();


        for (int i = 0; i < a.length - 1; i++) {
            int sum = a[i] + a[i + 1];
            if (sum == beforeSum) {
                beforeSum = -1;
            } else {
                if (record.containsKey(sum)) {
                    cnt = record.get(sum) + 1;
                    record.put(sum, cnt);
                } else {
                    cnt = 1;
                    record.put(sum, cnt);
                }
                max = Math.max(max, cnt);
                beforeSum = sum;
            }
        }
        return max;
    }

    int solution(int[] A) {
        int cnt = 1;
        int beforeSum = -1;
        int maxICnt = 0;
        int iCnt;

        Map<Integer, Integer> sumCntMap;
        sumCntMap = new HashMap<>();

        for (int i = 0; i < A.length - 1; i++) {
            int sum = A[i] + A[i + 1];
            if (sum == beforeSum && cnt != 2) {
                cnt = 2;
            } else {
                if (!sumCntMap.containsKey(sum)) {
                    iCnt = 1;
                } else {
                    iCnt = sumCntMap.get(sum) + 1;
                }
                sumCntMap.put(sum, iCnt);
                maxICnt = Math.max(maxICnt, iCnt);
                cnt = 1;
            }
            beforeSum = sum;
        }

        return maxICnt;
    }

    public int solution(int[] X, int[] Y, String colors) {
        PriorityQueue<Point> priorityQueue = new PriorityQueue<>(Y.length, Comparator.comparingDouble(Point::distance));
        for (int i = 0; i < X.length; i++) {
            priorityQueue.add(new Point(X[i], Y[i], colors.charAt(i)));
        }

        HashMap<Character, Point> map = new HashMap<>();

        while (map.size() != X.length) {
            Point point = priorityQueue.poll();
            if (map.containsKey(point.getTag())) {
                Point firstPoint = map.get(point.getTag());
                return Double.compare(firstPoint.distance(), point.distance()) == 0 ? map.size() - 1 : map.size();
            } else {
                map.put(point.getTag(), point);
            }
        }

        return map.size();
    }

    class Point {
        private int x;
        private int y;
        private char tag;

        public Point(int x, int y, char tag) {
            this.x = x;
            this.y = y;
            this.tag = tag;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public char getTag() {
            return tag;
        }

        public double distance() {
            return Math.sqrt(x ^ 2 + y ^ 2);
        }
    }

    public int minSumOfLengths(int[] arr, int target) {
        Map<Integer, Integer> record = new HashMap<>();
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE, sum = 0;
        int lastIdx = -1;
        record.put(0, lastIdx);
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (record.containsKey(sum - target)) {
                int length = i - record.get(sum - target);
                if (min1 > length) {
                    min1 = length;
                } else if (min2 > length) {
                    min2 = length;
                }
                lastIdx = i;
                record.clear();
                record.put(0, lastIdx);
                sum = 0;
            } else {
                record.put(sum, i);
            }
        }
        if (min1 == Integer.MAX_VALUE || min2 == Integer.MAX_VALUE) {
            return -1;
        }
        return min1 + min2;
    }

    @Test
    public void testMin() {
        minSumOfLengths(new int[] {1, 1, 1, 2, 2, 2, 4, 4}, 6);
    }


}
