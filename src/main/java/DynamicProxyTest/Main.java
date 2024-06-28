package DynamicProxyTest;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
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

        Person proxyMan = (Person) Proxy.newProxyInstance(Person.class.getClassLoader(),
                new Class[] {Person.class},
                new PersonInvocationHandler(man));

        proxyMan.introduce(man.getName());
        test();
    }

    public static void test() {
        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println(method);
                if (method.getName().equals("introduce")) {
                    System.out.println("Good morning, " + args[0]);
                }
                return null;
            }
        };
        Person person = (Person) Proxy.newProxyInstance(
                Person.class.getClassLoader(), // 传入ClassLoader
                new Class[] {Person.class}, // 传入要实现的接口
                handler); // 传入处理调用方法的InvocationHandler
        person.introduce("Bob");
    }
}
