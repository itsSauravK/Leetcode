public class LowestCommonAncestorOfBinaryTree {

    //Approach 1 - Here I am finding the parent node and putting it in map.
    //Then I am traversing from p to null and put everything in visited.
    //Then I traverse from q, and then check if i reach a node already in visited

    public TreeNode lowestCommonAncestorParent(TreeNode root, TreeNode p, TreeNode q) {
        //dfs to find everyone's parent'
        Map<TreeNode, TreeNode> map = new HashMap<>();

        dfs(root, null, map);

        //now we iterate p through the map until we reach null or we reach q. We keep visited
        Set<TreeNode> visited = new HashSet<>();
        TreeNode temp = p;

        while(temp != null){
            visited.add(temp);
            temp = map.get(temp);
        }
        temp = q;

        while(temp != null){
            if(visited.contains(temp))
                return temp;
            temp = map.get(temp);
        }

        return null;
    }

    public void dfs(TreeNode root, TreeNode parent, Map<TreeNode, TreeNode> map){
        if(root == null)
            return;
        map.put(root, parent);
        dfs(root.left, root, map);
        dfs(root.right, root, map);

    }
    //Approach 2 - Single traversal, if we find p or q then we return that.
    //if a node receives p and q then it is the parent. For cases, where p and q itself is the node then root
    // will receive null from one branch and p or q from other. So, the answer will be p or q

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q){
        if(root == null || root == p || root == q)
            return root;

        TreeNode left = lowestCommonAncestor(root.left, p , q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if(left == null)
            return right;

        else if(right == null)
            return left;

        else
            return root;
    }
}
