package ALeetCode;

import org.junit.Test;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2022-01-10
 */
public class LeetCode306 {
    @Test
    public void test() {
        System.out.println(isAdditiveNumber("1023"));
    }

    public boolean isAdditiveNumber(String num) {
        for (int firstIdx = 0; firstIdx < num.length() - 2; firstIdx++) {
            if (firstIdx > 0 && num.startsWith("0")) {
                return false;
            }
            for (int secondIdx = firstIdx + 1; secondIdx < num.length() - 1; secondIdx++) {
                if (secondIdx - firstIdx > 1 && "0".equals(num.substring(firstIdx + 1, firstIdx + 2))) {
                    continue;
                }
                long first = Long.parseLong(num.substring(0, firstIdx + 1));
                long second = Long.parseLong(num.substring(firstIdx + 1, secondIdx + 1));
                long third = first + second;
                String thirdStr = String.valueOf(third);
                int thirdIdx = secondIdx + thirdStr.length();
                if (thirdIdx >= num.length()) {
                    break;
                }
                if (num.substring(secondIdx + 1, thirdIdx + 1).equals(thirdStr)) {
                    if (check(firstIdx, secondIdx, thirdIdx, num)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean check(int firstIdx, int secondIdx, int thirdIdx, String num) {
        if (thirdIdx == num.length() - 1) {
            return true;
        }
        long first = Long.parseLong(num.substring(firstIdx + 1, secondIdx + 1));
        long second = Long.parseLong(num.substring(secondIdx + 1, thirdIdx + 1));
        long third = first + second;
        String thirdStr = String.valueOf(third);
        int newThirdIdx = thirdIdx + thirdStr.length();
        if (newThirdIdx >= num.length()) {
            return false;
        }
        if (num.substring(thirdIdx + 1, thirdIdx + 2).equals("0")) {
            return false;
        }
        if (thirdStr.equals(num.substring(thirdIdx + 1, newThirdIdx + 1))) {
            return check(secondIdx, thirdIdx, newThirdIdx, num);
        } else {
            return false;
        }
    }

    @Test
    public void testDouble() {
        long num = 235;
        System.out.printf("你有%.2f%n", num / 100d);
    }

    @Test
    public void testBits() {
        singleNumber(new int[] {3, 4, 3, 3});
    }

    public int singleNumber(int[] nums) {
        int[] bits = new int[32];
        for (int i : nums) {
            for (int j = 0; j < 32; j++) {
                if (((i >> j) & 1) == 1) {
                    bits[j]++;
                }
            }
        }
        int res = 0;
        for (int i = 0; i < 32; i++) {
            bits[i] = bits[i] % 3;
            if (bits[i] == 1) {
                res += (1 << i);
            }
        }
        return res;
    }

}
