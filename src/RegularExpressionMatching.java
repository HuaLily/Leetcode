//10.Regular Expression Matching
//Given an input string (s) and a pattern (p),
// implement regular expression matching with support for '.' and '*'.
//'.' Matches any single character.
//'*' Matches zero or more of the preceding element.
public class RegularExpressionMatching {
    public static void main(String[] args) {
        String s = "aabb";
        String p = "aa.b*";
        boolean ret = isMatch2(s,p);
        System.out.println(ret);
    }


    //官方方法，第一种是递归方法(Recursion Approach)
    //如果没有“*”这个数字大小的通配符的话,可以直接用递归的方法
    //When a star is present, we may need to check many different suffixes
    // of the text and see if they match the rest of the pattern.
    // A recursive solution is a straightforward way to represent this relationship.
    //匹配的判断就是是不是.或者说是同个字符

    //If a star is present in the pattern, it will be in the second position
    //Then, we may ignore this part of the pattern,
    // or delete a matching character in the text.
    // If we have a match on the remaining strings after any of these operations,
    // then the initial inputs matched.
    public static boolean isMatch(String text, String pattern) {

        if (pattern.isEmpty())
        {
            //如果pattern是空的话，就看text是不是空
            return text.isEmpty();
        }

        //判断text不为空的情况，是不是相同字符，或者是'.'
        boolean first_match = (!text.isEmpty())&&
                (text.charAt(0) == pattern.charAt(0)||
                pattern.charAt(0) == '.');

        //Then, we may ignore this part of the pattern,
        // or delete a matching character in the text.
        // If we have a match on the remaining strings after any of these operations,
        // then the initial inputs matched.
        if (pattern.length() >= 2 && pattern.charAt(1) == '*'){  //当p的第二个字符是*
            return (isMatch(text,pattern.substring(2)) || //由于*可以表示之前的字符出现0次，所以去掉两个字符，递归判断后面的
                    (first_match && isMatch(text.substring(1),pattern)));//如果第一个字符和text第一个字符一样的情况
            //就删掉text中一致的字符，比较其他和pattern
        }else{
            //pattern只有一个字符或者pattern中没有*
            return first_match && isMatch(text.substring(1),pattern.substring(1));//返回第一个字符不一致直接false，
            //或者是第一个字符一致的情况，text和pattern都去掉这个字符之后，其他是不是一致
        }
    }

    //方法二：动态规划（备忘录法，将已经解决的问题的结果保留着待用，避免重复）
    //1.用dp[i][j] 来存放结果;表示 s中的i个字符(尤其注意这里i表示的个数)和 p中的j个字符（j表示的是字符个数）一致与否的结果
    //                       长度：[s.length + 1][p.length + 1 ]
    //                       默认都是false

    //2.初始化 (1)dp[0][0] = true 最开始dp[0][0]表示的是p和s都没有字符，则一致表示True：
    //         (2)dp[0][i+1] = dp[0][i-1] 当s没有字符时，p="a*"表示0个a字符,由于这种情况，遍历一下p，第i个为"*",
    //         表示要跳过两个取之前的备忘录中的值
    //        （3）dp[i][0](i != 0) = false  默认表示

    //3.迭代:遍历这个数组：
    //        (1)If ( p.charAt(j-1) == s.charAt(i-1) || p.charAt(j-1) == '.' ):  dp[i][j] = dp[i-1][j-1];
    //              i,j表示个数，在charAt()时候要-1
    //        (2)If ( p.charAt(j-1) == '*'):
    //               1   if p.charAt(j-2) != s.charAt(i-1) : dp[i][j] = dp[i][j-2]
    //                      in this case, a* only counts as empty
    //                    当*的前一个字符 不 和s中一致，那就把* 当作0
    //                     eg：s = "abc",p ="abd*"
    //               2   if p.charAt(j-2) == s.charAt(i-1) or p.charAt(j-2) == '.':  当 *号前一个字符和s一致，或者字符为"."
    //                      dp[i][j] = dp[i-1][j]    //in this case, a* counts as multiple a
    //                   or dp[i][j] = dp[i][j-1]   // in this case, a* counts as single a
    //                   or dp[i][j] = dp[i][j-2]   // in this case, a* counts as empty

    //4.返回 dp[s.length][p.length]
    private static boolean isMatch2(String s, String p) {
        //1.定义数组
        boolean dp[][] = new boolean[s.length()+1][p.length()+1];

        //2.初始化
        dp[0][0]= true;
        for (int i = 1; i < p.length(); i++) {
            if (p.charAt(i) == '*' && dp[0][i-1]){
                dp[0][i+1] = dp[0][i-1];
            }
        }
        //3.迭代
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                if (p.charAt(j-1) == s.charAt(i-1) || p.charAt(j-1) == '.'){
                    dp[i][j] = dp[i-1][j-1];
                }
                if (p.charAt(j-1) == '*'){
                    if (p.charAt(j-2) != s.charAt(i-1) && p.charAt(j-2) != '.') {
                        dp[i][j] = dp[i][j - 2];
                    }else{
                       dp[i][j] = dp[i-1][j] || dp[i][j-2] ||dp[i][j-1];
                   }
                }
            }

        }
        //4.返回结果
        return dp[s.length()][p.length()];

    }




}
