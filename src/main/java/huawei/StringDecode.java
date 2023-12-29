package huawei;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2022-09-12
 */
public class StringDecode {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] >= 'a' && chars[i] <= 'z') {
                chars[i] -= 'a' - 'A';
                if (chars[i] == 'Z') {
                    chars[i] = 'A';
                } else {
                    chars[i] += 1;
                }
            } else if (chars[i] >= 'A' && chars[i] <= 'Z') {
                chars[i] += 'a' - 'A';
                if (chars[i] == 'a') {
                    chars[i] = 'Z';
                } else {
                    chars[i] -= 1;
                }
            } else if (chars[i] == '9') {
                chars[i] = '0';
            } else {
                chars[i] += 1;
            }
        }
        String result = new String(chars);
        System.out.println(result);
    }

    @Test
    public void testInt() {
        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
        System.out.println(set);
        System.out.println(set.stream()
                .sorted(Comparator.comparingInt(Integer::intValue).reversed())
                .collect(Collectors.toList()));
    }
}