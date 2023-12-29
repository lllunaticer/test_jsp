package ALeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.hadoop.shaded.com.google.common.collect.Lists;
import org.junit.Test;

import com.kuaishou.framework.util.ObjectMapperUtils;

import ALeetCode.leecode336.Student;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2022-06-11
 */
public class PointToOffer {
    char[][] b;
    String w;
    int row;
    int col;
    int[][] directs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    private static final String USERNAME_PLACEHOLDER = "\\$\\{user_name}";

    public boolean exist(char[][] board, String word) {
        b = board;
        w = word;
        row = board.length;
        col = board[0].length;
        boolean[][] marks = new boolean[board.length][board[0].length];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (searchInBoard(marks, 0, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean searchInBoard(boolean[][] marks, int idx, int x, int y) {
        if (idx > w.length() - 1) {
            return true;
        }
        if (x < 0 || x > col - 1 || y < 0 || y > row - 1 || marks[x][y]) {
            return false;
        }
        if (b[x][y] == w.charAt(idx)) {
            marks[x][y] = true;
        }
        boolean res = false;
        for (int[] d : directs) {
            if (searchInBoard(marks, idx + 1, x + d[0], y + d[1])) {
                res = true;
                break;
            }
        }
        marks[x][y] = false;
        return res;
    }

    @Test
    public void testExist() {
        exist(new char[][] {{'b'}}, "s");
    }

    @Test
    public void testQueue() {
        Queue<int[]> seeds = new LinkedList<>();

        HashMap<String, String> map = new HashMap<String, String>() {{
            put("String", "String");
            put("s", "k");
        }};
    }

    private static final Map<Character, Integer>[] statusMap =
            new Map[] {
                    new HashMap<Character, Integer>() {{
                        put(' ', 0);
                        put('s', 1);
                        put('d', 2);
                        put('.', 4);
                    }}, //0
                    new HashMap<Character, Integer>() {{
                        put('.', 4);
                        put('d', 2);
                    }},                           //1
                    new HashMap<Character, Integer>() {{
                        put('d', 2);
                        put('e', 6);
                        put(' ', 9);
                        put('.', 3);
                    }}, //2
                    new HashMap<Character, Integer>() {{
                        put('d', 5);
                        put('e', 6);
                        put(' ', 9);
                    }},               //3
                    new HashMap<Character, Integer>() {{
                        put('d', 5);
                    }},                                        //4
                    new HashMap<Character, Integer>() {{
                        put('d', 5);
                        put('e', 6);
                        put(' ', 9);
                    }},               //5
                    new HashMap<Character, Integer>() {{
                        put('s', 7);
                        put('d', 8);
                    }},                           //6
                    new HashMap<Character, Integer>() {{
                        put('d', 8);
                    }},                                        //7
                    new HashMap<Character, Integer>() {{
                        put('d', 8);
                        put(' ', 9);
                    }},                            //8
                    new HashMap<Character, Integer>() {{
                        put(' ', 9);
                    }},                                        //9
            };

    private static final Set<Integer> validStatus = new HashSet<Integer>() {{
        add(2);
        add(3);
        add(5);
        add(8);
        add(9);
    }};


    public boolean isNumber(String s) {
        int status = 0;
        for (char c : s.toCharArray()) {
            c = getSign(c);
            Map<Character, Integer> currentStatusMap = statusMap[status];
            if (!currentStatusMap.containsKey(c)) {
                return false;
            }
            status = currentStatusMap.get(c);
        }
        //2，3，5，8，9是有效终态
        return validStatus.contains(status);
    }

    private char getSign(char c) {
        if (c >= '0' && c <= '9') {
            return 'd';
        }
        if (c == '-' || c == '+') {
            return 's';
        }
        if (c == 'e' || c == 'E') {
            return 'e';
        }
        if (c == '.' || c == ' ') {
            return c;
        }
        return '?';
    }

    @Test
    public void test() {
        Set<Long> uids = new HashSet<Long>() {{
            add(100L);
            add(200L);
            add(300L);
        }};
        filterFunction(uids);
        System.out.println(uids);
    }

    private void filterFunction(Set<Long> uids) {
        uids = uids.stream()
                .filter(id -> id < 200)
                .collect(Collectors.toSet());
    }

    @Test
    public void testChangeStudents() {
        List<Student> students = new ArrayList<Student>() {{
            add(Student.builder().age(50).build());
            add(Student.builder().age(100).build());
        }};
        filterStudent1(students);
        //结果不变 因为改变的是传给filterStudent1的新的引用指向
        System.out.println(ObjectMapperUtils.toJSON(students));
        filterStudent2(students);
        System.out.println(ObjectMapperUtils.toJSON(students));
        changeStudents(students);
        System.out.println(ObjectMapperUtils.toJSON(students));
    }

    private void filterStudent1(List<Student> students) {
        //传入这里的students 是从对象上复制出来的一个地址，和调用方不是一个引用；改变这个引用的指向不会影响调用方持用的引用
        students = students.stream()
                .filter(student -> student.getAge() > 50)
                .collect(Collectors.toList());
    }

    private void filterStudent2(List<Student> students) {
        //但如果改变了引用的地址上对象的内容，则会影响对象。传入这里的引用和调用方持有的引用因为是同一个对象，所以调用方也看到了对象的变化
        students.remove(0);
    }

    private void changeStudents(List<Student> students) {
        //change 同理
        students.forEach(s -> s.setAge(s.getAge() + 1));
    }

    @Test
    public void testChangePeople() {
        Student s = Student.builder()
                .age(1L)
                .addr("xx")
                .build();
        changePeople(s);
        System.out.println(s.getAge());
    }

    private void changePeople(Student s) {
        s.setAge(100L);
    }

    @Test
    public void testSub() {
        String template = "لقد قدّر ${user_name} بقيمة ${A}${0}";
        String abc = template.replaceAll(USERNAME_PLACEHOLDER, "abc");
        System.out.println(abc);
        System.out.println(template);
    }

    @Test
    public void testString() {
        String userName = "User_staging";
        String template = "${user_name} is super excited to get your Praise!";
        System.out.println(template.replaceAll(USERNAME_PLACEHOLDER, userName));
        System.out.println(template.replace(USERNAME_PLACEHOLDER, userName));
    }

    @Test
    public void testSublist() {
        List<Integer> list = Arrays.asList(1, 2);
        System.out.println(list.subList(0, 1));
        System.out.println(list.subList(0, 0));
    }

    @Test
    public void testStack() {
        int[] pushed = new int[] {1, 0};
        int[] popped = new int[] {1, 0};
        validateStackSequences(pushed, popped);
    }

    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int len = pushed.length;
        if (len == 0) {
            return true;
        }

        int right = findIdx(popped[0], pushed);
        if (right == -1) {
            return false;
        }
        int left = right;

        for (int i = 1; i < len; i++) {
            if (right + 1 < len && pushed[right + 1] == popped[i]) {
                right += 1;
            } else if (left - 1 >= 0 && pushed[left - 1] == popped[i]) {
                left -= 1;
            } else {
                return false;
            }
        }
        return true;
    }

    private int findIdx(int val, int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == val) {
                return i;
            }
        }
        return -1;
    }

}
