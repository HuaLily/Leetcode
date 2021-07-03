package dayday;

import java.util.Arrays;

/**
 * @author ：wenli.hua
 * @description：TODO
 * @date ：2021/7/2 9:51
 */

public class Q1833 {
    public static void main(String[] args) {
        int[] costs = {1,6,3,1,2,5};
        int coins = 20;
        System.out.println(maxIceCream(costs,coins));


    }

    //贪心算法
    public static int maxIceCream(int[] costs, int coins) {
        if(coins == 0) return 0;
        Arrays.sort(costs);
        int count = 0;
        for (int i = 0; i < costs.length; i++) {
            int letMoney = coins - costs[i];
            if(letMoney < 0){
                break;
            }else{
                coins = letMoney;
                count ++;
            }
        }

        return count;



    }
}
