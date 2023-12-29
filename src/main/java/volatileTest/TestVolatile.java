package volatileTest;

import java.util.concurrent.TimeUnit;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2022-09-10
 */
public class TestVolatile {
    static volatile boolean stop = false;

    public static void main(String[] args) throws InterruptedException {

        new Thread("线程1") {
            @Override
            public void run() {
                while (!stop) {
                }
                System.out.println("线程停下来了");
            }
        }.start();
        TimeUnit.MILLISECONDS.sleep(200);
        stop = true;
        System.out.println("需要停下来 >>> " + stop);
    }

}

