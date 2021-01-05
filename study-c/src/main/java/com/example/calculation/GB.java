package com.example.calculation;

/**
 * 归并算法排序
 * 输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的
 */
public class GB {

    private void merge(int[] a, int low, int high, int[] temp) {
        if (low < high) {
            int mid = (low + high) / 2;
            merge(a, low, mid, temp);
            merge(a, mid + 1, high, temp);
            sort(a, low, mid, high, temp);
        }
    }

    private void sort(int[] a, int low, int mid, int high, int[] temp) {
        int i = 0;
        int j = low, k = mid + 1;  //左边序列和右边序列起始索引
        while (j <= mid && k <= high) {
            if (a[j] < a[k]) {
                temp[i++] = a[j++];
            } else {
                temp[i++] = a[k++];
            }
        }
        //若左边序列还有剩余，则将其全部拷贝进temp[]中
        while (j <= mid) {
            temp[i++] = a[j++];
        }

        while (k <= high) {
            temp[i++] = a[k++];
        }

        for (int t = 0; t < i; t++) {
            a[low + t] = temp[t];
        }

    }

    public static void main(String[] args) {
        int[] a = {1, 2, 4, 32, 39, 100, 38};
        int[] temp = new int[a.length];

        new GB().merge(a, 0, a.length - 1, temp);

        for (int i = 0; i < a.length; i++) {

            System.out.print(a[i] + " ");

        }


    }

}
