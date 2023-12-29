package ALeetCode;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2021-12-10
 */
public class LeetCode748 {
    public String shortestCompletingWord(String licensePlate, String[] words) {
        int[] plate = getStatistics(licensePlate);

        String candidate = "77777777777777777";

        for (String word : words) {
            int[] wordArr = getStatistics(word);
            if (isCompleting(plate, wordArr)) {
                if (candidate.length() > word.length()) {
                    candidate = word;
                }
            }
        }
        return candidate;
    }


    private int[] getStatistics(String licensePlate) {
        int[] plate = new int[26];
        for (char c : licensePlate.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                plate[c - 'a']++;
            } else if (c >= 'A' && c <= 'Z') {
                plate[c - 'A']++;
            }
        }
        return plate;
    }

    private boolean isCompleting(int[] plate, int[] wordArr) {
        for (int i = 0; i < 26; i++) {
            if (plate[i] > wordArr[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        LeetCode748 leetCode748 = new LeetCode748();
        System.out.println(leetCode748.shortestCompletingWord("1s3 PSt",
                new String[] {"step", "steps", "stripe", "stepple"}));
    }
}
