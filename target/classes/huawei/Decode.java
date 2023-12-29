package huawei;

import java.util.Scanner;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2022-09-18
 */
public class Decode {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String dstr = in.nextLine();
            int value = Integer.parseInt(dstr, 16);
            int len = dstr.length();
            if (len % 2 == 1 || len < 2 || len > 12) {
                System.out.println(-1);
                continue;
            }
            if (len == 2) {
                if ((value & (1 << 8)) != 0) {
                    System.out.println(-1);
                } else {
                    System.out.println(value);
                }
                continue;
            }
            
            System.out.println(value);
        }
    }
}
