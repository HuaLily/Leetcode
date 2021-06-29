package dayday;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author ：wenli.hua
 * @description：TODO
 * @date ：2021/6/27 16:31
 */
//矩阵n*n 中按照蛇形从左下角编号
//选择 【+1，+6】色子数前进
//当走到非-1的数字x时，则跳转到编号x的格子

//BFS
//搜索状态：移动次数
//搜索条件：出边
//走【+1，+6】
//当前x！=-1 下一个x编号的格子
//queue存放{1，0} 表示当前的格子数，移动的次数

    //广度优先算法经典的题目

public class Q909 {

    public static void main(String[] args) {

        int[][] board = {
                {-1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, 35, -1, -1, 13, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, 15, -1, -1, -1, -1}
        };
        System.out.println(snakesAndLadders1(board));
    }

    //官方题解
    public static int snakesAndLadders1(int[][] board) {
        int n = board.length;
        //1.queue存放，{{当前格子编号，次数}...}
        Deque<int[]> queue = new LinkedList<int[]>();
        queue.offer(new int[]{1, 0});

        //记录当前编号的格子是否访问过
        boolean[] visited = new boolean[n * n+1];

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            //2.走1-6步
            for (int i = 1; i <= 6; i++) {
                int nextId = now[0] + i;

                if (nextId > n * n) { //当超出棋盘
                    break;
                }

                //根据当前的位序得到当前的格子位置
                int[] xAndY = getXAndY(nextId,n);
                int x = xAndY[0];
                int y = xAndY[1];

                //如果当前的格子不为-1的情况
                if (board[x][y] != -1) {
                    nextId = board[x][y];
                }

                if (nextId == n * n) {
                    return now[1] + 1;
                }

                if (!visited[nextId]) {
                    visited[nextId] = true;
                    queue.offer(new int[]{nextId, now[1] + 1});
                }


            }


        }
        return -1;

    }

    private static int[] getXAndY(int nowNumber,int n) {

        int x = (nowNumber -1)/n;//行数
        int y = (nowNumber -1)%n;//列数
        if(x%2 == 1){ //奇数行
            y = n - y -1;
        }
        return new int[]{n-x-1,y};
    }

    //将二维图转换成一维图
}
