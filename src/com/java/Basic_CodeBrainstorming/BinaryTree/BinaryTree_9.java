package com.java.Basic_CodeBrainstorming.BinaryTree;

/**
 * ClassName: BinaryTree_9
 * Package: com.java.Basic_CodeBrainstorming.BinaryTree
 * Description:
 * 110. 平衡二叉树
 * 给定一个二叉树，判断它是否是
 * 平衡二叉树
 *
 * @Author chen_sir
 * @Create 2024/3/31 9:48
 * @Version 1.0
 */
public class BinaryTree_9 {

}

class Solution9 {
    public boolean isBalanced(TreeNode root) {
        if (root == null)
            return true;
        return treeHigh(root) != -1;
    }

    //思路：平衡二叉树是要计算该树的左右子树的高度差，因此分别求出其左右子树的高度，然后如果差值小于等于1，则返回当前二叉树的高度，否则返回-1，表示已经不是二叉平衡树了。
    //时间复杂度：O(n)，空间复杂度：O(logn)（平均），最坏为O(n)
    public int treeHigh(TreeNode root) {
        if (root == null)
            return 0;
        int leftHigh = treeHigh(root.left);
        if (leftHigh == -1)//高度差已经大于1，返回-1
            return -1;
        int rightHigh = treeHigh(root.right);//高度差已经大于1，返回-1
        if (leftHigh == -1 || leftHigh == -1 || Math.abs(leftHigh - rightHigh) > 1) {//判断高度差是否大于1，满足则返回-1
            return -1;
        } else {//高度差大于1，记录其高度值
            return Math.max(leftHigh, rightHigh) + 1;
        }
    }
}
