package com.java.Basic_CodeBrainstorming.BinaryTree;

import com.sun.corba.se.impl.orb.ParserTable;
import org.junit.Test;

import java.util.*;
import java.util.zip.Adler32;

/**
 * ClassName: BinaryTree_3
 * Package: com.java.Basic_CodeBrainstorming.BinaryTree
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/3/28 13:22
 * @Version 1.0
 */
public class BinaryTree_3 {
    @Test
    public void test() {
        Solution3 solution3 = new Solution3();
        TreeNode node4 = new TreeNode(7, null, null);
        TreeNode node3 = new TreeNode(15, null, null);
        TreeNode node2 = new TreeNode(20, node3, node4);
        TreeNode node1 = new TreeNode(9, null, null);
        TreeNode root = new TreeNode(3, node1, node2);
        List<List<Integer>> lists = solution3.levelOrder(root);
        for (List<Integer> list : lists) {
            System.out.println(list);
        }
    }
}

class Solution3 {
    //思路：使用队列来存储当前层的个数，然后根据队列的大小来进行循环遍历当前层的结点，时间复杂度：O(n)，空间复杂度：O(n)
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();//存储遍历结果
        Deque<TreeNode> queue = new LinkedList<>();//队列存储当前层的结点
        if (root == null)
            return result;
        queue.offer(root);//将非空的头结点入队
        int deep = 0;//树的高度
        while (queue.size() != 0) {//队列不为空，说明仍有结点未遍历
            int size = queue.size();//当前层的结点的个数
            List<Integer> curLayer = new ArrayList<>();
            for (int i = 0; i < size; i++) {//遍历当前层的结点
                TreeNode p = queue.poll();
                curLayer.add(p.val);
                if (p.left != null)
                    queue.offer(p.left);
                if (p.right != null)
                    queue.offer(p.right);
            }
            deep++;//用于记录树的高度
            result.add(curLayer);
        }
        List<List<Integer>> result1=new ArrayList<>(result.size());
        Collections.reverse(result);
        return result;
    }
}
