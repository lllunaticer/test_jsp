/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2022-04-27
 */
import org.apache.commons.lang3.StringUtils;
public class GradientAwardConfig {

    private static final int RADIX = 36;
    private static final int ID_LENGTH = 13;
    private static final String DEFAULT = "0";

    public static void main(String[] args) {
        long userId = 150000498472280L;
        System.out.println(StringUtils.leftPad(Long.toString(userId, RADIX), ID_LENGTH, DEFAULT));
    }
}
