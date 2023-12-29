package ThreadLocal;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author longxingjian <longxingjian@kuaishou.com>
 * Created on 2021-04-16
 */
public class ThreadId {
    private static final AtomicInteger nextId = new AtomicInteger(0);

    private static final ThreadLocal<Integer> threadId = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return nextId.getAndIncrement();
        }
    };

    public static int getId() {
        return threadId.get();
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(
                () -> {
                    System.out.println("first: " + Thread.currentThread().getName() + ", id is " + getId());
                    nextId.getAndIncrement();
                });

        Thread t2 = new Thread(
                () -> {
                    System.out.println("first: " + Thread.currentThread().getName() + ", id is " + getId());
                    nextId.getAndIncrement();
                });

        t1.start();
        t2.start();
    }
}
