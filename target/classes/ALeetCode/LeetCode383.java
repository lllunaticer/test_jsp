package ALeetCode;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2021-12-06
 */
public class LeetCode383 {
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] noteDict = new int[26];
        int[] magaDict = new int[26];

        for (int i = 0; i < ransomNote.length(); i++) {
            noteDict[ransomNote.charAt(i) - 'a']++;
        }

        for (int j = 0; j < magazine.length(); j++) {
            magaDict[magazine.charAt(j) - 'a']++;
        }

        for (int i : noteDict) {
            if (noteDict[i] > magaDict[i]) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        LeetCode383 leetCode383 = new LeetCode383();
        leetCode383.canConstruct("fihjjjjei", "hjibagacbhadfaefdjaeaebgi");
    }
}
