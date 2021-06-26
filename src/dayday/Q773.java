package dayday;

import java.util.*;

/**
 * @author ：wenli.hua
 * @description：TODO
 * @date ：2021/6/26 19:06
 */
//滑动谜题，类似华容道 2*3 的格子，0周围的数字可以移动
//求多少步骤能够到[[1,2,3],[4,5,0]]

public class Q773 {
    //BFS
    //初始将数组转化成字符串操作
    //将当前可以移动的每种情况都列出，放入队列，用hashset记录控制重复的情况
    //用一个邻接数组记录0周围的情况
    
    //0 的相邻位置是 1, 3
    //11 的相邻位置是 0, 2, 4
    //22 的相邻位置是 1, 5
    //33 的相邻位置是 0, 4
    //44 的相邻位置是 1, 3, 5
    //55 的相邻位置是 2, 4

    private static int[][] neighbors = {{1,3},{0,2,4},{1,5},{0,4},{1,3,5},{2,4}};

    public static void main(String[] args) {
//        int[][] board  ={{1,2,3},{4,0,5}};
        int[][] board  ={{4,1,2},{5,0,3}};
        System.out.println(slidingPuzzle(board));
    }

    /**
     *
     * @param board 原始状态
     * @return -1 不能完成/ 具体的步骤数
     */
    private static int slidingPuzzle(int[][] board) {
        if(board.length == 0)return -1;
        
        //1.将原始数组转化成字符串
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                sb.append(board[i][j]);
            }
        }

        String initial = sb.toString();

        if(initial.equals("123450")){return 0;}
        
        //2.BFS
        int step = 0; //一层情况表示一个步骤
        Deque<String> queue = new LinkedList<String>();
        queue.offer(initial);
        Set<String> visitedSet = new HashSet<String>(); //防止重复访问
        visitedSet.add(initial);
        
        while(!queue.isEmpty()){
            step++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String nowSituation = queue.poll();
                List<String> nextSituations = getNextSituation(nowSituation); //列出0可以移动的所有情况
                for(String nextSituation: nextSituations){
                    if(nextSituation.equals("123450")){
                        return step;
                    }
                    if(!visitedSet.contains(nextSituation)){ //如果是没有出现过的情况
                        queue.offer(nextSituation); //将这个下一层的情况加入队列
                        visitedSet.add(nextSituation);
                    }
                }

            }

        }


        return -1;
    }

    private static List<String> getNextSituation(String nowSituation) {
        List<String> situations  = new ArrayList<String>();
        char[] chars = nowSituation.toCharArray();
//        int x = 0; //用来找0的位序
//        for (int i = 0; i < chars.length; i++) {
//            if(chars[i] == 0){
//                x = i;
//            }
//        }
        int x = nowSituation.indexOf('0');
        int[] y_indexs = neighbors[x]; //邻居的位序
        for(int y :y_indexs){
            char[] newSituation = Arrays.copyOf(chars,chars.length);
            swap(newSituation,x,y);
            situations.add(String.valueOf(newSituation));
        }

        return situations;
    }

    private static void swap(char[] newSituation, int x, int y) {
        char tmp = newSituation[x];
        newSituation[x] = newSituation[y];
        newSituation[y] = tmp;
    }


}
