package com.java.Basic_CodeBrainstorming.BinaryTree;

import org.junit.Test;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: BinaryTree_10
 * Package: com.java.Basic_CodeBrainstorming.BinaryTree
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/4/1 9:34
 * @Version 1.0
 */
public class BinaryTree_10 {
    @Test
    public void test() {
        TreeNode n5 = new TreeNode(5, null, null);
        TreeNode n4 = new TreeNode(4, null, null);
        TreeNode n3 = new TreeNode(3, null, null);
        TreeNode n2 = new TreeNode(2, n4, n5);
        TreeNode n1 = new TreeNode(1, n2, n3);
        List<String> strings = new Solution10().binaryTreePaths(n1);
        System.out.println(strings);

    }
}

class Solution10 {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        List<Integer> paths = new ArrayList<>();// 作为结果中的路径
        if (root == null)
            return result;
        findTreePaths(root, paths, result);
        findTreePaths2(root, "");
        System.out.println(result);
        return result;
    }

    //思路：基于前序递归遍历和回溯法方式一
    public void findTreePaths(TreeNode root, List<Integer> paths, List<String> result) {
        paths.add(root.val);// 前序遍历，中
        // 遇到叶子结点
        if (root.left == null && root.right == null) {
            // 输出
            StringBuilder s = new StringBuilder();// StringBuilder用来拼接字符串，速度更快
            for (int i = 0; i < paths.size() - 1; i++) {// 记录前n-1个节点
                s.append(paths.get(i) + "->");
            }
            s.append(paths.get(paths.size() - 1));// 记录最后一个节点
            result.add(s.toString());// 收集一个路径
            return;
        }
        // 递归和回溯是同时进行，所以要放在同一个花括号里
        if (root.left != null) {
            findTreePaths(root.left, paths, result);
            paths.remove(paths.size() - 1);//回溯
        }
        // 递归和回溯是同时进行，所以要放在同一个花括号里
        if (root.right != null) {
            findTreePaths(root.right, paths, result);
            paths.remove(paths.size() - 1);//回溯
        }
    }

    //思路：基于前序递归遍历和回溯法方式二
    List<String> result = new ArrayList<>();

    public void findTreePaths2(TreeNode root, String s) {
        if (root == null)
            return;
        if (root.left == null && root.right == null) {
            s = s.concat(String.valueOf(root.val));
            result.add(s);
            return;
        }
        StringBuilder sb = new StringBuilder(s).append(root.val).append("->");//拼接前n-1个节点，并实现回溯
        findTreePaths2(root.left, sb.toString());
        findTreePaths2(root.right, sb.toString());
    }
}
