package ALeetCode;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2021-10-14
 */
public class leetcode668 {
    public int findKthNumber(int m, int n, int k) {
        int max = m * n;
        int count = 0;
        for (int i = 1; i <= max; i++) {
            if (count >= k) {
                return i - 1;
            } else {
                count += findFactors(i, m, n);
            }
        }
        if (count >= k) {
            return max;
        }
        return -1;
    }

    private int findFactors(int number, int m, int n) {
        int maxMN = Math.max(m, n);
        int minMN = Math.min(m, n);
        int min = Math.min(minMN, (int) Math.sqrt(number));
        int count = 0;

        if (min == minMN) {
            for (int i = 1; i <= min; i++) {
                if (number % i == 0) {
                    int tmp = number / i;
                    if (tmp <= maxMN) {
                        count = count + 1;
                    }
                }
            }
        } else {
            for (int i = 1; i <= min; i++) {
                if (number % i == 0) {
                    int tmp = number / i;
                    if (tmp <= minMN) {
                        count = count + 2;
                    } else if (tmp <= maxMN) {
                        count = count + 1;
                    }
                }
            }
            if (min * min == number) {
                count--;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        leetcode668 leetcode668 = new leetcode668();
        System.out.println(leetcode668.findKthNumber(9, 9, 81));
        //        System.out.println(leetcode668.findFactors(6,2, 6));
    }
}
