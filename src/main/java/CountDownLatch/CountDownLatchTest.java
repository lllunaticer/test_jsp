package CountDownLatch;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.Uninterruptibles;

/**
 * @author longxingjian <longxingjian@kuaishou.com>
 * Created on 2021-04-21
 */
public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        ArrayList<String> name = new ArrayList<>();
        name.add("longxingjian");
        name.add("litianpeng");
        name.add("likang");
        CountDownLatch count = new CountDownLatch(name.size());

        ExecutorService pool = Executors.newFixedThreadPool(10);
        long start = System.currentTimeMillis();
        for (String nn : name) {
            pool.submit(() -> {
                Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
                System.out.println(nn + "\n");
                count.countDown();
            });
        }
        count.await();
        long end = System.currentTimeMillis();
        System.out.println("Finally we run all process! cost " + (end - start) + " seconds");
    }
}
