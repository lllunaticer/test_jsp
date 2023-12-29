package ALeetCode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2021-12-12
 */
public class Contest1212_1 {
    public int countPoints(String rings) {
        Map<Integer, Set<Character>> record = new HashMap<>();
        for(int i = 0; i<10; i++){
            record.put(i, new HashSet<>());
        }

        for(int i =0; i<rings.length(); i = i+2){
            record.get(((int) rings.charAt(i + 1))-'0').add(rings.charAt(i));
        }

        int res = 0;
        for(int i = 0; i< 10; i++){
            if(record.get(i).size()>=3){
                res++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Contest1212_1 contest1212_1 = new Contest1212_1();
        contest1212_1.countPoints("B0B6G0R6R0R6G9");
    }
}
