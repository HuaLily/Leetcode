//6.ZigZag Conversion
//The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:
// (you may want to display this pattern in a fixed font for better legibility)
//P   A   H   N
//A P L S I I G
//Y   I   R

//example1:Input: s = "PAYPALISHIRING", numRows = 3
//Output: "PAHNAPLSIIGYIR"

//example2:Input: s = "PAYPALISHIRING", numRows = 4
//Output: "PINALSIGYAHRPI"
//Explanation:
//
//P     I    N
//A   L S  I G
//Y A   H R
//P     I



import java.util.ArrayList;
import java.util.List;

public class ZigZagConversion {
    public static void main(String[] args){
       String s = "PAYPALISHIRING";
       int numRows = 4;
       String ret = convert2(s,numRows);
       System.out.println(ret);
    }


    private static String convert2(String s, int numRows) {
        //official approach 2: visit by row
        //Intuition: Visit the characters in the same order as reading the Zig-Zag pattern line by line.
        //Algorithm: Visit all characters in row 0 first, then row 1, then row 2, and so on...
        //For all whole numbers k,(k是自动+1)
        //Characters in row 0 are located at indexes k(2⋅numRows−2)
        //Characters in row numRows−1 are located at indexes k(2⋅numRows−2)+numRows−1
        //Characters in inner row i are located at indexes k(2⋅numRows−2)+i and (k+1)(2⋅numRows−2)−i.



        if (numRows == 1) return s;

        StringBuilder ret = new StringBuilder();
        int n = s.length();
        //两个竖排之间的差是(2* nums - 2)
        int cyclelen = 2 * numRows - 2;

        for (int i = 0; i < numRows; i++) { //一行一行访问
            for (int j = 0; j + i < n ; j += cyclelen ) { //当最后的时候，j+i = 1
                ret.append(s.charAt(j+i));//第一行和最后一行的公式
                if (i != 0 && i != numRows -1 && j + cyclelen - i < n){//当中间的行数
                    ret.append(s.charAt(j + cyclelen - i));
                }
            }
        }



        return ret.toString();
    }

    //official approach 1: sort by row
    //By iterating through the string from left to right, we can easily determine which row in the Zig-Zag pattern that a character belongs to.
    //
    //We can use min(numRows,len(s)) lists to represent the non-empty rows of the Zig-Zag Pattern.
    //Iterate through ss from left to right, appending each character to the appropriate row.
    // The appropriate row can be tracked using two variables: the current row and the current direction.
    //The current direction changes only when we moved up to the topmost row or moved down to the bottommost row.

    private static String convert1(String s, int numRows) {
        if (numRows == 1) return s;
        List<StringBuilder> rows = new ArrayList<>();//每行都是用StringBuilder创建
        for (int i = 0; i < Math.min(s.length(), numRows); i++) {
            //Math.min(s.length(), numRows) 表示不是空的行的个数
            rows.add(new StringBuilder());//每一行都是一个StringBuilder
        }

        //找到对应的行关键在于  现在的行 和  继续的方向
        int currow = 0;
        boolean goingdown = false;

        //把s从左到右遍历一遍，把每个字符加到对应的行上去
        for (char c : s.toCharArray()){

            rows.get(currow).append(c); //对应的currow行上的StringBuilder加上字符

            //改变方向的情况
            if (currow == 0 || currow == numRows - 1){
                goingdown = !goingdown;
            }

            currow += goingdown? 1: -1;
        }

        //串起最终的字符
        StringBuilder ret = new StringBuilder();
        for (StringBuilder row : rows){
            ret.append(row);
        }
        return ret.toString();
    }
}
