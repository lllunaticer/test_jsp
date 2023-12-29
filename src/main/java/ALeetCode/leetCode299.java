package ALeetCode;

import java.util.HashMap;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2021-11-08
 */
public class leetCode299 {
    public String getHint(String secret, String guess) {
        char[] secretArray = secret.toCharArray();
        char[] guessArray = guess.toCharArray();
        HashMap<Character, Integer> cnt = new HashMap<>();
        for (char c : secretArray) {
            if (cnt.containsKey(c)) {
                cnt.put(c, cnt.get(c) + 1);
            } else {
                cnt.put(c, 1);
            }
        }
        int a = 0;
        int b = 0;
        for (int i = 0; i < secretArray.length; i++) {
            if (secretArray[i] == guessArray[i]) {
                a++;
            }
            if (cnt.getOrDefault(guessArray[i], 0) > 0) {
                cnt.put(guessArray[i], cnt.get(guessArray[i])-1);
                b++;
            }
        }

        b = b - a;
        return a + "A" + b + "B";
    }

    public static void main(String[] args) {

    }
}
