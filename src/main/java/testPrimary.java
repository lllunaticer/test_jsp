import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * @author longxingjian <longxingjian@kuaishou.com>
 * Created on 2021-01-21
 */
@Component
public class testPrimary {

    private int age;

    public static void main(String[] args) {
        Long i = 99999999999L;
        Object obj = i;
        Long t = 99999999999L;
        Object obj2 = t;
        Assert.isTrue(obj.equals(obj2), "fail");
    }

}
