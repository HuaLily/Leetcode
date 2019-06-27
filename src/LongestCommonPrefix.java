//14. Longest Common Prefix
//Write a function to find the longest common prefix string amongst an array of strings.
//If there is no common prefix, return an empty string "".

//Example 1:
//Input: ["flower","flow","flight"]
//Output: "fl"
//Example 2:
//
//Input: ["dog","racecar","car"]
//Output: ""
//Explanation: There is no common prefix among the input strings.

//Note:
//
//All given inputs are in lowercase letters a-z.


public class LongestCommonPrefix {
    public static void main(String[] args) {
        String[] input = {"flower","flow","flight"};
        String ret = LCP5(input[0],input);
        System.out.println(ret);

    }

    //官方的第五种解法 利用树的性质
    //Follow up
    //创建一个新树，root为头节点
    //将每个字符串插入树中，找最长前缀的时候，若当前节点的孩子只有一个，并且没有到尾节点，加到结果上面
    //
    private static String LCP5(String q , String[] input) {
        if(input == null || input.length == 0){
            return "";
        }
        if (input.length == 1){
            return input[0];
        }
        Trie trie = new Trie();

        for (int i = 1; i < input.length; i++) { //将字符串插入到树中
            trie.insert(input[i]);
        }

        return trie.searchLongestPrefix(q);
    }

    public static class TrieNode{
        private TrieNode[] links;  // R links to node children

        private final int R = 26;

        private boolean isEnd;

        private int size; //表示有几条分叉

        //构造函数  创建节点，按照26个字母一排，每个字母又是又26个向下的链接
        public TrieNode() {
            links = new TrieNode[R];
        }

        //将字符放入节点值
        public void put(char ch, TrieNode node){
            links[ch - 'a'] = node;
            size++;
        }

        public boolean containKey(char ch) {
            return links[ch - 'a'] != null;
        }


        public TrieNode get(char ch) {
            return links[ch - 'a'];
        }

        public void setEnd() {
            isEnd = true;
        }

        public int getLinks() {
            return size;
        }

        public boolean isEnd(){
            return isEnd;
        }
    }

    public static class Trie{
        private TrieNode root; //根节点
        public Trie(){
            root = new TrieNode();
        }

        //给树增加一个节点s,从根节点开始加
        public void insert(String s) {
            TrieNode node = root;
            for (int i = 0; i < s.length(); i++) {
                char currentChar = s.charAt(i); //将字符串分成一个个字符加入
                if (!node.containKey(currentChar)){ //当字符没有出现过
                    node.put(currentChar,new TrieNode()); //分出一条新的叉支
                }
                node = node.get(currentChar); //将node指针指向下一个节点
            }//字符串插入树完毕要加个一个end节点
            node.setEnd();

        }
        //针对树 找到制定的word匹配的最长前缀
        public String searchLongestPrefix(String word) {
            TrieNode node = root;
            StringBuilder prefix = new StringBuilder();
            for (int i = 0; i < word.length(); i++) {
                char curLetter = word.charAt(i);
                if (node.containKey(curLetter) && (node.getLinks() == 1) && (!node.isEnd())) {
                    //节点没有分叉，且不是最后的尾节点，并且节点包含当前字符
                    prefix.append(curLetter);
                    node = node.get(curLetter);
                }
                else
                    return prefix.toString();

            }
            return prefix.toString();
        }
    }

    //官方的第四种解法
    // Binary search
    //思路:在第一个字符中，先找到长度最短的那个字符串，最终的结果不会长于这个长度
    //int low = 0; int high = minlen;
    //然后把第一个字符串分成两部分，判断在每个字符串中是不是以第一部分开头，不是的丢弃,保证[0,mid）是正确答案
    //eg: {leets,leetcode,leetc,leeds}
    //  min = 5 leets 截取 5个字符，low = 0 ,high = 4 mid = 2 分成两个部分 lee 和 ts ，
    //判断 lee in leetcode / in leetc /in leeds , low = mid + 1= 3 ,high = 4 ,mid = 3
    //判断 leet in leetcode /leet in leetc/ leet not in leeds ,high = mid - 1 = 2 ,low = 2 ,end
    //结果是lee
    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Longest Common Prefix.
    //Memory Usage: 37.3 MB, less than 99.07% of Java online submissions for Longest Common Prefix.
    private static String LCP4(String[] input) {
        if (input == null||input.length == 0){
            return "";
        }
        //找到数组中字符串最短长度
        int minlen = Integer.MAX_VALUE;
        for ( String str:input){
            minlen = Math.min(minlen,str.length());
        }

        int low = 0;
        int high = minlen;
        while (low <= high){
            int mid  = (low + high)/2;
            if (IsCommonPreix(input,mid)){
                low = mid + 1;
            }else {
                high = mid - 1;
            }
        }
        return input[0].substring(0,(low + high)/2);

    }

    /**
     * 判断第一个字符串[0,mid) 是不是数组所有的字符串的前缀
     * @param input 字符串数组
     * @param mid 位序
     * @return
     */
    private static boolean IsCommonPreix(String[] input, int mid) {
        String s = input[0].substring(0,mid);
        for (int i = 1; i < input.length ; i++) {
            if (!input[i].startsWith(s)) {
                return false;
            }
        }
        return true;
    }

