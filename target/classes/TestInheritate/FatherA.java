package TestInheritate;

/**
 * @author longxingjian <longxingjian@kuaishou.com>
 * Created on 2021-05-12
 */
public class FatherA {
    int a;

    public int test(){
        System.out.println("FatherA");
        return 10;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }
}
