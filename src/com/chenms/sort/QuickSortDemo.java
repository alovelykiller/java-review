package com.chenms.sort;

import java.lang.reflect.Array;

public class QuickSortDemo {
    public static void main(String[] args) {
        int[] ins = {2,3,5,1,23,6,78,34};
        quickSort(ins,0,ins.length-1);
        for (int i :
                ins) {
            System.out.println(i+"\n");
        }
    }

    public static void quickSort(int[] arr,int left,int right){
        if (left>right){
            return;
        }
        int base=arr[left];
        int i=left;
        int j=right;

        while (i!=j){


            while (arr[j]>=base&&i<j){
                j--;
            }

            while (arr[i]<=base&&i<j){
                i++;
            }

            int temp=arr[i];
            arr[i]=arr[j];
            arr[j]=temp;
        }

        arr[left]= arr[i];
        arr[i]=base;

        quickSort(arr,left,i-1);
        quickSort(arr,j+1,right);
    }
}
