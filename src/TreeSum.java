import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Given an array nums of n integers, are there elements a, b, c in nums
// such that a + b + c = 0?
// Find all unique triplets in the array which gives the sum of zero.
public class TreeSum {
    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> ret = treeSum(nums);
        System.out.println(ret);
    }
    //自己还是想不出来答案啊，看了讨论里面别人的答案
    //思路：1.首先是要将数组进行排序，我一开始想在排序之后就删除重复的，但是例如：（-1） + （-1） + 2 = 并不能在排序之后删除
    //2.设置a ，往后计算 b + c ,当 b + c = 0 情况中，跳过重复的值
    //再看下面的评论，还有一个巧妙地设置，如果a > 0 就没必要计算，因为排序地缘故，b 和 c都会大于0
    //Runtime: 25 ms, faster than 98.75% of Java online submissions for 3Sum.
    //Memory Usage: 47.4 MB, less than 88.81% of Java online submissions for 3Sum.
    private static List<List<Integer>> treeSum(int[] nums) {
        List<List<Integer>> ret = new ArrayList();
        if (nums.length < 3) return ret;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0) break;  //如果a > 0 就没必要计算，因为排序地缘故，b 和 c都会大于0
            if (i == 0 || nums[i] != nums[i-1]){ //a 剔除重复的值
                int lo = i + 1;
                int hi = nums.length - 1;
                int sum = 0 - nums[i];
                while (lo < hi){
                    if (nums[lo] + nums[hi] == sum){
                        ret.add(Arrays.asList(nums[i],nums[lo],nums[hi]));
                        while (lo < hi && nums[lo] == nums[lo+1] ) lo++;
                        while (lo < hi && nums[hi] == nums[hi-1] ) hi--;
                        lo++;
                        hi--;
                    }else{
                        if (nums[lo] + nums[hi] < sum){
                            lo++;
                        }else {
                            hi--;
                        }
                    }
                }
            }
        }

        return ret;
    }

}
