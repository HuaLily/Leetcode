//4. Median of Two Sorted Arrays
//There are two sorted arrays nums1 and nums2 of size m and n respectively.
//
//Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
//
//You may assume nums1 and nums2 cannot be both empty.
//
//Example 1:
//
//nums1 = [1, 3]
//nums2 = [2]
//
//The median is 2.0
//Example 2:
//
//nums1 = [1, 2]
//nums2 = [3, 4]
//
//The median is (2 + 3)/2 = 2.5

//注意时间复杂度，不然就是简单地合并一个数组，排序找到中位数

public class MedianofTwoSortedArrays {
    public static void main(String[] args){
       int[] nums1 = {1,2};
       int[] nums2 = {1,2,3};
       double answer = findMedianSortedArrays(nums1, nums2);
        System.out.println(" mid= "+ answer);
    }

//题解找到中位数的性质，保证了中位数的左右数目相等，且左边都小于右边的数

    //找到A 数组中的i，B数组中的j，将两个数组分成两个部分，
    // 左边和右边的数目相等，得到j用i表示的方程式，为了让j不小于0，要保证n >= m

    //        left_part         |      right_part
    //A[0] A[1] A[2] ... A[i-1] | A[i] A[i+1] ... A[m-1]
    //B[0] B[1] B[2] ... B[j-1] | B[j] B[j+1] ... B[n-1]

    //左边数目 = 右边数目 i + j = m - i + n - j + 1 / m + n - i -j

    //要保证 max(left_part) < min(right_part)
    //A[i-1] <= B[j] && B[j-1] <= A[i]
    private static double findMedianSortedArrays(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;

        //保证 n >= m
        if (n < m){
            int[] tmp = A;
            A = B;
            B = tmp;
            int tmpn = n;
            n = m ;
            m = tmpn;
        }

        int i_Min = 0;
        int i_Max = m;
        int half_len = ( m + n + 1)/2;
        //System.out.println("half = "+half_len);

        while(i_Min <= i_Max){

            int i = (i_Max + i_Min)/2;
            int j = half_len - i;
           // System.out.println("j = "+j);

            //二分法来确定i的合适位置
            if (i < i_Max && B[j-1] > A[i]){
                i_Min = i + 1; //i is too small
            }
            else if (i > i_Min && A[i-1] > B[j] ){
                i_Max = i - 1; //i is too big
            }else {
                //i is perfect,保证了B[j−1]≤A[i] ， A[i−1]≤B[j]
                //要注意，A[i−1],B[j−1],A[i],B[j] 部分不存在的情况
                int maxleft ;
                if (i == 0){
                    //表示A只有一个元素，A[i-1]不存在
                    //B[j-1] < A[i]
                    //B[j-1] < B[j]
                    maxleft = B[j-1];
                }else if (j == 0){
                    //表示B只有一个元素,B[j-1] 不存在
                    //A[i-1] < B[j]
                    //A[i-1] < A[i]
                    maxleft = A[i-1];
                }else {
                    maxleft = Math.max(A[i-1],B[j-1]);
                }
                System.out.println("maxleft="+maxleft);
                if ((m + n ) % 2 == 1){
                    return maxleft;
                }

                int minRight;
                if (i == m){
                    //表示A[i] 不存在
                    minRight = B[j];
                }else if (j == n){
                    //表示B[j] 不存在
                    minRight = A[i];
                }else {
                    minRight = Math.min(B[j],A[i]);
                }
            return (maxleft+ minRight) / 2.0;
            }
        }

        return 0.0;
    }
}
