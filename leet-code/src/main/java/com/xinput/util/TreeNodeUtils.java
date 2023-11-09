package com.xinput.util;

import com.xinput.model.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:xinput.xx@gmail.com">xinput</a>
 * @since
 */
public class TreeNodeUtils {

    /**
     * 前序遍历
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList();
        // 前序排列，即根节点最先遍历
        preorder(root, result);
        return result;
    }

    public void preorder(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }

        result.add(root.getVal());
        preorder(root.getLeft(), result);
        preorder(root.getRight(), result);
    }

    /**
     * 中序遍历
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList();
        // 中序排列，左根右
        inorder(root, result);
        return result;
    }

    public void inorder(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }

        inorder(root.getLeft(), result);
        result.add(root.getVal());
        inorder(root.getRight(), result);
    }

    /**
     * 后序遍历
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList();
        // 后序排列，左右根
        postorder(root, result);
        return result;
    }

    public void postorder(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }

        postorder(root.getLeft(), result);
        postorder(root.getRight(), result);
        result.add(root.getVal());
    }
}
