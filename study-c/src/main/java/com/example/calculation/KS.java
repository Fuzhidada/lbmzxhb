package com.example.calculation;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * 快速排序
 */
public class KS implements Sort {

    @Override
    public void sort(int[] a, int[] b) {
        sorts(a, 0, a.length - 1);
//        Stream.of(a).forEach(System.out::print);
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }

    }

    private void sorts(int[] a, int begin, int end) {
        /*
         *   核心思想就是分治
         *  选定数组中的两个数下标i，j，i<j
         *  选定一个数temp
         *  i从左到右与temp比较 大于temp则i++, 小于则交换位置
         *  j从右到左与temp比较，小于temp则j-- 大于则交换位置
         *  直到i>=j
         */
        if (begin < end) {
            int temp = a[begin];
            int i = begin;
            int j = end;

            while (i < j) {
                while (a[j] > temp && i < j) {
                    j--;
                }
                //右边数据大于基准数据则指针前移
                a[i] = a[j];
                while (a[i] <= temp && i < j) {
                    i++;
                }
                a[j] = a[i];
            }
            a[i] = temp;
            // 再次分片
            sorts(a, begin, i - 1);
            sorts(a, i + 1, end);
        }
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 4, 98, 221, 2, 11};
        int[] b = {};
        new KS().sort(a, b);

        Arrays.sort(a);
    }
}