    //官方的第三种解法：
    //Divide and conquer(分治法)
    //利用最长前缀的性质 LCP(S1...Sn) = LCP( LCP(S1...Sk),LCP(Sk+1...Sn) )把字符串数组分开
    //递归使用函数
    //eg: {leetcode,leet,lee,le}
    //divide :{leetcode ,leet} {lee,le}
    //                |           |
    //               \|/         \|/
    //conquer :    {leet}        {le}
    //answer:              le
    //Runtime: 1 ms, faster than 75.60% of Java online submissions for Longest Common Prefix.
    //Memory Usage: 35.8 MB, less than 99.82% of Java online submissions for Longest Common Prefix.
    private static String LCP3(String[] input) {
        if(input == null || input.length == 0 ) return "";
                    return LongestCommonPrefix1(input,0,input.length -1);
    }

    /**
     * 用来递归使用的函数
     * @param input 输入的字符串数组
     * @param l 数组的最开始位序
     * @param r 最右边的位序
     * @return  字符串数组中（l,r）范围内的最长前缀
     */
    private static String LongestCommonPrefix1(String[] input, int l, int r) {
        if ( l == r){   //当左右相同时，返回当前的字符串
            return input[l];
        } else{
            int mid = (l + r)/2;
            String lcpLeft =  LongestCommonPrefix1(input,l,mid);
            String lcpRight = LongestCommonPrefix1(input,mid + 1 ,r);
            return commonPrefix(lcpLeft,lcpRight);
        }
    }

    /**
     * 找到两个字符串的最长前缀
     * @param left 字符串
     * @param right 字符串
     * @return 两个字符串的最长前缀
     */
    private static String commonPrefix(String left, String right) {
        int min = Math.min(left.length(),right.length());
        for (int i = 0; i < min; i++) {
            if (left.charAt(i) != right.charAt(i)){
                return left.substring(0,i);
            }
        }
        return left.substring(0,min);
    }

    //官方解答的第二种 Vertical scanning 垂直扫描
    //思路关键在于 最长的前缀一定以第一个字符串为基础，找到其他字符串和第一个字符串比较之后，所一致的位序，截取第一个字符串
    //比较每列字符
    //   i
    //j  f l o w e r
    //   f l o w
    //   f l i g h t
    //Runtime: 1 ms, faster than 75.60% of Java online submissions for Longest Common Prefix.
    //Memory Usage: 37.6 MB, less than 86.53% of Java online submissions for Longest Common Prefix.
    private static String LCP2(String[] input) {
        if (input == null || input.length == 0) return "";
        for (int i = 0; i < input[0].length() ; i++) { //遍历第一行的字符
            char c = input[0].charAt(i);              //取出字符
            for (int j = 1; j < input.length; j++) {  //比较之后的字符
                if (i == input[j].length() || c != input[j].charAt(i) ){
                    return input[0].substring(0,i);
                    // 当遍历超过字符串的长度 或者不一致时 停止
                }

            }
        }
        return input[0];
    }


    //看了官方答案，我自己做的思想第一种就是横向扫描
    //但是官方的写法更加巧妙 Horizontal scanning ，而且时间复杂度更低
    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Longest Common Prefix.
    //Memory Usage: 38 MB, less than 77.62% of Java online submissions for Longest Common Prefix.
    private static String LCP1(String[] input) {
        if (input.length == 0) return "";
        String prefix = input[0]; //把第一个字符串默认为最长前缀
        for (int i = 1; i < input.length; i++)   //针对之后的每个字符串
            while (input[i].indexOf(prefix) != 0) {  //如果之后的字符串和前缀不一致 ，前缀就从后往前少一位，直到找到一致的前缀
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return ""; //前缀为空的情况
            }
        return prefix;
    }

    //1.自己写的，直接比较前两个，把前面比较得出的结果 和 后一个比较
    //Runtime: 1 ms, faster than 78.08% of Java online submissions for Longest Common Prefix.
    //Memory Usage: 37.2 MB, less than 99.16% of Java online submissions for Longest Common Prefix.
    private static String LCP(String[] input) {
        int length = input.length;
        if ( length == 0 ){
            return "";
        }

        if ( length == 1 ){
            return input[0];
        }
        String ret = lcp(input[0],input[1]);
        for (int i = 2; i < length ; i++) {
            ret = lcp(ret,input[i]);
        }
        return ret;
    }

    //针对两个字符串的比较
    private static String lcp(String s1, String s2){
        //保证s1的长度小于s2
        if (s1.length() > s2.length()){
            String s3 = s1;
            s1 = s2;
            s2 = s3;
        }
        StringBuilder stringBuilder = new StringBuilder("");
        char[] chars1 = s1.toCharArray();

        if (s1.length() == 0 || s2.length() == 0){
            return "";
        }

        for (int i = 0; i < chars1.length ; i++) {
            if (chars1[i] == s2.charAt(i)){
                stringBuilder.append(chars1[i]);
            }else {
                break;
            }
        }
        return stringBuilder.toString();
    }


    //


}
