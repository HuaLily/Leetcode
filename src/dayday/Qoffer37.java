package dayday;



import javax.sound.midi.Soundbank;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author ：wenli.hua
 * @description：TODO
 * @date ：2021/6/30 22:21
 */

public class Qoffer37 {
    static int INF = -2000;
    static TreeNode emptyNode = new TreeNode(INF);

    public static class TreeNode {
        //static 只能修饰内部类，普通类不能修饰，是因为内部类new对象时，没有static时需要先new出外部类使用
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
    public static void main(String[] args) {
        // Encodes a tree to a single string.
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        System.out.println(serialize(root));
        System.out.println(deserialize("1 2 3 null null 4 5"));

        // Decodes your encoded data to tree.


    }

    // Encodes a tree to a single string.
    public static String serialize(TreeNode root) {

        if (root == null) return null;
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        StringBuilder sb = new StringBuilder();

        //遍历这个树
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();

            if(node.equals(emptyNode)){
                sb.append("null ");
            }else{
                sb.append(node.val+" ");
            }

        if(!node.equals(emptyNode)){
            if(node.left != null ){
                queue.offer(node.left);
            }else{
                queue.offer(emptyNode);
            }

            if(node.right != null){
                queue.offer(node.right);
            }else{
                queue.offer(emptyNode);
            }

        }


        }
        return sb.toString();


    }

    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) {
        if(data == " " || data == null) return null;
        String[] s = data.split(" ");
        TreeNode root = new TreeNode(Integer.parseInt(s[0]));
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        for (int i = 1; i < s.length - 1 ; i+=2) {
            TreeNode node = queue.poll();
            //两个一组
//            int a = Integer.parseInt(s[i]);
//            int b = Integer.parseInt(s[i+1]);

            if (!s[i].equals( "null")){
                node.left = new TreeNode(Integer.parseInt(s[i]));
                queue.add(node.left);
            }

            if (!s[i+1].equals("null") ){
                node.right = new TreeNode(Integer.parseInt(s[i+1]));
                queue.add(node.right);
            }


        }
        return root;



    }

}
