package com.hzb.interview;

import java.util.Stack;

public class DataStructure {
    public static void main(String[] args){
        new binaryTreeVisit().run();
    }
}

class binaryTreeVisit{
    public void run(){
        BinaryTree node = new BinaryTree("A");
        BinaryTree root = node;

        BinaryTree node1 = new BinaryTree("B");
        node.setLeft(node1);

        BinaryTree node2 = new BinaryTree("C");
        node.setRight(node2);

        BinaryTree node3 = new BinaryTree("D");
        node1.setRight(node3);

        BinaryTree node4 = new BinaryTree("E");
        node3.setRight(node4);
        BinaryTree node5 = new BinaryTree("F");
        node3.setLeft(node5);

        postVisitTree(root);
    }

    class BinaryTree{

        private String name;
        private BinaryTree left;
        private BinaryTree right;

        public BinaryTree(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
        public BinaryTree getLeft() {
            return left;
        }

        public void setLeft(BinaryTree left) {
            this.left = left;
        }

        public BinaryTree getRight() {
            return right;
        }

        public void setRight(BinaryTree right) {
            this.right = right;
        }
        public void visit(){
            System.out.print(name + " ");
        }
    }

    //非递归后序遍历
    public void postVisitTree(BinaryTree root){
        Stack<BinaryTree> stack = new Stack();
        for(BinaryTree  node = root; !stack.empty() || node != null; ){
            //将左节点放入栈中
            while(node != null){
                stack.push(node);
                node = node.getLeft();
            }
            //弹出节点
            while(!stack.empty() && node == stack.peek().getRight()){
                node = stack.pop();
                node.visit();
            }

            //
            if(stack.empty()){
                return;
            }else{
                node = stack.peek().getRight();
            }
        }

    }

}
