//14. Longest Common Prefix
//Write a function to find the longest common prefix string amongst an array of strings.
//If there is no common prefix, return an empty string "".

//Example 1:
//Input: ["flower","flow","flight"]
//Output: "fl"
//Example 2:
//
//Input: ["dog","racecar","car"]
//Output: ""
//Explanation: There is no common prefix among the input strings.

//Note:
//
//All given inputs are in lowercase letters a-z.

import java.time.OffsetDateTime;

public class LongestCommonPrefix {
    public static void main(String[] args) {
        String[] input = {};
        String ret = LCP(input);
        System.out.println(ret);
    }

    //自己写的，直接比较前两个，把前面比较得出的结果 和 后一个比较
    //Runtime: 1 ms, faster than 78.08% of Java online submissions for Longest Common Prefix.
    //Memory Usage: 37.2 MB, less than 99.16% of Java online submissions for Longest Common Prefix.
    private static String LCP(String[] input) {
        int length = input.length;
        if ( length == 0 ){
            return "";
        }

        if ( length == 1 ){
            return input[0];
        }
        String ret = lcp(input[0],input[1]);
        for (int i = 2; i < length ; i++) {
            ret = lcp(ret,input[i]);
        }
        return ret;
    }

    //针对两个字符串的比较
    private static String lcp(String s1, String s2){
        //保证s1的长度小于s2
        if (s1.length() > s2.length()){
            String s3 = s1;
            s1 = s2;
            s2 = s3;
        }
        StringBuilder stringBuilder = new StringBuilder("");
        char[] chars1 = s1.toCharArray();

        if (s1.length() == 0 || s2.length() == 0){
            return "";
        }

        for (int i = 0; i < chars1.length ; i++) {
            if (chars1[i] == s2.charAt(i)){
                stringBuilder.append(chars1[i]);
            }else {
                break;
            }
        }
        return stringBuilder.toString();
    }


}
