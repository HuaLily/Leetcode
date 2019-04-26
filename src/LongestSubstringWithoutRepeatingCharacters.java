import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

//1.第三题 Longest Substring Without Repeating Characters
//Given a string, find the length of the longest substring without repeating characters.
//
//Example 1:
//
//Input: "abcabcbb"
//Output: 3
//Explanation: The answer is "abc", with the length of 3.
//Example 2:
//
//Input: "bbbbb"
//Output: 1
//Explanation: The answer is "b", with the length of 1.
//Example 3:
//
//Input: "pwwkew"
//Output: 3
//Explanation: The answer is "wke", with the length of 3.
//             Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
public class LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args){
            String s1 = "abcabcbb";
            String s2 = "bbbbb";
            String s3 = "pwwkew";
        //该问题总结一下，是为了给出指定字符串的最长的里面没有重复字符的子串长度
        //自己做毫无头绪，整理答案
        //1.暴力破解，逐个检查子字符串，看它有没有解
//        System.out.println(lengthOfLongestSubstring1(s1));
//        System.out.println(lengthOfLongestSubstring1(s2));
//        System.out.println(lengthOfLongestSubstring1(s3));

        //2.滑动窗口
        System.out.println(lengthOfLongestSubstring2(s1));
        System.out.println(lengthOfLongestSubstring2(s2));
        System.out.println(lengthOfLongestSubstring2(s3));

        //3.滑动窗口的改进
        System.out.println(lengthOfLongestSubstring3(s1));
        System.out.println(lengthOfLongestSubstring3(s2));
        System.out.println(lengthOfLongestSubstring3(s3));

        //4.假设字符集为ASCII 128
        System.out.println(lengthOfLongestSubstring4(s1));
        System.out.println(lengthOfLongestSubstring4(s2));
        System.out.println(lengthOfLongestSubstring4(s3));
    }



    //1.暴力破解，逐个检查子字符串，看它有没有解
    private static int lengthOfLongestSubstring1(String s) {
        int n = s.length();
        int ans = 0;
        //遍历s所有可能的子字符串
        for (int i = 0; i < n ; i++) {
            for (int j = i + 1; j < n ; j++) {
                if (allunique(s,i,j)) ans = Math.max(ans,j-i);
            }
        }
        return ans;
    }

    private static boolean allunique(String s, int i, int j) {
        Set<Character> set = new HashSet<>();
        //保证[i,j-1]都是unique并返回true
        for (int k = i; k < j; k++) {
            char c = s.charAt(k);
            if (set.contains(c)) return false;
            set.add(c);
        }
        return true;
    }

    //2.滑动窗口
    //核心思想：检查s[j]是不是在s[i,j-1]中，使j为滑动元素
    //大概步骤：用HashSet存放s[i,j-1]之间的不重复的字符，j++，如果s[j]不在set里面，继续滑动j,
    //直到s[j]在set里面。最长无重复的字串就是s[i,j-1]，对i<n做这样子的操作，直到结束
    private static int lengthOfLongestSubstring2(String s) {
        int n = s.length();
        HashSet<Character> set = new HashSet<>();
        int ans = 0;
        int i = 0 ,j = 0;
        while (i < n && j < n){
            //自己写的
//            char c = s.charAt(j);
//            if (!set.contains(c)){//set里面没有s[j]元素
//                set.add(c);
//                j++;
//                ans = Math.max(ans,j-i);
//            }else { //set里面有s[j]的元素,变成s[i+1,j)
//                set.remove(s.charAt(i));
//                i++;
//            }
            //优雅的答案
            if (!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                ans = Math.max(ans,j-i);
            }else {
                set.remove(s.charAt(i++));
            }
        }
        return ans ;
    }

    //3.滑动窗口的改进，不再是将s[i]一味的删除,i++，而是找到重复的那个j'，直接跳过[i,j'] ，i= j'+1
    //仅仅有值，但是不知道位序，set集合里是乱序，所以使用map
    private static int lengthOfLongestSubstring3(String s) {
        int n = s.length();
        int ans = 0 ;
        HashMap<Character,Integer> map = new HashMap<>();
        for ( int j = 0 , i = 0; j < n ; j++) {
            if (map.containsKey(s.charAt(j))) {//如果map中有值,更新i的值
                i = Math.max(map.get(s.charAt(j)),i);
            }
                ans = Math.max(ans,j - i + 1);
                map.put(s.charAt(j),j+1);//将所有的值都放map，key比原来位序大一，准备要给i更新
        }
        return ans;
       }

       //4.假设字符集为ASCII 128,用int数组来代替map
    private static int lengthOfLongestSubstring4(String s) {
        int n = s.length();
        int ans = 0;
        int[] index = new int[128];
        for (int i = 0, j = 0;j < n; j++){
            i = Math.max(i,index[s.charAt(j)]);
            ans = Math.max(ans,j - i + 1);
            index[s.charAt(j)] = j + 1;
        }



        return ans;

    }


}
