import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;

import ALeetCode.leecode336.Student;

/**
 * @author longxingjian <longxingjian@kuaishou.com>
 * Created on 2021-04-28
 */
public class testRegx {
    //    public static void main(String[] args) {
    //        String regex = "bizName=(.*?)[&]?.*";
    //        String content = "/v3/oversea/api/block/common/form/getMineTreasure?bizName=oversea-snack
    //        -bucketDropdownComponent";
    //        Pattern p = Pattern.compile(regex);
    //        Matcher m = p.matcher(content);
    //        while (m.find())
    //        {
    ////            System.out.println(m.group(1));
    //        }
    //
    //        System.out.println("xxx".indexOf('_'));
    //    }

//    public static void main(String[] args) {
//
//        ObjectMapper oMapper = new ObjectMapper();
//
//        Student obj = new Student("", 0, "", true);
//        obj.setName("mkyong");
//        obj.setAge(34);
//
//        // object -> Map
//        Map map = oMapper.convertValue(obj, Map.class)
//                .entrySet()
//                .stream()
//                .map(e->(Entry<String, Object>) e)
//                .collect(Collectors.toMap(e->String.valueOf(e.getKey()), e->String.valueOf(e.getValue())));
//
//        System.out.println(map);
//
//    }
}
