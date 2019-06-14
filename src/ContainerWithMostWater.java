//11. Container With Most Water
// Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai).
// n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0).
// Find two lines, which together with x-axis forms a container, such that the container contains the most water.

import java.util.jar.JarEntry;

public class ContainerWithMostWater {
    public static void main(String[] args) {
        int[] input = new  int[]{1,8,6,2,5,4,8,3,7};
        int ret = maxArea2(input);
        System.out.println(ret);
    }


    //首先想到的思路是：暴力破解,
    //Runtime: 205 ms, faster than 18.41% of Java online submissions for Container With Most Water.
    //Memory Usage: 40 MB, less than 92.10% of Java online submissions for Container With Most Water.
    private static int maxArea(int[] height) {
        int max_area = 0;
        for (int i = 0; i < height.length - 1; i++) {
            for (int j = i + 1; j < height.length ; j++) {
//               int new_area = (j - i) * Math.min(height[i],height[j]);
//               if (new_area > max_area){
//                   max_area = new_area;
//            }
            //看官方的答案，可以写的更加简单
                max_area = Math.max(max_area,Math.min(height[i],height[j])*(j-i));
            }
        }
        return max_area;
    }

    //第二种，设置两个指针，分别指向数组两端，向内计算面积的同时，保留长的数据，改变短的数据
    //比暴力破解运算减少一轮，最多计算n次
    //Runtime: 2 ms, faster than 95.45% of Java online submissions for Container With Most Water.
    //Memory Usage: 39.5 MB, less than 95.14% of Java online submissions for Container With Most Water.
    private static int maxArea2(int[] height) {
        int max_area = 0;
        int left = 0;
        int right = height.length - 1;
        while (left < right){
            max_area = Math.max(max_area,Math.min(height[left],height[right])*(right - left));
            if (height[left] < height[right]){
                left++;
            }else {
                right--;
            }
        }
        return max_area;
    }


}
