//5.Longest Palindromic Substring
//Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
//
//Example 1:
//
//Input: "babad"
//Output: "bab"
//Note: "aba" is also a valid answer.
//Example 2:
//
//Input: "cbbd"
//Output: "bb"
//本题又五种解法，值得好好解。
//本来想的是，把s逆转成s'，找到两者之间最长的公共子串，但是如果前后重复的公共子串不是回文
// S  =“abacdfgdcaba”,
// S’=“abacdgfdcaba”
//他们之间的最长公共子串为 “abacd”。显然，这不是回文。
//所以要判断一下 回文在s的索引和s'的索引是不是相同的
public class LongestPalindromicSubstring {
    public static void main(String[] args) {
        String s = "babad";
        String result = longestPalindrome2(s);
        System.out.println("result="+result);
    }
    //2.中心扩展法
    //每个字符为中心，往两边扩展并判断
    //当字符串个数是偶数的时候，中心点是两个重复字符的中间，为奇数的时候，就是中间的那个数
    //看讨论题中，有一种很简单的方法，将两种情况都考虑进去，extend（s,i,i）;extend(s,i,i+1)
    //但是考虑到偶数时，中间是重复的字符（eg:"aa"）,可以把j[j+1,k-1]k(表示回文范围)k++;其他左右扩展
    //同时，可以稍稍改进算法，将i的循环范围改到 s.length()- maxlength/2;
    private static String longestPalindrome2(String s) {
        if (s == null || s.equals("") ||s.length() == 1){
            return s;
        }
        int beginIndex = 0;
        int maxsize = 0;
        for (int i = 0; i <= s.length() - maxsize / 2; i++) {
            //从i 出发，[j+1,k-1] 表示回文范围
            int j = i;
            int k = i;
            //重复的字符直接往右边扩展
            while (k <s.length() -1 && s.charAt(k) == s.charAt(k+1)){
                k++;
            }
            //中心扩展
            while (j >= 0 && k < s.length() && s.charAt(j) == s.charAt(k)){
                j--;
                k++;
            }
            int newlength = k - j - 1;
            if (newlength > maxsize){
                beginIndex = j + 1 ;
                maxsize = newlength;
            }
        }
        if (maxsize == 0){
            return s.charAt(0)+"";
        }
        return s.substring(beginIndex,beginIndex + maxsize);
    }

    //1.暴力破解法，效率太低
    private static String longestPalindrome1(String s) {
        String ans = "";

        int maxPalindromiclength = 0;
        int newmaxPalindromiclength = maxPalindromiclength;

        if (s.length() == 1){
            return s;
        }
        for (int i = 0 ; i < s.length(); i++ ){
            for (int j = i; j < s.length(); j++) {
               if(isPalindromic(s,i,j)){
                   newmaxPalindromiclength = j-i;
                   if (newmaxPalindromiclength > maxPalindromiclength){
                       ans = s.substring(i,j+1);
                       maxPalindromiclength = newmaxPalindromiclength;
                   }
               }
            }
        }

        if (ans.equals("") && s != ""){
           ans = s.charAt(0) + "";
        }
        return ans;
    }

    private static boolean isPalindromic(String s, int i, int j) {
        String s1 = s.substring(i,j+1);
      //  System.out.println("s1 = "+ s1);
        String s2 = reverse(s1);
      //  System.out.println("s2 = "+ s2);
        if (s1.equals(s2)) {
            return true;
        }else {
            return false;
        }

    }

    private static String reverse(String s) {
        String s1 = "";
        for (int i = s.length() - 1; i>= 0; i--) {
            s1 += s.charAt(i) ;
        }
        return s1;
    }




}
