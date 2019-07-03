//17. Letter Combinations of a Phone Number
//Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
//A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.

import java.util.*;

//Example:
//Input: "23"
//Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
public class LetterCombinationsofaPhoneNumber_17 {

    private static List<String> ret = new ArrayList<>();
    private static Map<String, String> phone = new HashMap<String, String>(){{
        put("2", "abc");
        put("3", "def");
        put("4", "ghi");
        put("5", "jkl");
        put("6", "mno");
        put("7", "pqrs");
        put("8", "tuv");
        put("9", "wxyz");
    }};

    public static void main(String[] args) {
        String digits = "";
        List<String> ret = letterCombinations2(digits);
        System.out.println(ret);
    }

    //FIFO queue
    //关键在于 （1）字符个数对应几个数字
    // （2）移出之前的值，并循环加上对应的字符
    private static List<String> letterCombinations2(String digits) {
        LinkedList<String> ans = new LinkedList<String>();
        if(digits.isEmpty()) return ans;
        String[] mapping = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        ans.add(""); //先在队列中放入头节点
        for(int i =0; i < digits.length();i++){  //遍历整个数字
            int x = Character.getNumericValue(digits.charAt(i)); //得到每个数字
            while(ans.peek().length()==i){     //队列弹出的字符个数和第i相等
                String t = ans.remove(); //移除头节点
                for(char s : mapping[x].toCharArray()) //遍历数字对应的map
                    ans.add(t+s);
            }
        }
        return ans;
    }


    //果然我还是太菜，这题想不出来
    // 第一种解法也就是是官方答案，利用回溯法
    //什么是回溯？
    //Backtracking is an algorithm for finding all solutions by exploring all potential candidates.
    // If the solution candidate turns to be not a solution (or at least not the last one),
    // backtracking algorithm discards it by making some changes on the previous step, i.e. backtracks and then try again.
    //列出所有可能的解状态空间树，并用约束函数减去不含答案的子树
    //这题主要还是列出所有的结果
    //Runtime: 1 ms, faster than 67.32% of Java online submissions for Letter Combinations of a Phone Number.
    //Memory Usage: 36.1 MB, less than 99.17% of Java online submissions for Letter Combinations of a Phone Number.
    private static List<String> letterCombinations(String digits) {
        phone.put("2","abc");
        phone.put("3", "def");
        phone.put("4", "ghi");
        phone.put("5", "jkl");
        phone.put("6", "mno");
        phone.put("7", "pqrs");
        phone.put("8", "tuv");
        phone.put("9", "wxyz");

        if (digits.length() != 0){
            backtrack("",digits);
        }
        return ret;
    }

    //列出所有的情况
    private static void backtrack(String combination, String next_digits) {
        // if there is no more digits to check
        if (next_digits.length() == 0){
            ret.add(combination);
        }

        else {  // if there are still digits to check
            //拿出digits的第一个数字所对应的字母

            // iterate over all letters which map
            // the next available digit
            String digit = next_digits.substring(0, 1);
            String letters = phone.get(digit);

            for (int i = 0; i < letters.length(); i++) {
                String letter = letters.substring(i, i + 1);
                // append the current letter to the combination
                // and proceed to the next digits
                backtrack(combination + letter, next_digits.substring(1));

            }
        }






    }
}
