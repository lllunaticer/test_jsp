package classVerify;

/**
 * Anything that can go wrong will go wrong
 *
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * @date 2023-12-11
 */
public class SonClass extends FatherClass implements FatherInterface{
    public static void main(String[] args) {
        System.out.println();
    }

    public SonClass(){
        System.out.println(super.a);
    }
}
