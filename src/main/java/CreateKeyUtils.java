import java.util.Random;

/**
 * @author longxingjian <longxingjian@kuaishou.com>
 * Created on 2020-12-03
 */
public class CreateKeyUtils {

    private static final String strTable = "23456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnpqrstuvwxyz!@#$!@#$";
    private static final Random random = new Random();

    public static void main(String[] args) {
        System.out.println(getPassword());
    }

    public static String getPassword() {
        int passwordLen = random.nextInt(12) % (5) + 8;
        System.out.println(passwordLen);
        return getRandKeys(passwordLen);
    }

    // 生成指定长度的密码
    private static String getRandKeys(int intLength) {

        String retStr;      //生成的密码

        int len = strTable.length();
        boolean bDone = false;  //生成结束标志
        do {
            retStr = "";
            int count = 0;      //生成密码中数字的个数
            int count1 = 0;     //生成密码中字母的个数
            int count2 = 0;     //生成密码中符号的个数

            for (int i = 0; i < intLength; i++) {
                int intR = (int) Math.floor(Math.random() * len);
                char c = strTable.charAt(intR);   //找到指定字符

                //判断字符类型并计数：数字，字母，符号
                if (('0' <= c) && (c <= '9')) {
                    count++;
                } else if (('A' <= c) && (c <= 'z')) {
                    count1++;
                } else {
                    count2++;
                }
                retStr += strTable.charAt(intR);
            }
            if (count >= 1 && count1 >= 4 && count2 >= 1) {
                //            if (count >= 1 && count1 >= 4) {
                //如果符号密码强度，则置结束标志：密码至少包含1个数字，4个字母，1个符号
                bDone = true;
            }
        } while (!bDone);

        return retStr;
    }
}