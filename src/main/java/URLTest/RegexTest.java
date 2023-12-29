package URLTest;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2022-08-29
 */
public class RegexTest {

    @Test
    public void regexTest() {
        String regex = "(?=/api/vdata.*/23/.*)(?!(/api/vdata/business/23/line.*))";
        Pattern r = Pattern.compile(regex);

        String[] test = new String[] {
                "/api/vdata/config/23/configKey/update",
                "/api/vdata/config/23/data/update",
                "/api/vdata/business/23/lineoff"
        };
        Arrays.stream(test)
                .forEach(t -> {
                            Matcher m = r.matcher(t);
                            System.out.println("test - " + t + " matchers -" + m.find());
                            m.matches();
                        }
                );
    }

    @Test
    public void testRegex() {
        String pattern = "my dog is (?=(/yellow))(?!(red))\\w+";
        Pattern r = Pattern.compile(pattern);
        System.out.println(r.matcher("my dog is /yellow").matches());

        String pattern2 = "my dog is (?=(yellow|blue))(?!(red))\\w+";
        Pattern r2 = Pattern.compile(pattern2);
        System.out.println(r2.matcher("my dog is yellow").matches());

        Pattern pattern3 = Pattern.compile("/23243/");
        Matcher matcher = pattern3.matcher("/23243/");
        if (matcher.find()) {
            System.out.println("Does match!");
        } else {
            System.out.println("Does not match!");
        }

        String p4 = "(?=(apivdata.*23))\\w*";
        Pattern r4 = Pattern.compile(p4);
        System.out.println(r4.matcher("apivdataconfig23configKeyupdate").matches());
    }
}
