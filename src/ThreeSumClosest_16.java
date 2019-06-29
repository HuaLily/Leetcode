//16.3Sum Closest
//Given an array nums of n integers and an integer target,
// find three integers in nums such that the sum is closest to target.
// Return the sum of the three integers. You may assume that each input would have exactly one solution.


import java.util.Arrays;

//Example:Given array nums = [-1, 2, 1, -4], and target = 1.
//The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
public class ThreeSumClosest_16 {
    public static void main(String[] args) {
        int[] nums = {1,1,1,0};
        int target = 100;
        int ret = threeSumClosest(nums,target);
        System.out.println(ret);
    }

    //Runtime: 4 ms, faster than 96.37% of Java online submissions for 3Sum Closest.
    //Memory Usage: 36.7 MB, less than 99.97% of Java online submissions for 3Sum Closest.
    private static int threeSumClosest(int[] nums, int target) {
        //看错题目，不是相邻的，哎
//        int sum ,sub = Integer.MAX_VALUE,ret = 0;
//        for (int i = 1; i <= nums.length - 2 ; i++) {
//            sum = nums[i] + nums[i-1] + nums[i+1];
////            sub = Math.min( sub,Math.abs(target - sum));
//            int newsub = Math.abs(target - sum);
//            if (newsub < sub){
//                sub = newsub;
//                ret = sum;
//            }
//        }
//       return ret;


        //还是和之前的threesum一样的思路
        //当前，之后，和最后数字相加
        Arrays.sort(nums);
        int ret = nums[0] + nums[1] + nums[nums.length -1];
        for (int i = 0; i < nums.length - 2 ; i++) {
            int current = nums[i];
            int lo = i + 1;
            int hi = nums.length - 1;
            while (lo < hi){
                int sum = current + nums[lo] + nums[hi];
                if (sum == target){ return sum; }
                if (sum > target){ hi --; }
                if (sum < target){ lo ++; }
                if (Math.abs(target - sum) < Math.abs(ret - target)){
                    ret = sum;
                }
            }
        }
        return ret;

    }
}
