package org.example;

import java.util.*;

class TreeNode {
    int data;
    TreeNode left;
    TreeNode right;
    TreeNode(int data) {
        this.data = data;
    }
}
public class BinaryTrees {

    BinaryTrees() {

    }

    TreeNode constructTree() {
        TreeNode root = new TreeNode(1);
        TreeNode rootLeft = new TreeNode(2);
        TreeNode rootRight = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(8);
        TreeNode node9 = new TreeNode(9);
        TreeNode node10 = new TreeNode(10);
        TreeNode node11 = new TreeNode(11);
        root.left = rootLeft;
        root.right = rootRight;
        rootLeft.left = node4;
        rootRight.left = node5;
        node4.left = node6;
        node4.right = node7;
        node5.left = node8;
        rootRight.right = node9;
       // node7.left = node10;
        rootLeft.right = node11;
        node11.right = node10;
        return  root;
    }

    void preOrder(TreeNode root) {
        if(root == null) return;
        System.out.print(root.data+"\t");
        preOrder(root.left);
        preOrder(root.right);
    }

    void postOrder(TreeNode root) {
        if(root == null) return;
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.data+"\t");
    }

    void inOrder(TreeNode root) {
        if(root == null) return;
        inOrder(root.left);
        System.out.print(root.data+"\t");
        inOrder(root.right);
    }

    private Queue<TreeNode> queueForLevelOrder = new LinkedList<TreeNode>();
    void levelOrder(TreeNode root) {
            if(root == null) return;
            queueForLevelOrder.add(root);
            while (!queueForLevelOrder.isEmpty()) {
                TreeNode t = queueForLevelOrder.poll();
                System.out.print(t.data+"\t");
                if (t.left != null) queueForLevelOrder.add(t.left);
                if (t.right != null) queueForLevelOrder.add(t.right);
            }
    }

    void  dfsTraversal() {
        TreeNode root = constructTree();
        System.out.println("====preOrder====");
        preOrder(root);
        System.out.println();
        System.out.println("====inOrder====");
        inOrder(root);
        System.out.println();
        System.out.println("====postOrder====");
        postOrder(root);
    }

    void bfsTraversal() {
        TreeNode root = constructTree();
        System.out.println("====levelOrder====");
        levelOrder(root);
    }

    void getTraversals() {
        dfsTraversal();
        System.out.println();
        bfsTraversal();
        System.out.println();
    }

    void printLeafNodes(TreeNode root) {
        if(root == null) return;
        if(root.right ==null && root.left==null) {
            System.out.print(root.data+"\t");
        }
        printLeafNodes(root.right);
        printLeafNodes(root.left);
    }

    private int maxElement = Integer.MIN_VALUE;
    private Queue<TreeNode> queueForMax = new LinkedList<TreeNode>();
    void printMaximumElement(TreeNode root) {
        if(root == null) return;
        queueForMax.add(root);
        while (!queueForMax.isEmpty()) {
            TreeNode t = queueForMax.poll();
            if(t.data> maxElement) maxElement = t.data;
            if(t.left != null) queueForMax.add(t.left);
            if(t.right != null) queueForMax.add(t.right);
        }
    }

    int getMaxElement() {
        TreeNode root = constructTree();
        printMaximumElement(root);
        return maxElement;
    }

    private int numberOfLeafNodes = 0;
    void countLeafNodes(TreeNode root) {
        if(root == null) return;
        if(root.right == null && root.left == null) {
            numberOfLeafNodes++;
        }
        countLeafNodes(root.right);
        countLeafNodes(root.left);
    }
    int getNumberOfLeafNodes() {
        TreeNode root = constructTree();
        countLeafNodes(root);
        return numberOfLeafNodes;
    }

    void printAllPathsFromRootToLeafNodes(TreeNode root, int[] path,int len) {
        if(root == null) return;
        path[len] = root.data;
        len++;
        if(root.left == null && root.right == null) {
            for (int i = 0; i < len; i++) {
                System.out.print(" "+path[i]);
            }
            System.out.println();
            return;
        }
        printAllPathsFromRootToLeafNodes(root.left,path,len);
        printAllPathsFromRootToLeafNodes(root.right,path,len);
    }

    private HashMap<Integer,Integer> mapForTopView = new HashMap<Integer, Integer>();
    private Queue<Pair> queueForTopView = new LinkedList<Pair>();
    void topView(TreeNode root,int level) {
        if(root == null) return;
        queueForTopView.add(new Pair(root,level));
        while (!queueForTopView.isEmpty()) {
            Pair pair = queueForTopView.remove();
            TreeNode currentNode = pair.node;
            int line = pair.level;
            if(mapForTopView.get(line) == null) {
                mapForTopView.put(line,currentNode.data);
            }
            if(currentNode.left != null) queueForTopView.add(new Pair(currentNode.left,line-1));
            if(currentNode.right != null) queueForTopView.add(new Pair(currentNode.left,line+1));
        }
    }

    private HashMap<Integer,Integer> mapForBottomView = new HashMap<Integer, Integer>();
    private Queue<Pair> queueForBottomView = new LinkedList<Pair>();
    void bottomView(TreeNode root,int level) {
        if(root == null) return;
        queueForBottomView.add(new Pair(root,level));
        while (!queueForBottomView.isEmpty()) {
            Pair pair = queueForBottomView.poll();
            TreeNode currentNode = pair.node;
            int line = pair.level;
            mapForBottomView.put(line,currentNode.data);
            if(currentNode.left != null) queueForBottomView.add(new Pair(currentNode.left,line-1));
            if(currentNode.right != null) queueForBottomView.add(new Pair(currentNode.left,line+1));
        }
    }

    private ArrayList<Integer> listForLeftView = new ArrayList<>();
    void leftView(TreeNode root,int level) {
        if(root == null) return;
        if(level == listForLeftView.size()) listForLeftView.add(root.data);
        leftView(root.left,level+1);
        leftView(root.right,level+1);
    }

    private ArrayList<Integer> listForRightView = new ArrayList<>();
    void rightView(TreeNode root,int level) {
        if(root == null) return;
        if(level == listForRightView.size()) listForRightView.add(root.data);
        rightView(root.right,level+1);
        rightView(root.left,level+1);
    }

    void allViews() {
        TreeNode root = constructTree();
        System.out.println("====top view====");
        topView(root,0);
        for (Map.Entry<Integer, Integer> entry : mapForTopView.entrySet()) {
            System.out.print(entry.getValue()+"\t");
        }
        System.out.println();
        System.out.println("====bottom view====");
        bottomView(root,0);
        for (Map.Entry<Integer, Integer> entry : mapForBottomView.entrySet()) {
            System.out.print(entry.getValue()+"\t");
        }
        System.out.println();
        System.out.println("====Left view====");
        leftView(root,0);
        System.out.println(listForLeftView.toString());

        System.out.println("====Right view====");
        rightView(root,0);
        System.out.println(listForRightView.toString());

    }

    int getHeightOftree(TreeNode root) {
        if(root == null) return 0;

        int left = getHeightOftree(root.left);
        int right = getHeightOftree(root.right);

        return 1+Math.max(left,right);
    }

    void printAllPathsFromRootToNode(TreeNode root, int[] path,int len,int x) {
        if(root == null) return;
        path[len] = root.data;
        len++;
        if(root.data == x) {
            for (int i = 0; i < len; i++) {
                System.out.print(" "+path[i]);
            }
            System.out.println();
            return;
        }
        printAllPathsFromRootToNode(root.left,path,len,x);
        printAllPathsFromRootToNode(root.right,path,len,x);
    }

    int getlca(TreeNode root,int x,int y) {
        if(root == null) return -1;
        if(root.data == x || root.data == y){
            return root.data;
        }
        int left = getlca(root.left,x,y);
        int right = getlca(root.right,x,y);
        if(left != -1 && right != -1) {
            System.out.println(root.data);
            return root.data;
        }
        if(left != -1 || right != -1) {
            return root.data;
        }
        return -1;
    }

    int maxSum = 0;
    int getMaxSumPath(TreeNode root) {
        if(root == null) return 0;
        int left = getMaxSumPath(root.left);
        int right = getMaxSumPath(root.right);
        maxSum = Math.max(maxSum,root.data+left+right);
        return root.data+Math.max(left,right);
    }

    int maxDiameter = 0;
    int getMaxDiameter(TreeNode root) {
        if(root == null) return 0;
        int left = getMaxDiameter(root.left);
        int right = getMaxDiameter(root.right);
        maxDiameter = Math.max(maxDiameter,1+left+right);
        return 1+Math.max(left,right);
    }

    boolean isSymmetric(TreeNode root) {
        return root==null || symmetricHelp(root.left,root.right);
    }
    
    boolean symmetricHelp(TreeNode left, TreeNode right){
        if(left == null || right == null) return  left==right;
        if(left.data != right.data) return  false;
        return symmetricHelp(left.left,right.right) && symmetricHelp(left.right,right.left);
    }

    int isBalanced(TreeNode root) {
        if(root == null) return 0;
        int left = isBalanced(root.left);
        int right = isBalanced(root.right);
        if(left == -1 || right == -1) return -1;
        if(Math.abs(left-right) > 1) return -1;
        return 1+Math.max(left,right);
    }
    
    void doAll() {
        getTraversals();
        TreeNode root = constructTree();
        System.out.println("====leaf nodes====");
        printLeafNodes(root);
        System.out.println();
        System.out.println("====max element====");
        System.out.println(getMaxElement());
        System.out.println("====no of leafnodes====");
        System.out.println(getNumberOfLeafNodes());
        System.out.println();
        System.out.println("====all paths to leaf nodes====");
        printAllPathsFromRootToLeafNodes(root,new int[1000],0);
        System.out.println();
        System.out.println("====all views====");
        allViews();
        System.out.println("====height of tree====");
        System.out.println(getHeightOftree(root));
        System.out.println("====print path from root to 8====");
        printAllPathsFromRootToNode(root,new int[100],0,8);

        System.out.println("====lca for 6 and 11====");
        getlca(root,6,8);

        System.out.println("====max sum path====");
        getMaxSumPath(root);
        System.out.println(maxSum);

        System.out.println("====max diameter====");
        getMaxDiameter(root);
        System.out.println(maxDiameter-1);
        System.out.println("====is tree balanced?====");
        if(isBalanced(root) != 1) {
            System.out.println("yes");
        } else System.out.println("no");
    }

}

class Pair {
    TreeNode node;
    int level;

    Pair(TreeNode node,int level) {
        this.node = node;
        this.level = level;
    }
}
