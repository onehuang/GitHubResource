package com.hzb;

/**
 * Created by zibeen on 2017/9/29.
 */
public class learn {
    public static  void  main(String[] args){
        LinkedList ll = new LinkedList();
        ll.initLinkedNode("a b b b c c c d d e e");
//        ll.deleteRepeteNode();
        ll.deleteRepeteAllNode();
        ll.printLinkedlist();
    }
}

class Node{
    public String name;
    public Node next;

    public Node(String name){
        this.name = name;
    }

    public Node(){
        this.name = "";
    }
}

///////////////////////////////////////////////////////////////////////////
// 链表的去重
///////////////////////////////////////////////////////////////////////////
class  LinkedList{

    private Node head;

    /**
     * 删除所有重复的元素
     */
    public void deleteRepeteAllNode(){
        Node pre = head.next;
        Node cur, cur1;
        while(pre!=null){
            cur= pre.next;
            cur1 = cur.next;
            while ( (cur1 != null) && (cur1.name.equals(cur.name)) ){
                cur1 = cur1 .next;
            }
            pre = cur1;

        }
    }
    /**
     * 只用来删除有序的节点
     * 头节点为空
     */
    public void  deleteRepeteNode(){
        Node pre = head.next;
        Node cur;
        while ( pre!=null) {
            cur = pre.next;
            if ( (cur != null) && (cur.name.equals(pre.name))){
                pre.next = cur.next;
            }else{
                pre = cur;
            }

        }
    }

    public void initLinkedNode(String str){
      String[] ss = str.split(" ");
      head = new Node();
      Node pre = head;
      for(int i=0; i< ss.length ;i++){
         pre.next = new Node(ss[i]);
         pre = pre.next;
      }
    }

    public  void  printLinkedlist(){
        Node pre = head;
        while(pre!=null){
            System.out.print(pre.name + " ");
            pre = pre.next;
        }
        System.out.println();
    }
}