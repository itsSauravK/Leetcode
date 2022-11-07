import java.util.*;

public class BoundaryOfBinaryTree {
    public void dfs(TreeNode root, List<Integer> res) {
        if (root == null)
            return;
        if (root.left == null && root.right == null)
            res.add(root.val);
        dfs(root.left, res);
        dfs(root.right, res);
    }

    public List<Integer> boundaryOfBinaryTree(TreeNode root) {

        List<Integer> result = new ArrayList<>();
        if (root == null)
            return result;
        result.add(root.val);
        //left boundary preorder
        leftBoundary(root.left, result);

        //adding child
        if (root.left != null || root.right != null)
            dfs(root, result);

        //right boundary post order
        rightBoundary(root.right, result);
        return result;
    }

    public void leftBoundary(TreeNode root, List<Integer> result) {
        if (root == null || (root.left == null && root.right == null))
            return;

        result.add(root.val);

        if (root.left != null)
            leftBoundary(root.left, result);
        else
            leftBoundary(root.right, result);

    }

    public void rightBoundary(TreeNode root, List<Integer> result) {
        if (root == null || (root.left == null && root.right == null))
            return;

        if (root.right != null) {
            rightBoundary(root.right, result);
        } else
            rightBoundary(root.left, result);

        result.add(root.val);
    }


}

/*
    The boundary of a binary tree is the concatenation of the root, the left boundary, the leaves ordered from left-to-right, and the reverse order of the right boundary.

        The left boundary is the set of nodes defined by the following:

        The root node's left child is in the left boundary. If the root does not have a left child, then the left boundary is empty.
        If a node in the left boundary and has a left child, then the left child is in the left boundary.
        If a node is in the left boundary, has no left child, but has a right child, then the right child is in the left boundary.
        The leftmost leaf is not in the left boundary.
        The right boundary is similar to the left boundary, except it is the right side of the root's right subtree.
        Again, the leaf is not part of the right boundary, and the right boundary is empty if the root does not have a right child.

        The leaves are nodes that do not have any children. For this problem, the root is not a leaf.

        Given the root of a binary tree, return the values of its boundary.



        Example 1: https://leetcode.com/problems/boundary-of-binary-tree/description/

*/
/*
-First find the left boundary, then all the leaves, then the right boundary/
-Make sure you do not add duplicate element as in case of [1], while adding leaf nodes
 */

*/
 */


//used bfs
//
//    public List<Integer> boundaryOfBinaryTreeWithBFS(TreeNode root) {
//
//        List<Integer> result = new ArrayList<>();
//        Queue<TreeNode> q = new LinkedList<>();
//        if(root.left != null || root.right != null)
//            result.add(root.val);
//        if(root.left != null && (root.left.left != null || root.left.right != null)){
//            q.add(root.left);
//            result.add(root.left.val);
//        }
//        //add left using bfs
//        while(!q.isEmpty()){
//            TreeNode temp = q.remove();
//            if(temp.left != null){
//                if(!(temp.left.left == null && temp.left.right == null))
//                    result.add(temp.left.val);
//                q.add(temp.left);
//
//            }else if(temp.right != null){
//                if(!(temp.right.left == null && temp.right.right == null))
//                    result.add(temp.right.val);
//                q.add(temp.right);
//            }
//
//        }
//
//        //adding child
//        dfs(root, result);
//        List<Integer> rightRes = new ArrayList<>();
//        //adding right using bfs
//        if(root.right != null && (root.right.left != null || root.right.right != null)){
//            q.add(root.right);
//            rightRes.add(root.right.val);
//        }
//
//        while(!q.isEmpty()){
//            TreeNode temp = q.remove();
//            if(temp.right != null){
//                if(!(temp.right.left == null && temp.right.right == null))
//                    rightRes.add(temp.right.val);
//                q.add(temp.right);
//
//            }else if(temp.left != null){
//                if(!(temp.left.left == null && temp.left.right == null))
//                    rightRes.add(temp.left.val);
//                q.add(temp.left);
//            }
//
//        }
//        Collections.reverse(rightRes);
//        System.out.println(rightRes);
//        result.addAll(rightRes);
//        return result;
//    }
