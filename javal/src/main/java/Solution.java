import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeMap;

/**
 * Author: shaoff
 * Date: 2020/12/2 19:41
 * Package: PACKAGE_NAME
 * Description:
 */

class Singleton{
    private Singleton(){

    }

    volatile private static Singleton instance;
    public static Singleton getInstance(){
        if (instance==null){
            synchronized (Singleton.class){
                if (instance==null){
                    instance=new Singleton();
                }
            }
        }
        return instance;
    }
}
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int v) {
        this.val = v;
    }
}

public class Solution {

    static void levelTraverse(TreeNode root){
        assert root!=null;
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode border=new TreeNode(-2);
        queue.offer(root);
        queue.offer(border);
        while (!queue.isEmpty()){
            TreeNode curr=queue.poll();
            if(curr==border){
                System.out.println();
                if(!queue.isEmpty()){
                    queue.offer(border);
                }
            }else{
                System.out.print(curr.val + " ");
                if(curr.left!=null){
                    queue.offer(curr.left);
                }
                if(curr.right!=null){
                    queue.offer(curr.right);
                }
            }
        }
    }

    static void traverse(TreeNode root) {
        if(root==null){
            return;
        }
        System.out.println(root.val);
        traverse(root.left);
        traverse(root.right);
    }

    static TreeNode mkTree(int[] arr) {
        return mkTree0(arr, new int[]{0});
    }


    static TreeNode mkTree0(int[] arr, int[] idx) {
        if (arr[idx[0]] == -1) {
            idx[0] += 1;
            return null;
        }
        TreeNode root = new TreeNode(arr[idx[0]]);
        idx[0] += 1;
        root.left = mkTree0(arr, idx);
        root.right = mkTree0(arr, idx);
        return root;
    }

    //  3
    // 1  2
    //   4
    public static void main(String[] args) {
        int[] arr=new int[]{3,1,-1,-1,2,4,-1,-1,-1};
//        int[] arr2=new int[]{1,2,-1,-1,3,-1,-1};

        TreeNode root=mkTree(arr);
        levelTraverse(root);
    }
}
