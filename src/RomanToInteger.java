import java.io.IOException;

//13. Roman to Integer
public class RomanToInteger {
    public static void main(String[] args) {
        String s = "MM";
       int i = romanToInt(s);
        System.out.println(i);
    }

    private static int romanToInt(String s) {
//        String[] M = {"","M","MM","MMM"}; //千位
//        String[] C = {"","C","CC","CCC","CD","D","DC","DCC","DCCC","CM"};
//        String[] X = {"","X","XX","XXX","XL","L","LX","LXX","LXXX","XC"};
//        String[] I = {"","I","II","III","IV","V","VI","VII","VIII","IX"};
        int ret = 0;
        int size = s.length();
        char[] input = new char[size];
        for (int i = 0; i < size; i++) {
            input[i] = s.charAt(i);
        }
        for(int i = 0; i<size;i++){
            if(input[i]== 'I'){
                if(i+1!=size && input[i+1] == 'V'){
                    ret += 4;// IV 表示4
                    i++;
                } else if( i+1 != size && input[i+1] == 'X'){
                    ret += 9;// IX 表示9
                    i++;
                } else {
                    ret += 1;//I表示1
                }
            } else if(input[i]=='V'){
                ret+=5;
            } else if(input[i] == 'X'){
                if(i+1!=size && input[i+1] == 'L'){
                    ret += 40;  // XL 表示40
                    i++;
                } else if(i+1!=size && input[i+1] == 'C'){
                    ret += 90;  //XC 表示90
                    i++;
                } else {
                    ret += 10;  //X表示10
                }
            } else if(input[i]== 'L'){
                ret += 50;   //L表示50
            } else if (input[i] == 'C'){
                if(i+1!=size && input[i+1] =='D'){
                    ret += 400;  //CD表示400
                    i++;
                } else if(i+1!=size && input[i+1]== 'M'){
                    ret += 900;  //CM 表示900
                    i++;
                } else{
                    ret += 100; //C表示100
                }
            } else if(input[i] == 'D'){
                ret += 500; //D表示500
            } else if(input[i] =='M'){
                ret += 1000; //M表示1000
            }
        }

        return ret;
    }


}
