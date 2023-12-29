package TestInheritate;

import com.kuaishou.framework.util.ObjectMapperUtils;

/**
 * @author longxingjian <longxingjian@kuaishou.com>
 * Created on 2021-05-12
 */
public class TestInheritate {
    public static void main(String[] args) {
        Son son = new Son();
        FatherA son2 = new Son();
        InterfaceA son3 = new Son();

        son.setA(20);
        son2.setA(30);
        son3.setA(40);

        if(son2 instanceof  InterfaceA){
            ((InterfaceA) son2).setA(50);
        }

        System.out.println(ObjectMapperUtils.toJSON(son));
        System.out.println(ObjectMapperUtils.toJSON(son2));
        System.out.println(ObjectMapperUtils.toJSON(son3));
    }
}
