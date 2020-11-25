package com.example.calculation;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 归并算法
 * 输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的
 */
public class GB implements Sort{

    @Override
    public void sort(int[] a, int[] b) {


        ConcurrentHashMap mm=new ConcurrentHashMap();

        mm.put(null,null);
        for (int i = 0; i < 20; i++) {

            mm.put(i,1);
        }

        HashMap map=new HashMap();
        for (int i = 0; i < 20; i++) {

//            map.put(i,1);
        }

    }

    public static void main(String[] args){
        int[] a={1,2,4};
        int[] b={1,3,4};

        new GB().sort(a,b);
    }

}
