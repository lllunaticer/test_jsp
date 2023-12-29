package StringTest;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2021-11-06
 */
public class MultlanguageTest {
    public static void main(String[] args) {
        String ss = "الشائع ال %s";
        System.out.println(String.format(ss, 100));
    }
}
