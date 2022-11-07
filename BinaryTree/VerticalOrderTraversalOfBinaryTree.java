public class VerticalOrderTraversalOfBinaryTree {
    //time complextiy  - O(nlogn)
    //space complexity - O(n)
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        //This list store index of elements along with the value, eg [key: 0 value: all elements at 0 and their height], this index can store negative values as well
        Map<Integer, PriorityQueue<int []>> store = new HashMap<>();
        //This queue is to store index, i.e, -1 when we go left. +1 when we go right
        Queue<Integer> indexQ = new LinkedList<>();
        Queue<TreeNode> q = new LinkedList<>();

        List<List<Integer>> result = new ArrayList<>();

        if(root == null)
            return result;

        //store minimum index, at the end we just do index stored in store + Math.abs(minimumindex)
        int min = 0;
        //int index = 0;
        q.add(root);
        indexQ.add(0);
        int height = -1;
        while(!q.isEmpty()){
            int n = q.size();
            //ystem.out.println(n);
            height++;
            for(int i = 0; i < n; i++){
                TreeNode temp = q.remove();
                int index = indexQ.remove();
                //adding elements in store
                //we are checking if the height is same, if yes then we sort by value, else we sort by height
                if(!store.containsKey(index))
                    store.put(index, new PriorityQueue<>((a,b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]));
                //store.(new int[]{index, temp.val});
                store.get(index).add(new int[]{height, temp.val});
                //we store the minimum value of index present, so at the end we can do all keys + min
                if(min > index)
                    min = index;

                if(temp.left != null){
                    q.add(temp.left);
                    indexQ.add(index - 1);
                }

                if(temp.right!=null){
                    q.add(temp.right);
                    indexQ.add(index + 1);
                }
            }

        }
        //intializing arraylist
        for(int i = 0; i < store.size(); i++){
            result.add(i, new ArrayList<>());
        }

        for(int key : store.keySet()){
            int index = key - min;
            PriorityQueue<int[]> temp = store.get(key);
            List<Integer> singleIndexRes = new ArrayList<>();
            //traversing through pq
            while(!temp.isEmpty()){
                int pq[] = temp.remove();
                singleIndexRes.add(pq[1]);
            }
            result.get(index).addAll(singleIndexRes);

        }
        return result;
    }
}
/*

Given the root of a binary tree, calculate the vertical order traversal of the binary tree.

For each node at position (row, col), its left and right children will be at positions (row + 1, col - 1) and (row + 1, col + 1) respectively. The root of the tree is at (0, 0).

The vertical order traversal of a binary tree is a list of top-to-bottom orderings for each column index starting from the leftmost column and ending on the rightmost column. There may be multiple nodes in the same row and same column. In such a case, sort these nodes by their values.

Return the vertical order traversal of the binary tree.

https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/description/
 */

/*
My approach- got left then -1 index, right then +1 index. Another queue to store indexes, map with keys as indexes and value as pq
with values present at that index along with height. Sprt the pq based on height if height is not equal, otherwise sort by the elemet.
Keep track of least index so at the end we can indexes + leastIndex

 */