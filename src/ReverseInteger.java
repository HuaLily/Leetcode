//7.Given a 32-bit signed integer, reverse digits of an integer.
//Example 1:
//Input: 123
//Output: 321

//Example 2:
//Input: -123
//Output: -321

//Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range:
// [−231,  231 − 1].
// For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.

//Example 3:
//Input: 120
//Output: 21
public class ReverseInteger {
    public static void main(String[] args){
        int x = 0;
        int ret;
        ret = Reverse2(x);
        System.out.println(ret);
    }



    //看的讨论区，最简单的一个方法
    //If overflow exists, the new result will not equal previous one.
    //No flags needed. No hard code like 0xf7777777 needed.
    private static int Reverse(int x) {
        int ret = 0;
        while (x != 0){
            int tail = x % 10;
            int newret = ret * 10 + tail;
            //巧妙的地方
            if ((newret - tail)/10 != ret){
                return 0;
            }
            ret = newret;
            x = x / 10;
        }

        return ret;
    }

    //official solution
    //Pop and Push Digits & Check before Overflow
    ////pop operation:
    //pop = x % 10;
    //x /= 10;
    //
    ////push operation:
    //temp = rev * 10 + pop;
    //rev = temp;

    private static int Reverse2(int x) {
        int rev = 0;
        while (x != 0){
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE /10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)){
                return 0;
            }
            if (rev < Integer.MIN_VALUE /10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)){
                return 0;

            }
            rev = rev * 10 + pop;
        }
        return rev;
    }




}
