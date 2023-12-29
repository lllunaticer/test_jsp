package enumTest;

import org.junit.Test;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2022-04-13
 */
public class Testclass {
    @Test
    public void testEnum() {
        Book good = Book.of("GOOD");
        System.out.println(good);
    }
}
