package com.java.Basic_CodeBrainstorming.BinaryTree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * ClassName: BinaryTree_4
 * Package: com.java.Basic_CodeBrainstorming.BinaryTree
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/3/28 19:52
 * @Version 1.0
 */
public class BinaryTree_4 {

}

class Solution4 {
    //DFS递归
    public TreeNode invertTree(TreeNode root) {
        if (root == null)
            return null;
        invertTree(root.left);
        invertTree(root.right);
        swaoChildren(root);
        return root;
    }

    //BFS
    public TreeNode invertTree2(TreeNode root) {
        if (root == null)
            return null;
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (queue.size() > 0) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode p = queue.poll();
                swaoChildren(p);//翻转当前结点的左右子树
                if (p.left != null)
                    queue.offer(p.left);
                if (p.right != null)
                    queue.offer(p.right);
            }
        }
        return root;
    }

    public void swaoChildren(TreeNode root) {
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
    }
}
