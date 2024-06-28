package BitTest;

import org.junit.Test;

public class BitTest {
    /**
     * 获取固定位置状态
     *
     * @param s switch开关
     * @param b 位置 从0开始
     */
    public static boolean getBooleanForSwitch(int s, int b) {
        return getSwitch(s, b) > 0;
    }

    private static int getSwitch(int s, int b) {
        return s & 1 << b;
    }

    /**
     * 设置固定状态后的值
     *
     * @param s switch开关
     * @param b 位置 从0开始
     * @param v 具体值
     */
    public static int setSwitch(int s, int b, boolean v) {
        if (v) {
            return s | 1 << b;
        } else {
            return s & ~(1 << b);
        }
    }

    @Test
    public void testBit() {
        System.out.println(Integer.toBinaryString(0));
        System.out.println(Integer.toBinaryString(31 & 1 << 3));

        System.out.println(Integer.toBinaryString(31));
        System.out.println(getBooleanForSwitch(31, 1));
        int result = setSwitch(31, 1, false);
        System.out.println(Integer.toBinaryString(result));
        System.out.println(getBooleanForSwitch(result, 1));
        result = setSwitch(result, 0, false);
        result = setSwitch(result, 1, true);
        System.out.println(getBooleanForSwitch(result, 0));
        System.out.println(getSwitch(result, 1));
    }
}
