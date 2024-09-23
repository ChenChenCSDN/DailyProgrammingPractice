package com.java.Basic_CodeBrainstorming.BinaryTree;

import org.junit.Test;

import java.util.*;

/**
 * ClassName: BinaryTree_5
 * Package: com.java.Basic_CodeBrainstorming.BinaryTree
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/3/29 11:30
 * @Version 1.0
 */
public class BinaryTree_5 {
    @Test
    public void test() {
        TreeNode node4 = new TreeNode(3, null, null);
        TreeNode node3 = new TreeNode(3, null, null);
        TreeNode node2 = new TreeNode(2, null, node4);
        TreeNode node1 = new TreeNode(2, null, node3);
        TreeNode root = new TreeNode(1, node1, node2);
        System.out.println(new Solution5().isSymmetric(root));
    }
}

class Solution5 {
    //递归法
    public boolean isSymmetric(TreeNode root) {
        return compare(root.left, root.right);
    }

    public boolean compare(TreeNode left, TreeNode right) {
        if (left == null && right == null)
            return true;
        if (left == null || right == null || left.val != right.val)
            return false;
        boolean comparLeft = compare(left.left, right.right);//对称递归左节点的左子树和右节点的右子树
        boolean compareRight = compare(left.right, right.left);//对称递归左节点的右子树和右节点的左子树
        return comparLeft && compareRight;
    }

    //迭代法：
    //双端队列，相当于两个栈
    public boolean isSymmetric2(TreeNode root) {
        if (root == null)
            return false;
        Deque<TreeNode> deque = new LinkedList<>();
        deque.offerFirst(root.left);
        deque.offerLast(root.right);
        while (deque.size() > 0) {
            TreeNode leftNode = deque.pollFirst();
            TreeNode rightNode = deque.pollLast();
//            if (leftNode == null && rightNode == null)
//                continue;
//            if (leftNode == null && rightNode != null)
//                return false;
//            if (leftNode != null && rightNode == null)
//                return false;
//            if (leftNode.val != rightNode.val)
//                return false;
            //以上三个判断进行合并
            if (leftNode == null || rightNode == null || leftNode.val != rightNode.val)
                return false;
            //按对称顺序入队
            deque.offerFirst(leftNode.left);
            deque.offerLast(rightNode.right);
            deque.offerFirst(leftNode.right);
            deque.offerLast(rightNode.left);

        }
        return true;
    }

}
