package com.hzb;

public class linkedlistReversal{
  /**
     date: 2018-06-14
      ������ת

  */


  public static void main(String[] args) {
      NodeLinked linked = initLinked();

      print(linked);

      // linked = reversalLinkedByHeadInsertMethod(linked);

      linked = reversallInkedByNewLinkedList(linked);

      print(linked);

  }

  /***
   β�巨
  */
  public static NodeLinked reversalLinkedByTailInsertMethod(NodeLinked head){
       System.out.println("reversalLinkedByTailInsertMethod");
       return null;
  }

  /**
  ͷ�巨
  ԭʼ  1 3 4 5 6 3 4
  ����1 3 1 4 5 6 3 4
  ����2 4 3 1 5 6 3 4
  ����3 5 4 3 1 6 3 4
  ��������
  */
  public static NodeLinked reversalLinkedByHeadInsertMethod(NodeLinked head){

    NodeLinked temp = new NodeLinked("");
    temp.next = head;
    while(head.next != null ){
      System.out.println(head.value);
      NodeLinked node = head.next;
      head.next = node.next;
      node.next = temp.next;
      temp.next = node;
    }
    return temp.next;
  }

  /**
     ÿһ���ڵ�����������ͷ�ڵ�
      ͷ�ڵ��ڸı�
     ԭʼ   1 3 4 5 6 3 4  ��
     ����1  3 4 5 6 3 4       1
     ����2  4 5 6 3 4         3 1

  */
  public static NodeLinked reversallInkedByNewLinkedList(NodeLinked head){

        NodeLinked temp = null;
        while(head != null){
          System.out.println(head.value);
          NodeLinked node = head.next;
          head.next = temp;
          temp = head;
          head = node;
        }

        return temp;
  }

  public static NodeLinked initLinked(){
    // 1 3 4 5 6 3 4
    NodeLinked head = new NodeLinked("1");
    head.next = new NodeLinked("3",
              new NodeLinked("4",
                      new NodeLinked("5",
                             new NodeLinked("6",
                                     new NodeLinked("3",
                                         new NodeLinked("4"))))));

    return head;
  }

  public static void print(NodeLinked head){
     while(head != null ){
       System.out.print(head.value);
       head = head.next;
     }

     System.out.println();
  }


}

class NodeLinked{
  public  String value;
  public NodeLinked next;

  NodeLinked(String value){
    this.value = value;
    this.next = null;
  }

  NodeLinked(String value, NodeLinked next){
    this.value = value;
    this.next = next;
  }
}
