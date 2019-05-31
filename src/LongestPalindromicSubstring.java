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
        String s = "abccba";
        String result = longestPalindrome4(s);
        System.out.println("result="+result);
    }

    //4.Manacher algorithm
    private static String longestPalindrome4(String s) {
        //1.对数据进行处理,解决下奇数和偶数的问题，在每个字符间插入”#”，
        // 并且为了使得扩展的过程中，到边界后自动结束，在两端分别插入 “^” 和 “$”，
        // 两个不可能在字符串中出现的字符，这样像中心扩展法的时候，
        // 判断两端字符是否相等，如果到了边界就一定会不相等，从而出了循环。经过处理，字符串的长度永远都是奇数了。
        int n = s.length();
        if (n == 0 || n == 1) {
            return s;
        }
        String s1 = "^";
        for (int i = 0; i < n; i++)
            s1 += "#" + s.charAt(i);
        s1 += "#$";
        System.out.println("s1 = "+ s1);

        int[] p = new int[s1.length()];
        int c = 0;
        int r = 0;
        int i_mirror = 0;

        for (int i = 1; i < s1.length() - 1; i++) {
            i_mirror = 2 * c - i;
            if (r > i){
                p[i] = Math.min(r-i,p[i_mirror]);
            }else {
                //i == r
                p[i] = 0;
            }
            //扩展i
            System.out.println("i = "+i );
            System.out.println("p[1] ="+p[i]);
            while (s1.charAt(i - p[i] - 1) == s1.charAt(i+p[i]+1)){
                p[i] += 1;
            }

            //更新c和r
            if (i+ p[i] > r){
                r = i + p [i];
                c = i;
            }
        }
        //找出最大的P[i]
        int maxlength = 0;
        int centerIndex = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (p[i] > maxlength){
                maxlength = p[i];
                centerIndex = i;
            }
        }
       int start = (centerIndex - p[centerIndex])/2;
       return s.substring(start,start + maxlength);

    }

    //3.动态规划法
    //p(i,j) = true 表示s(i....j)是回文的
    //p(i,j) = p(i+1,j-1) and s[i] == s[j]
    //base cases = p(i,i) = true ,p(i,i+1)=(s[i]==s[i+1])
    private static String longestPalindrome3(String s) {
        int n = s.length();
        boolean[][] p = new boolean[n][n];
        int maxlenth = 0;
        int beginIndex = 0;

        for (int i = n; i >= 0  ; i--) {
            //刚开始这边是从0到n，但是因为更新的时候是p(i,j) = p(i+1,j-1)，往内计算，要先算外围，从n到0计算
            for (int j = i; j < n ; j++) {
                //base cases
                if (i == j ){
                    p[i][j] = true;
                }else if (j == i + 1 || j == i + 2) {
                    p[i][j] = s.charAt(i) == s.charAt(j);
                }else {
                    p[i][j] = s.charAt(i) == s.charAt(j) && p[i+1][j-1];
                }
                if (p[i][j] && j - i + 1 >= maxlenth){
                    //j - i + 1 >= maxlenth 必须是 >= ，以便更新不到最开头 eg:babad
                    maxlenth = j - i + 1;
                    beginIndex = i;
                    System.out.println("i = "+ i);
                    System.out.println("j = "+ j);
                    System.out.println("maxlength= "+maxlenth);
                }
            }
        }
        // System.out.println("beginIndex = "+ beginIndex);

        //把二维数组打印出来看看
        for (int i = 0; i < n ; i++) {
            for (int j = 0; j < n ; j++) {
                System.out.print(p[i][j] + " ");
            }
            System.out.println();
        }


        return s.substring(beginIndex,beginIndex + maxlenth);
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
