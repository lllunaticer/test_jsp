package testArrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Xingjian LONG
 * Created on 2021-07-10
 */
public class testArray {
    public static void main(String[] args) {
        List<String> ss = new ArrayList<>();
        ss.add("pristl_1234");
        ss.add("pristl_1234_sub_1");
        ss.add("pristl_1234_sub_2");
        ss.add("pristl_1234_sub_3");

        String[] strings = ss.toArray(new String[5]);
        System.out.println(Arrays.toString(strings));
        System.out.println(strings.length);
        System.out.println(ss);
    }
}
