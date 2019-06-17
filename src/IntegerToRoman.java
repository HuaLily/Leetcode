//Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
//Given an integer, convert it to a roman numeral. Input is guaranteed to be within the range from 1 to 3999.


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class IntegerToRoman {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int num = Integer.parseInt(line);

            String ret = new Solution().intToRoman(num);

            String out = (ret);

            System.out.print(out);
        }

    }
}

    //看到一个特别简单的答案，本来思考很多，但是其实很简单
    //对于整数来说，就是个十百千每位数字都分出来，分别找到对应的字符串
    //Runtime: 4 ms, faster than 71.86% of Java online submissions for Integer to Roman.
    //Memory Usage: 35.9 MB, less than 99.98% of Java online submissions for Integer to Roman.
    class Solution {
        public String intToRoman(int input) {
            String M[] = {"","M","MM","MMM"}; //千位
            String C[] = {"","C","CC","CCC","CD","D","DC","DCC","DCCC","CM"};//百位
            String X[] ={"","X","XX","XXX","XL","L","LX","LXX","LXXX","XC"}; //十位
            String I[] = {"","I","II","III","IV","V","VI","VII","VIII","IX"}; //个位
            return M[input/1000] + C[(input % 1000)/100] + X[(input % 100)/10] + I[input % 10];
        }
    }

