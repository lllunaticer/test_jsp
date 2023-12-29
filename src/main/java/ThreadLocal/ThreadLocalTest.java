package ThreadLocal;

import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.Uninterruptibles;

/**
 * @author longxingjian <longxingjian@kuaishou.com>
 * Created on 2021-04-16
 */
public class ThreadLocalTest {
    static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    static void print(String str) {
        //打印当前线程中本地内存中本地变量的值
        System.out.println(str + " :" + threadLocal.get());
        //清除本地内存中的本地变量
        //        threadLocal.remove();
    }

    public static void main(String[] args) {
        threadLocal.set("main thread value");
        Thread t1 = new Thread(
                () -> {
                    threadLocal.set("local value 1");
                    print("thread 1");
                    System.out.println("after remove : " + threadLocal.get());
                });

        Thread t2 = new Thread(
                () -> {
                    threadLocal.set("local value 2");
                    print("thread 2");
                    System.out.println("after remove : " + threadLocal.get());
                }
        );
        t1.start();
        t2.start();
        Uninterruptibles.sleepUninterruptibly(100, TimeUnit.MILLISECONDS);
        System.out.println(threadLocal.get());
    }
}
