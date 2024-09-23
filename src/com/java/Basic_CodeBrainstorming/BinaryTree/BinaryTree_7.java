package com.java.Basic_CodeBrainstorming.BinaryTree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * ClassName: BinaryTree_7
 * Package: com.java.Basic_CodeBrainstorming.BinaryTree
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/3/29 16:23
 * @Version 1.0
 */
public class BinaryTree_7 {

}

class Solution7 {
    public int minDepth(TreeNode root) {
        if (root == null)
            return 0;
        int leftHigh = deepTree(root.left, 1);
        int rightHigh = deepTree(root.right, 1);
        return Math.min(leftHigh, rightHigh);
    }

    public int deepTree(TreeNode p, int high) {
        if (p == null)
            return high;
        if (p.left == null && p.right != null)
            return high;
        int leftHigh = deepTree(p.left, high + 1);
        int righHigh = deepTree(p.right, high + 1);
        return Math.min(leftHigh, righHigh);
    }

    /**
     * 迭代法，层序遍历
     */
    public int minDepth2(TreeNode root) {
        if (root == null)
            return 0;
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int deep = 0;
        int minHigh = 0;
        while (queue.size() > 0) {
            int size = queue.size();
            deep++;
            for (int i = 0; i < size; i++) {
                TreeNode p = queue.poll();
                if (p.left != null)
                    queue.offer(p.left);
                if (p.right != null)
                    queue.offer(p.right);
                if (p.left == null && p.right == null) {// 是叶子结点，直接返回depth，因为从上往下遍历，所以该值就是最小值
                    return deep;
                }
            }
        }
        return deep;
    }
}
