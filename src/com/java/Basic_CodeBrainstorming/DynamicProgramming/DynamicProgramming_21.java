package com.java.Basic_CodeBrainstorming.DynamicProgramming;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: DynamicProgramming_21
 * Package: com.java.Basic_CodeBrainstorming.DynamicProgramming
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/8/16 16:42
 * @Version 1.0
 */
public class DynamicProgramming_21 {
    public static void main(String[] args) {
        //treeNode6 = [3,4,5,1,3,null,1]树的初始化
        TreeNode treeNode1 = new TreeNode(1, null, null);
        TreeNode treeNode2 = new TreeNode(3, null, null);
        TreeNode treeNode3 = new TreeNode(1, null, null);
        TreeNode treeNode4 = new TreeNode(4, treeNode1, treeNode2);
        TreeNode treeNode5 = new TreeNode(5, null, treeNode3);
        TreeNode treeNode6 = new TreeNode(3, treeNode4, treeNode5);
        new Solution21().rob(treeNode6);


    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution21 {
    /**
     * 动态规划：本题与之前的打家劫舍题意相同，但区别在于房屋之间是通过二叉树进行连接，故需要通过遍历二叉树为基础进行求解，而本题只能使用后序遍历，因为当前结点偷还是不偷取决于其左孩子和右孩子的情况，故只能使用后序遍历。
     * 而每个结点无非就两种情况，偷或者不偷，如果当前结点偷的话，那么其左右孩子一定不偷，而当前结点不偷的话，其左右孩子都可以偷，因此可以使用大小为2的数组来记录当前结点的情况。
     * 确定dp数组及其下标含义：大小为2的dp数组用于记录每个结点的状态信息，dp[0]表示当前结点不偷的最大金额，dp[1]表示偷当前结点的最大金额
     * 确定dp数组递推公式：如果偷当前结点，那么左右孩子都不能偷：val1 = cur->val + left[0] + right[0]，如果不偷当前结点，那么左右孩子都可以偷，至于左右孩子到底偷不偷是根据其最大金融来的，所以：val2 = max(left[0], left[1]) + max(right[0], right[1])，
     * 而在递归过程中，递归栈会保存每一层递归的参数，因此res数组用于记录当前结点的情况，left用于记录左孩子结点的情况，right用于记录右孩子结点的情况。
     * 确定dp数组的遍历顺序：因为当前结点偷还是不偷取决于其左孩子和右孩子的情况，故只能使用后序遍历。通过递归左节点，得到左节点偷与不偷的金钱。通过递归左节点，得到左节点偷与不偷的金钱。然后记录当前结点偷与不偷的金钱。
     * 最后整颗树遍历完成后，res[0]就表示不偷根结点的情况下偷取整颗树的最大金额，res[1]就表示偷根节点的情况下偷取整棵树的最大金额。
     * 时间复杂度：O(n)(后序遍历二叉树的时间复杂度），空间复杂度：O(n)(递归调用栈的深度）
     *
     * @param root 待偷取的房屋二叉树
     * @return 能偷取的最大金额
     */
    public int rob(TreeNode root) {
        int[] res = RobSolution(root);
        return Math.max(res[0], res[1]);
    }

    public int[] RobSolution(TreeNode root) {
        //初始化dp数组，记录当前结点的情况
        int[] res = new int[2];
        //空结点，即偷与不偷都为0，直接返回
        if (root == null)
            return res;
        //left为当前结点的左孩子的情况记录
        int[] left = RobSolution(root.left);
        //right为当前结点的右孩子的情况记录
        int[] right = RobSolution(root.right);
        //当前结点不偷的情况
        //错误写法：当前结点不偷，左右孩子只能偷一个，至于偷哪一个取决于最大值
//        res[0] = Math.max(left[0] + right[1], left[1] + right[0]);
        //正确写法：当前结点不偷，左右孩子都可以偷，而左右孩子偷还是不偷取最大值

        //res为当前结点的情况记录
        res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        res[1] = root.val + left[0] + right[0];
        //返回当前结点的情况记录给上一层
        return res;
    }

}
