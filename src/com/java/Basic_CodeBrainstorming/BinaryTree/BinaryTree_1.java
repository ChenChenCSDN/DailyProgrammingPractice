package com.java.Basic_CodeBrainstorming.BinaryTree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: BinaryTree_1
 * Package: com.java.Basic_CodeBrainstorming.BinaryTree
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/3/27 9:30
 * @Version 1.0
 */
public class BinaryTree_1 {
    @Test
    public void test() {
        TreeNode root = new TreeNode(1);
        root.left = null;
        TreeNode node1 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3, null, null);
        root.right = node1;
        node1.left = null;
        node1.right = node3;
        Solution1 solution1 = new Solution1();
        solution1.orderTraversal(root);
    }
}

class Solution1 {
    public List<Integer> orderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        preorder(root, result);
        return result;
    }

    //前序递归遍历二叉树
    public void preorder(TreeNode root, List<Integer> result) {
        if (root == null)//递归结束条件
            return;
        //前序遍历顺序：NLR（根左右）
        result.add(root.val);
        preorder(root.left, result);
        preorder(root.right, result);
    }

    //中序遍历二叉树
    public void inorder(TreeNode root, List<Integer> result) {
        if (root == null)//递归结束条件
            return;
        //中序遍历顺序：LNR（左根右）
        inorder(root.left, result);
        result.add(root.val);
        inorder(root.right, result);
    }

    //后序遍历二叉树
    public void postorder(TreeNode root, List<Integer> result) {
        if (root == null)//递归结束条件
            return;
        //后序遍历顺序：LRN（左右根）
        postorder(root.left, result);
        postorder(root.right, result);
        result.add(root.val);
    }
}