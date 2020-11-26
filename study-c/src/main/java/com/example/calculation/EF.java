package com.example.calculation;

/**
 * 二分查找
 * <p>
 * 返回数组下标
 */

public class EF {

    public static void main(String[] args) {
        int[] a = {1, 2, 4, 22, 33, 55, 66, 67, 90, 100};
        System.out.println(new EF().find(a, 100));
    }

    int find(int[] a, int k) {
        int left = 0;
        int right = a.length - 1; //
        while (left <= right) {   //
            int mid = (left + right) / 2; //
            if (a[mid] == k) {
                return mid;
            }
            if (a[mid] > k) {
                right = mid - 1;  //
            }
            if (a[mid] < k) {
                left = mid + 1; //
            }
        }
        return left;
    }


}
