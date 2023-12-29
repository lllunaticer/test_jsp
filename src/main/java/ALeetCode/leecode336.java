package ALeetCode;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.joda.time.DateTime;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kuaishou.ip.constant.Country;

import lombok.Builder;
import lombok.Data;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2022-02-02
 */
public class leecode336 {
    TreeNode root = new TreeNode();

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("YYYY-MM-dd HH");

    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        int len = words.length;
        // 初始化前缀树
        for (int i = 0; i < len; i++) {
            insert(words[i], i);
        }

        for (int i = 0; i < len; i++) {
            String word = words[i];
            int l = word.length();
            int idx;
            //            int idx = findWordReversely(word);
            //            if(idx > -1 && idx != i){
            //                res.add(Arrays.asList(idx, i));
            //            }

            for (int j = -1; j < l; j++) {
                if (isParlindom(word, 0, j)) {
                    idx = findWordReversely(word.substring(j + 1, l));
                    if (idx > -1 && idx != i) {
                        res.add(Arrays.asList(idx, i));
                    }
                }

                if (isParlindom(word, j + 1, l - 1)) {
                    idx = j < 0 ? findWordReversely("") : findWordReversely(word.substring(0, j));
                    if (idx > -1 && idx != i) {
                        res.add(Arrays.asList(i, idx));
                    }
                }
            }
        }
        return res;
    }

    private boolean isParlindom(String word, int left, int right) {
        while (left < right) {
            if (word.charAt(left) != word.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    private void insert(String word, int idx) {
        int len = word.length();
        TreeNode start = root;
        if (word == "") {
            root.idx = idx;
            return;
        }
        char candidate;
        for (int i = 0; i < len; i++) {
            candidate = word.charAt(i);
            if (!start.sons.keySet().contains(candidate)) {
                TreeNode son = new TreeNode();
                start.sons.put(candidate, son);
                start = son;
            } else {
                start = start.sons.get(candidate);
            }
        }
        start.idx = idx;
    }

    private int findWordReversely(String word) {
        int len = word.length();
        TreeNode start = root;
        char candidate;
        for (int i = len - 1; i >= 0; i--) {
            candidate = word.charAt(i);
            if (!start.sons.keySet().contains(candidate)) {
                return -1;
            } else {
                start = start.sons.get(candidate);
            }
        }
        return start.idx;
    }

    class TreeNode {
        public HashMap<Character, TreeNode> sons;
        public int idx;

        TreeNode() {
            this.idx = -1;
            this.sons = new HashMap<>();
        }
    }

    @Test
    public void testPalindrom() {
        System.out.println(palindromePairs(new String[] {"a", "abc", "aba", ""}));
    }

    @Test
    public void testTime() {
        long timestamp = 1652516541277L;
        System.out.println(new DateTime(timestamp));
    }

    @Test
    public void removeAnagrams() {
        String[] words = {"abba", "baba", "bbaa", "cd", "cd"};
        List<String> res = new ArrayList<>();
        int idx = 0;
        int left = 0;
        res.add(words[idx]);
        while (idx + 1 < words.length) {
            if (!getOrderedString(res.get(left)).equals(getOrderedString(words[idx + 1]))) {
                res.add(words[idx + 1]);
                left++;
            }
            idx++;
        }
        System.out.println(res);
    }

    private String getOrderedString(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    @Test
    public void testTimeUnit() {
        System.out.println(TimeUnit.DAYS.toSeconds(1L));
    }

    @Test
    public void testSimpleDateFormat() {
        System.out.println(TimeUnit.DAYS.toMillis(7));
        System.out.println(TimeUnit.MINUTES.toMillis(10));
        System.out.println(TimeUnit.DAYS.toMillis(2));
    }

    @Test
    public void testBeanUtils() {
        Student student = Student.builder()
                .name("xxx")
                .age(10L)
                .addr("lll")
                .build();
        ObjectMapper oMapper = new ObjectMapper();
        Map map = oMapper.convertValue(student, Map.class);
        map.put("buckt", Country.AFG);
        System.out.println(map);
    }

    @Data
    @Builder
    public static class Student {
        private String name;
        private long age;
        private String addr;
        private Boolean t;
    }

    @Test
    public void testBool() {
        String sqlTemplate =
                "alter table photo_music_tag_index_%d add index idx_id_type_create_time (`music_id`,"
                        + "`music_type`, `create_time`);";
        for (int i = 0; i < 100; i++) {
            System.out.println(String.format(sqlTemplate, i));
        }
    }

    @Test
    public void testAtomicInt() {
        AtomicInteger ai = new AtomicInteger(Integer.MAX_VALUE - 1);
        System.out.println(ai.incrementAndGet());
        System.out.println(ai.incrementAndGet());
        System.out.println(ai.incrementAndGet());
        System.out.println(ai.get() % 500);
    }

    @Test
    public void testEqual() {
        int l;
    }

    @Test
    public void exchange(){
        //因为 长度为n的数组里的数字大小在0~n-1之间，所以不会有越界
        //遍历每个位置，把该位置上的数字放到下标值也等于这个数的位置上去
        int[] nums = {3, 4, 2, 0, 0, 1};
        for(int i = 0; i<nums.length; i++){
            int tmp = nums[nums[i]];
            if(tmp == nums[i] && i != nums[i]){
                return;
            }
            nums[nums[i]] = nums[i];
            nums[i] = tmp;
        }
    }

    @Test
    public void test(){
        System.out.println(1L << 49);
    }

    private int getL() {
        Random random = new Random();
        return random.nextInt();
    }

}
