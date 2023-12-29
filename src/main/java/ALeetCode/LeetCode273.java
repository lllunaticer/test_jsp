package ALeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2021-10-11
 */
public class LeetCode273 {
    private static final int ten = 10;
    private static final int hundred = 100;
    private static final int thousand = 1000;
    private static final int million = 1000000;
    private static final int billion = 1000000000;

    private static final Map<Integer, String> numMap = new HashMap<>();

    static {
        numMap.put(0,"Zero");
        numMap.put(1, "One");
        numMap.put(2, "Two");
        numMap.put(3, "Three");
        numMap.put(4, "Four");
        numMap.put(5, "Five");
        numMap.put(6, "Six");
        numMap.put(7, "Seven");
        numMap.put(8, "Eight");
        numMap.put(9, "Nine");
        numMap.put(10, "Ten");
        numMap.put(11, "Eleven");
        numMap.put(12, "Twelve");
        numMap.put(13, "Thirteen");
        numMap.put(14, "Fourteen");
        numMap.put(15, "Fifteen");
        numMap.put(16, "Sixteen");
        numMap.put(17, "Seventeen");
        numMap.put(18, "Eighteen");
        numMap.put(19, "Nineteen");
        numMap.put(20, "Twenty");
        numMap.put(30, "Thirty");
        numMap.put(40, "Forty");
        numMap.put(50, "Fifty");
        numMap.put(60, "Sixty");
        numMap.put(70, "Seventy");
        numMap.put(80, "Eighty");
        numMap.put(90, "Ninety");
        numMap.put(100, "Hundred");
        numMap.put(1000, "Thousand");
        numMap.put(1000000, "Million");
        numMap.put(1000000000, "Billion");
    }

    public static void main(String[] args) {
        LeetCode273 leetCode273 = new LeetCode273();
        System.out.println(leetCode273.numberToWords(0));
    }

    public String numberToWords(int num) {
        List<String> result = new ArrayList<>();
        if (num >= billion) {
            int biPlace = num / billion;
            dealLessThanHundred(biPlace, result);
            result.add(numMap.get(billion));
            num = num % billion;
        }
        dealLessThanBillion(num, result);

        StringBuilder res = new StringBuilder();
        for (String s : result) {
            res.append(s).append(" ");
        }
        return result.size() > 1 ? res.substring(0, res.length() - 1) : res.toString();
    }

    // x<100
    private void dealLessThanHundred(int num, List<String> result) {
        if (num >= 0) {
            if (num <= 19) {
                result.add(numMap.get(num));
            } else {
                result.add(numMap.get(num - num % 10));
                result.add(numMap.get(num % 10));
            }
        }
    }

    // 100 <= x < 1000
    private void dealLessThanThousand(int num, List<String> result) {
        if (num >= hundred) {
            int hunPlace = num / hundred;
            result.add(numMap.get(hunPlace));
            result.add(numMap.get(hundred));
            dealLessThanHundred(num % hundred, result);
        } else {
            dealLessThanHundred(num, result);
        }
    }

    // 1000 <= x < 100 0000
    private void dealLessThanMillion(int num, List<String> result) {
        if (num >= thousand) {
            int thPlace = num / thousand;
            // thPlace的取值范围是1到999
            dealLessThanThousand(thPlace, result);
            result.add(numMap.get(thousand));
            // 余数的范围是 1到999
            dealLessThanThousand(num % thousand, result);
        } else {
            dealLessThanThousand(num, result);
        }
    }

    // 100 0000 <= x < 10 0000 0000
    private void dealLessThanBillion(int num, List<String> result) {
        if (num >= million) {
            // miPlace的取之范围是 1到 999
            int miPlace = num / million;
            dealLessThanThousand(miPlace, result);
            result.add(numMap.get(million));
            // 余数的范围是1到999
            dealLessThanMillion(num % million, result);
        } else {
            dealLessThanMillion(num, result);
        }

    }
}
