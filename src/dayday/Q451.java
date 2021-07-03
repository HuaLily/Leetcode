package dayday;

import java.util.*;

/**
 * @author ：wenli.hua
 * @description：TODO
 * @date ：2021/7/3 22:15
 */

public class Q451 {
    public static void main(String[] args) {
        String s = "tree";
        System.out.println(frequencySort(s));

    }

    public static String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            int frequency = map.getOrDefault(c, 0) + 1;
            map.put(c, frequency);
        }
        List<Character> list = new ArrayList<Character>(map.keySet()); // key 的list集合
        Collections.sort(list, (a, b) -> map.get(b) - map.get(a)); //升序，比较器是比较的list中的两个元素
        StringBuffer sb = new StringBuffer();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            char c = list.get(i);
            int frequency = map.get(c);
            for (int j = 0; j < frequency; j++) {
                sb.append(c);
            }
        }
        return sb.toString();


    }
}
