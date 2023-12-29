package DynamicProxyTest;


import java.lang.reflect.Proxy;

/**
 * Anything that can go wrong will go wrong
 *
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * @date 2023-02-24
 */
public class Main {
    public static void main(String[] args) {
        Man man = new Man("Arnold", 30, "Thal", "China");

        ClassLoader cl = man.getClass().getClassLoader();
        Class<?>[] interfaces = man.getClass().getInterfaces();

        Person proxyMan = (Person) Proxy.newProxyInstance(cl, interfaces, new PersonInvocationHandler(man));

        proxyMan.introduce(man.getName());
    }
}
