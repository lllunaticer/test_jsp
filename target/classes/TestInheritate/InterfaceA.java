package TestInheritate;

/**
 * @author longxingjian <longxingjian@kuaishou.com>
 * Created on 2021-05-12
 */
public interface InterfaceA {

    default int test() {
        System.out.println("Interface A");
        return 10;
    }

    void setA(int a);
}
