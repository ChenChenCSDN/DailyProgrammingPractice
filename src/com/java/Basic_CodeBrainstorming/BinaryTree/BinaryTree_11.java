package com.java.Basic_CodeBrainstorming.BinaryTree;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * ClassName: BinaryTree_11
 * Package: com.java.Basic_CodeBrainstorming.BinaryTree
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/4/1 13:56
 * @Version 1.0
 */
public class BinaryTree_11 {
    @Test
    public void test() {
        TreeNode n5 = new TreeNode(7, null, null);
        TreeNode n4 = new TreeNode(15, null, null);
        TreeNode n3 = new TreeNode(20, n4, n5);
        TreeNode n2 = new TreeNode(9, null, null);
        TreeNode n1 = new TreeNode(3, n2, n3);
        System.out.println(new Solution11().sumOfLeftLeaves2(n1));
    }
}

class Solution11 {
    //本题重点在于明确什么是左节点：如果该节点的左节点不为空，该节点的左节点的左节点为空，该节点的左节点的右节点为空，则找到了一个左叶子
    //法一：基于层序迭代遍历
    public int sumOfLeftLeaves(TreeNode root) {
        Deque<TreeNode> queue = new LinkedList<>();
        int sum = 0;
        if (root == null)
            return 0;
        queue.offer(root);
        while (queue.size() > 0) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode p = queue.poll();
                if (p.left != null && p.left.left == null && p.left.right == null)
                    sum += p.left.val;
                if (p.left != null)
                    queue.offer(p.left);
                if (p.right != null)
                    queue.offer(p.right);
            }
        }
        return sum;
    }

    //法二：根据前序遍历求和
    public int sumOfLeftLeaves2(TreeNode root) {
        if (root == null)
            return 0;
        int sum = 0;
        if (root.left != null && root.left.left == null && root.left.right == null)
            sum += root.left.val;
        sum += sumOfLeftLeaves2(root.left);
        sum += sumOfLeftLeaves2(root.right);
        return sum;
    }

    //法三：基于前序迭代遍历
    public int sumOfLeftLeaves3(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode p = root;
        int sum = 0;
        while (p != null || stack.size() > 0) {
            if (p != null) {
                if (p.left != null && p.left.left == null && p.left.right == null)
                    sum += p.left.val;
                stack.push(p);
                p = p.left;
            } else {
                p = stack.pop();
                p = p.right;
            }
        }
        return sum;
    }

}
