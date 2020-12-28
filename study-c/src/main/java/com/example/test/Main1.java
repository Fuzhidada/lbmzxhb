package com.example.test;

import java.util.ArrayList;
import java.util.Scanner;


/**
 * 华为成语接龙面试题
 *
 */
public class Main1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int k = scanner.nextInt();
        int n = scanner.nextInt();

        ArrayList<String> a = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            a.add(scanner.next());
        }
        String result = a.get(k);

        char start = a.get(k).charAt(a.get(k).length() - 1);
        a.remove(a.get(k));
        result += getM(a, start);

        System.out.println(result);

    }

    private static String getM(ArrayList<String> a, char start) {
        String temp = "";
        for (int i = 0; i < a.size(); i++) {
            String p = a.get(i);
            if (a.get(i).charAt(0) == start) {
                if (p.length() > temp.length()) {
                    temp = p;
                } else if (p.length() == temp.length()) {
                    if (p.compareTo(temp) < 0) {
                        temp = p;
                    }
                }
            }
        }
        a.remove(temp);
        if (a.size() > 0 && !"".equals(temp)) {
            temp += getM(a, temp.charAt(temp.length() - 1));
        }
        return temp;
    }
}
