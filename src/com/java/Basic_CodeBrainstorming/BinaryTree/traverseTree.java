package com.java.Basic_CodeBrainstorming.BinaryTree;

import java.util.*;

/**
 * ClassName: traverseTree
 * Package: com.java.Basic_CodeBrainstorming.BinaryTree
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/3/29 14:59
 * @Version 1.0
 */
public class traverseTree {
    public static void main(String[] args) {
        // 构造一个示例树
//        TreeNode root = new TreeNode(1);
//        root.left = new TreeNode(2);
//        root.right = new TreeNode(3);
//        root.left.left = new TreeNode(4);
//        root.left.right = new TreeNode(5);
//        root.right.left = new TreeNode(6);
//        root.right.right = new TreeNode(7);
        TreeNode node4 = new TreeNode(3, null, null);
        TreeNode node3 = new TreeNode(3, null, null);
        TreeNode node2 = new TreeNode(2, null, node4);
        TreeNode node1 = new TreeNode(2, null, node3);
        TreeNode root = new TreeNode(1, node1, node2);
        // 执行层序遍历并将结果打印出来
        traverseTree lot = new traverseTree();
        List<TreeNode> result = lot.traverseBinaryTree(root);
        for (TreeNode node : result) {
            if (node == null) {
                System.out.print("null ");
            } else {
                System.out.print(node.val + " ");
            }
        }
    }

    public List<TreeNode> traverseBinaryTree(TreeNode root) {
        Deque<TreeNode> queue = new LinkedList<>();
        List<TreeNode> list = new ArrayList<>();
        if (root == null)
            return list;
        queue.offer(root);
        while (queue.size() > 0) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode p = queue.poll();
                list.add(p);
                if (p != null) {//当前结点不为空，将其左右结点入队
                    queue.offer(p.left);
                    queue.offer(p.right);
                }
            }
        }
        return list;
    }
}

