package ALeetCode;

import org.junit.Test;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2022-02-02
 */
public class LeetCode125 {
    public boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length()-1;
        while(right>left){
            while(!isValid(s.charAt(left))){
                left++;
                if(left >= s.length()){
                    return true;
                }
            }
            while(!isValid(s.charAt(right))){
                right--;
            }
            if(!isEqual(s.charAt(left), s.charAt(right))){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    private boolean isValid(char c){
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c<= 'Z') || (c >= '0' && c <= '9');
    }

    private boolean isEqual(char a, char b){
        return a == b || Math.abs(a-b) == 'a'-'A';
    }

    public String breakPalindrome(String palindrome) {
        //尽量用字母表顺序前面的字符从前往后尝试替换每个字符串中的字符
        //特殊情况是奇数正中心的字符串无论怎么替换都还是回文，这个位置需要跳过
        int i = 0;
        int len = palindrome.length();
        while(i < len){
            if(2 * i -1 == len){
                i++;
                continue;
            }
            if(palindrome.charAt(i) == 'a'){
                if(i == len - 1){
                    return palindrome.substring(len-1) + 'b';
                }
                i++;
                continue;
            }
            StringBuilder sb = new StringBuilder(palindrome);
            sb.replace(i, i+1, "a");
            return sb.toString();
        }
        return "";
    }


    @Test
    public void testString(){
        breakPalindrome("aba");
    }
}
