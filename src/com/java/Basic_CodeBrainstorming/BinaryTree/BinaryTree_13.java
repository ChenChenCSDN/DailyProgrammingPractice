package com.java.Basic_CodeBrainstorming.BinaryTree;

import jdk.nashorn.internal.ir.Flags;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * ClassName: BinaryTree_13
 * Package: com.java.Basic_CodeBrainstorming.BinaryTree
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/4/2 10:30
 * @Version 1.0
 */
public class BinaryTree_13 {
    @Test
    public void test() {
        TreeNode n3 = new TreeNode(3, null, null);
        TreeNode n2 = new TreeNode(2, null, null);
        TreeNode n1 = new TreeNode(1, n2, n3);
        new Solution13().hasPathSum(n1, 1);
    }
}

class Solution13 {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        List<Integer> paths = new ArrayList<>();
        if (root == null)
            return false;
        return findPathSum(root, targetSum, paths);
    }

    //思路：同查找所有路径的思路，通过回溯找到所有叶节点的路径，计算路径和是否满足要求
    public boolean findPathSum(TreeNode p, int targerSum, List<Integer> paths) {
        paths.add(p.val);
        if (p.left == null && p.right == null) {//该节点为叶节点
            int pathsSum = 0;
            for (int i = 0; i < paths.size(); i++) {//计算当前路径之和
                pathsSum += paths.get(i);
            }
            if (pathsSum == targerSum)//当前路径之和满足要求
                return true;
        }
        if (p.left != null) {
            if (findPathSum(p.left, targerSum, paths) == true) {
                return true;
            }
            paths.remove(paths.size() - 1);//回溯
        }
        if (p.right != null) {
            if (findPathSum(p.right, targerSum, paths)) {
                return true;
            }
            paths.remove(paths.size() - 1);//回溯
        }
        return false;
    }
}
