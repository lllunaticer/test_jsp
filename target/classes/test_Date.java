import java.text.SimpleDateFormat;

/**
 * @author longxingjian <longxingjian@kuaishou.com>
 * Created on 2021-01-14
 */
public class test_Date {
    public static final String MINUTE_DATE_FORMAT = "yyyy-MM-dd HH:mm";

    public static void main(String[] args) {
        System.out.println(parseStr2DateMill(""));
        System.out.println(parseStr2DateMill("0"));
    }

    public static long parseStr2DateMill(String dateString) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(MINUTE_DATE_FORMAT);
            return dateFormat.parse(dateString).getTime();
        } catch (Exception e) {
            return 0;
        }
    }
}
