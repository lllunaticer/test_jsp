package TestInheritate;

/**
 * @author longxingjian <longxingjian@kuaishou.com>
 * Created on 2021-05-12
 */
public class Son extends FatherA implements InterfaceA {
    int a;
    int b =1;

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    @Override
    public int getA() {
        return a;
    }

    @Override
    public void setA(int a) {
        this.a = a;
    }
}
