package DynamicProxyTest;

/**
 * Anything that can go wrong will go wrong
 *
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * @date 2023-02-24
 */
public interface Person {
    public void introduce(String name);

    public void sayAge(int age);

    public void sayWhereFrom(String city, String country);
}
