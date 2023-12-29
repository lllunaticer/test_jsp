package DynamicProxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author longxingjian <longxingjian@kuaishou.com>
 * Created on 2021-04-20
 */
public class MyInvocationHandler implements InvocationHandler {
    private Bird bird;

    public MyInvocationHandler(Bird bird) {
        this.bird = bird;
    }

    @Override
    public void invoke(Object proxy, Method method, Object[] args) {
        long start = System.currentTimeMillis();
        System.out.println("执行💰");

        try {
            method.invoke(bird, new Object[] {});
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println("执行🐒");
        long end = System.currentTimeMillis();
        System.out.println("Fly time = " + (end - start));
    }
}

