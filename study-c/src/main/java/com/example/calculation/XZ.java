package com.example.calculation;

/**
 * 选择排序
 * <p>
 * 简单选择排序的基本思想：给定数组：int[] arr={里面n个数据}；第1趟排序
 * ，在待排序数据arr[1]~arr[n]中选出最小的数据，将它与arrr[1]交换；第2趟，
 * 在待排序数据arr[2]~arr[n]中选出最小的数据，将它与r[2]交换；以此类推，
 * 第i趟在待排序数据arr[i]~arr[n]中选出最小的数据，将它与r[i]交换，直到全部排序完成。
 */
public class XZ {
    public static void main(String[] args) {
        int[] arr = {1, 3, 2, 45, 65, 33, 12};
        System.out.println("交换之前：");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        //选择排序的优化
        for (int i = 0; i <= arr.length; i++) {// 做第i趟排序
            int k = i;
            for (int j = k + 1; j < arr.length; j++) {// 选最小的记录
                if (arr[j] < arr[k]) {
                    k = j; //记下目前找到的最小值所在的位置
                }
            }
            //在内层循环结束，也就是找到本轮循环的最小的数以后，再进行交换
            if (i != k) {  //交换a[i]和a[k]
                int temp = arr[i];
                arr[i] = arr[k];
                arr[k] = temp;
            }
        }
        System.out.println();
        System.out.println("交换后：");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }

}
