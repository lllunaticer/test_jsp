package ALeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2021-10-13
 */
public class LeetCode412 {
    public List<String> fizzBuzz(int n) {
        List<String> result = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (i % 15 == 0) {
                result.add("FizzBuzz");
            } else if (i % 3 == 0) {
                result.add("Fizz");
            } else if (i % 5 == 0) {
                result.add("Buzz");
            } else {
                result.add((String.valueOf(i)));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        LeetCode412 leetCode412 = new LeetCode412();
        System.out.println(leetCode412.fizzBuzz(15));
    }
}
