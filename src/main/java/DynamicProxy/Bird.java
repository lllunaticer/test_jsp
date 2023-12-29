package DynamicProxy;

import java.util.Random;

/**
 * @author longxingjian <longxingjian@kuaishou.com>
 * Created on 2021-04-20
 */
public class Bird implements Flyable{
    @Override
    public void fly() {
        System.out.println("Bird is flying...");
        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
