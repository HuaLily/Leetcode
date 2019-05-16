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
//}S=“abacdfgdcaba”, S' = \textrm{“abacdgfdcaba”}
//他们之间的最长公共子串为 “abacd”。显然，这不是回文。
public class LongestPalindromicSubstring {
    public static void main(String[] args) {
        String s = "babad";
        String result = longestPalindrome(s);
        System.out.println("result="+result);

    }

    private static String longestPalindrome(String s) {

        return null;
    }
}
