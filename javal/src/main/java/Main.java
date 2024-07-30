/**
 * Author: shaoff
 * Date: 2020/9/23 20:03
 * Package: PACKAGE_NAME
 * Description:
 */
import lombok.val;

import java.util.Scanner;
import java.util.*;
public class Main {
    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int ele){
            this.val=ele;
        }
    }

    //-1 每层结束
    static void traverse(TreeNode root){
        assert root!=null;
        boolean flag=true;
        List<TreeNode> ls=new ArrayList<>();
        List<TreeNode> level=new ArrayList<>();
        ls.add(root);
        ls.add(null);
        while(ls.size()>1){
            TreeNode cur=ls.remove(0);
            level.add(cur);
            if(cur==null){
                flag= !flag;
                ls.add(null);
               // System.out.println(level.);
                continue;
            }else{
                System.out.print(cur.val+" ");
            }
            if(cur.left!=null){
                ls.add(cur.left);
            }
            if(cur.right!=null){
                ls.add(cur.right);
            }
        }
    }

    //1
    //2 3
    //4 5 6

    static TreeNode prepare(){
        TreeNode t1=new TreeNode(1);
        TreeNode t2=new TreeNode(2);
        TreeNode t3=new TreeNode(3);
        TreeNode t4=new TreeNode(4);
        TreeNode t5=new TreeNode(5);
        TreeNode t6=new TreeNode(6);
        t1.left=t2;
        t1.right=t3;
        t2.left=t4;
        t2.right=t5;
        t3.left=t6;
        return t1;
    }

    public static void main(String[] args) throws InterruptedException {
        //Scanner in = new Scanner(System.in);
        //int a = in.nextInt();
        //System.out.println(a);

        TreeNode root=prepare();
        traverse(root);
        //1
        //2 3
        // 4 5 6
        Thread.sleep(1000000000);
    }

}