package ALeetCode;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2021-10-10
 */

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class CBTInserter {
    private final TreeNode root;
    Queue<TreeNode> treeNodeQueue = new ArrayDeque<>();

    public CBTInserter(TreeNode root) {
        this.root = root;
        treeNodeQueue.add(root);
        while (!treeNodeQueue.isEmpty()) {
            TreeNode node = treeNodeQueue.peek();
            if (node.left != null && node.right != null) {
                treeNodeQueue.poll();
                treeNodeQueue.add(node.left);
                treeNodeQueue.add(node.right);
                continue;
            }
            if (node.left != null) {
                treeNodeQueue.add(node.left);
            }
            break;
        }
    }

    public int insert(int val) {
        TreeNode current = new TreeNode(val);
        TreeNode father = treeNodeQueue.peek();
        if(father.left == null){
            father.left = current;
        }else {
            father.right = current;
            treeNodeQueue.poll();
        }
        treeNodeQueue.add(current);
        return father.val;
    }

    public TreeNode get_root() {
        return root;
    }

}

/**
 * Your CBTInserter object will be instantiated and called as such:
 * CBTInserter obj = new CBTInserter(root);
 * int param_1 = obj.insert(val);
 * TreeNode param_2 = obj.get_root();
 */