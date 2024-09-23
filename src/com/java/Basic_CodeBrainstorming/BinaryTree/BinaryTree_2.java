package com.java.Basic_CodeBrainstorming.BinaryTree;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * ClassName: BinaryTree_2
 * Package: com.java.Basic_CodeBrainstorming.BinaryTree
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/3/27 10:05
 * @Version 1.0
 */
public class BinaryTree_2 {
    @Test
    public void test() {

    }
}

class Solution {
    //迭代法实现前序遍历：借助栈实现，时间复杂度O(n)，空间复杂度O(n)
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();//存放遍历结果
        Deque<TreeNode> stack = new ArrayDeque<>();//栈暂存结点信息
        TreeNode p = root;//操作指针
        while (p != null || !stack.isEmpty()) {
            if (p != null) {//当前结点不为空，入栈，向左遍历
                result.add(p.val);
                stack.push(p);
                p = p.left;
            } else {//左节点为空，出栈，向右遍历
                p = stack.pop();
                p = p.right;
            }
        }
        return result;
    }

    //迭代法实现中序遍历：借助栈实现，时间复杂度O(n)，空间复杂度O(n)
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();//存放遍历结果
        Deque<TreeNode> stack = new ArrayDeque<>();//栈暂存结点信息
        TreeNode p = root;//操作指针
        while (p != null || !stack.isEmpty()) {
            if (p != null) {//当前结点不为空，不入栈，向左遍历
                stack.push(p);
                p = p.left;
            } else {//左节点为空，出栈，输出当前结点（加入到list中），向右遍历
                p = stack.pop();
                result.add(p.val);
                p = p.right;
            }
        }
        return result;
    }

    //迭代法实现后序遍历：借助栈实现，时间复杂度O(n)，空间复杂度O(n)
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();//存放遍历结果
        Deque<TreeNode> stack = new ArrayDeque<>();//栈暂存结点信息
        TreeNode p = root;//操作指针
        TreeNode r = null;//r用于指向最近访问过的结点
        while (p != null || !stack.isEmpty()) {
            if (p != null) {//扫描p的所有左子树，并入栈
                stack.push(p);
                p = p.left;
            } else {
                p = stack.peek();//获取栈顶元素
                if (p.right != null && p.right != r)
                    p = p.right;
                else {
                    p = stack.pop();
                    result.add(p.val);
                    r = p;//r记录最近访问过的结点
                    p = null;//重置指针
                }
            }
        }
        return result;
    }
}

