package com.example.test;

import java.util.Scanner;

/**
 * 牛客网测试
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
    static int getSum(String[] a) {

        int len = a.length;
        int kk = 0;
        int mm = 0;

        //移动
        for (int i = 0; i <= len - 1; i++) {
            String o = a[0];
            for (int j = 0; j < len - 1; j++) {
                a[j] = a[j + 1];
            }
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
