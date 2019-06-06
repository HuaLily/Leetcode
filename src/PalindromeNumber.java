import sun.java2d.pipe.SpanIterator;

//9. Palindrome Number
//Determine whether an integer is a palindrome. An integer is a palindrome when it reads the same backward as forward.
public class PalindromeNumber {
    public static void main(String[] args) {
        int x  = 21120;
        boolean ret = isPalindrome2(x);
        System.out.println(ret);
    }

    //最简单的想法，用字符串
    private static boolean isPalindrome(int x) {
        String s1 = Integer.toString(x);
        //System.out.println("s1 = "+ s1);
        StringBuilder s2 = new StringBuilder();
        for (int i = s1.length() - 1; i >= 0; i--) {
            s2.append(s1.charAt(i));
        }
       //System.out.println("s2 = "+ s2.toString());
        if (s1.equals(s2.toString())){
            return true;
        }
        return false;
    }

    //不用字符串的话，首先想到翻转整数，但是会有超过Integer.Max的可能性
    //考虑只反转一半，把整数分为两个部分，从末尾开始往前加，左右相等的情况就是回文，这样子分开的整数就不会超过最大值
    private static boolean isPalindrome2(int x) {
        //负数不可能是回文 ,这里考虑到尾巴有0的话，除非是0，不然不可能是回文的
        if (x < 0 || x % 10 == 0 && x != 0 ){
            return false;
        }
        //一位数是回文
        if (x < 10){
            return true;
        }
        int tail = 0;
        int y = 0;


        while (x > y ){

            tail = x % 10;
            //System.out.println("tail = "+tail);

            y = y * 10 + tail;
           // System.out.println("y = "+ y);

            if (x == y){  //这里主要是因为会有121这种奇数个数的情况，在x少一位之前，提前判断
                return true;
            }

            x = x / 10;
           // System.out.println("x = "+ x);

        }

        if (x == y){
            return true;
        }
        return false;
    }


}
