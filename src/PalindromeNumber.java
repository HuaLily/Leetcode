//9. Palindrome Number
//Determine whether an integer is a palindrome. An integer is a palindrome when it reads the same backward as forward.
public class PalindromeNumber {
    public static void main(String[] args) {
        int x  = -121;
        boolean ret = isPalindrome(x);
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

    //不用字符串的话

}
