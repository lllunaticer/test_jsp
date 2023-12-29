package testPrint;

import com.google.common.collect.Lists;

/**
 * @author longxingjian <longxingjian@kuaishou.com>
 * Created on 2021-05-19
 */
public class test {
    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int CAPACITY = (1 << COUNT_BITS) - 1;

    private static final int RUNNING = -1 << COUNT_BITS;
    private static final int SHUTDOWN = 0 << COUNT_BITS;
    private static final int STOP = 1 << COUNT_BITS;
    private static final int TIDYING = 2 << COUNT_BITS;
    private static final int TERMINATED = 3 << COUNT_BITS;

    public static void main(String[] args) {
        System.out.println("COUNT_BITS:" + Integer.toBinaryString(COUNT_BITS));
        System.out.println("CAPACITY:" + Integer.toBinaryString(CAPACITY));
        System.out.println("RUNNING:" + Integer.toBinaryString(RUNNING));
        System.out.println("SHUTDOWN:" + Integer.toBinaryString(SHUTDOWN));
        System.out.println("STOP:" + Integer.toBinaryString(STOP));
        System.out.println("TIDYING:" + Integer.toBinaryString(TIDYING));
        System.out.println("TERMINATED:" + Integer.toBinaryString(TERMINATED));
    }
}
