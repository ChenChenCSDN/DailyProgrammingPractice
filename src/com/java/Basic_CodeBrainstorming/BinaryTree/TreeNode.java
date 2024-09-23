package com.java.Basic_CodeBrainstorming.BinaryTree;

/**
 * ClassName: TreeNode
 * Package: com.java.Basic_CodeBrainstorming.BinaryTree
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/3/27 9:30
 * @Version 1.0
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
