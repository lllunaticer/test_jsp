import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;

import com.kuaishou.encryption.util.IdEncryptorUtils;

/**
 * @author longxingjian <longxingjian@kuaishou.com>
 * Created on 2021-05-07
 */
public class testCal {
    public static void main(String[] args) {
        System.out.println(IdEncryptorUtils.decryptLongPhotoId(
                5254443439717898649L
        ));
    }

    @Test
    public void check() {
        Set<Integer> check = new HashSet<>();
        boolean add = check.add(2);
    }

    @Test
    public void testMin() {
        System.out.println(minSwaps(new int[] {1}));
    }

    public int minSwaps(int[] nums) {
        String target = "123";

        System.out.println(target.contains("23"));
        int len = nums.length;
        int count = 0;
        for (int i : nums) {
            if (i == 1) {
                count++;
            }
        }
        if (count == len) {
            return 0;
        }

        int largestLen = 0;
        int left = 0;
        int right = 0;
        while (right < len * 2) {
            if (nums[right % len] == 1) {
                right++;
            } else {
                largestLen = Math.max(right - left, largestLen);
                left = right + 1;
                right = left;
            }
        }

        largestLen = Math.max(right - left, largestLen);
        return count - largestLen;
    }

    @Test
    public void testFindLen() {
        System.out.println(findLen(new int[] {1, 0, 1, 1}));
    }

    public int findLen(int[] nums) {
        int len = nums.length;
        int largestLen = 0;
        int left = 0;
        int right = 0;
        while (right < len * 2) {
            if (nums[right % len] == 1) {
                right++;
            } else {
                largestLen = Math.max(right - left, largestLen);
                left = right + 1;
                right = left;
            }
        }

        largestLen = Math.max(right - left, largestLen);
        return largestLen;
    }

    public int wordCount(String[] startWords, String[] targetWords) {
        int count = 0;
        List<Set<Character>> startWordLists = new ArrayList<>();
        for (String start : startWords) {
            Set<Character> startSet = new HashSet<>();
            for (char c : start.toCharArray()) {
                startSet.add(c);
            }
            startWordLists.add(startSet);
        }

        for (String target : targetWords) {
            Set<Character> targetSet = new HashSet<>();
            for (char c : target.toCharArray()) {
                targetSet.add(c);
            }
            for (Set<Character> start : startWordLists) {
                if (start.size() == target.length() - 1) {
                    if (targetSet.containsAll(start)) {
                        count++;
                        break;
                    }
                }
            }
        }
        return count;
    }

    @Test
    public void testSlowestKey() {
        slowestKey(new int[] {28, 65, 97}, "gaf");
    }

    public char slowestKey(int[] releaseTimes, String keysPressed) {
        int largest = releaseTimes[0];
        int idx = 0;
        for (int i = 1; i < releaseTimes.length; i++) {
            if (releaseTimes[i] - releaseTimes[i - 1] == largest) {
                if (keysPressed.charAt(i) < keysPressed.charAt(idx)) {
                    idx = i;
                }
            }
            if (releaseTimes[i] - releaseTimes[i - 1] > largest) {
                idx = i;
            }
        }
        return keysPressed.charAt(idx);
    }

    Set<Integer> startSet = new HashSet<>();

    public int wordCount1(String[] startWords, String[] targetWords) {
        int count = 0;

        for (String start : startWords) {
            startSet.add(generateIntFlagForString(start));
        }

        for (String target : targetWords) {
            int targetInt = generateIntFlagForString(target);
            if (check(targetInt)) {
                count++;
            }
        }

        return count;
    }

    private int generateIntFlagForString(String s) {
        int flag = 0;
        for (char c : s.toCharArray()) {
            flag |= 1 << c - 'a';
        }
        return flag;
    }

    private boolean check(int flag) {
        // 每次从target中删除一个字母，再去startSet中去匹配。
        for (int i = 0; i < 26; i++) {
            if ((flag >> i & 1) == 1) {
                int flagSub = ((~(1 << i)) & flag);
                if (startSet.contains(flagSub)) {
                    return true;
                }
            }
        }
        return false;
    }

    public int earliestFullBloom(int[] plantTime, int[] growTime) {
        Arrays.sort(growTime);
        return 1;
    }

    public int earliestFullBloom1(int[] plantTime, int[] growTime) {
        List<Bloom> blooms = new ArrayList<>();
        for (int i = 0; i < plantTime.length; i++) {
            blooms.add(new Bloom(plantTime[i], growTime[i]));
        }

        blooms = blooms.stream().sorted((b1, b2) -> b2.getGrowTime() - b1.getGrowTime()).collect(Collectors.toList());

        int count = 0;
        int longest = 0;
        int cur = 0;
        for (Bloom bloom : blooms) {
            cur += bloom.getPlantTime();
            longest = Math.max(cur + bloom.getGrowTime(), longest);
        }
        return longest;
    }

    class Bloom {
        private int plantTime;
        private int growTime;

        public int getPlantTime() {
            return plantTime;
        }

        public int getGrowTime() {
            return growTime;
        }

        public Bloom(int plantTime, int growTime) {
            this.plantTime = plantTime;
            this.growTime = growTime;
        }
    }
}