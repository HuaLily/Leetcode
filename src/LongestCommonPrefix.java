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
        String ret = LCP2(input);
        System.out.println(ret);

    }

    //官方解答的第二种 Vertical scanning 垂直扫描
    //关键在于 最长的前缀一定以第一个字符串为基础，找到其他字符串和第一个字符串比较之后，所一致的位序，截取第一个字符串
    //比较每列字符
    //   i
    //j  f l o w e r
    //   f l o w
    //   f l i g h t
    private static String LCP2(String[] input) {
        if (input == null || input.length == 0) return "";
        for (int i = 0; i < input[0].length() ; i++) { //遍历第一行的字符
            char c = input[0].charAt(i);              //取出字符
            for (int j = 1; j < input.length; j++) {  //比较之后的字符
                if (i == input[j].length() || c != input[j].charAt(i) ){
                    return input[0].substring(0,i);
                    // 当遍历超过字符串的长度 或者不一致时 停止
                }

            }
        }
        return input[0];
    }


    //看了官方答案，我自己做的思想第一种就是横向扫描
    //但是官方的写法更加巧妙 Horizontal scanning
    private static String LCP1(String[] input) {
        if (input.length == 0) return "";
        String prefix = input[0]; //把第一个字符串默认为最长前缀
        for (int i = 1; i < input.length; i++)   //针对之后的每个字符串
            while (input[i].indexOf(prefix) != 0) {  //如果之后的字符串和前缀不一致 ，前缀就从后往前少一位，直到找到一致的前缀
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return ""; //前缀为空
            }
        return prefix;
    }

    //1.自己写的，直接比较前两个，把前面比较得出的结果 和 后一个比较

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
