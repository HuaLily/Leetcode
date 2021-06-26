package dataStruct;

import jdk.nashorn.internal.ir.IfNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author ：wenli.hua
 * @description：TODO
 * @date ：2021/6/5 14:13
 */

public class Tree {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode sec = new TreeNode(2);
        TreeNode third = new TreeNode(3);
        TreeNode forth = new TreeNode(4);
        TreeNode fifth = new TreeNode(5);
        root.left = sec;
        root.right = third;
        sec.left = forth;
        sec.right =fifth;

        //前序遍历:中 左 右
        //12453
//        PreOrder1(root); //递归法
//       PreOrder2(root); //非递归
//
//        //中序遍历
//        //42512
//        midOrder1(root); //递归法
//        midOrder2(root); //非递归
//
//        //后序遍历
//        //45231
//        afterOrder1(root); //递归法
//        afterOrder2(root); //非递归

        //广度优先遍历
        //12345
        BFS(root);


    }

    private static void BFS(TreeNode root) {
        if (root == null ) return ;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        while(!queue.isEmpty()){
            TreeNode node = queue.remove();
            System.out.print(node.val);
            if(node.left != null){
                queue.add(node.left);
            }
            if(node.right != null){
                queue.add(node.right);
            }
        }
    }

    //前序遍历的过程 是 中左右。
    //将其转化成 中右左。也就是压栈的过程中优先压入左子树，在压入右子树。
    //然后将这个结果返回来，这里是利用栈的先进后出倒序打印。
    private static void afterOrder2(TreeNode root) {
        if(root == null) return;
        Deque<TreeNode> stack1 = new LinkedList<TreeNode>();
        Deque<TreeNode> stack2 = new LinkedList<TreeNode>(); //用来倒序输出的栈

        stack1.push(root);
        while (!stack1.isEmpty()){
            TreeNode node = stack1.pop();
            stack2.push(node);
            if(node.left != null){
                stack1.push(node.left);
            }
            if(node.right != null ){
                stack1.push(node.right);
            }
        }

        //倒序输出
        //右 左 中 -》中 左 右
        while (!stack2.isEmpty()){
            TreeNode pop = stack2.pop();
            System.out.print(pop.val);
        }


    }

    private static void afterOrder1(TreeNode root) {
        if(root == null) return;
        afterOrder1(root.left);
        afterOrder1(root.right);
        System.out.print(root.val);
    }

    private static void midOrder2(TreeNode root) {
        if(root == null) return;
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        TreeNode cur = root; //用当前指针带头作为子树的root
        while(!stack.isEmpty()|| cur != null){
            //一直走左子树直到最左
            while(cur != null){
                stack.push(cur);
                cur = cur.left;
            }
            //返回上一层时需要处理好左边
            TreeNode node = stack.pop();
            System.out.print(node.val);
            if(node.right != null){
                cur = node.right;
            }
        }
    }

    private static void midOrder1(TreeNode root) {
        if(root == null ){return;}
        midOrder1(root.left);
        System.out.print(root.val);
        midOrder1(root.right);
    }

    private static void PreOrder1(TreeNode root) {
        if(root == null) {return ;}
        System.out.print(root.val);
        PreOrder1(root.left);
        PreOrder1(root.right);

    }

    private static void PreOrder2(TreeNode root) {
        if(root == null) return ;
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode node = stack.pop();
            System.out.print(node.val);
            if(node.right != null){
                stack.push(node.right);
            }

            if(node.left != null){
                stack.push(node.left);
            }
        }
    }


}
