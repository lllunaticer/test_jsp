import java.util.List;
import java.util.Map;

/**
 * @author longxingjian <longxingjian@kuaishou.com>
 * Created on 2021-01-20
 */
public class TestGetter {
    public static void main(String[] args) {
        IndexGetter getter = new IndexGetterImpl();
        List<Map<String, Integer>> mapList = getter.getIndex("mapList");
        mapList.forEach(
                System.out::println
        );

        System.out.println("********************");

        List<Long>  longList = getter.getIndex("longList");
        longList.forEach(System.out::println);

        System.out.println("********************");

    }
}
