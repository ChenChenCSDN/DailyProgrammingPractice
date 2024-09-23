package com.java.Basic_CodeBrainstorming.BinaryTree;

import java.sql.ResultSet;
import java.util.Deque;
import java.util.LinkedList;

/**
 * ClassName: BinaryTree_12
 * Package: com.java.Basic_CodeBrainstorming.BinaryTree
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/4/2 10:09
 * @Version 1.0
 */
public class BinaryTree_12 {

}

class Solution12 {
    public int findBottomLeftValue(TreeNode root) {
        Deque<TreeNode> queue = new LinkedList<>();
        if (root == null)
            return 0;
        queue.offer(root);
        int result = 0;
        while (queue.size() > 0) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode p = queue.pop();
                if (i == 0)
                    result = p.val;
                if (p.left != null)
                    queue.offer(p.left);
                if(p.right!=null)
                    queue.offer(p.right);
            }
        }
        return result;
    }
}
