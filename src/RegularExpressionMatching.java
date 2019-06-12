//10.Regular Expression Matching
//Given an input string (s) and a pattern (p),
// implement regular expression matching with support for '.' and '*'.
//'.' Matches any single character.
//'*' Matches zero or more of the preceding element.
public class RegularExpressionMatching {
    public static void main(String[] args) {
        String s = "aabb";
        String p = "aa.b*";
        boolean ret = isMatch(s,p);
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
        if (pattern.length() >= 2 && pattern.charAt(1) == '*'){
            return (isMatch(text,pattern.substring(2)) ||
                    (first_match && isMatch(text.substring(1),pattern)));
        }else{
            return first_match && isMatch(text.substring(1),pattern.substring(1));
        }

    }




}
