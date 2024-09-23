package com.java.Basic_CodeBrainstorming.BinaryTree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * ClassName: BinaryTree_8
 * Package: com.java.Basic_CodeBrainstorming.BinaryTree
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/3/30 9:29
 * @Version 1.0
 */
public class BinaryTree_8 {

}

class Solution8 {
    //迭代法：
    //BFS
    //时间复杂度：O(n)，空间复杂度：O(n)（队列）
    public int countNodes(TreeNode root) {
        Deque<TreeNode> treeNodeDeque = new LinkedList<>();
        int sum = 0;
        if (root == null)
            return sum;
        treeNodeDeque.offer(root);
        while (treeNodeDeque.size() > 0) {
            int size = treeNodeDeque.size();
            sum += size;//当前层结点个数计算
            for (int i = 0; i < size; i++) {
                TreeNode p = treeNodeDeque.poll();
                if (p.left != null)
                    treeNodeDeque.offer(p.left);
                if (p.right != null)
                    treeNodeDeque.offer(p.right);
            }
        }
        return sum;
    }

    //递归法：
    //DFS，后序遍历
    //时间复杂度：O(n)，空间复杂度：O(logn)（平均），最坏为O(n)
    public int countNodes2(TreeNode root) {
        if (root == null)
            return 0;
        int leftNumber = countNodes2(root.left);//左子树结点递归
        int rightNumber = countNodes2(root.right);//右子树结点递归
        return 1 + leftNumber + rightNumber;
    }

}
