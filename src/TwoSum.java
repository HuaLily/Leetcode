import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//1.TwoSum
public class TwoSum {
    public static void main(String[] args){
        int[] nums ={3,2,4};
        int target = 6;
        //int[] answer = twosum2(nums,target);
        int[] answer = twosum(nums,target);
        System.out.println(Arrays.toString(answer));
    }

    //暴力破解的方法
    //执行用时 : 70 ms
    //内存消耗 : 38.9 MB  时间复杂度为O(n^2)
    private static int[] twosum(int[] nums, int target) {
        int[] ans = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (target == nums[i] + nums[j]) {
                    ans[0] = i;
                    ans[1] = j;
                }
            }
        }

        return ans;
    }

    //两次for 使用map
    //时间复杂度为O(n^2) 执行用时：5 ms
    private static int[] twosum2(int[] nums, int target) {

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i],i);
        }

        for (int i = 0; i < nums.length ; i++) {
            //先算出和减去每个数的差，检查map里面有没有
          int num = target - nums[i];
          if (map.containsKey(num) && map.get(num) != i){ //相加不能重复！
              return new int[]{ i ,map.get(num)};
          }
        }

        throw new IllegalArgumentException("No two num sum!");
    }

    //一次for
    private static int[] twosum1(int[] nums,int target){
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = target - nums[i];
            if (map.containsKey(num)){
                return new int[]{map.get(num),i};
            }
            map.put(nums[i],i);
        }

        throw new IllegalArgumentException("No two nums sum!");
    }
}
