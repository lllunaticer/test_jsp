package ALeetCode;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2022-01-16
 */
public class Contest0116 {
    public String[] divideString(String s, int k, char fill) {
        int cnt = (s.length()+k-1)/k;
        String[] res = new String[cnt];
        int start = 0;
        for(int i = 0; i<cnt; i++){
            int end = start + k;
            if(end < s.length()){
                res[i] = s.substring(start, end);
                start = end;
                continue;
            }else{
                StringBuilder tmp = new StringBuilder(s.substring(start));
                while (tmp.length() < k){
                    tmp.append(fill);
                }
                res[i] = tmp.toString();
            }
        }
        return res;
    }
}
