//17. Letter Combinations of a Phone Number
//Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
//A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

//Example:
//Input: "23"
//Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
public class LetterCombinationsofaPhoneNumber_17 {
    public static void main(String[] args) {
        String digits = "23";
        List<String> ret = letterCombinations(digits);
        System.out.println(ret);
    }

    //果然我还是太菜，这题想不出来
    // 第一种解法也就是是官方答案，利用递归
    private static List<String> letterCombinations(String digits) {
        String[] abc = {"a","b","c"};//2
        String[] def = {"d","e","f"};//3
        String[] ghi = {"g","h","i"};//4
        String[] jkl = {"j","k","l"};//5
        String[] mno = {"m","n","o"};//6
        String[] pqrs = {"p","q","r","s","t"};//7
        String[] tuv = {"t","u","v"};//8
        String[] wxyz = {"w","x","y","z"};//9

        List ret = new ArrayList<String>();

        if (digits == null || digits.length() == 0) return ret;



        return null;
    }
}
