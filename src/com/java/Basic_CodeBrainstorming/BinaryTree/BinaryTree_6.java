package com.java.Basic_CodeBrainstorming.BinaryTree;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * ClassName: BinaryTree
 * Package: com.java.Basic_CodeBrainstorming.BinaryTree
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/3/29 15:44
 * @Version 1.0
 */
public class BinaryTree_6 {
    @Test
    public void test() {
        TreeNode node5 = new TreeNode(5, null, null);
        TreeNode node4 = new TreeNode(4, node5, null);
        TreeNode node3 = new TreeNode(3, null, null);
        TreeNode node2 = new TreeNode(2, null, node4);
        TreeNode node1 = new TreeNode(1, null, node3);
        TreeNode root = new TreeNode(0, node1, node2);
        System.out.println(new Solution6().maxDepth3(root));
    }
}

class Solution6 {
    //迭代法：
    //BFS
    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int deep = 0;
        while (queue.size() > 0) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode p = queue.poll();
                if (p.left != null)
                    queue.offer(p.left);
                if (p.right != null)
                    queue.offer(p.right);
            }
            deep++;
        }
        return deep;
    }

    //递归法：
    //DFS（从上往下求深度（
    public int maxDepth2(TreeNode root) {
        if (root == null)
            return 0;
        int leftHigh = deepTree(root.left, 1);//左子树的高度
        int rightHigh = deepTree(root.right, 1);//右子树的高度
        return Math.max(leftHigh, rightHigh);//比较左右子树哪个高，返回较高的值
    }

    public int deepTree(TreeNode p, int high) {
        if (p == null)
            return high;
        int leftHigh = deepTree(p.left, high + 1);
        int rightHigh = deepTree(p.right, high + 1);
        return Math.max(leftHigh, rightHigh);
    }

    //后序递归遍历求深度：
    public int maxDepth3(TreeNode root) {
        if (root == null)
            return 0;
        int leftHigh = maxDepth3(root.left);//左子树的高度
        int rightHigh = maxDepth3(root.right);//右子树的高度
        return Math.max(leftHigh, rightHigh) + 1;//从叶节点往上走，高度+1
    }


}
