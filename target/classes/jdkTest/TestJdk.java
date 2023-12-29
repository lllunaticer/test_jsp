package jdkTest;

import java.util.ArrayList;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2021-12-27
 */
public class TestJdk {
    public static void main(String[] args) {
        // User中加入成员变量，观察它的内存布局，此时会看到实例数据部分的内容
        User obj1 = new User(1);
        String layout1 = ClassLayout.parseInstance(obj1).toPrintable();
        System.out.println(layout1);

        System.out.println("++++++++++++++++++++++++++++++");
        // User数组对象，观察它的内存布局，此时会看到数组数据部分的内容
        // 数组数据所占字节数 = 数组长度 * 4；下例中长度为：5 * 4 = 20字节
//        User[] obj2 = new User[5];
//        obj2[0] = new User(1);
//        obj2[1] = new User(1);
//        obj2[2] = new User(1);
//        obj2[3] = new User(1);
//        obj2[4] = new User(1);
//        String layout2 = ClassLayout.parseInstance(obj2).toPrintable();
//        System.out.println(layout2);
    }
}
