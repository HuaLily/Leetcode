package dataStruct;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author ：wenli.hua
 * @description：排序练习
 * @date ：2020/11/9 14:24
 */

public class Sort {
    public static void main(String[] args) {
        int[] a =  {1,6,3,123,42,123,5231,541};
        sort2(a);

        for (int i = 0; i < a.length ; i++) {
            System.out.print(a[i]+" ");
        }


    }

    //冒泡
    private static void sort1(int[] a) {
        for(int i = 0;i< a.length-1;i++){
            if(a[i] > a[i+1]){
                swap(a,i,i+1);
            }
        }

//        for (int i = 0; i < a.length ; i++) {
//            System.out.print(a[i]+" ");
//        }

    }

    private static void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }


    //快排
    private static void sort2(int[] a) {
        quicksort(a,0,a.length-1);

    }

    private static void quicksort(int[] a, int start, int end) {
        if(start < end){
            int fix_index = fix(a,start,end);
            quicksort(a,start,fix_index-1);
            quicksort(a,fix_index+1,end);
        }

    }

    private static int fix(int[] a, int start, int end) {
        int target = a[start];

        while(start < end){
            while(start < end && a[end] >= target){ end--;  };
            a[start] = a[end];
            while(start < end && a[end] <= target){start++;}
            a[end] = a[start];
        }

            a[start] = target;

        return start;

    }

    private static void sort3(int[] a) {
        quicksort(a,0,a.length-1);
    }


}
