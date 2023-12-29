import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2021-12-30
 */
public class TestThreadLocal {
    public static void main(String[] args) {
        int seed = 10;
        int l =(int) ThreadLocalRandom.current()
                .nextLong(seed/2, seed);
        System.out.println(l);
    }
}
