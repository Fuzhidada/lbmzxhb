package com.example.hw;

import java.util.Scanner;

/**
 * 牛客网测试
 * 华为-求可左右移动NxN阶数组的最大值
 */

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int l = scanner.nextInt();
        String[] a;
        int result = 0;
        for (int i = 0; i < l; i++) {
            a = (scanner.next().split(","));
            result += getSum(a);
        }
        System.out.println(result);

      /*  char[] m=line.toCharArray();
       StringBuilder a=new StringBuilder();
        for (int i = m.length; i >0 ; i--) {
          if(a.indexOf(String.valueOf(m[i-1]))==-1){
              a.append(m[i-1]);
            }
        }
        System.out.println(a.toString());*/

        //字符反转
      /*  char[] m=line.toCharArray();
        char[] a = new char[line.length()];
        int k=m.length;
        for (int i = k; i >0 ; i--) {
            a[k-i]=m[i-1];
        }
        System.out.println(a);*/


        // 统计字符出现次数
    /*    BitSet bitSet=new BitSet(128);
        for (char c: line.toCharArray()) {
            if (!bitSet.get(c)){
                bitSet.set(c);
            }
        }
        System.out.println(bitSet.cardinality());*/
    }

    /**
     *  计算最大数
     */
    private static int getSum(String[] a) {

        int len = a.length;
        int kk = 0;
        int mm = 0;

        //移动
        for (int i = 0; i <= len - 1; i++) {
            String o = a[0];
            // 源数组 源数组开始复下标制的 目标数组 目标数组开始复制的下标 复制多少个元素
            System.arraycopy(a, 1, a, 0, len - 1);
            a[len - 1] = o;

            for (int m = 0; m < len; m++) {
                kk += Integer.parseInt(a[m]) << len - 1 - m;
            }
            if (kk >= mm) {
                mm = kk;
            }
            kk=0;
        }

        return mm;
    }

}
