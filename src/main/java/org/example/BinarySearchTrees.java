package org.example;

import java.util.Stack;

public class BinarySearchTrees {

    TreeNode root;
    boolean flag;

    BinarySearchTrees(){

    }
    BinarySearchTrees(TreeNode root,boolean flag) {
        this.root = root;
        this.flag = flag;
        pushAll(root);
    }
    void insertNode(TreeNode node) {
        TreeNode main = root;
        if(root==null) {
            root = node;
            return;
        }
        while(true) {
            if(root.data > node.data){
                if(root.left != null)
                    root = root.left;
                else {
                    root.left = node;
                    break;
                }
            } else  if(root.data < node.data) {
                if(root.right != null)
                    root = root.right;
                else {
                    root.right = node;
                    break;
                }
            }
        }
        root = main;
    }

    void inOrder(TreeNode root) {
        if(root == null) return;
        inOrder(root.left);
        System.out.print(root.data+"\t");
        inOrder(root.right);
    }

    int ceil(int n) {
        int ceil = -1;
        TreeNode main = root;
        while(root!=null) {
            if(root.data == n) {
              ceil = root.data;
              break;
            }
            if(root.data>n) {
                ceil = root.data;
                root = root.left;
            } else if(root.data<n) {
                root = root.right;
            }
        }
        root = main;
        return ceil;
    }

    int c=0;
    void smallestElement(TreeNode root,int k) {
        if(root==null) return;
        smallestElement(root.left,k);
        if(c==k) return;
        c++;
        if(c==k) {
            System.out.println(root.data);
            return;
        }
        smallestElement(root.right,k);
    }

    boolean isValidBST(TreeNode node,int low,int high) {
        if(node == null) return true;
        if(node.data< low || node.data>high) return false;
        boolean left = isValidBST(node.left,low, node.data);
        boolean right = isValidBST(node.right,node.data,high);
        return left && right;
    }

    TreeNode deleteNode(TreeNode node,int key) {
        if(node == null) return node;
        TreeNode dummy = node;
        if(node.data == key) {
            return helper(node);
        }
        while(node !=null) {
            if(node.left != null && node.left.data == key) {
                node.left = helper(node.left);
                break;
            }
            else if(node.right != null && node.right.data == key) {
                node.right = helper(node.right);
                break;
            } else if(node.data>key) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return dummy;
    }

    TreeNode helper(TreeNode node) {
        if(node.left == null) {
            return node.right;
        } else if(node.right == null) {
            return node.left;
        }
        TreeNode nodeRight = node.right;
        TreeNode nodeLastRightOfLeft = getRight(node.left);
        nodeLastRightOfLeft.right = nodeRight;
        return node.left;
    }

    TreeNode getRight(TreeNode node) {
        if(node.right != null)
            return getRight(node.right);
        return node;
    }
    
    void doAll() {
        insertNode(new TreeNode(10));
        insertNode(new TreeNode(20));
        insertNode(new TreeNode(5));
        insertNode(new TreeNode(7));
        insertNode(new TreeNode(16));
        insertNode(new TreeNode(18));
        inOrder(root);
        System.out.println();
        System.out.println(ceil(14));

        System.out.println();
        smallestElement(root,5);

        System.out.println();
        System.out.println(isValidBST(root,Integer.MIN_VALUE,Integer.MAX_VALUE));

        System.out.println();
        TreeNode dummy = deleteNode(root,12);
        inOrder(dummy);
    }

    Stack<TreeNode> elements = new Stack<>();
    void pushAll(TreeNode node) {
        if(node == null) return;
        elements.push(node);
        if(flag)
            pushAll(node.left);
        else
            pushAll(node.right);
    }

    TreeNode next(){
        TreeNode node = elements.pop();
        if(flag)
            pushAll(node.right);
        else
            pushAll(node.left);
        return node;
    }

    void twoSum() {
        BinarySearchTrees bst =new BinarySearchTrees();
        bst.insertNode(new TreeNode(10));
        bst.insertNode(new TreeNode(20));
        bst.insertNode(new TreeNode(5));
        bst.insertNode(new TreeNode(7));
        bst.insertNode(new TreeNode(16));
        bst.insertNode(new TreeNode(18));
        bst.pushAll(bst.root);


        BinarySearchTrees left = new BinarySearchTrees(bst.root,true);
        BinarySearchTrees right = new BinarySearchTrees(bst.root,false);
        for(TreeNode t:left.elements){
            System.out.println(t.data);
        }
        TreeNode leftNode = left.next();
        TreeNode rightNode = right.next();
        while (leftNode!= null && rightNode != null){
            int x = leftNode.data;
            int y = rightNode.data;
            if(x+y<12){
                leftNode = left.next();
            } else if(x+y>12){
                rightNode = right.next();
            } else {
                System.out.println("left:"+leftNode.data+" and right:"+rightNode.data);
                break;
            }
        }
    }

    boolean hasNext() {
        return !elements.empty();
    }
}
