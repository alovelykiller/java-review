package com.chenms.datastructuresandalgorithms;

public class SinglyLinkListReverse {

    public class Node{
        private Node next;
        private int data;
    }
    public static void printReverseChain(Node curNode) {
        if(curNode != null) {
            printReverseChain(curNode.next);
            System.out.print(curNode.data+" ");
        }
    }
}
