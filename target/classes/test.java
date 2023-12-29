import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author longxingjian on 2020/10/28-9:41 下午
 */
public class test {

    public static void main(String[] args) throws Exception {
        byte[] bytes = "ID".getBytes(StandardCharsets.UTF_8);
        for (byte b : bytes) {
            System.out.println(b);
        }
        byte[] bytes1 = new byte[] {-17, -69, -65, 73, 68};
        byte[] bytes2 = new byte[] {-17, -69, -65};
        String s = new String(bytes, StandardCharsets.UTF_8);
        System.out.println(s);
        String s1 = new String(bytes1, StandardCharsets.UTF_8);
        String s2 = new String(bytes2, StandardCharsets.UTF_8);
        String yourstring = s1.replaceAll("\\p{C}", ".");
        System.out.println("yourstring #"+yourstring+"#");

        System.out.println("s1 + " + s1);
        System.out.println("s2 + " + "#"+s2+"#");
    }
}
