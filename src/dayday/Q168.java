package dayday;



import java.util.*;

/**
 * @author ：wenli.hua
 * @description：TODO
 * @date ：2021/6/29 15:40
 */

public class Q168 {
    public static void main(String[] args) {
        int a = 701;
        System.out.println(convertToTitle(a));
        System.out.println((int) 'A');
        System.out.println((char) 65);

    }

    //这是一道从 1 开始的的 26 进制转换题。
    public static String convertToTitle(int columnNumber) {
        List<Character> list = new ArrayList<>();
        int tmp = columnNumber;

        while(tmp != 0  ){
            tmp = tmp-1; //每一位都是【1-26】，每次进行的都是当前位数上的计算
           int yushu = tmp % 26;
           list.add((char) (yushu+65));
           tmp = tmp / 26;
        }


        StringBuilder sb = new StringBuilder();
        for (int i = list.size()-1; i >= 0 ; i--) {
//        for (int i = 0; i < list.size() ; i++) {
            sb.append(list.get(i));
        }


        return sb.toString();
    }
}
